<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link  href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

.m_login_box {
	background: #e7e6e657;
	width: 100%;
	height: 500px;
	display: flex;
	justify-content: center
}

.m_login_main {
	width: 40%;
	height: 82%;
}

.h1_text {
	font-size: 37px;
}



/* ///////////////////////////////////////////////////// */


.m_login_mid {
	margin-top: 30px;
	display: flex;
	justify-content: center;
}



.m_login_mid_left {
	
	border: solid 1px  #e7e6e657;
	border-right-color:  #E06666;
	display: flex;
	flex-direction: column;

}

.m_login_mid_left_text {
	padding-left: 80px;
	padding-right: 80px;
}

.m_login_mid_left_input {
	display: flex;
	flex-direction: column;
	align-items: center;
}

#m_login_id {
	width: 200px;
	height: 35px;
	margin-bottom: 5px;
}

#m_login_pwd {
	width: 200px;
	height: 35px;
}


.m_login_mid_left_check {
	padding-left: 56px;
}

.m_login_mid_left_loginBtnBox {
	margin-top: 20px; 
	justify-content: center;
	display: flex;
	
	
}

#m_login_mid_left_loginBtn {
	width: 120px;
	height: 40px;
	color: black;
	border: solid 1px rgb(224,102,102);
	background-color: rgb(224,102,102); 
	font-size: 15px;
	
	border-radius: 3px;
}

.m_login_mid_left_find {
	margin-top: 10px;
	margin-bottom: 10px; 
	justify-content: center;
	display: flex;
}


/*         ///////////////////////////////////              */



.m_login_mid_right {
	border: solid 1px  #e7e6e657;
	border-left-color: #E06666;
	display: flex;
	flex-direction: column;
}

.m_login_mid_right_text {
	padding-left: 80px;
	padding-right: 80px;
}
.m_login_mid_right_choose{
	display: flex;
	align-items: center;
	flex-direction: column;
	margin-top: 38px;
}

.m_login_mid_right_choose_naver{
	display: flex;	
	background-color: white;
	width: 250px;
	height: 50px;
	justify-content: center;
	margin-bottom: 5px;
	
	border-radius: 3px;
}

.m_login_naver_login{
	margin: 10px;
}

.m_login_naver_img{
	display: flex;	
	align-items: center;
}

#naver_img{

	width: 40px;
	height: 40px;
}

.m_login_mid_right_choose_kakao{
	display: flex;	
	background-color: white;
	width: 250px;
	height: 50px;
	justify-content: center;
	
	border-radius: 3px;
}

.m_login_kakao_login{
	margin: 10px;
}

.m_login_kakao_img{
	display: flex;	
	align-items: center;
}

#kakao_img{

	width: 45px;
	height: 45px;
}
</style>
</head>



</head>
<body>
<script>

function checkValue()
{
    inputForm = eval("document.loginInfo");
    if(!inputForm.login_id.value)
    {
        alert("아이디를 입력하세요");    
        inputForm.id.focus();
        return false;
    }
    if(!inputForm.login_pwd.value)
    {
        alert("비밀번호를 입력하세요");    
        inputForm.password.focus();
        return false;
    }
}



</script>
	<header id="header"></header>
	<section id="m_login_section1">
		<div class="m_login_box">
			<div class="m_login_main">

				<!-- 맨위 -->
				<div class="m_login_top">
					<div class="m_login_top_text">
						<h1 class="h1_text">로그인</h1>
						<hr width="100%" color="#E06666" size="2">
					</div>
					<!-- m_login_top_text -->
				</div>
				<!-- m_login_top -->


				<!-- 중간 -->
				<div class="m_login_mid">
					<div class="m_login_mid_left">
						<div class='m_login_mid_left_text'>
							<h2>
								삐---용! <strong>로그인</strong>
							</h2>
						</div>
					<form action="/beepPro/member/login.do" method="post" name="loginInfo" onsubmit="return checkValue()">
						<!-- m_login_mid_left_text -->
						<div class="m_login_mid_left_input">
							<input type="text" name="login_id" id="login_id" placeholder="아이디" autofocus="autofocus" /> 
							<input type="password" name="login_pwd" id="login_pwd" placeholder="비밀번호" />
						</div>
						<!-- m_login_mid_left_input -->

						<div class="m_login_mid_left_check">
							<label class="m_login_checkbox" for="m_login_idSaveCheck">
								<input type="checkbox" id="m_login_idSaveCheck" /> 
								<span class="label">아이디 저장</span>
							</label>
						</div>
						<!-- m_login_mid_left_check -->

						<div class="m_login_mid_left_loginBtnBox">
							<input type="submit" id="m_login_mid_left_loginBtn" value="로그인">
						</div>
						<!-- m_login_mid_left_loginBtn -->
					</form>
						<div class="m_login_mid_left_find">
							<a href=""><ins>회원가입</ins></a> 
							&nbsp;&nbsp; 
							<a href=""><ins>아이디 찾기</ins></a> 
							&nbsp;&nbsp; <a href=""><ins>비밀번호 찾기</ins></a>
						</div>
						<!-- m_login_mid_left_find  -->
					</div>
					<!-- m_login_mid_left -->













					<div class="m_login_mid_right">
						<div class='m_login_mid_right_text'>
							<h2>
								SNS계정 <strong>로그인</strong>
							</h2>
						</div>
						<!-- m_login_mid_right_text -->
						
						<div class="m_login_mid_right_choose">
							<div class="m_login_mid_right_choose_naver">
								<div class="m_login_naver_img">
								<img id= "naver_img" alt="네이버 아이콘" src="../beep_images/member-images/naver_img.PNG" >
								</div><!-- m_login_naver_img -->
									
								<div class="m_login_naver_login">
									<a href="" >네이버 계정으로 로그인</a>
								</div><!-- m_login_naver_login -->
								
							</div>
							<!-- m_login_mid_right_choose_naver -->
							
							<div class="m_login_mid_right_choose_kakao">
								<div class="m_login_kakao_img">
								<img id="kakao_img" alt="카카오 아이콘" src="../beep_images/member-images/kakao_img.png">
								</div><!-- m_login_kakao_img -->
								
								<div class="m_login_kakao_login">
								<a href="" >카카오 계정으로 로그인</a>
								</div>
								<!-- m_login_kakao_login -->
							</div>
							<!-- m_login_mid_right_choose_kakao -->						
						</div>
						<!-- m_login_mid_right_choose -->
					</div>
					<!-- m_login_mid_right -->


				</div>
				<!-- m_login_mid -->



			</div>
			<!-- m_login_main -->
		</div>
		<!-- m_login_box -->
	</section>
	<footer></footer>
</body>
</html>