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
<link rel="stylesheet" href="review-style/hinfoDetail-style.css?after=1"
	type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {

   $("#headers").load("../layout/header.jsp");  // 외부 헤더 삽입
   $("footer").load("../layout/footer.html");  // 외부 푸터 삽입
   
 
   $( ".bookmark-box i" ).hover( function() {
 	
	$(this).toggleClass("activeB");
	
   });
   
   //검색바 값 초기화
   $("#searchWord").on("click", function() {
   	$(this).val("");
   })
   
   //커서 정리
   $("div").css("cursor","default");
   $("input").css("outline","none");
   $("button").css("outline","none");
   
   //리뷰박스로 스크롤 이동
   $(".hinfo-menu-box").children().eq(1).on("click", function() {
   		var offset = $(".review-box").offset();
   		
   		$('html').animate({scrollTop: offset.top}, 400);
   })
 	
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
	   
	   if ($("input[name='userid']").val() != "") {
		
	   location.href = "../review/hreview_write.do?h_code="+getParam.h_code+"&h_name="+ getParam.h_name+"&certi=0";
		   
	}else {
		alert("로그인 후 이용해주세요")
		location.href = "../member/login.do";
	}
	   
   })
   
   //인증 리뷰 작성하기
      $("#cwrite").on("click", function() {
	   
	   if ($("input[name='userid']").val() != "") {
		
	   location.href = "../review/hreview_write.do?h_code="+getParam.h_code+"&h_name="+ getParam.h_name+"&certi=1";
		   
	}else {
		alert("로그인 후 이용해주세요")
		location.href = "../member/login.do";
	}
	   
   })
   
   
   
   //리뷰 삭제하기 경로 보내기
   $(".deleteBtn").on("click", function() {
	 var index = $(".deleteBtn").index(this)
	 h_review_code = $("input[name='review_code']").eq(index).val();
	 
   	 location.href= "../review/hreview_delete.do?h_review_code="+h_review_code+"&h_code="+getParam.h_code+"&h_name="+getParam.h_name;
   })
   
   //리뷰 수정하기 경로 보내기
   $(".editBtn").on("click", function() {
   		var index = $(".editBtn").index(this)
	  	h_review_code = $("input[name='review_code']").eq(index).val();
	 
	   location.href = "../review/hreview_update.do?h_review_code="+h_review_code+"&h_code="+getParam.h_code+"&h_name="+getParam.h_name;
	   
   })
   
   //모달창 띄우기
   $("#doctorEdit").on("click", function() {
   		$("#editModal").modal("show");
   		$("#doctorText").val("");
   })
   
   
   });//body
   
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
					style="width: 300px; height: 300px;">
					<div class="rowa fc" style="height: 100%">
						<div class="title-image-box fc">
							<div class="fc"
								style="height: 60%; width: 100%; margin-top: 30px;">
								<i class="fas fa-clinic-medical" style="font-size: 100px"></i>
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
						<div class="hospital-name">${dto.h_name}</div>
						
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
								style="font-size: 15px; color: #6c757d; padding-left: 10px; line-height: 2.5;">(${dto.reviewer})</span>
						</div>
					</div>
					<div class="hinfo-list" style="height: 210px;">
						<ul
							style="height: 100%; list-style-type: none; text-align: justify;">
							<li><i class="fas fa-map-marker-alt"></i>주소 : <b>${dto.h_address}</b></li>
							<li><i class="fas fa-phone"></i>전화번호 : <b> ${dto.h_tel}</b></li>
							<li><i class="fas fa-home"></i>홈페이지 : <a href="${dto.h_link }">${dto.h_link }</a></li>
						</ul>
					</div>
				</div>
				<div style="width: 80px; position: relative; margin-left: 60px;">
					<div  id="bookmark" class="bookmark-box fc">
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
					<div id= "cwrite" class="bookmark-box fc" style="top:140px; font-size: 12px; font-weight: bold;">
						<i class="fas fa-user-edit"
							style="font-size: 50px;"></i>
						<p style="margin: 0">인증 리뷰쓰기</p>
					</div>
					<div id="write" class="bookmark-box fc" style="top:225px; font-size: 12px; font-weight: bold;">
						<i class="fas fa-pen-fancy"
							style="font-size: 50px;"></i>
						<p style="margin: 0">일반 리뷰쓰기</p>
					</div>
				</div>

			</div>
		</div>
		<div class="fc col" style="width: 100%;">
		<!-- 정보/리뷰/가격 메뉴바 -->
			<div class="hinfo-menu-box">
				<div class="menu-bar fc">상세정보</div>
				<div class="menu-bar fc" style="cursor: pointer;">리뷰보기(${dto.reviewer })</div>
				<div class="menu-bar fc" style="cursor:pointer">가격정보</div>
			</div>
		<!-- 상세정보 목록  -->	
			<div class="content-detail-box">
				<div>
					<div class="content-title mtl">
						<i class="fas fa-notes-medical"></i>진료과목
					</div>
					<div class="content-list">
						<ul>
						<c:forEach items="${dto.sub}" var="subDto">
								
						<li>${subDto.m_sub_name }</li>
							
						</c:forEach>
						</ul>
					</div>
				</div>

				<div class="content-title mtl">
					<i class="fas fa-clock"></i>병원시간
				</div>
				<div class="content-list">
					<ul class="col htimeset">
					 	
					 <c:set value="${dto.htime }" var="htime"></c:set>
							<c:if test="${not empty htime.mon }">
								<li>월요일 : ${htime.mon }</li>
							</c:if>
							<c:if test="${not empty htime.tue }">
								<li>화요일: ${htime.tue}</li>
							</c:if>
							<c:if test="${not empty htime.wed }">
								<li>수요일: ${htime.wed }</li>
							</c:if>
							<c:if test="${not empty htime.thur}">
								<li>목요일: ${htime.thur }</li>
							</c:if>
							<c:if test="${not empty htime.mon }">
								<li>금요일: ${htime.fri }</li>
							</c:if>			
							<c:if test="${not empty htime.sat }">
								<li>토요일: ${htime.sat }</li>
							</c:if>
							<c:if test="${not empty htime.ltime_week }">
								<li>점심시간(평일): ${htime.ltime_week }</li>
							</c:if>
							<c:if test="${not empty htime.ltime_weekend }">
								<li>점심시간(주말): ${htime.ltime_weekend }</li>
							</c:if>
						
				
					</ul>

				</div>
				<div class="content-title mtl">
					<i class="fas fa-plus-circle"></i>특이사항
				</div>
				<div class="content-list" style="display: flex">
				
						<c:forEach items="${dto.special}" var="speDto">
							<c:choose>
							<c:when test="${speDto.st_code eq 1 }">
								<div class="content-special mtl col fc">
								<i class="fas fa-sun"></i>오전진료
								</div>
							</c:when>
							<c:when test="${speDto.st_code eq 2 }">
								<div class="content-special mtl col fc">
								<i class="fas fa-moon"></i>야간진료
								</div>
							</c:when>
							<c:when test="${speDto.st_code eq 3 }">
								<div class="content-special mtl col fc">
								<i class="fas fa-history"></i>일요일/공휴일
								</div>
							</c:when>
							<c:when test="${speDto.st_code eq 4}">
								<div class="content-special mtl col fc">
								<i class="fas fa-venus"></i>여의사
								</div>
							</c:when>
							</c:choose>									
							</c:forEach>

				</div>

				<div class="mtl justify">
					<div class="hinfo-update mtl mlt" style="width: 80%">

						<button id="doctorEdit">의사회원으로 정보 수정 요청하기</button>
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
						<option class="sort-option" value="1">최신 순서</option>
						<option class="sort-option" value="2">좋아요 많은 순서</option>
						<option class="sort-option" value="3">평점 높은 순서</option>
						<option class="sort-option" value="4">평점 낮은 순서</option>
					</select>
				</div>
				<input type="hidden" id="userid" name="userid" value="${authUser.id}" />
				<!-- 리뷰텍스트  -->
				
				<c:if test="${not empty list }">
				<div id='review-in-detail-text-box-frame'>
				<c:forEach items="${list }" var ="dto" varStatus="status">
				
				<div class="review-in-detail-text-box">
					<div class="review-in-detail-text-box-border"
						style="margin-top: 20px;">			
						<div class="rowa"
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

								<div class="reviewer-name"
									style="letter-spacing: -0.9px; font-size: 16px; font-weight: bold;">
									${ dto.nickname}</div>

								<div class="mb">
									<div>
										<i class="fas fa-star" style="color: #ffc107"></i> <span
											class="h-100 align-bottom"
											style="font-size: 18px; font-weight: bold; letter-spacing: -0.9px;">
											${dto.star_score } </span> <span
											style="letter-spacing: -0.7px; color: #9b9b9b; font-size: 14px;">
											/ 5 </span>
									</div>

								</div>

								<div class="">

									<div class="rowa mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">친절도</div>
										<div style="margin-left: 8px; position: absolute; right: 0">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le dto.score_kind}">							
												<i class="fas fa-star"></i> <!-- 색깔별 -->		
												</c:if>
												<c:if test="${i gt dto.score_kind }">
												<i class="far fa-star" style="color: black;"></i>
												</c:if>
												</c:forEach>  
											</span>
										</div>
									</div>

									<div class="rowa mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">금액</div>
										<div style="margin-left: 8px; position: absolute; right: 0">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le dto.score_price}">							
												<i class="fas fa-star"></i> <!-- 색깔별 -->		
												</c:if>
												<c:if test="${i gt dto.score_price }">
												<i class="far fa-star" style="color: black;"></i>
												</c:if>
												</c:forEach>
											</span>
										</div>
									</div>

									<div class="rowa mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">결과</div>
										<div style="margin-left: 8px; position: absolute; right: 0">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le dto.score_result}">							
												<i class="fas fa-star"></i> <!-- 색깔별 -->		
												</c:if>
												<c:if test="${i gt dto.score_result }">
												<i class="far fa-star" style="color: black;"></i>
												</c:if>
												</c:forEach>
											</span>
										</div>
									</div>

									<div class="rowa mb" style="position: relative;">
										<div style="font-size: 15px; font-weight: 500;">시설만족도</div>
										<div style="margin-left: 8px; position: absolute; right: 0;">
											<span
												style="display: block; text-align: center; font-size: 15px;">
												<c:forEach begin="1" end="5" step="1" var="i">
												<c:if test="${i le dto.score_satisfaction}">							
												<i class="fas fa-star"></i> 		
												</c:if>
												<c:if test="${i gt dto.score_satisfaction}">
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

								<div class="rowa"
									style="height: 50px; justify-content: space-between;">
									<div class="">
									<c:if test="${dto.review_type eq 1 }">
										<div class= "certi fc" style="background-color: #d8e2f3; font-size: 10px;">영수증
										인증된 리뷰
										</div>
									</c:if>	
									</div>
									<div style="font-size: 13px; color: #9b9b9b;">${dto.dates }</div>
								</div>

								<div class="mb treatment-tag-box"
									style="font-weight: bold; letter-spacing: -0.8px; font-size: 180">
									받은 치료 : 

									<c:forEach items="${dto.treatment }" var="treatDTO">
									<span>${treatDTO.m_sub_name }-${treatDTO.treatment_name }</span>									
									</c:forEach>
									</div>

								<div class="content-box mb"
									style="font-size: 15px; font-weight: 300; letter-spacing: -0.6px;">
									${dto.contents }</div>

								<p class="price-content-box mb"
									style="font-size: 15px; font-weight: 300; letter-spacing: -0.6px;">
									<c:forEach items="${dto.treatment }" var="treatDTO">
									
									${treatDTO.treatment_name } : ${treatDTO.price }원
									</c:forEach>
									<br>
								</p>
								<br>
								<c:if test="${not empty dto.picture }">
								<div class="mb rowa">
								<c:forEach items="${dto.picture }" var="picDTO">
									<img src="../beep_images/hrev-images/${picDTO.pic }" style="height: 150px"
										alt="" />

								</c:forEach>

								</div>
								</c:if>
								
								<c:if test="${authUser.id eq dto.id}">
								<div class="mb editDelete">
										<input type="hidden" name="review_code" value="${dto.h_review_code }" />
										<button type="button" class="editBtn" >수정하기</button>
										<button type="button" class="deleteBtn">삭제하기</button>
								</div>
								</c:if>                                                                                                                                                
								<div class="" style="display: flex; justify-content: space-between;">
									<button type="button"  class="btn-report" id="btn-report"
										style="color: #b00020; cursor:pointer; font-size: 14px; text-decoration: underline; border: none; background: none;">
										신고하기</button>
									<c:if test="${dto.userlike eq 1}">
									<button type="button" class="btn-likes rowa fc y" id="userlike${dto.h_review_code}" style="background: #ffd4dc"> 
										도움이 되었어요 <i class="fas fa-heart"
											style="margin-left: 10px; margin-right: 10px; color: #f94d4d"></i>
										<div class="userAllLikes" id="userAllLikes${dto.h_review_code }">${dto.likes }</div>
									</button>									
									</c:if>
									<c:if test="${dto.userlike eq 0}">
									<button type="button" id="userlike${dto.h_review_code}" class="btn-likes rowa fc n" >	
										도움이 되었어요 <i class="fas fa-heart"
											style="margin-left: 10px; margin-right: 10px;"></i>
										<div class="userAllLikes" id="userAllLikes${dto.h_review_code }">${dto.likes }</div>
									</button>
									</c:if>
									
								</div>
								
								<!-- 신고하기 블록  -->
										<!-- 신고박스 -->
		<div class="reportbox" id="hreview_reportbox${dto.h_review_code }" style="display: none">
			<form class="col" action="../manager/reportPosts.do" method="post" onsubmit="return checkReport();"> <!-- onsubmit 서브밋되기전 거치는 함수 로그인 안되있으면 리턴 false -->
				<select name="report_type" class="question_report_select">
					<option value="1">부적절한 홍보 게시글</option>
					<option value="2">악성코드 신고</option>
					<option value="3">음란성 또는 청소년에게 부적합한 내용</option>
					<option value="4">저작권 침해</option>
					<option value="5">명예훼손</option>
					<option value="6">기타</option>
				</select>
				<input type="hidden" name="board_seq" value="${ dto.h_review_code }"/> <!-- 리뷰코드 -->
				<input type="hidden" name="all_board_seq" value="1" /><!-- 올려드린 게시판 번호 ex)1.병원리뷰게시판  -->
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
	
	<!-- 모달창 구간 / 의사정보 수정요청 -->
	<div class="modal fade" id="editModal" role="dialog">
		<div class="modal-dialog" style="width: 350px">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">병원정보 수정요청</h4>
				</div>
				<div class="modal-body">
					
					<div style="text-align: center; width: 100%; height: 100%;" >
						<form id="form1" style="height: 100%;" >
							<table width="300px" height="100%" border="0" align="center">
								<tr style="height: 10%;">
									<td>수정내용을 입력해주세요.</td>
								</tr>
								<tr style="height: 10%">
									<td>관리자 확인 후 정보가 반영됩니다.</td>
								</tr>
								<tr>
									<td><textarea rows="11" cols="40" name="doctorText" id="doctorText"></textarea>
									<input	type="hidden" id="docH_code" name="docH_code" value="${param.h_code}"></td>
								</tr>
								<tr>
									<td><input type="submit" id="btnSubmit" value="수정요청">
									
								</tr>
							</table>
						</form>
					</div>

				</div>
				<div class="modal-footer" style="padding: 10px;">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- modal-content(끝)  -->
		</div>
	</div>
	
	<script>
	
	function checkReport(){
		var authid =  "<c:out value="${ authUser.id }" />";
		if(authid==null||authid==""){
			alert("로그인 후 이용 가능합니다.");
			return false;
		}
	}
	
	</script>
	
	
	
	<footer></footer>
	<script type="text/javascript" src="review-style/hinfo-detail.js?var=5"></script>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>