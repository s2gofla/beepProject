<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m13_findId.css?var=177888555"
	type="text/css" charset="UTF-8" />
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
	<section id="m_findID_section1">
		<div class="m_findID_box">
			<div class="m_findID_main">

				<!-- 맨위 -->
				<div class="m_findID_top">
					<div class="m_findID_top_text">
						<h1 class="h1_text">아이디 찾기</h1>
						<hr width="100%" color="#E06666" size="2">
					</div>
					<!-- m_findID_top_text -->
				</div>
				<!-- m_findID_top -->


				<!-- 중간 -->
				<div class="m_findID_mid">
					<div class="m_findID_mid_left">
						<div class='m_findID_mid_text'></div>
						<!-- m_findID_mid_text -->



						<div class="m_findID_mid_input">
							<p>휴대폰 번호 입력</p>
							<input type="tel" name="tel" id="tel" 
								autofocus="autofocus" placeholder="00(0)-000(0)-000(0)"
								pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13"
								required />
						</div>
						<!-- m_findID_mid_input -->


						<div class="m_findID_mid_loginBtnBox">
							<input type="button" id="m_findID_btn" value="찾기">

						</div>
						<!-- m_findID_mid_loginBtn -->



						<div class="m_findID_mid_find">
							<a href="/beepPro/member/signup.do"><ins>회원가입</ins></a>
							 &nbsp;&nbsp; 
							<a href="/beepPro/member/findPwd.do"><ins>비밀번호 찾기</ins></a>
						</div>
						<!-- m_findID_mid_find  -->
					</div>
					<!-- m_findID_mid_left -->

				</div>
				<!-- m_findID_mid -->



			</div>
			<!-- m_findID_main -->
		</div>
		<!-- m_findID_box -->

	</section>
	<footer></footer>
	<script src="m13_findId.js?var=222"></script>
</body>
</html>