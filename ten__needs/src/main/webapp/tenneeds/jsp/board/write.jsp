<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지-게시글 작성</title>


<!-- summerNote -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<!-- 사용자 정의 css -->
<link href="/ten__needs/tenneeds/css/board/write.css" rel = "stylesheet">

</head>
<link rel = "shortcut icon" href="/ten__needs/tenneeds/favicon.ico" type="image/x-icon">
<body>
	<!-- jsp 태그; JSP 페이지 포함 --> 
	<%@ include file = "/tenneeds/header.jsp" %> <!-- 헤더 포함 --> 
	<!-- 게시글 쓰기 페이지 -->
	<div class = "container">  
		<h2 class = "wHead">공지사항/이벤트 글쓰기</h2>
		<form class = "writecontent">
			<div class = "writeTitle">
				<h4>제목 : </h4>
				<input name = "bTitle" type = "text" class = "bTitle">
			</div>
			<br/>
			<h4>내용</h4>
			<textarea id="summernote" name="bContent" class = "bContent"></textarea>
			<button class = "blbtn" onClick = "writeBoard()" type = "button">글쓰기</button>
		</form>
	</div>
	
	
	
  
   <!-- summerNote -->
   <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
   
	<!-- 사용자 정의 js -->
   <script src = "/ten__needs/tenneeds/js/board/write.js" type="text/javascript"></script>
</body>
</html>