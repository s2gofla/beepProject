<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="price_info.css?var=1510" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {
	$("#headers").load("/beepPro/layout/header.jsp");  // 외부 헤더 삽입
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
			<div class="white_window" style="border-color: white; width: 100%;">
				<input type="text" id="searchWord"  title="검색" placeholder="치료명을 입력하세요"  value="${ searchWord }" />
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
					location.href='/beepPro/information/price_info.do?searchWord='+value;
				}
			})
		</script>



 <!-- onchange="search(1)" -->
	<!-- 검색창 목록 -->
	<div class="medicine-info">
			<div class="medicine-info-filter">
				<div class="medicine-info-option">
					<span style="position: absolute; bottom: 10px; right: 180px;">검색 결과 ( ${ view.totalCount } )</span>
					<select class="mselect" title="정렬 조건" id="mSearch" name="mSearch">
						<option value="v0" selected="selected">전체</option>
						
			<!-- 진료과목 카테고리 -->
			<c:forEach items="${categoryList}" var="categoryList" >
						<option value="${categoryList.m_sub_seq }">${categoryList.m_sub_name }</option>
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
				<c:if test="${ !empty view.list }">
				<c:forEach items="${ view.list }" var="list">
				<a href="#" class="flatbtn" id="info-code${ list.pinfo_code }">
					<h3 class="stress">${list.pinfo_treatment }</h3>
				</a>	
			</c:forEach>
			</c:if>
  			</div>	
  			<c:if test="${ empty view.list }">
						<div style="height:200px; display: flex;justify-content: center;">
							<div style="margin-top: 20px; font-size: 28px; display: flex; align-items: center; font-family: 'NanumSquare', sans-serif;">검색 결과가   없습니다
								<img src="/beepPro/beep_images/inf_images/sad.svg" alt="" width="50px" style="margin-left: 10px"/>
								
							</div>
						</div>
					</c:if>
		</div>
	</div>
</div>



	<!-- 페이징 처리 -->
	<div class="pageBlock">
			<c:if test="${view.prev eq true}">
				<a href="/beepPro/information/price_info.do?page=${ view.start -1 }&searchWord=${searchWord}">&laquo;</a>
			</c:if>
			<c:forEach var="pageNum" begin="${view.start }" end="${ view.end }">
				<c:if test="${ pageNum eq view.currentPageNumber and pageNum ne 0}">
					<span>${ pageNum }</span>
				</c:if>
				<c:if test="${ not (pageNum eq view.currentPageNumber) and pageNum ne 0 }">
					<a href="/beepPro/information/price_info.do?page=${ pageNum }&searchWord=${searchWord}">${ pageNum }</a>
				</c:if>
			</c:forEach>
			<c:if test="${view.next eq true }">
				<a href="/beepPro/information/price_info.do?page=${view.end+1 }&searchWord=${searchWord}">&raquo;</a>
			</c:if>
		</div>
	
</div>


<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title" id="myModalLabel">Modal title</h4>
         </div>
         <div class="modal-body">
         
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
         </div>
       </div>
     </div>
</div> -->
<!-- 모달창 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title" id="myModalLabel">비뇨기과 검사 비용</h4>
         </div>
         <div class="modal-body">
         	<div class="modal-price">
	         	<p><b>최대 가격:</b> 85000 원 <br /></p>
	         	<p><b>평균 가격:</b> 50000 원 <br /></p>
	         	<p><b>최소 가격:</b> 4800   원 <br /></p>
         	</div>
         	<div class="modal-comment">
         	*** 삐용삐용은 해당 가격정보로 인한 불이익에 법적인 책임이 없습니다. 이 자료는 참고자료로 사용하세요. ***
         	- 치료항목에 대한 비용은 삐용삐용 해당리뷰 및 자체조사에 근거합니다.
         	</div>
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
         </div>
       </div>
     </div>
</div>



<!--모달윈도우부분-->
<script type="text/javascript" src="/beepPro/information/js/price_info.js?var=11511">
</script>
	

<!---------------------------------------------------------------------------------------------->
<footer></footer>
</body>
</html>