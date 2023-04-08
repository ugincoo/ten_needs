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
				html += `<tr>
							<td class = "member_profile"><img class = "replyProfile" src = "/ten__needs/tenneeds/jsp/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" /></td>
							<td class = "member_nickname">${o.mid}</td>
							<td class = "member_winCount">${o.count}</td>
							<td class = "member_Accute">${o.winnerAccute}</td>
							<td class = "member_etc">비고</td>
						</tr>`
			})
			document.querySelector('.totalTable').innerHTML = html;
			
		}
	});
}


function getChart(winnerAccute){
	const data = {
	  labels: [
	    '전체(100%)',
	    '정확도(스매싱)'
	  ],
	  datasets: [{
	    label: '정확도',
	    data: [100, winnerAccute],
	    backgroundColor: [
	      '#648977',
	      '#E6D9CF'
	    ],
	    hoverOffset: 4
	  }]
	};
	
}