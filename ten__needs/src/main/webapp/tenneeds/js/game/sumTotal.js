console.log("total js 열림");

getTotal();

function getTotal(){
	$.ajax({
		url : "/ten__needs/game/result",
		method : "get",
		data : {"type" : 2},
		async : false,
		success : (r) => {
			console.log(r);
			let html = `<tr>
							<th class = "member_profile">프로필</th>
							<th class = "member_nickname">닉네임</th>
							<th class = "member_winCount">승리횟수</th>
							<th class = "member_Accute">정확도</th>
							<th class = "member_etc">비고</th>
						</tr>` 	
						
			r.forEach((o)=>{
				html += `<tr class = "member_tableinfo" onClick = "viewChart('${o.mid}', ${o.winnerAccute})">
							<td class = "member_profile"><img class = "replyProfile" src = "/ten__needs/tenneeds/jsp/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" /></td>
							<td class = "member_nickname">${o.mid}</td>
							<td class = "member_winCount">${o.count}</td>
							<td class = "member_Accute">${o.winnerAccute}</td>
							<td class = "member_etc">${memberInfo.mId == o.mid ? '<button onClick = "viewDetail()">내 전적 보기(상세)</button>' : ''}</td>
						</tr>`
			})
			document.querySelector('.totalTable').innerHTML = html;
			
		}
	});
}

let chartDoughnut = undefined;

const ctx = document.getElementById('myChart');

function viewChart(mid, winnerAccute){
	$.ajax({
		url : "/ten__needs/game/result",
		method : "get",
		data : {"type" : 3, "mid" : mid},
		async : false,
		success : (o) => {
			console.log(o)
			let html = `<img src = "/ten__needs/tenneeds/jsp/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" />
							<span class="mId">${o.mid}</span>
							<span class = "mEmail">${o.memail}</span>`;
							
			document.querySelector('.member_info').innerHTML = html;
			
		}
	})
	
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
	        data: [100, winnerAccute],
	        borderWidth: 1,
	         backgroundColor: [
		      '#648977',
		      '#E6D9CF'
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

function viewDetail(){
	console.log('마이페이지 넘어가면 될 것 같음')
	location.href = "/ten__needs/tenneeds/jsp/member/mypage.jsp";
	
}