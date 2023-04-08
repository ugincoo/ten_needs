<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>승률-정확도 분석</title>
<link href="/ten__needs/tenneeds/css/game/sumTotal.css" rel="stylesheet" >
</head>
<body>
	<%@include file = "/tenneeds/header.jsp" %>
	<div class = "container">
		<div class = "totalTitle"> <!-- 맨위 타이틀 부분 -->
				<h3>Player Ranking</h3>
			</div>
		<table class = "totalTable">
			<!-- <tr>
				<th class = "member_profile">프로필</th>
				<th class = "member_nickname">닉네임</th>
				<th class = "member_winCount">승리횟수</th>
				<th class = "member_Accute">정확도</th>
				<th class = "member_etc">비고</th>
			</tr> -->
			<!-- <tr>
				<td class = "member_profile">프로필</td>
				<td class = "member_nickname">닉네임</td>
				<td class = "member_winCount">승리힛수</td>
				<td class = "member_Accute">정확도</td>
				<td class = "member_etc">비고</td>
			</tr> -->
		</table>
	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/ten__needs/tenneeds/js/game/sumTotal.js" type="text/javascript"></script>
</html>