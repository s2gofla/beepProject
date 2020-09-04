<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="disease.css?var=1330" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {
	$("#headers").load("../layout/header.html");  // 외부 헤더 삽입
	$("footer").load("../layout/footer.html");  // 외부 푸터 삽입
});
</script>
</head>
<body>
<div id="headers"></div>
<!---------------------------------------------------------------------------------------------->
 
 

<div id="all">
	<div id="content">
		<div class="section_wrap ">
			<div class="headword_title"> 				
				<h2 class="headword">
				<img src="inf_images/dis.jpg" alt="">치통
				</h2>
			</div>
			
			<div id="inf">
			<ul>
				<li class="inf_li"><h3 class="d_title">정의</h3></li>
				<p class="d_content">치아나 잇몸의 통증이나 불편감</p>
				<li class="inf_li"><h3 class="d_title">원인</h3></li>
				<p class="d_content">생활습관 : 불량한 구강위생과 당분 과다 섭취가 위험 요인임</p>
				<li class="inf_li"><h3 class="d_title">증상 상세내용</h3></li>
				<p class="d_content">뜨겁거나 차거나, 혹은 단 음식물을 씹고 마실 때 느껴지는 예리한 통증은 충치의 초기 증상일 수 있다. 치아 골절, 잇몸 퇴축, 잇몸 질환(치주염)은 치통을 일으킬 수 있다. 음식물을 씹을 때 발생하는 치아의 둔통은 플라그(세균, 타액, 음식물 찌꺼기의 끈적한 혼합물)가 축적되어 발생한 잇몸의 염증이 원인일 수 있다.</p>
				<li class="inf_li"><h3 class="d_title">진단&치료</h3></li>
				<p class="d_content">의사는 증상에 대해 물어보고 치아와 잇몸을 살펴본다. 충치를 찾아내기 위해 X-선 촬영을 할 수도 있다. 만약 충치에 의해 치통이 생긴 것이라면 썩은 부분을 제거하고 충전물을 채워넣어 통증을 완화시키고 더이상 썩지 않게 한다(치아 충전). 잇몸 염증이 있을 때에는 스케일링을 해서 플라그를 제거한다. 또한 감염이 된 경우에는 항생제를 처방한다.</p>
				<li class="inf_li"><h3 class="d_title">예방방법</h3></li>
				<p class="d_content">치통이 있다면 되도록 빨리 치과 의사의 진료를 받는다. 치통뿐만 아니라 열이 있고 얼굴이 붓는다면 즉시 치과 의사에게 진찰을 받아야 한다. 아스피린 등 진통제나 따뜻한 식염수로 입 안을 씻어내는 것이 통증을 가라앉히는 데 도움이 될 수 있다. 그러나 치통에 대한 근본적인 처치는 치과 의사에게 치료를 받는 것이다.</p>
			</ul>
			</div>
			<button class="back_btn">뒤로 가기</button>
		</div>
		
	</div>
	
</div>


<!---------------------------------------------------------------------------------------------->
<footer></footer>
<script type="text/javascript" src="layout-js.js"></script><!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>