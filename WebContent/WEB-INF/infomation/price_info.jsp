<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="price_info.css?var=130" type="text/css" charset="UTF-8" />
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
	<img src="../beep_images/inf_images/price.jpg" alt="">가격 정보
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
						<option value="v0" selected="selected">전체</option>
						
			<!-- 진료과목 카테고리 -->
			<c:forEach items="${categoryList}" var="categoryList">
						<option value="v1">${categoryList.m_sub_name }</option>
			</c:forEach>

					</select>
				</div>
			</div>
	</div>
	
	
<div id="container">	
	<div id="content">
		<div class="section_wrap ">
			<div id="wrap">
				<!-- 가격 정보 리스트 -->
				<c:forEach items="${ list }" var="list">
				<a href="#inf_modal" class="flatbtn" id="modaltrigger">
					<h3 class="stress">${list.pinfo_treatment }</h3>
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



<!-- 모달창 -->
<div id="inf_modal" style="display:none;">
 
	 	<table class="tg" style="table-layout: fixed; width: 200px">
		<colgroup>
			<col style="width: 80px">
			<col style="width: 120px">
		</colgroup>

		<tr>
			<td class="tg-0lax">최대</td>
			<td class="tg-0lax">${pricecontentlist.min_price} 원</td>
		</tr>
		<tr>
			<td class="tg-0lax">평균</td>
			<td class="tg-0lax">${pricecontentlist.avg_price} 원</td>
		</tr>
		<tr>
			<td class="tg-0lax">최소</td>
			<td class="tg-0lax">${pricecontentlist.max_price} 원</td>
		</tr>
	</table>
 
</div>



<!--모달윈도우부분-->
<script type="text/javascript">
$(function(){
  $('#loginform').submit(function(e){
    return false;
  });
  
  $('#modaltrigger').leanModal({ top: 110, overlay: 0.8, closeButton: ".hidemodal" });
});
</script>

<!---------------------------------------------------------------------------------------------->
<footer></footer>
</body>
</html>