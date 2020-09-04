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
* {
	font-family: 'NanumSquare', sans-serif;
}

.m_JF_box {
	background: #e7e6e657;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
}

.m_JF_main {
	/* border: solid 1px #e7e6e657; */
	padding-bottom: 70px;
	width: 80%;
	display: flex;
	flex-direction: column;
}

.m_JF_totalTop {
	/* border: solid 1px #e7e6e657; */
	display: flex;
	flex-direction: column;
	align-items: center;
}

/* //////////////////////////////////////////   */
.m_JF_top1 {
	position: fixed;
	/* background-color:#e7e6e657; */
	width: 80%;
}

.m_JF_top1_text {
	text-align: center;
	font-size: 30px;
}

.m_JF_top2 {
	margin-top: 90px;
	display: flex;
	justify-content: center;
	background-color: white;
	width: 100%;
}

.m_JF_top2_numCircle4 {
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

.m_JF_top2_numCircle {
	width: 30px;
	height: 30px;
	border: solid 1px rgb(245, 207, 207);
	background-color: rgb(245, 207, 207);
	border-radius: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

#m_JF_num {
	font-size: 15px;
	text-align: center;
	color: white;
	bor
}

.m_JF_numSub {
	margin: 0 10px;
	padding: 10px;
	display: flex;
	flex-direction: column;
	align-items: center;
	display: flex;
	display: flex
}

.m_JF_numBottonText {
	text-align: center;
	font-size: 13px;
}

/*  //////////////////////////////////////////////// */

.m_JF_totalMid {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
}

.m_JF_borderMid {
	border: solid 1px rgb(245, 207, 207);
	padding: 5px;
	margin-top: 30px;
	background-color: white;
	width: 40%;
	height: auto;
	display: flex;
	justify-content: center;
}

.m_JF_img {
	width: 100px;
	height: 100px;
	margin-right: 30px;
	margin-top: 5px;
	margin-bottom: 10px;
}

.m_JF_text{

	display: flex;
	flex-direction: column;
	justify-content: center;
}

.m_JF_text1{
	margin-bottom: 2px;
	font-size: 25px;
}

.m_JF_text2{
	margin-top: 2px;
	font-size: 17px;
}

.m_JF_button{
	border: solid 1px rgb(224,102,102);
	background-color:rgb(224,102,102);
	border-radius:3px;
	font-size:14px;
	color: white;
	margin-bottom: 13px;
	
	width: 100%;
	height: 30px;
}
/* ////////////////////////////////////////// */

</style>
</head>
<body>
	<header id="header"></header>
	<section id="m_JF_section1">
		<div class="m_JF_box">
			<div class="m_JF_main">


				<div class="m_JF_totalTop">
					<div class="m_JF_top1">
						<h1 class="m_JF_top1_text">회원가입</h1>
					</div>
					<!--m_JF_top1  -->

					<div class="m_JF_top2">
						<!-- 순서도 숫자  시작-->

						<!-- 1 -->
						<div class="m_JF_numSub">
							<div class="m_JF_top2_numCircle">
								<p id=m_JF_num>1</p>
							</div>
							<div class="m_JF_numBottonText">약관동의</div>
						</div>
						<!--m_JF_numSub  -->


						<!-- 2 -->
						<div class="m_JF_numSub">
							<div class="m_JF_top2_numCircle">
								<p id=m_JF_num>2</p>
							</div>
							<div class="m_JF_numBottonText">본인인증</div>
						</div>
						<!--m_JF_numSub"  -->


						<!-- 3 -->
						<div class="m_JF_numSub">
							<div class="m_JF_top2_numCircle">
								<p id=m_JF_num>3</p>
							</div>
							<div class="m_JF_numBottonText">정보입력</div>
						</div>
						<!--m_JF_numSub"  -->


						<!-- 4 -->
						<div class="m_JF_numSub">
							<div class="m_JF_top2_numCircle4">
								<p id=m_JF_num>4</p>
							</div>
							<div class="m_JF_numBottonText">[ 가입완료 ]</div>
						</div>
						<!--m_JF_numSub -->



						<!-- 순서도 숫자 끝 -->
					</div>
					<!-- m_JF_top2 -->

				</div>
				<!-- m_JF_totalTop -->



				<div class="m_JF_totalMid">
					<div class="m_JF_borderMid">
						<div class="m_JF_img">
							<img class="m_JF_img" alt="고고"
								src="../beep_images/member-images/lets-go2.svg">
						</div>

						<div class="m_JF_text">
							<p class="m_JF_text1">회원가입 완료.</p>
							<p class="m_JF_text2">더 많은 서비스를 제공하겠습니다. 감사합니다.</p>
							<input class = "m_JF_button" type="button" value="로그인하러 가기">
						</div>

					</div>
					<!-- m_JF_borderMid -->

				</div>
				<!-- m_JF_totalMid -->




			</div>
			<!-- m_JF_main -->
		</div>
		<!-- m_JF_box -->
	</section>
	<footer></footer>
</body>
</html>