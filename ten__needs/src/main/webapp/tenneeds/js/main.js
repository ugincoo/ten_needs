console.log('열림');
html=``;
	if(memberInfo  == null){
	 document.querySelector('.mainbtnbox').innerHTML=
		` <li><a href ="/ten__needs/tenneeds/jsp/member/login.jsp"> 로그인 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/board/list.jsp"> 이벤트 / 공지사항  </a></li>`
	}else if(memberInfo.mid == 'admin'){
		html+=` <li><a href ="/ten__needs/tenneeds/jsp/member/logout.jsp"> 로그아웃 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/admin/adminRacketList.jsp"> 관리자페이지 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/board/list.jsp"> 이벤트 / 공지사항 </a></li>`
               document.querySelector('.mainbtnbox').innerHTML=html;
	}else{
		html+=` <li><a href ="/ten__needs/tenneeds/jsp/member/logout.jsp"> 로그아웃 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/member/mypage.jsp"> 마이페이지 </a></li>
               <li><a href ="/ten__needs/tenneeds/jsp/board/list.jsp"> 이벤트 / 공지사항 </a></li>`
               document.querySelector('.mainbtnbox').innerHTML=html;
	}
