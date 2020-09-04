<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="disease_board.css?var=133" type="text/css" charset="UTF-8" />
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
	<img src="inf_images/inf.jpg" alt="">증상별 정보
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
						<option value="v1">머리</option>
						<option value="v2">이마</option>
						<option value="v3">얼굴</option>
						<option value="v4">눈</option>
						<option value="v5">코</option>
						<option value="v6">입</option>
						<option value="v7">귀</option>
						<option value="v8">턱</option>
						<option value="v9">목</option>
						<option value="v10">어깨</option>
						<option value="v11">가슴</option>
						<option value="v12">배</option>
						<option value="v13">등</option>
						<option value="v14">엉덩이</option>
						<option value="v15">허벅지</option>
						<option value="v16">종아리</option>
						<option value="v17">팔</option>
						<option value="v18">팔꿈치</option>
						<option value="v19">허리</option>
						<option value="v20">손</option>
						<option value="v21">손목</option>
						<option value="v22">손가락</option>
						<option value="v23">다리</option>
						<option value="v24">무릎</option>
						<option value="v25">발</option>
						<option value="v26">발목</option>
						<option value="v27">발가락</option>
					</select>
				</div>
			</div>
	</div>
	
	
<div id="container">	
	<div id="content">
		<div class="section_wrap">
			<div id="size_ct">
				<a href="../information/disease.jsp">
					<h3 class="stress" id="">치통</h3>
					<p class="txt">치아나 잇몸의 통증이나 불편감</p>
				</a>	
				<a href="../information/disease.jsp">
					<h3 class="stress" id="">충치(치아우식증)</h3>
					<p class="txt">구멍을 형성하며 진행되는, 치아가 썩는 현상</p>
				</a>	
				<a href="../information/disease.jsp">
					<h3 class="stress" id="">치수염</h3>
					<p class="txt">치아의 살아있는 핵심인 치수의 염증</p>
				</a>	
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