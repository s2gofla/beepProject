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
<link rel="stylesheet" href="hrevWrite-style.css?var=137262"
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
$(document).ready( function() {

   $("#headers").load("../layout/header.jsp");  // 외부 헤더 삽입
   $("footer").load("../layout/footer.html");  // 외부 푸터 삽입
   
   $("div").css("cursor","default");
   
   $("button").css({
	  "outline":"none"
	  ,"cursor":"pointer"
   });
   
   $("input").css({"outline":"none", "border":"none"})
   
   
   	//별점 저장
	var score_p = $("input[name=score_price]").val();
	var score_r = $("input[name=score_result]").val();
   	var score_s = $("input[name=score_satisfaction]").val();
   
	$(".2").find("span").text(score_p);
	$(".3").find("span").text(score_r);
	$(".4").find("span").text(score_s);
   
   
	
	
	
   //별점 제목 바꾸기
   $(".list-group").children().eq(1).find(".star-title").text("금액");
   $(".list-group").children().eq(2).find(".star-title").text("결과");
   $(".list-group").children().eq(3).find(".star-title").text("시설만족도");
   
   //별점 input태그 name값 변경
    $(".list-group").children().eq(1).find("input").attr("name")
   
   var targetNum;
   var kscore;
   var liNum;
   //별점 클릭 시 색깔바꾸고, 넘어갈 값 세팅
   $(".review-star i").on("click", function() {
	  targetNum = $(this).index();
	  liNum = $(this).parent().parent().parent().parent().index();
  	 
	  
	  $(this).parent().children("i").css("color","lightgray")
	  $(this).css("color","#ffc107").prevAll("i").css("color","#ffc107")

	  $(this).parent().parent().next().children("span").text(targetNum+1);

  	 kscore =$(".list-group").children().eq(liNum).find("span").text();
  
  	  $(".score").eq(liNum).val(kscore);
  	  
   });
   
	//파일 첨부
	

	$("#rfile").on("change", function() {
		var cur = $("input[name=rfile]").val();
		$(".rfileName").val(cur);
	})
   
	$("#cfile").on("change", function() {
		var cur = $("input[name=cfile]").val();
		$(".cfileName").val(cur);
	})
	
	
	$(".filename:first i").on("click", function() {
		$(".cfileName").val("");
	})
	
	$(".filename:last i").on("click", function() {
		$(".rfileName").val("");
	})
   
   
   //파라미터값 받아오기
	function getUrlParams() {
		var params = {};
		window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi,
				function(str, key, value) {
					params[key] = value;
				});
		return params;
	}

	var getParam = getUrlParams();
	


   
   });//function
</script>
</head>
<body>

	<div id="headers"></div>
	<div class="layout-main">
	<form action="../review/hreview_update.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<div class="review-title p1 mt1 justify">
		
		<c:choose>
		<c:when test="${dto.review_type eq 0 }">
		병원 일반리뷰 작성
		</c:when>
		<c:otherwise>
		병원 인증리뷰 작성
		</c:otherwise>
		</c:choose>
		</div>
		<div class="review-body a1">

			<!-- 병원목록 조회  -->
			<div class="review-contentbox p1 mt1 col justify">
				<div class="rowa mb5">
					<div class="contentbox-num">1</div>
					<div class="contentbox-title">병원조회</div>
				</div>
				<div class="rowa mb5 bt justify">
				<c:if test="${not empty param.h_name }">
				${param.h_name }
				</c:if>
				</div>
				<div class="rowa mb5 justify">
					<button type="button" class="review-button">병원 목록 조회</button>
				</div>
			</div>
			<!-- 평점 -->
			<div class="review-contentbox p1 mt1 col justify">
				<div class="rowa mb5">
					<div class="contentbox-num">2</div>
					<div class="contentbox-title">평점</div>
				</div>
				<div class="rowa mb5">
					<div class="col" style="width: 100%">
						<ul class="list-group">
							<c:forEach begin="1" end="4" step="1" var="i">
							<li class="list-group-item ${i }">
								<div class="rowa">
									<div class="pr rowa">
										<div class="star-title">친절도</div>
										<div class="review-star">
										<c:forEach begin="1" end="5" step="1">
										<i class="fas fa-star" style="cursor: pointer; color:lightgray;"></i>							
										</c:forEach>
										</div>
									</div>
									<div class="review-score a1"><span>${dto.score_kind }</span>/5</div>
									
								</div>
							</li>
							</c:forEach>			
						</ul>

					</div>


				</div>
			</div>
			<!-- 치료항목 조회 -->
			<div class="review-contentbox p1 mt1 col justify">
				<div class="rowa mb5">
					<div class="contentbox-num">3</div>
					<div class="contentbox-title">치료항목-가격</div>
				</div>
				<div class="rowa">
					<c:if test="${not empty dto.treatment }" >
					<div class="rowa col mb5 justify price odd">
						<c:forEach items="${dto.treatment}" var="treat">
						<input type="hidden" name="rtreatment_seq" value="${treat.rtreatment_seq }" />
						<input type="hidden" name="treatment_code" value="${treat.treatment_code}" />
						<div>${treat.treatment_name}</div>
						
						</c:forEach>
						
					</div>
					<div class="rowa col mb5 justify price odd">
						<c:forEach items="${dto.treatment}" var="treat">
						<input type="hidden" name="price" value="${treat.price }" />
						<div>${treat.price}</div>
						
						</c:forEach>

					</div>
					</c:if>
				</div>
				<div class="rowa mb5 justify">
					<button type="button" class="review-button">치료 항목 조회</button>
				</div>

			</div>
			<!-- 내용 -->
			<div class="review-contentbox col p1 mt1 justify">
				<div class="rowa mb5">
					<div class="contentbox-num">4</div>
					<div class="contentbox-title">내용쓰기</div>
				</div>
				<div class="justify">
					<textarea name="content" cols="80" rows="10">${dto.contents }</textarea>

				</div>
			</div>
			<!-- 영수증첨부(인증회원일 경우만) -->
			<c:if test="${dto.review_type eq 1}">
			<div class="review-contentbox p1 col mt1 justify">
				<div class="rowa mb5">
					<div class="contentbox-num">5</div>
					<div class="contentbox-title">인증첨부-영수증</div>
				</div>
				<div class="rowa justify"
					style="color: #018dad; font-size: 20px; font-weight: bold;">
					<div>전체화면 캡쳐 필수(병원명+회원성명 포함)</div>
				</div>
				<div class="rowa col">
					<div class="card-deck">
						<div class="card">
							<div class="card-body">
								<ul class="slist-group">
									<li class="slist-group-item">
										<div class="rowa">
											<div class="">
												<i class="fas fa-check" style="padding-right: 10px"></i>
											</div>
											<div class="" style="color: #494949;">카드 결제 문자</div>
										</div>
									</li>

								</ul>
							</div>
						</div>
						<div class="card mb5">
							<div class="card-body justify" style="background-color: #f8f8f8;">
								<img
									src="https://d23zwvh2kbhdec.cloudfront.net/static_20_07_08/img/mdd_event/receipt_example_content.svg"
									class="img-fluid w-100 h-100" alt="Responsive image">
							</div>
						</div>
					</div>



				</div>

				<div class="rowa mb5 justify">
					<div class="rowa justify col a1 filebtn">
						<label for="cfile" class="review-button a1 justify">영수증
							첨부하기</label> <input type="file" id="cfile" name="cfile" />
						<div class="filename pr">
							<input type="text" class="cfileName" id="cfileName" readonly="readonly">
							<i class="fas fa-times mb5" style="cursor: pointer;"></i>
						</div>
					</div>
				</div>
			</div>
			</c:if>
			<!-- 사진첨부 -->
			<div class="review-contentbox col p1 mt1 justify">
				<div class="rowa mb5">
					<div class="contentbox-num">
					<c:choose>
					<c:when test="${dto.review_type eq 1 }">
					6
					</c:when>
					<c:otherwise>
					5
					</c:otherwise>
					</c:choose>
					</div>
					<div class="contentbox-title">사진첨부</div>
				</div>
				<div class="rowa justify col a1 filebtn">
					<label for="rfile" class="review-button a1 justify">사진
						첨부하기</label> <input type="file" name= "rfile" id="rfile" />
					<div class="filename pr">
						<input type="text" class="rfileName" id="rfileName" readonly="readonly">
						<i class="fas fa-times mb5" style="cursor: pointer;"></i>
					</div>
				</div>
			</div>
			<input type="hidden" name="h_code" value="${param.h_code}" />	
			<input type="hidden" name="h_name" value="${param.h_name}" />		
			<input type="hidden" name="h_review_code" value="${param.h_review_code}" />			
			<input type="hidden" class="score" name="score_kind" value="${dto.score_kind }"/>
			<input type="hidden" class="score" name="score_price" value="${ dto.score_price }" />
			<input type="hidden" class="score" name="score_result" value="${ dto.score_result }" />
			<input type="hidden" class="score" name="score_satisfaction" value="${dto.score_satisfaction}" />
			<!-- 리뷰제출  -->
			<button id="submitbtn" class="review-submit p1 mt1 mb5 justify">리뷰제출</button>
		</div>
	</form>
	</div>
	
	<footer></footer>
	<script type="text/javascript" src="layout-js.js"></script>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>