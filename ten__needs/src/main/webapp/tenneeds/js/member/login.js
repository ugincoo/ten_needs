// 1. 로그인
function login(){
	let mid = document.querySelector('.mid').value;
	let mpw = document.querySelector('.mpw').value;
	
	$.ajax({
		url : "/ten__needs/login" ,
		method : "post" ,
		data : { "mid" : mid , "mpw" : mpw } ,
		success :(r)=>{
			if( r == 'true'){
				location.href="/ten__needs/tenneeds/jsp/main.jsp"
			}else{
				document.querySelector('.checkconfirm').innerHTML = '회원정보가 틀립니다.'
			}
			
		}
	})
}
// 아이디 찾기 모달
function onpenModalID( ){
	let html = `
				<h3 class="modal_title">
					 아이디 찾기 
				</h3>
				<div class="modal_content">
					<div class="maintitle"> 이메일 </div>
					<input class="memailfind" type="text">
				
					<div class="checkconfirmfind"></div>
				
					<button class="signupbtn" onclick="findmid()" type="button"> 아이디 찾기 </button>
				</div>
				<div class="modal_btns">
					<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
				</div>
				`
	document.querySelector('.modal_box').innerHTML = html;
	document.querySelector('.modal_wrap').style.display ='flex';
}
// 아이디 찾기
function findmid(){
	let memail = document.querySelector('.memailfind').value;
	
	$.ajax({
		url : "/ten__needs/find" ,
		method : "get" ,
		data : { "type" : 1 , "memail" : memail },
		success : (r)=>{
			if( r == 'false' ){
				document.querySelector('.checkconfirmfind').innerHTML = '동일한 회원정보가 없습니다.';
			}else{
				document.querySelector('.checkconfirmfind').innerHTML = '회원님 ID : '+r;
			}
		}
	})
}

// 비밀번호 찾기 모달
function onpenModalPW( ){
	let html = `
				<h3 class="modal_title">
					비밀번호 찾기 
				</h3>
				<div class="modal_content">
					<div class="maintitle"> 아이디 </div>
					<input class="midfind" type="text">
					
					<div class="maintitle"> 이메일 </div>
					<input class="memailfind" type="text">
					
					<div class="checkconfirmfind"></div>
					
					<button class="signupbtn" onclick="findmpw()" type="button"> 비밀번호 찾기 </button>
				</div>
				<div class="modal_btns">
					<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
				</div>
				`
	document.querySelector('.modal_box').innerHTML = html;
	document.querySelector('.modal_wrap').style.display ='flex';
}

// 비밀번호 찾기
function findmpw(){
	let info = {
		type : 2 ,
		mid : document.querySelector('.midfind').value ,
		memail : document.querySelector('.memailfind').value
	}
	
	$.ajax({
		url : "/ten__needs/find" ,
		method : "get" ,
		data : info ,
		success : (r)=>{
			if( r == 'false' ){
				document.querySelector('.checkconfirmfind').innerHTML = '동일한 회원정보가 없습니다.';
			}else{
				document.querySelector('.checkconfirmfind').innerHTML = '임시 비밀번호를 이메일로 전송했습니다.';
			}
		}
	})
}

function closeModal(){
	document.querySelector('.modal_wrap').style.display ='none';
}