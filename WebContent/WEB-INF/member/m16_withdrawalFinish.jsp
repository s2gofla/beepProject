<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m16_withdrawalFinish.css?var=134027777" type="text/css" charset="UTF-8" />
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
	<section id="m_WF_section1">
		<div class="m_WF_box">
			<div class="m_WF_main">



				<div class="m_WF_totalMid">
					<div class="m_WF_borderMid">
					
					
						<div class="m_WF_img">
							<img class="m_WF_img" alt="07/22사진추가함"
								src="../beep_images/member-images/sad.svg">
						</div>



						<div class="m_WF_text">
							<h2 style="color: gray; ">회원 탈퇴 완료</h2>
							
						</div>



					</div>
					<!-- m_WF_borderMid -->

				</div>
				<!-- m_WF_totalMid -->




			</div>
			<!-- m_WF_main -->
		</div>
		<!-- m_WF_box -->
	</section>
	<footer></footer>

	
</body>
</html>