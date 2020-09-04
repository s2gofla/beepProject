<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="price_info.css?var=13" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>

<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/jquery.leanModal.min.js"></script>
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
	<img src="inf_images/price.jpg" alt="">가격 정보
	</h3>

 
			 
	<!-- 검색창 -->
	<div class="medicine-search">
		<form id="search-form" class="search">
			<div class="white_window" style="border-color: white; width: 100%;">
				<input type="text" id="searchWord" name="searchWord" title="검색" placeholder="치료명을 입력하세요" />
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
						<option value="v0" selected="selected">치료과</option>
						<option value="v1">치과</option>
						<option value="v2">피부과</option>
						<option value="v3">성형외과</option>
						<option value="v4">안과</option>
						<option value="v5">산부인과</option>
						<option value="v6">비뇨기과</option>
						<option value="v7">정신건강의학과</option>
						<option value="v8">정형외과</option>
						<option value="v9">마취통증의학과</option>
						<option value="v10">신경외과</option>
						<option value="v11">재활의학과</option>
						<option value="v12">영상의학과</option>
						<option value="v13">외과</option>
						<option value="v14">신경과</option>
						<option value="v15">소아과</option>
						<option value="v16">내과</option>
						<option value="v17">이비인후과</option>
						<option value="v18">가정의학과</option>
					</select>
				</div>
			</div>
	</div>
	
	
<div id="container">	
	<div id="content">
		<div class="section_wrap ">
			<div id="wrap">
				<a href="#inf_modal" data-toggle="modal" class="flatbtn modaltrigger" id="modaltrigger">
					<h3 class="stress">비뇨기과 검사</h3>
				</a>	
				<a href="#inf_modal" data-toggle="modal" class="flatbtn modaltrigger" id="modaltrigger">
					<h3 class="stress">갑상선검사</h3>
				</a>	
				<a href="">
					<h3 class="stress">출산</h3>
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



<!-- 모달창 -->
<div id="inf_modal" style="display:none;">
	 	<table class="tg" style="table-layout: fixed; width: 200px">
		<colgroup>
			<col style="width: 80px">
			<col style="width: 120px">
		</colgroup>

		<tr>
			<td class="tg-0lax">최대</td>
			<td class="tg-0lax">70000 원</td>
		</tr>
		<tr>
			<td class="tg-0lax">평균</td>
			<td class="tg-0lax">49449 원</td>
		</tr>
		<tr>
			<td class="tg-0lax">최소</td>
			<td class="tg-0lax">16900 원</td>
		</tr>
	</table>
</div>



<!--모달윈도우부분-->
<script type="text/javascript">
$(function(){  
  $('.modaltrigger').leanModal({ top: 110, overlay: 0.8, closeButton: ".hidemodal" });
});
</script>

<!---------------------------------------------------------------------------------------------->
<footer></footer>
</body>
</html>