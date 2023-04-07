<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/ten__needs/tenneeds/css/map.css" rel="stylesheet">
</head>
<body>

	<%@include file = "/tenneeds/header.jsp" %>
	
	<div class="wrap container">
		 <a href="/ten__needs/tenneeds/jsp/board/list.jsp"><button onclick="go_main()" type="button" class="gomain"> 뒤로가기 </button></a>
		
		<div class="maptitle"> 
			<h3> 주변 테니스장 정보 </h3>
		</div>
		<div class="mapbox"> 
			<div id="map" style="width:90%;height:500px;"></div>
		</div>
	</div>
	
	
	<!-- 모달 HTMLs -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title"></h3>
			<div class="modal_content">
			</div>
			<div class="modal_btns">
				<button onclick="closeModal()" class="modla_cancle" type="button"> 닫기 </button>
			</div>
		</div>
	</div>
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db8620d22f203bb5f5678d7b78ad885a&libraries=clusterer"></script>	<script src="/ten__needs/tenneeds/js/map.js" type="text/javascript"> </script>
	<script src="/ten__needs/tenneeds/js/modal.js" type="text/javascript"> </script>
	
</body>
</html>