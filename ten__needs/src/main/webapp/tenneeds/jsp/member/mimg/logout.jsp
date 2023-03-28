<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.getSession().setAttribute("login", null );
	%>
	
	<script type="text/javascript">
		location.href="/tenneeds/jsp/main.jsp";
	</script>

</body>
</html>