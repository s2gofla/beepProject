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
   
   $(".star-title2").text("가격")
   $(".star-title3").text("복용편이성")
   
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
	<form action="../review/mreview_write.do" method="post" enctype="multipart/form-data" >
		<div class="review-title p1 mt1 justify">약 리뷰작성</div>
		<div class="review-body a1">
		<!-- 약 목록 조회 -->
			<div class="review-contentbox p1 mt1 col justify">
				<div class="rowa mb5">
					<div class="contentbox-num">1</div>
					<div class="contentbox-title">약 제품 조회</div>
				</div>
				<div class="rowa mb5 bt justify">
				<c:if test="${not empty param.m_name }">${param.m_name }
				</c:if>
				</div>
				<c:if test="${empty param.m_name }">
				<div class="rowa mb5 justify">
					<button class="review-button">약 제품 조회</button>
				</div>				
				</c:if>
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
							<c:forEach begin="1" end="3" step="1" var="i">
							<li class="list-group-item">
								<div class="rowa">
									<div class="pr rowa">
										<div class="star-title${i}">효과</div>
										<div class="review-star">
										<c:forEach begin="1" end="5" step="1">
										<i class="fas fa-star" style="cursor: pointer; color:lightgray;"></i>							
										</c:forEach>
										</div>
									</div>
									<div class="review-score a1"><span>0</span>/5</div>
									
								</div>
							</li>
							</c:forEach>			
						</ul>

					</div>
				</div>
			</div>
			<!-- 내용  -->
			<div class="review-contentbox col p1 mt1 justify">
				<div class="rowa mb5">
					<div class="contentbox-num">3</div>
					<div class="contentbox-title">내용쓰기</div>
				</div>
				<div class="justify">
					<textarea name="content" cols="80" rows="10"></textarea>

				</div>
			</div>
			<!-- 가격입력 -->
				<div class="review-contentbox p1 mt1 col justify">
				<div class="rowa mb5">
					<div class="contentbox-num">4</div>
					<div class="contentbox-title">약-가격</div>
				</div>
				<div class="rowa">
					<div class="rowa mb5 justify price odd">
						<div class="a1 justify" style="width: 50%">${param.m_name }</div>
					<input type="text" name="m_price" value="가격 입력" style="width: 50%; border:none; border-bottom: 2px solid gray; font-size: 20px"/>
					</div>
				</div>

			</div>
			
			<!-- 사진첨부 -->
			<div class="review-contentbox col p1 mt1 justify">
				<div class="rowa mb5">
					<div class="contentbox-num">5</div>
					<div class="contentbox-title">사진첨부</div>
				</div>
				<div class="rowa justify col a1 filebtn">
					<label for="ex_file" class="review-button a1 justify">사진
						첨부하기</label> <input type="file" name= "rfile" id="ex_file" />
					<div class="filename pr">
						<input type="text" class="fileName" readonly="readonly">
						<i class="fas fa-times mb5" style="cursor: pointer;"></i>
					</div>
				</div>
			</div>
			<input type="hidden" name="id" value=${authUser.id } />
			<input type="hidden" name="m_code" value="${param.m_code}" />
			<input type="hidden" class="score" name="score_effect" value="" />
			<input type="hidden" class="score" name="score_price" value="" />
			<input type="hidden" class="score" name="score_comfort" value="" />
			
			<button id="submitbtn" class="review-submit p1 mt1 mb5 justify">리뷰제출</button>
		</div>
	</form>
	</div>

	<footer></footer>
	<script type="text/javascript" src="layout-js.js"></script>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>