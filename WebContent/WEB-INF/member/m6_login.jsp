<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m6_login.css?var=17788888999" type="text/css" charset="UTF-8" />
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
							<h2 id="h2">
								삐---용! <strong>로그인</strong>
							</h2>
						</div>
						<!-- m_login_mid_left_text -->
               <form action="/beepPro/member/login.do" method="post">
                     <%
                        /* String msg = (String) request.getAttribute("errcode"); */
                        Cookie[] c = request.getCookies();
                        String cookieVal = "";
                        if (c != null) {
                           for (Cookie i : c) {
                              if (i.getName().equals("checkbox")) {
                                 cookieVal = i.getValue();
                              }
                           }
                        }
                     %>

                     <div class="m_login_mid_left_input">
                        <input type="text" name="id" id="m_login_id"
                           value="<%=cookieVal != "" ? cookieVal : ""%>" placeholder="아이디"
                           autofocus="autofocus" required /> <input type="password"
                           name="password" id="m_login_pwd" placeholder="비밀번호" required />
                     </div>
                     <!-- m_login_mid_left_input -->

                     <div class="m_login_mid_left_check">
                        <!-- <label class="m_login_checkbox" for="m_login_idSaveCheck"> -->
                           <input type="checkbox" name="checkbox" 
                           id="m_login_idSaveCheck" <%=cookieVal != "" ? "checked" : "" %> />

							<span class="label">아이디 저장</span>
								<c:if test="${ errcode eq 1 }">
									<span class="error-msg">※ 등록되지 않거나 틀린 아이디입니다.</span>
								</c:if>
								<c:if test="${errcode eq 2 }">
									<span class="error-msg">※ 틀린 비밀번호 입니다.</span>
								</c:if>
						</div>
						<!-- m_login_mid_left_check -->

						<div class="m_login_mid_left_loginBtnBox">
							<input type="submit" id="m_login_mid_left_loginBtn"  value="로그인">
						</div>
						</form>
						<!-- m_login_mid_left_loginBtn -->

						<div class="m_login_mid_left_find">
							<a href="/beepPro/member/signup.do"><ins>회원가입</ins></a> 
							&nbsp;&nbsp; 
							<a href="/beepPro/member/findId.do"><ins>아이디 찾기</ins></a> 
							&nbsp;&nbsp; 
							<a href="/beepPro/member/findPwd.do"><ins>비밀번호 찾기</ins></a>
						</div>
						<!-- m_login_mid_left_find  -->
					</div>
					<!-- m_login_mid_left -->

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