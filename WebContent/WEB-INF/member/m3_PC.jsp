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
<link rel="stylesheet" href="m3_PC.css?var=137778888888" type="text/css"
	charset="UTF-8" />
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

<%
	String ctype = request.getParameter("ctype");
	String mcode = request.getParameter("mcode");
	String gcode = request.getParameter("gcode");
%>
</head>
<body>
	<header id="header"></header>
	<section id="m_GPC_section1">
		<div class="m_GPC_box">
			<div class="m_GPC_main">


				<div class="m_GPC_totalTop">
					<div class="m_GPC_top1">
						<h1 class="m_GPC_top1_text">회원가입</h1>
					</div>
					<!--m_GPC_top1  -->

					<div class="m_GPC_top2">
						<!-- 순서도 숫자  시작-->

						<!-- 1 -->
						<div class="m_GPC_numSub">
							<div class="m_GPC_top2_numCircle">
								<p id=m_GPC_num>1</p>
							</div>
							<div class="m_GPC_numBottonText">약관동의</div>
						</div>
						<!--m_GPC_numSub  -->


						<!-- 2 -->
						<div class="m_GPC_numSub">
							<div class="m_GPC_top2_numCircle2">
								<p id=m_GPC_num>2</p>
							</div>
							<div class="m_GPC_numBottonText">[ 본인인증 ]</div>
						</div>
						<!--m_GPC_numSub"  -->


						<!-- 3 -->
						<div class="m_GPC_numSub">
							<div class="m_GPC_top2_numCircle">
								<p id=m_GPC_num>3</p>
							</div>
							<div class="m_GPC_numBottonText">정보입력</div>
						</div>
						<!--m_GPC_numSub"  -->


						<!-- 4 -->
						<div class="m_GPC_numSub">
							<div class="m_GPC_top2_numCircle">
								<p id=m_GPC_num>4</p>
							</div>
							<div class="m_GPC_numBottonText">가입완료</div>
						</div>
						<!--m_GPC_numSub -->



						<!-- 순서도 숫자 끝 -->
					</div>
					<!-- m_GPC_top2 -->

				</div>
				<!-- m_GPC_totalTop -->



				<div class="m_GPC_totalMid">
					<div class="m_GPC_borderMid">
						<div class="m_GPC_content">
							<div class="m_GPC_img">
								<img id="m_GPC_phoneImg" alt="폰사진"
									src="../beep_images/member-images/phone.svg">
							</div>
							
							<div class="m_GPC_text">
								<h2 class="m_GPC_h2">휴대폰번호 인증</h2>
								<p class="m_GPC_p1">휴대폰번호</p>
								<div class="m_GPC_text2">
									<input type="tel" name="tel" id="tel" title="전화번호를 입력하세요."
										placeholder="00(0)-000(0)-000(0)"
										pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13">
									<button class="m_GPC_button1">중복확인</button>
								</div>
								<!-- m_GPC_text2 -->
							</div>
						</div>
						<!-- m_GPC_content -->
						<input type="hidden" name="ctype" value="<%= ctype %>" />
						<input type="hidden" name="mcode" value="<%= mcode %>"/>
						<input type="hidden" name="gcode" value="<%= gcode %>" />
						<div class="m_GPC_bottom">
							<button  class="m_GPC_button2" id="m_GPC_button2" disabled >다음</button>
						</div>
						<!-- m_GPC_bottom -->
					</div>
					<!-- m_GPC_borderMid -->

				</div>
				<!-- m_GPC_totalMid -->

			</div>
			<!-- m_GPC_main -->
		</div>
		<!-- m_GPC_box -->
	</section>

	<footer></footer>
	<script src="m3_PC.js?var=0127777"></script>
</body>
</html>