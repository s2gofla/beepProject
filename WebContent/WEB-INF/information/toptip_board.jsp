<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="toptip_board.css?var=1303" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">

<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon" type="image/x-icon">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.min.js"></script>
<!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
 
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {
		$("#headers").load("<%= path %>/layout/header.jsp"); // 외부 헤더 삽입
		$("footer").load("<%= path %>/layout/footer.html"); // 외부 푸터 삽입
	});
</script>
</head>
<body>
	<div id="headers"></div>
	<!---------------------------------------------------------------------------------------------->



	<div class="layout-main">

		<h3 class="headword">
			<img src="<%= path %>/beep_images/inf_images/tip.jpg" alt="">꿀팁 정보
		</h3>

<!-- 검색창 -->
		<div class="medicine-search">
				<div class="white_window" style="border-color: white; width: 100%;">
					<input type="text" id="searchWord" title="검색" placeholder="검색어를 입력하세요"  value="${ searchWord }" />
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
					location.href='<%= path %>/information/toptip_board.do?searchWord='+value;
				}
			})
		</script>

		<div class="medicine-info">
			<div class="medicine-info-filter">
				<div class="medicine-info-option">
				<span style="position: absolute; bottom: 10px; right: 180px;">검색 결과 ( ${ view.totalCount } )</span>
					<select class="mselect" title="정렬 조건" onchange="search(1)"
						id="mSearch" name="mSearch">
						<option value="v0" selected="selected">전체</option>
						
			<!-- 꿀팁 카테고리 -->
			<c:forEach items="${toptipcategoryList}" var="toptipcategoryList">
						<option value="v1">${toptipcategoryList.tt_type_name }</option>
			</c:forEach>
					</select>
				</div>
			</div>


			<div>
				<ul class="medcine-info-list">
				
				<!-- 꿀팁 리스트 -->
					<c:if test="${ !empty view.list }">
					<c:forEach items="${view.list}" var="list">

					<li class="mlist-body"><a href="#"class="flatbtn" id="ttcode${ list.tt_code }">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="${list.pic}" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">${list.title }</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">${list.dates }</span> <i
											class="se_divide_line"></i>
									</div>
								</div>
							</div>
					</a></li>
					
					</c:forEach>
					</c:if>

				<c:if test="${ empty view.list }">
						<div style="height:200px; display: flex;justify-content: center;">
							<div style="margin-top: 20px; font-size: 28px; display: flex; align-items: center; font-family: 'NanumSquare', sans-serif;">검색 결과가   없습니다
								<img src="<%= path %>/beep_images/inf_images/sad.svg" alt="" width="50px" style="margin-left: 10px"/>
								
							</div>
						</div>
					</c:if>
				</ul>
			</div>
		</div>

		<div class="pageBlock">
			<c:if test="${view.prev eq true}">
				<a href="<%= path %>/information/toptip_board.do?page=${ view.start -1 }&searchWord=${searchWord}">&laquo;</a>
			</c:if>
			<c:forEach var="pageNum" begin="${view.start }" end="${ view.end }">
				<c:if test="${ pageNum eq view.currentPageNumber and pageNum ne 0}">
					<span>${ pageNum }</span>
				</c:if>
				<c:if test="${ not (pageNum eq view.currentPageNumber) and pageNum ne 0 }">
					<a href="<%= path %>/information/toptip_board.do?page=${ pageNum }&searchWord=${searchWord}">${ pageNum }</a>
				</c:if>
			</c:forEach>
			<c:if test="${view.next eq true }">
				<a href="<%= path %>/information/disease_board.do?page=${view.end+1 }&searchWord=${searchWord}">&raquo;</a>
			</c:if>
		</div>
	</div>


<div class="row justify-content-center">
    <div class="col-md-8">
        <div class="row" id="image-md">
            <a href="https://unsplash.it/1200/768.jpg?image=251" data-toggle="lightbox" data-gallery="example-gallery" class="col-sm-4" data-title="모달 제목" data-footer="모달 푸터내용"></a>
            <a href="https://unsplash.it/1200/768.jpg?image=252" data-toggle="lightbox" data-gallery="example-gallery" class="col-sm-4"></a>
            <a href="https://unsplash.it/1200/768.jpg?image=253" data-toggle="lightbox" data-gallery="example-gallery" class="col-sm-4"></a>
        </div>
    </div>
</div>




	<!--모달윈도우부분-->
	<script type="text/javascript" src="<%= path %>/information/js/toptip_board.js?var=1523"></script>
	<!--//모달윈도우부분-->

	<!---------------------------------------------------------------------------------------------->
	<footer></footer>
</body>
</html>