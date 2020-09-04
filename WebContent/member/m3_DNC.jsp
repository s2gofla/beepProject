
<!-- DNC : Doctor Number Check -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.html"); // 원하는 파일 경로를 삽입하면 된다
		$("footer").load("../layout/footer.html");
	});
</script>
<style>
*{
	font-family: 'NanumSquare', sans-serif;
}

.m_DNC_box {
	background: #e7e6e657;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
}

.m_DNC_main {
	/* border: solid 1px #e7e6e657; */
	padding-bottom: 70px;
	width: 80%;
	display: flex;
	flex-direction: column;
	
}


.m_DNC_totalTop {
	/* border: solid 1px #e7e6e657; */
	display: flex;
	flex-direction: column;
	align-items: center;
}




/* //////////////////////////////////////////   */



.m_DNC_top1 {
	position: fixed;
	/* background-color:#e7e6e657; */
	width: 80%;
}

.m_DNC_top1_text {
	text-align: center;
	font-size: 30px;
}

.m_DNC_top2 {
	margin-top: 90px;
	display: flex;
	justify-content: center;
	background-color: white;
	width: 100%;
}

.m_DNC_top2_numCircle2 {
	width: 30px;
	height: 30px;
	border: solid 1px rgb(234, 153, 153);
	background-color: rgb(234, 153, 153);
	border-radius: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	justify-content: center;
}

.m_DNC_top2_numCircle {
	width: 30px;
	height: 30px;
	border: solid 1px rgb(245, 207, 207);
	background-color: rgb(245, 207, 207);
	border-radius: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

#m_DNC_num {
	font-size: 15px;
	text-align: center;
	color: white;
	bor
}

.m_DNC_numSub {
	margin: 0 10px;
	padding: 10px;
	display: flex;
	flex-direction: column;
	align-items: center;
	display: flex;
	display: flex
}

.m_DNC_numBottonText {
	text-align: center;
	font-size: 13px;
}

/*  //////////////////////////////////////////////// */

.m_DNC_totalMid{
	width: 100%;
	height: 100%;
	
	display: flex;
	justify-content: center;
}


.m_DNC_borderMid{
	border: solid 1px rgb(245, 207, 207);
	padding: 5px;
	margin-top: 30px;
	background-color: white;
 	width: 40%;
	height: auto;
		
}

/* ////////////////////////////////////////// */

.m_DNC_content{
	width: 100%;
	display: flex;
	justify-content: center;
	margin-top: 13px;
}


#m_DNC_certificationImg{
	width: 150px;
	height: 130px;
	

}

.m_DNC_p1{
	margin-bottom: 3px;
}

.m_DNV_text2{
	display: flex;
}

.m_DNC_input{

	font-family: sans-serif;
}

.m_DNC_button1{
	background-color: rgb(234,153,153);
	border: solid 1px rgb(234,153,153);
	border-radius:3px;
	height: 24px;
	color:white;
	margin-left: 3px;
}
/* ////////////////////////////////////// */
.m_DNC_bottom{
	display: flex;
	flex-direction :column;
	align-items: center;
	
}	
.m_DNC_p2{
	font-size: 11px;
	color: red;
}	


.m_DNC_button2{
	border: solid 1px rgb(224,102,102);
	background-color:rgb(224,102,102);
	border-radius:3px;
	font-size:14px;
	color: white;
	margin-bottom: 13px;
	
	width: 70px;
	height: 30px;
	
}
</style>
</head>
<body>
	<header id="header"></header>
	<section id="m_DNC_section1">
		<div class="m_DNC_box">
			<div class="m_DNC_main">


				<div class="m_DNC_totalTop">
					<div class="m_DNC_top1">
						<h1 class="m_DNC_top1_text">회원가입</h1>
					</div>
					<!--m_DNC_top1  -->

					<div class="m_DNC_top2">
						<!-- 순서도 숫자  시작-->

						<!-- 1 -->
						<div class="m_DNC_numSub">
							<div class="m_DNC_top2_numCircle">
								<p id=m_DNC_num>1</p>
							</div>
							<div class="m_DNC_numBottonText">약관동의</div>
						</div>
						<!--m_DNC_numSub  -->


						<!-- 2 -->
						<div class="m_DNC_numSub">
							<div class="m_DNC_top2_numCircle2">
								<p id=m_DNC_num>2</p>
							</div>
							<div class="m_DNC_numBottonText">[ 본인인증 ]</div>
						</div>
						<!--m_DNC_numSub"  -->


						<!-- 3 -->
						<div class="m_DNC_numSub">
							<div class="m_DNC_top2_numCircle">
								<p id=m_DNC_num>3</p>
							</div>
							<div class="m_DNC_numBottonText">정보입력</div>
						</div>
						<!--m_DNC_numSub"  -->


						<!-- 4 -->
						<div class="m_DNC_numSub">
							<div class="m_DNC_top2_numCircle">
								<p id=m_DNC_num>4</p>
							</div>
							<div class="m_DNC_numBottonText">가입완료</div>
						</div>
						<!--m_DNC_numSub -->
						
						
						
						<!-- 순서도 숫자 끝 -->
					</div>
					<!-- m_DNC_top2 -->

				</div>
				<!-- m_DNC_totalTop -->


				
				<div class="m_DNC_totalMid">
					<div class="m_DNC_borderMid">
					
						<div class="m_DNC_content">
							<div class="m_DNC_img">
							<img id="m_DNC_certificationImg" alt="면허사진" src="../beep_images/member-images/id3.svg">
							</div>
							
							<div class="m_DNC_text">
								<h2 class="m_DNC_h2">의사면허번호 인증</h2>
								<p class="m_DNC_p1">인증번호</p> 
								<div class="m_DNV_text2">
									<input type="password" class="m_DNC_input" autofocus="autofocus">
									<button class="m_DNC_button1">중복확인</button>
								</div><!--m_DNV_text2  -->
								
							</div>
							
							</div><!-- m_DNC_content -->
							
							<div class="m_DNC_bottom">
								<p class="m_DNC_p2">타인의 면허정보를 도용하면 형사처벌을 받을 수 있습니다.</p>
								<button class="m_DNC_button2" onclick="location.href = '../member/m4_infoDoctor.jsp' ">다음</button>
							</div>	<!-- m_DNC_bottom -->
												
						
					</div><!-- m_DNC_borderMid -->    
				
				</div><!-- m_DNC_totalMid -->




			</div>
			<!-- m_DNC_main -->
		</div>
		<!-- m_DNC_box -->
	</section>

	<footer></footer>
</body>
</html>