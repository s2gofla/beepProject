<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m14_findPwd.css?var=177888" type="text/css" charset="UTF-8" />
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
	<section id="m_findPWD_section1">
		<div class="m_findPWD_box">
			<div class="m_findPWD_main">

				<!-- 맨위 -->
				<div class="m_findPWD_top">
					<div class="m_findPWD_top_text">
						<h1 class="h1_text">비밀번호 찾기</h1>
						<hr width="100%" color="#E06666" size="2">
					</div>
					<!-- m_findPWD_top_text -->
				</div>
				<!-- m_findPWD_top -->


				<!-- 중간 -->
				<div class="m_findPWD_mid">
					<div class="m_findPWD_mid_left">
						<div class='m_findPWD_mid_text'></div>
						<!-- m_findPWD_mid_text -->
						
						

						<div class="m_findPWD_mid_input">
								
							<p>이름 입력</p>
							<input type="text" name ="name" id="name" autofocus="autofocus" required />
							<p>생년월일 입력</p>
							<input type="date" max="9999-12-31"name="birth" id="birth" required/>
							<p>아이디 입력</p>
							<input type="text" name="id" id="id" required/>
							
						</div>
						<!-- m_findPWD_mid_input -->

					
					

						<div class="m_findPWD_mid_loginBtnBox">
							<input type="button" id="m_findPWD_btn"  value="찾기" >
						</div>		
						<!-- m_findPWD_mid_loginBtn -->



						<div class="m_findPWD_mid_find">
							<a href="/beepPro/member/signup.do"><ins>회원가입</ins></a> 
							&nbsp;&nbsp; 
							<a href="/beepPro/member/findId.do"><ins>아이디 찾기</ins></a>
						</div>
						<!-- m_findPWD_mid_find  -->
					</div>
					<!-- m_findPWD_mid_left -->

				</div>
				<!-- m_findPWD_mid -->



			</div>
			<!-- m_findPWD_main -->
		</div>
		<!-- m_findPWD_box -->

	</section>
	<footer></footer>
	<script src="m14_findPwd.js?var=22233377777"></script>
</body>
</html>