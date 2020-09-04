<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="hinfoDetail-style.css?var=419923"  type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" 	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>삐이용삐이용</title>
<title>Insert title here</title>
<script>
$(document).ready( function() {
	$("#header").load("../layout/header.jsp?var=210");  // 원하는 파일 경로를 삽입하면 된다
	$("footer").load("../layout/footer.html");
});
</script>
</head>
<body>
<header id="header"></header>
<section class="review-part">
	<c:forEach items="${list }" var ="dto" varStatus="status">	
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

									<div class="row mb" style="position: relative;">
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

									<div class="row mb" style="position: relative;">
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

									<div class="row mb" style="position: relative;">
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

									<div class="row mb" style="position: relative;">
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

								<div class="row"
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
								<div class="mb row">
								<c:forEach items="${ dto.picture }" var="picDTO">
									<img src="../beep_images/hrev-images/${ picDTO.pic }" style="height: 150px"
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
									<button type="button" class="btn-likes row fc y" id="userlike${dto.h_review_code}" style="background: #ffd4dc"> 
										도움이 되었어요 <i class="fas fa-heart"
											style="margin-left: 10px; margin-right: 10px; color: #f94d4d"></i>
										<div class="userAllLikes" id="userAllLikes${dto.h_review_code }">${dto.likes }</div>
									</button>									
									</c:if>
									<c:if test="${dto.userlike eq 0}">
									<button type="button" id="userlike${dto.h_review_code}" class="btn-likes row fc n" >	
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
</section>





<footer></footer>
</body>
</html>