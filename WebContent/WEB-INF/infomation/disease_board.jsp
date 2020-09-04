<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="disease_board.css?var=136" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {
	$("#headers").load("../layout/header.html");  // 외부 헤더 삽입
	$("footer").load("../layout/footer.html");  // 외부 푸터 삽입
});
</script>
</head>
<body>
<div id="headers"></div>
<!---------------------------------------------------------------------------------------------->



<div class="layout-main">
 
 	<!-- 타이틀 -->
	<h3 class="headword">
	<img src="../beep_images/inf_images/inf.jpg" alt="">증상별 정보
	</h3>

 
			 
	<!-- 검색창 -->
	<div class="medicine-search">
		<form id="search-form" class="search">
			<div class="white_window" style="border-color: white; width: 100%;">
				<input type="text" id="searchWord" name="searchWord" title="검색"  placeholder="증상, 질환명을 입력하세요" />
				<button id="searchBtn" value="검색">
					<i class="fas fa-search" style="color: rgb(234, 153, 153); font-size: 35px;"></i>
				</button>
			</div>
		</form>
	</div>



	<!-- 검색창 목록 -->
	<div class="medicine-info">
			<div class="medicine-info-filter">
				<div class="medicine-info-option">
					<select class="mselect" title="정렬 조건" onchange="search(1)" id="mSearch" name="mSearch">
						<option value="v0" selected="selected">아픈 부위</option>
			<!-- 진료과목 카테고리 -->
			<c:forEach items="${sorespotcategoryList}" var="sorespotcategoryList">
						<option value="v1">${sorespotcategoryList.ss_name }</option>
			</c:forEach>
					</select>
				</div>
			</div>
	</div>
	
	
<div id="container">	
	<div id="content">
		<div class="section_wrap">
			<div id="size_ct">
				<c:forEach items="${ list }" var="list">
					<a href="/beepPro/information/disease.do?seq=${ list.seq }">
						<h3 class="stress">${ list.d_name }</h3>
						<p class="txt">${list.definition }</p>
					</a>	
				</c:forEach>
  			</div>
		</div>
	</div>
</div>



	<!-- 페이징 처리 -->
	<div class="paging">
		<a href="#">1</a>
		<a href="#">2</a>
		<a href="#" class="active">3</a>
		<a href="#">4</a>
		<a href="#">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		<a href="#">8</a>
		<a href="#">9</a>
		<a href="#">10</a>
		<a href="#">&gt;</a> 
		<a href="#">&raquo;</a> 
	</div>
	
</div>



<!---------------------------------------------------------------------------------------------->
<footer></footer>
<script type="text/javascript" src="layout-js.js"></script><!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>