<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="<%= path %>/information/disease_board.css?var=1350" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {
	$("#headers").load("<%= path %>/layout/header.jsp");  // 외부 헤더 삽입
	$("footer").load("<%= path %>/layout/footer.html");  // 외부 푸터 삽입
});
</script>
</head>
<body>
	<div id="headers"></div>
	<!---------------------------------------------------------------------------------------------->


	<div class="layout-main">

		<!-- 타이틀 -->
		<h3 class="headword">
			<img src="<%= path %>/beep_images/inf_images/inf.jpg" alt="">증상별 정보
		</h3>


		<!-- 검색창 -->
		<div class="medicine-search">
				<div class="white_window" style="border-color: white; width: 100%;">
					<input type="text" id="searchWord" title="검색" 	placeholder="증상, 질환명을 입력하세요" value="${ searchWord }" />
					<button id="searchBtn" value="search">
						<i class="fas fa-search" style="color: rgb(234, 153, 153); font-size: 35px;"></i>
					</button>
				</div>
		</div>
		<script>
			$("#searchBtn").on("click", function(){
				var value = $("input[id=searchWord]").val();
				if(value==""){
					alert("검색어를 입력하세요");
				}else{
					location.href='<%= path %>/information/disease_board.do?searchWord='+value;
				}
			})
		</script>


		<!-- 검색창 목록 -->
		<div class="medicine-info">
			<div class="medicine-info-filter">
				<div class="medicine-info-option">
					<span style="position: absolute; bottom: 10px; right: 180px;">검색 결과 ( <span style="text-decoration: underline;">${ view.totalCount }</span> )</span>
					<select class="mselect" title="정렬 조건" onchange="search(1)" id="mSearch" name="mSearch">
						<option value="v0" selected="selected">아픈 부위</option>

						<!-- 아픈부위 카테고리 -->
						<c:forEach items="${diseasecategoryList}"
							var="diseasecategoryList">
							<option value="v1">${diseasecategoryList.ss_name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div id="container">
			<div id="content">
				<div class="section_wrap">
					<div id="size_ct">
					<c:if test="${ !empty view.list }">
						<c:forEach items="${ view.list }" var="list">
							<div class="d_list">
								<a href="<%= path %>/information/disease.do?seq=${ list.seq }">
									<span class="d_head"> 
										<span class="d_title"><span>&gt; ${ list.m_sub_name }</span></span>
										<span class="d_name">${ list.d_name }</span>
									</span> 
									<span class="d_contents">${ list.definition }</span>
								</a>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${ empty view.list }">
						<div style="height:200px; display: flex;justify-content: center;">
							<div style="margin-top: 20px; font-size: 28px; display: flex; align-items: center; font-family: 'NanumSquare', sans-serif;">검색 결과가   없습니다
								<img src="<%= path %>/beep_images/inf_images/sad.svg" alt="" width="50px" style="margin-left: 10px"/>
								
							</div>
						</div>
					</c:if>
					</div>
				</div>
			</div>
		</div>


		<!-- 페이징 처리 -->
		<div class="pageBlock">
			<c:if test="${view.prev eq true}">
				<a href="<%= path %>/information/disease_board.do?page=${ view.start -1 }&searchWord=${searchWord}">&laquo;</a>
			</c:if>
			<c:forEach var="pageNum" begin="${ view.start }" end="${ view.end }">
				<c:if test="${ pageNum eq view.currentPageNumber and pageNum ne 0}">
					<span>${ pageNum }</span>
				</c:if>
				<c:if test="${ not (pageNum eq view.currentPageNumber) and pageNum ne 0 }">
					<a href="<%= path %>/information/disease_board.do?page=${ pageNum }&searchWord=${searchWord}">${ pageNum }</a>
				</c:if>
			</c:forEach>
			<c:if test="${view.next eq true }">
				<a href="<%= path %>/information/disease_board.do?page=${view.end+1 }&searchWord=${searchWord}">&raquo;</a>
			</c:if>
		</div>

	</div>





	<!---------------------------------------------------------------------------------------------->
	<footer></footer>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>