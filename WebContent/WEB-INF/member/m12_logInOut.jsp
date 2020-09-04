<%@page import="com.util.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m12_logInOut.css?var=13402" type="text/css" charset="UTF-8" />
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {
		$("#header").load("<%= path %>/layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("footer").load("<%= path %>/layout/footer.html");
	});
</script>
<%
String id= request.getParameter("id");
String password = request.getParameter("password");
%>
</head>
<body>

	<header id="header"></header>
	<section id="m_InOut_section1">
		<div class="m_InOut_box">
			<div class="m_InOut_main">



				<div class="m_InOut_totalMid">
					<div class="m_InOut_borderMid">
						<div class="m_InOut_img">
							<img class="m_InOut_img" alt="고고"
								src="<%= path %>/beep_images/member-images/lets-go2.svg">
						</div>

						<div class="m_InOut_text">
							<c:if test="${!empty authUser}">
								<p id="m_InOut_Hi">${authUser.nickname}님, 안녕하세요.<p>
								<input type="button" class="m_InOut_button" value="메인으로 가기" onclick="location.href='<%= path %>/main/main.do'" />
								<input class = "m_InOut_button" type="button" value="로그아웃하러 가기" onclick="location.href='<%= path %>/member/logout.do'">
							</c:if>
		
							<c:if test="${empty authUser}">
								<input type="button" class="m_InOut_button" value="메인으로 가기" onclick="location.href='<%= path %>/main/main.do'" />
								<input class = "m_InOut_button" type="button" value="로그인하러 가기" onclick="location.href='<%= path %>/member/login.do'">
							</c:if>
							<input type="button" class="m_InOut_button" value="이전으로 돌아가기" onclick="history.back();" />
						</div>

					</div>
					<!-- m_InOut_borderMid -->

				</div>
				<!-- m_InOut_totalMid -->




			</div>
			<!-- m_InOut_main -->
		</div>
		<!-- m_InOut_box -->
	</section>
	<footer></footer>
</body>
</html>