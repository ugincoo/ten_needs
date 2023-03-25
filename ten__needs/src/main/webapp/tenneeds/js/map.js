	// console.log( '작동 확인' ); // --- 확인 완료

// ------------------------ 카카오멥 클러스터 API ------------------------
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	center : new kakao.maps.LatLng(37.3218778,126.8308848), // 지도의 중심좌표 
	level : 7 // 지도의 확대 레벨 
});

// ------------------------ 카카오멥 API: 마커 이미지 변경 및 마커 생성 ------------------------
var imageSrc = '/ten__needs/tenneeds/img/map/markup_map.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    
var clusterer = new kakao.maps.MarkerClusterer({
	map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
	averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	minLevel: 5 // 클러스터 할 최소 지도 레벨 
});
	
function printExeList( east, west, south, north ){
	
	clusterer.clear();
	
	$.ajax({
		url: "https://api.odcloud.kr/api/2241448/v1/uddi:71902926-a3d7-44b9-9a25-a46d2c6d2034_201909112103?page=1&perPage=150&serviceKey=c%2BqC%2BX56OkzH7wQp0MRSmjqIonksla%2FFjJR2zJurJLRPptbOM9ymF8kHAA5dSTesd8z%2BF%2Fg77w270mdXrEX10w%3D%3D",
		method: "get",
		async: false,
		success: (r) =>{
			console.log(r); // --- 확인 완료
			
			// ------------------------ 제품 리스트 카카오맵 클러스터 적용 ------------------------
		    var markers = r.data.map( (p) => {
					// console.log( p ); // ---- 확인 완료
					// console.log("p.경도:" + p.경도);
					// console.log( "east:" + east );	console.log( "west:" + west );
					// console.log("p.위도:" + p.위도);
					// console.log( "south:" + south );	console.log( "north:" + north );
					
				if( east >= p.경도 && west <= p.경도 && south <= p.위도 && north >= p.위도 ){
					let marker = new kakao.maps.Marker({
		    	    	position : new kakao.maps.LatLng(p['위도'], p['경도']),
		            	image: markerImage
		        	});
		        	// ------------------------ 카카오맵 클릭 이벤트 적용 ------------------------            
		        	kakao.maps.event.addListener(marker, 'click', function() {
						document.querySelector('.modal_title').innerHTML = p.시설명;
						document.querySelector('.modal_content').innerHTML = `
																			<div class="contentAddr">주소: ${p.소재지지번주소}</div>
																			<div class="contentPhone">전화번호: ${p.전화번호}</div>
																			`;
					
						openModal(); // --- modal open
					})
		        	return marker;
				}else{ return; }
				
			})
				// console.log( markers ); // --- 확인 완료
			clusterer.addMarkers(markers);
		} // --- ajax success method End
	}) // --- ajax End
} // --- printExeList() End

// 카카오맵 API 2) 사용 + 이벤트: dragend로 변경하여 적용
kakao.maps.event.addListener(map, 'dragend', function() {
	getPos();
});

// 현재 지도의 좌표 얻기
getPos();
function getPos(){
	// ------------------------ 카카오맵 API 1) start ------------------------
	// 지도의 현재 영역을 얻어옵니다 
	var bounds = map.getBounds();
	    
	// 영역의 남서쪽 좌표를 얻어옵니다 
	var swLatLng = bounds.getSouthWest(); 
	    
	// 영역의 북동쪽 좌표를 얻어옵니다 
	var neLatLng = bounds.getNorthEast();	    
	
	// message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
	// message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
	    
	// 개발자도구를 통해 직접 message 내용을 확인해 보세요.
		// ex) console.log(message);
	// ------------------------ 카카오맵 API 1) end ------------------------
	    
	 let east = neLatLng.getLng(); 		// 동쪽 좌표
	 let west = swLatLng.getLng(); 		// 서쪽 좌표
	 let south = swLatLng.getLat();		// 남쪽 좌표
	 let north = neLatLng.getLat(); 	// 북쪽 좌표
	    
		// console.log( "동" + east + "서" + west + "남" + south + "북" + north ); // --- 확인 완료
	printExeList( east, west, south, north );
	// 목적: getproductlist
}

// ------------------------ modal method ------------------------
function openModal(){
	document.querySelector('.modal_wrap').style.display = 'flex';
}

function closeModal(){
	document.querySelector('.modal_wrap').style.display = 'none';
}
