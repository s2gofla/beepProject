<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m1_type.css?var=1327777" type="text/css" charset="UTF-8" />
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


</head>
<body>
	<header id="header"></header>
	<section id="m_type_section1">
		<div class="m_type_box">
			<div class="m_type_main">
				<div calss ="m_type_intro">
					<p id="singupText">회원가입</p>
					<p>가입을 진행하실 회원 종류를 선택해주세요.</p>
				</div>
				<div class="m_type_chooseBox">
					

					<div class="m_type_choice">
						<div class="m_type_doctor_img">
							<img alt="의사" src="<%= path %>/beep_images/member-images/doctor2.svg"
								class="m_type_doctor_img">
						</div>
						<div class="m_type_korean">
							<p>의사회원</p>
						</div>
						<div class="m_type_explain">
							<p>의사님의 지식이 많은 환자들에게</p>
							<p>도움이 될 것입니다.</p>
						</div>
						<div class="m_type_btn">
							<button class="m_type_checkBtn"  name = "m_type_checkBtn1" onclick="location.href = '<%= path %>/member/signup.do?ctype=1' ">의사회원 선택</button>
						</div>
					</div>
					<!-- choice -->
					
					
					<p>&nbsp;&nbsp;</p>
					
					<div class="m_type_choice">
						<div class="m_type_general_img">
							<img alt="일반" src="<%= path %>/beep_images/member-images/patient.svg"
								class="m_type_general_img">
						</div>
						<div class="m_type_korean">
							<p>일반회원</p>
						</div>
						<div class="m_type_explain">
							<p>많은 의사의 도움과 의학과 관련된</p>
							<p>정보를 제공받을 수 있습니다.</p>
						</div>
						<div class="m_type_btn">
							<button  class="m_type_checkBtn" name = "m_type_checkBtn1"onclick="location.href = '<%= path %>/member/signup.do?ctype=2' ">일반회원 선택</button>
						</div>
					</div>
					<!--choice  -->

					

				</div>
				<!--chooseBox  -->
			</div>
			<!--main  -->
		</div>
		<!--centerBox  -->

	</section>
	<footer></footer>
</body>
</html>