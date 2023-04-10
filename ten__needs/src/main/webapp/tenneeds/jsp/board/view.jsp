<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>

<!-- 사용자 정의 css -->
<link href = "/ten__needs/tenneeds/css/board/view.css" rel = "stylesheet">
</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>
	<!-- jsp 태그; JSP 페이지 포함 --> 
	<%@ include file = "/tenneeds/header.jsp" %> <!-- 헤더 포함 --> 
	
	<%	
		String bno = request.getParameter("bno");
	%>
	
	<input type = "hidden" type = "text" class = "bno" value="<%=bno%>">
	
	<div class ="container">
		<div class = boardbox>
			<div class ="viewTop">
				<div class = "writerInfo">
					<img class = "profile" src = "/ten__needs/tenneeds/jsp/board/bimg/admin.png"/>
					<span class = "adminname">관리자</span>
					<span class = "bwritedate"></span> <!-- 작성일 -->
				</div>
				<div class = "boardViewInfo"> <!-- 게시물의 실질적 내용 -->
					<div class = "btitle"></div> <!-- 제목 -->
					<div class = "bcontent"></div> <!-- 내용 -->
				</div>
				
				<div class ="btnBox"></div>  <!-- 관리자라면, 수정 삭제[공지사항/이벤트] -->
				<div class = "replycount"></div> <!-- 댓글 수 -->
				<div class = "replyWritebox"> <!-- 댓글 작성 -->
					<textarea  class = "rcontent" rows="" cols=""></textarea>
					<button class = "replyBtn" onClick = "writeReply()" type = "button">댓글 작성</button>
				</div>
				<div class = "replayListBox">
					<!-- 댓글 출력 -->
				</div>
				
			</div>
		</div>
	</div>
	
	<!-- 사용자 정의 js -->
	<script src = "/ten__needs/tenneeds/js/board/view.js"></script>
</body>
</html>