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
<link rel="stylesheet" href="hrevWrite-style.css?var=144442"
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
   $("input").css({"outline":"none", "border":"none"});
   $("input[name='m_price']").click(function() {
   		$(this).val("");
   });
   
   $(".list-group").children().eq(1).find("input").attr("name")
   
   $(".2").text("가격")
   $(".3").text("복용편이성")
   
   //별점 저장
	var score_e = $("input[name=score_effect]").val();
	var score_p = $("input[name=score_price]").val();
   	var score_c = $("input[name=score_comfort]").val();
   
	$(".2").find("span").text(score_e);
	$(".3").find("span").text(score_p);
	$(".4").find("span").text(score_c);
   
   
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
  	console.log(kscore);
   });
   

   
   });
</script>
</head>
<body>
	<div id="headers"></div>
	<div class="layout-main">
	<form action="../review/mreview_update.do" method="post" enctype="multipart/form-data" >
		<div class="review-title p1 mt1 justify">약 리뷰작성</div>
		<div class="review-body a1">
		<!-- 약 목록 조회 -->
			<div class="review-contentbox p1 mt1 col justify">
				<div class="row mb5">
					<div class="contentbox-num">1</div>
					<div class="contentbox-title">약 제품 조회</div>
				</div>
				<div class="row mb5 bt justify">
				<c:if test="${not empty param.m_name }">${param.m_name }
				</c:if>
				</div>
				<div class="row mb5 justify">
					<button class="review-button">약 제품 조회</button>
				</div>
			</div>
			<!-- 평점 -->
			<div class="review-contentbox p1 mt1 col justify">
				<div class="row mb5">
					<div class="contentbox-num">2</div>
					<div class="contentbox-title">평점</div>
				</div>
				<div class="row mb5">
					<div class="col" style="width: 100%">
						<ul class="list-group">
							<c:forEach begin="1" end="3" step="1" var="i">
							<li class="list-group-item">
								<div class="row">
									<div class="pr row">
										<div class="star-title ${i}">효과</div>
										<div class="review-star">
										<c:forEach begin="1" end="5" step="1">
										<i class="fas fa-star" style="cursor: pointer; color:lightgray;"></i>							
										</c:forEach>
										</div>
									</div>
									<div class="review-score a1"><span>${dto.score_effect }</span>/5</div>
									
								</div>
							</li>
							</c:forEach>			
						</ul>

					</div>
				</div>
			</div>
			<!-- 내용  -->
			<div class="review-contentbox col p1 mt1 justify">
				<div class="row mb5">
					<div class="contentbox-num">3</div>
					<div class="contentbox-title">내용쓰기</div>
				</div>
				<div class="justify">
					<textarea name="content" cols="80" rows="10">${dto.contents }</textarea>

				</div>
			</div>
			<!-- 가격입력 -->
				<div class="review-contentbox p1 mt1 col justify">
				<div class="row mb5">
					<div class="contentbox-num">4</div>
					<div class="contentbox-title">약-가격</div>
				</div>
				<div class="row">
					<div class="row mb5 justify price odd">
						<div class="a1 justify" style="width: 50%">${param.m_name }</div>
					<input type="text" name="m_price" value="${dto.m_price }" style="width: 50%; border:none; border-bottom: 2px solid gray; font-size: 20px"/>
					</div>
				</div>

			</div>
			
			<!-- 사진첨부 -->
			<div class="review-contentbox col p1 mt1 justify">
				<div class="row mb5">
					<div class="contentbox-num">5</div>
					<div class="contentbox-title">사진첨부</div>
				</div>
				<div class="row justify col a1 filebtn">
					<label for="ex_file" class="review-button a1 justify">사진
						첨부하기</label> <input type="file" name= "rfile" id="ex_file" />
					<div class="filename pr">
						<c:set value="${dto.picture }" var="pic"></c:set>
						<input type="text" class="fileName" readonly="readonly" value="">
						<i class="fas fa-times mb5" style="cursor: pointer;"></i>
					</div>
				</div>
			</div>
			
			<input type="hidden" name="m_code" value="${param.m_code}" />
			<input type="hidden" name="m_name" value="${param.m_name }"/>
			<input type="hidden" name="m_review_code" value="${param.m_review_code }" />
			<input type="hidden" class="score" name="score_effect" value="${dto.score_effect }" />
			<input type="hidden" class="score" name="score_price" value="${dto.score_price }" />
			<input type="hidden" class="score" name="score_comfort" value="${dto.score_comfort }" />
			
			<button id="submitbtn" class="review-submit p1 mt1 mb5 justify">리뷰제출</button>
		</div>
	</form>
	</div>

	<footer></footer>
	<script type="text/javascript" src="layout-js.js"></script>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>