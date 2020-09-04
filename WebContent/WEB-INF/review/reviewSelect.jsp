<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="../review/reviewSelect-style.css?var=1320192" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {

   $("#headers").load("../layout/header.jsp");  // 외부 헤더 삽입
   $("footer").load("../layout/footer.html");  // 외부 푸터 삽입
   
   $(".layout-main").css("cursor","default");
   $("button").css(
			{	   
		   "outline":"none"
		   ,"cursor":"pointer"
			}
   );
   
   $("#hrevbtn").click(function() {
	   if ($("input[name='id']").val() != "") {
	   
	   location.href = "../review/hreview_write.do?certi=0";
	   } else {
		   alert("로그인 후 이용해주세요");
		   location.href = "../member/login.do";
	   }
	   
   });
   
   $("#hcrevbtn").click(function() {
	   if ($("input[name='id']").val() != "") {
	   location.href = "../review/hreview_write.do?certi=1";
		   
	   }else {
		   alert("로그인 후 이용해주세요");
		   location.href = "../member/login.do";
	   }
	   
   });
   
   $("#mrevbtn").on("click", function() {
   		
	   if ($("input[name='id']").val() != "") {
		   location.href = '../review/mreview_write.do';
			   
		   }else {
			   alert("로그인 후 이용해주세요");
			   location.href = "../member/login.do";
		   }
   	
   })
   
   
   });//function
</script>
</head>
<body>
<div id="headers"></div>
<div class="layout-main">
	
	<div class="review-notice">
		<div>리뷰 쓰기 안내</div>
		<div>
		<p><i class="fas fa-clipboard" style="	transform : rotate(-20deg);"></i>최근 이용한 병원 및 해당하는 약 제품을 산 누구나 리뷰를 쓸 수 있습니다!</p>
		<p><i class="fas fa-check-circle"></i>병원은 영수증 인증을 통해 인증된 리뷰를 작성하실 수 있습니다!</p>
		<p><i class="fas fa-chess-queen"></i>리뷰를 작성하시고 회원등급을 올려보세요!</p>	
		</div>
	</div>
	
	<div class="review-body-layout">
	<div class ="review-body">
		<div class="reivew-hospital" style="box-shadow: -1px 2px 5px 0 rgba(0,0,0,0.2)">
		<div class="review-title">병원 리뷰 쓰러가기</div>
		<div class="hreview-content-group">
		
		<div class="hreview-box">
		<div class="hreview-content">
			<h3>인증 리뷰</h3>
			<div></div>
			<p>영수증 첨부를 통해 인증된 리뷰를 쓸수있습니다.</p>
			<i class="fas fa-user-edit"></i>
		
		</div>
		<button id="hcrevbtn">리뷰쓰기</button>
		</div>
		<div class="hreview-box">
		<div class="hreview-content">
			<h3>일반 리뷰</h3>
			<div></div>
			<p>영수증 인증 없이 간단하게 리뷰를 써보세요!</p>
				<i class="fas fa-pen-fancy"></i>
		</div>
		<button style="border-left: 1px solid lightgray;" id="hrevbtn">리뷰쓰기</button>
		</div>	
		</div>
		</div>
		<div class="review-medicine" style="box-shadow:2px 2px 5px 0 rgba(0,0,0,0.2) ">
		<div class="review-title" style="background:#7bc3e6 ">약 리뷰 쓰러가기</div>
		<div class="hreview-content-group">
		
		<div class="hreview-box" style="width: 100%">
		<div class="hreview-content">
			<h3>일반 리뷰</h3>
			<div></div>
			<p>다양한 약 제품에 대한 리뷰를 쓸수있습니다.</p>
			<i class="fas fa-mortar-pestle"></i>
		
		</div>
		<button id="mrevbtn">리뷰쓰기</button>
		</div>
			

		</div>
	</div>
	</div>
</div>
</div>
<input type="hidden" id="id" name="id" value="${authUser.id}" />
	<footer></footer>
<script type="text/javascript" src=""></script><!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>