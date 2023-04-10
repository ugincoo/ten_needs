console.log("total js 열림");

function getTotal(){
	let wholeHtml = `<h4 class="gameTitle"> 게임 전체 순위 </h4>
						<button class ="deleteBtn" type = "button" onClick = "printGameInfo()">게임 전적 정보보기(개인)</button>`;
	
	document.querySelector('.memberGame_info').innerHTML = wholeHtml;
	
	wholeHtml = `<div class = "searchDiv">
						<input type="text" class="search" placeholder="ID검색">
						<button class = "searchID" onClick = "searchID()">검색</button>
				</div>`
	document.querySelector('.memberGame_info2').innerHTML = wholeHtml;
	
	$.ajax({
		url : "/ten__needs/game/result",
		method : "get",
		data : {"type" : 2},
		async : false,
		success : (r) => {
			console.log(r);
			document.querySelector('.realGame_info').innerHTML = `<table class = "totalTable table table-hover"></table>`;
			
			let html = `<tr>
							<th class = "member_profile">프로필</th>
							<th class = "member_nickname">닉네임</th>
							<th class = "member_winCount">승리횟수</th>
							<th class = "member_Accute">정확도</th>
							<th class = "member_etc">비고</th>
						</tr>` 	
						
			r.forEach((o)=>{
				html += `<tr class = "member_tableinfo">
							<td class = "member_profile"><img class = "replyProfile playerImg" src = "/ten__needs/tenneeds/jsp/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" /></td>
							<td class = "member_nickname">${o.mid}</td>
							<td class = "member_winCount">${o.count}</td>
							<td class = "member_Accute">${o.winnerAccute}</td>
							<td class = "member_etc">${(memberInfo.mid == o.mid || memberInfo.mid == 'admin')? `<button class = "chartBtn" onClick = "viewChart('${o.mid}', ${o.winnerAccute})">그래프보기</button>` : ''}</td>
						</tr>`
			})
			document.querySelector('.totalTable').innerHTML = html;
			
		}
	});
}

let chartDoughnut = undefined;

const ctx = document.getElementById('myChart');

function viewChart(mid, winnerAccute){
	
	document.querySelector('.modal_wrap2').style.display = 'flex';	
	
	document.querySelector('.modal_title2').innerHTML = `${mid}님 스매시(정확도)`
	
	if(chartDoughnut != undefined){
		chartDoughnut.destroy();
	}
	
	console.log("viewchart :" + " " + mid + " " + winnerAccute)
	
	chartDoughnut = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	      labels: [ '전체(100%)', '정확도(스매싱)'],
	      datasets: [{
	        label: '정확도',
	        data: [100-winnerAccute, winnerAccute],
	        borderWidth: 1,
	         backgroundColor: [
			  '#E6D9CF',
		      '#648977',
		    ],
		    hoverOffset: 4,
	      }]
	    },
	    options: {
	      scales: {
	        y: {
	          beginAtZero: true
	        }
	      }
	    }
	  });
}

function searchID(){//ID 검색
	let KeyID = document.querySelector('.search').value;
	
	$.ajax({
		url : "/ten__needs/game/result",
		method : "get",
		data : {"type" : 3,"keyword" : KeyID},
		async : false,
		success : (r) => {
			console.log(r);
			document.querySelector('.realGame_info').innerHTML = `<table class = "totalTable table table-hover"></table>`;
			
			let html = `<tr>
							<th class = "member_profile">프로필</th>
							<th class = "member_nickname">닉네임</th>
							<th class = "member_winCount">승리횟수</th>
							<th class = "member_Accute">정확도</th>
							<th class = "member_etc">비고</th>
						</tr>` 	
						
			r.forEach((o)=>{
				html += `<tr class = "member_tableinfo">
							<td class = "member_profile"><img class = "replyProfile playerImg" src = "/ten__needs/tenneeds/jsp/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" /></td>
							<td class = "member_nickname">${o.mid}</td>
							<td class = "member_winCount">${o.count}</td>
							<td class = "member_Accute">${o.winnerAccute}</td>
							<td class = "member_etc">${(memberInfo.mid == o.mid || memberInfo.mid == 'admin')? `<button class = "chartBtn" onClick = "viewChart('${o.mid}', ${o.winnerAccute})">그래프보기</button>` : ''}</td>
						</tr>`
			})
			document.querySelector('.totalTable').innerHTML = html;
			
		}
	});
}