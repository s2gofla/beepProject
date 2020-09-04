<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="review-style/minfoDetail-style.css?var=212182"
	type="text/css" charset="UTF-8" />
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js"
	crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {

		$("#headers").load("../layout/header.jsp"); // 외부 헤더 삽입
		$("footer").load("../layout/footer.html"); // 외부 푸터 삽입
		
		$("input").css("outline","none");
		$("div").css("cursor","default");
		$(".content-list p").css({
			"line-height":"25px"
			,"white-space" : "pre-line"
		})
		
		$(".content-title i").css("color","rgb(84, 179, 247)");
		
		//리뷰박스로 스크롤 이동
		   $(".hinfo-menu-box").children().eq(1).on("click", function() {
		   		var offset = $(".review-box").offset();
		   		
		   		$('html').animate({scrollTop: offset.top}, 400);
		   })
		
		//리뷰작성/찜하기 버튼 호버
		   $( ".bookmark-box i" ).hover( function() {
			 	
				$(this).toggleClass("activeB");
				
			   });
		
		 //요청 url 파라미터 값 가져오는 함수
		function getUrlParams() {
						var params = {};
						window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi,
								function(str, key, value) {
									params[key] = value;
								});
						return params;
					}
	
	   var getParam = getUrlParams();
	   //리뷰 작성하기로 보내기
	   $("#write").on("click", function() {
		   //$("input[name='id']").val()
		   if ($("input[name='id']").val() != "") {
			
		   var m_name = $("#hospital-name").text();
		   location.href = "../review/mreview_write.do?m_code="+getParam.m_code+"&m_name="+ m_name;
		} else {
			alert("로그인 후 이용해주세요")
			location.href = "../member/login.do";
		}
	   })
		
	
	      //리뷰 삭제하기 경로 보내기
   $(".deleteBtn").on("click", function() {
	 var index = $(".deleteBtn").index(this)
	 m_review_code = $("input[name='review_code']").eq(index).val();
	 
   	 location.href= "../review/mreview_delete.do?m_review_code="+m_review_code+"&m_code="+getParam.m_code;
   })
   
   //리뷰 수정하기 경로 보내기
   $(".editBtn").on("click", function() {
   		var index = $(".editBtn").index(this)
	  	m_review_code = $("input[name='review_code']").eq(index).val();
	 	m_name = $("#hospital-name").text();
	   location.href = "../review/mreview_update.do?m_review_code="+m_review_code+"&m_code="+getParam.m_code+"&m_name="+m_name;
	   
   })
   
     $("#searchWord").on("click", function() {
   	$(this).val("");
   })
	   
	   
	});
</script>
</head>
<body>
	<div id="headers"></div>
	<div class="layout-main col">
	<c:if test="${not empty dto }">
		<!-- 리뷰상세페이지 상단정보 -->
		<div class="hinfo-title-box justify">
			<div style="width: 80%; display: flex">
				<div class="hospital-title-box pl2"
					style="width: 300px; height: 500px;">
					<div class="row" style="height: 100%">
						<div class="title-image-box fc" style="width: 100%">
							<div class="fc"
								style="height: 60%; width: 100%; margin-top: 30px;">
								<div style="width: 100%; height: 100%;">
									<img src="${dto.m_pic }" style="height: 250px; width: 250px;"
										alt="" />
								</div>
							</div>
							<div class=""
								style="height: 40%; width: 100%; display: flex; flex-direction: column;">
								<i class="fas fa-bookmark"
									style="color: #9b9b9b; font-size: 50px;"></i> <span id="bookCount"
									style="color: #9b9b9b;"><b>${dto.bookmark_count }</b></span>
							</div>
						</div>
					</div>
				</div>
				<div>
					<div class="hospital-name-box">
						<div class="hospital-name" id="hospital-name">${dto.m_name }</div>
						<div class="star-score">
							<span> 
							<c:forEach begin="1" end="5" step="1" var="i">
							<c:if test="${i le dto.star_score}">							
							<i class="fas fa-star"></i> <!-- 색깔별 -->		
							</c:if>
							<c:if test="${i gt dto.star_score }">
							<i class="far fa-star" style="color: black;"></i>
							</c:if>
							</c:forEach>  
							</span> <span style="padding-left: 8px; color: #6c757d;">${dto.star_score }</span> <span
								style="font-size: 15px; color: #6c757d; padding-left: 10px; line-height: 2.5;">(${dto.reviewer })</span>
						</div>
					</div>
					<div class="hinfo-list" style="height: 210px;">
						<ul
							style="height: 100%; list-style-type: none; text-align: justify;">
							<c:set value="${dto.mpurpose }" var="mpurpose" ></c:set>
							<li><i class="fas fa-tablets"></i>분류 : ${mpurpose.bpurpose_name }&nbsp;&nbsp;<i
								class="fas fa-angle-right"></i> ${mpurpose.purpose_name }</li>
							<li><i class="fas fa-building"></i>제약회사 : <b>${dto.m_enterprise }</b></li>
							<li><i class="fas fa-money-bill-wave-alt"></i>가격 : 
								
								
								<c:choose>
								<c:when test="${dto.m_price eq 0 }">
								<b id="priceAvg"
								style="color: gray; font-weight: 0; font-size: 15px;">리뷰를 통해
									평균가격이 표시됩니다.</b>
								</c:when>
								<c:otherwise>
								<b id="priceAvg"
								style="font-weight: 0; font-size: 20px;">
									${dto.m_price }원 <span style="color: gray; font-size: 15px">(리뷰를 통한 평균가격이 표시됩니다)</span>
								</b>
								</c:otherwise>
								</c:choose>									
								</li>
						</ul>
					</div>
				</div>
				<div style="width: 80px; position: relative;">
					<div id="bookmark" class="bookmark-box fc">
						<c:choose >
						 <c:when test="${dto.isBookMark eq 1 }">
						<i class="fas fa-bookmark"
							style="font-size: 50px; color:rgb(234,153,153) " ></i>
						<p style="margin: 0">찜취소</p>						 
						 </c:when>
						<c:otherwise>
						<i class="far fa-bookmark"
							style="font-size: 50px;"></i>
						<p style="margin: 0">찜하기</p>
						</c:otherwise>
						</c:choose>	
					</div>
					<div  id="write" class="bookmark-box fc" style="top:140px; font-size: 12px; font-weight: bold;">
						<i class="fas fa-pen-fancy"
							style="font-size: 50px;"></i>
						<p style="margin: 0">리뷰쓰기</p>
					</div>
				</div>

			</div>
		</div>
		<!-- 정보/리뷰 메뉴바 -->
		<div class="fc col" style="width: 100%;">
			<div class="hinfo-menu-box">
				<div class="menu-bar fc">상세정보</div>
				<div class="menu-bar fc">리뷰보기(${dto.reviewer})</div>
			</div>
			<!-- 상세정보 목록  -->
			<div class="content-detail-box">
				<div class="mt">
					<div class="content-title mtl">
						<i class="fas fa-fill"></i>성분
					</div>
					<div class="content-list">
						<p class="mtl">${dto.m_ingredient }</p>
					</div>
				</div>
				<div class="mt">
					<div class="content-title mtl">
						<i class="fas fa-hand-holding-medical"></i>효과
					</div>
					<div class="content-list">
						<p class="mtl">${dto.m_effect }</p>
					</div>
				</div>
				<div class="mt">
					<div class="content-title mtl">
						<i class="fas fa-mortar-pestle"></i>용법용량
					</div>
					<div class="content-list" style="display: flex">
						<p class="mtl">${dto.m_dose }</p>
					</div>
				</div>
				<div class="mt">
					<div class="content-title mtl">
						<i class="fas fa-diagnoses"></i>부작용
					</div>
					<div class="content-list" style="display: flex">
						<p class="mtl">${dto.m_sideeffect }</p>
					</div>
				</div>

			</div>

			<!-- 리뷰정보  -->
			<div class="review-box">

				<div>
					<div class="mtl" style="font-size: 20px; font-weight: bold">
						리뷰 <b style="color: #ea9999;">${dto.reviewer }</b>
					</div>
					<div class="mtl">
						<div>

							<div
								style="border-right: solid 1px #e9e9e9; background-color: #f8f8f8;">
								<div
									style="color: #494949; letter-spacing: -0.6px; font-size: 20px; font-weight: bold; text-align: center; padding-top: 10px">별점
									평균</div>
								<span
									style="display: block; text-align: center; font-size: 30px;">
									<c:forEach begin="1" end="5" step="1" var="i">
										<c:if test="${i le dto.star_score}">							
										<i class="fas fa-star"></i> <!-- 색깔별 -->		
										</c:if>
										<c:if test="${i gt dto.star_score }">
										<i class="far fa-star" style="color: black;"></i>
										</c:if>
									</c:forEach>  
								</span>
								<div
									style="letter-spacing: -1.05px; text-align: center; color: #9b9b9b; font-size: 18px;"
									class="mb-2">
									<b
										style="letter-spacing: -2.33px; font-size: 40px; font-weight: bold; color: #000000;">
										${dto.star_score }</b> / 5
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="hospital-search">
					<form id="search-form" class="search">
						<div class="white_window"
							style="border: 3px solid #ea9999; width: 100%;">
							<input type="text" id="searchWord" name="searchWord" title="검색"
								value="검색어를 입력하세요" />
							<button id="searchBtn" value="검색">
								<i class="fas fa-search"
									style="color: rgb(234, 153, 153); font-size: 20px;"></i>
							</button>
						</div>
					</form>
				</div>
				<div class="review-sort-count-box"
					style="display: flex; justify-content: flex-end;">
					<select id="sort-select-box" style="height: 30px;"
						name="sort-options" class="selectpicker" title="기본정렬"
						data-style="btn-transparent" data-width="160px">
						<option class="sort-option" value="1">최신순서</option>
						<option class="sort-option" value="2">좋아요 많은 순서</option>
						<option class="sort-option" value="3">평점 높은 순서</option>
						<option class="sort-option" value="4">평점 낮은 순서</option>
					</select>
				</div>
				<input type="hidden" name="id" id="userid" value="${authUser.id}" />
				<!-- 리뷰텍스트  -->
				
				<c:if test="${not empty list }">
				<div id='review-in-detail-text-box-frame'>
				<c:forEach items="${list }" var="list" varStatus="status">
				<div class="review-in-detail-text-box">
					<div class="review-in-detail-text-box-border"
						style="margin-top: 20px;">
						<div class="row"
							style="padding-top: 20px; border-top: 1px solid lightgray;">
							<div class="review-in-detail-text-box-profile col"
								style="width: 200px;">
								<div>
									<div class="reviewer_profile"
										style="width: 48px; height: 100%;">
										<img
											src="https://d23zwvh2kbhdec.cloudfront.net/media/public/customers/photos/animals/hamster.png"
											style="width: 100%; height: auto;">
									</div>
								</div>

								<div class="reviewer-name "
									style="letter-spacing: -0.9px; font-size: 16px; font-weight: bold;">
									${list.nickname }</div>

								<div class="mb">
									<div>
										<i class="fas fa-star" style="color: #ffc107"></i> <span
											class="h-100 align-bottom"
											style="font-size: 18px; font-weight: bold; letter-spacing: -0.9px;">
											${list.star_score }</span> <span
											style="letter-spacing: -0.7px; color: #9b9b9b; font-size: 14px;">
											/ 5 </span>
									</div>

								</div>

								<div class="">

									<div class="row mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">효과</div>
										<div style="margin-left: 8px; position: absolute; right: 0">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le list.score_effect}">							
												<i class="fas fa-star"></i> <!-- 색깔별 -->		
												</c:if>
												<c:if test="${i gt list.score_effect }">
												<i class="far fa-star" style="color: black;"></i>
												</c:if>
												</c:forEach>
											</span>
										</div>
									</div>

									<div class="row mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">복용편이성</div>
										<div style="margin-left: 8px; position: absolute; right: 0">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le list.score_comfort}">							
												<i class="fas fa-star"></i> <!-- 색깔별 -->		
												</c:if>
												<c:if test="${i gt list.score_comfort}">
												<i class="far fa-star" style="color: black;"></i>
												</c:if>
												</c:forEach>
											</span>
										</div>
									</div>

									<div class="row mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">가격</div>
										<div style="margin-left: 8px; position: absolute; right: 0">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le list.score_price}">							
												<i class="fas fa-star"></i> <!-- 색깔별 -->		
												</c:if>
												<c:if test="${i gt list.score_price}">
												<i class="far fa-star" style="color: black;"></i>
												</c:if>
												</c:forEach>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="review-in-detail-text-box-content col mtl"
								style="width: 80%; margin-left: 60px;">


								<div class="row"
									style="height: 50px; justify-content: space-between;">

									<div style="font-size: 13px; color: #9b9b9b;">${list.dates }</div>
								</div>


								<div class="content-box mb"
									style="font-size: 15px; font-weight: 300; letter-spacing: -0.6px;">
									${list.contents }</div>

								<p class="price-content-box mb"
									style="font-size: 15px; font-weight: 300; letter-spacing: -0.6px;">
									가격: ${list.m_price }<br>
								</p>
								<br>
								<c:if test="${not empty list.picture }">
								<div class="mb row">
								<c:forEach items="${list.picture }" var="picDTO">
									<img src="${picDTO.pic }" style="height: 150px"
										alt="" />
								</c:forEach>
								</div>
								</c:if>
								
								<c:if test="${authUser.id eq list.id}">
								<div class="mb editDelete">
										<input type="hidden" name="review_code" value="${list.m_review_code }" />
										<button type="button" class="editBtn" >수정하기</button>
										<button type="button" class="deleteBtn">삭제하기</button>
								</div> 
								</c:if>
								<div class="" style="display: flex; justify-content: space-between;">
									<button type="button"  class="btn-report" id="btn-report"
										style="color: #b00020; cursor:pointer; font-size: 14px; text-decoration: underline; border: none; background: none;">
										신고하기</button>
									<c:if test="${list.userlike eq 1}">
									<button type="button" class="btn-likes row fc y" id="userlike${list.m_review_code}" style="background: #ffd4dc"> 
										도움이 되었어요 <i class="fas fa-heart"
											style="margin-left: 10px; margin-right: 10px; color: #f94d4d"></i>
										<div class="userAllLikes" id="userAllLikes${list.m_review_code }">${list.likes }</div>
									</button>									
									</c:if>
									<c:if test="${list.userlike eq 0}">
									<button type="button" id="userlike${list.m_review_code}" class="btn-likes row fc n" >	
										도움이 되었어요 <i class="fas fa-heart"
											style="margin-left: 10px; margin-right: 10px;"></i>
										<div class="userAllLikes" id="userAllLikes${list.m_review_code }">${list.likes }</div>
									</button>
									</c:if>
									
								</div>
							
							<!-- 신고하기 블록  -->
										<!-- 신고박스 -->
		<div class="reportbox" id="hreview_reportbox${list.m_review_code }" style="display: none">
			<form class="col" action="../manager/reportPosts.do" method="post" onsubmit="return checkReport();"> <!-- onsubmit 서브밋되기전 거치는 함수 로그인 안되있으면 리턴 false -->
				<select name="report_type" class="question_report_select">
					<option value="1">부적절한 홍보 게시글</option>
					<option value="2">악성코드 신고</option>
					<option value="3">음란성 또는 청소년에게 부적합한 내용</option>
					<option value="4">저작권 침해</option>
					<option value="5">명예훼손</option>
					<option value="6">기타</option>
				</select>
				<input type="hidden" name="board_seq" value="${ list.m_review_code }"/> <!-- 리뷰코드 -->
				<input type="hidden" name="all_board_seq" value="2" /><!-- 올려드린 게시판 번호 ex)1.병원리뷰게시판  -->
				<textarea name="contents" class="hreview_report" cols="30" rows="10" placeholder="신고 내용을 입력하세요."></textarea>
				<input type="submit" value="보내기" class="report_submit submit" />
			</form>
		</div>
							
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
				<div class="paging" style="justify-content: center ; display: flex;">
	
			<c:if test="${paging.prev eq true }">
			<b class="paging-num prev">이전</b>
			</c:if>
			<c:forEach begin="${paging.start }" end="${paging.end }" var="p">
			<c:choose>
				<c:when test="${ p== paging.currentPage }">
				<b class="paging-num" id="currentPage">${p}</b>
				</c:when>
				<c:when test="${p!= paging.currentPage }">
				<b class="paging-num" id="page${p}">${p}</b>
				</c:when>	
			</c:choose>
			</c:forEach>			
			<c:if test="${paging.next eq true }">
			<b class="paging-num next" >다음</b>
			</c:if>
	
			</div>
				
				</div>
				</c:if>
				<!-- 리뷰텍스트박스-->
			</div>
			<!-- 리뷰텍스트 끝 -->

		</div>
		</c:if>
	</div>
	<footer></footer>
	<script type="text/javascript" src="review-style/minfo-detail.js?var=2"></script>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>