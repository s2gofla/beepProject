
<!-- GPC : General Phone Check -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m11_clauseChangePWD.css?var=13402" type="text/css" charset="UTF-8" />
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
	<section id="m_GPC_section1">
		<div class="m_GPC_box">
			<div class="m_GPC_main">


				


				
				<div class="m_GPC_totalMid">
					<div class="m_GPC_borderMid">
					
						<div class="m_GPC_content">
							
							<div class="m_GPC_text">
								<h2 class="m_GPC_h2">약관 수정</h2>
								<p class="m_GPC_p1">비밀번호 입력</p> 
								<div class="m_GPC_text2">
									<input type="password" class="m_GPC_input" autofocus="autofocus">
									
								</div><!-- m_GPC_text2 -->
							</div>
							
							</div><!-- m_GPC_content -->
							
							<div class="m_GPC_bottom">
								<button class="m_GPC_button1" onclick="location.href='../memeber/m9_clauseChange.jsp'">확인</button>
							</div>	<!-- m_GPC_bottom -->
												
						
					</div><!-- m_GPC_borderMid -->    
				
				</div><!-- m_GPC_totalMid -->




			</div>
			<!-- m_GPC_main -->
		</div>
		<!-- m_GPC_box -->
	</section>

	<footer></footer>
</body>
</html>