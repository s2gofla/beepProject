<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m5_joinFinish.css?var=1340277777" type="text/css" charset="UTF-8" />
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("footer").load("../layout/footer.html");
	});
</script>
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
							<c:if test="${ result ne 0 }">
								<p class="m_JF_text1">회원가입 완료.</p>
								<p class="m_JF_text2">더 많은 서비스를 제공하겠습니다. 감사합니다.</p>
							</c:if>
							<c:if test="${ result eq 0 }">
								<p class="m_JF_text1">회원가입 실패.</p>							
								<p class="m_JF_text2">관리자에게 문의 바랍니다. 죄송합니다.</p>
							</c:if>
							<input class = "m_JF_button" type="button" value="로그인하러 가기" onclick="location.href='/beepPro/member/login.do'">
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