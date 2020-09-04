<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m8_2_infoGeneralChange.css?var=13402777" type="text/css" charset="UTF-8" />
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
	<section id="m_infoGC_section1">
		<div class="m_infoGC_box">
			<div class="m_infoGC_main">


				
					<div class="m_infoGC_top1">
						<h1 class="m_infoGC_top1_text">나의 정보 수정하기</h1>
					</div>
					<!--m_infoGC_top1  -->

					



				<!-- 본격적으로 -->
				<div class="m_infoGC_totalInfo ">

					<div class="m_infoGC_basic">
						<h2 id="m_infoGC_title">개인정보</h2>
						<br>
					</div>


					<!-- 입력 -->
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">회원사진</p>

						<div class="m_infoD_filebox">
							<input type="file" id="m_info_file" /> 
						<!-- 	<input class="upload-name" value="선택된 파일이 없음" /> -->
						</div>
						
						<br>	
					</div>
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">성명</p>
						<input type="text" class="m_infoGC_input" title="성명" autofocus="autofocus"> <br>
						<br>
					</div>

					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">생년월일</p>
						<p class="m_infoGC_label2">(예:960507)</p>
						<input type="text" class="m_infoGC_input" title="생년월일"> <br>
						<br>
					</div>
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">성별</p>
						<input type="text" class="m_infoGC_input"  title="성별"  id="m_infoGC_sex" placeholder="남/여로 기입"> <br>
						<br>
					</div>
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">휴대폰번호</p>
						<p class="m_infoGC_label2">(예:010-1234-5789)</p>
						<input type="text" class="m_infoGC_input" title="휴대폰번호"> <br>
						<br>
					</div>
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">이메일</p>

						

						<form>
							<input type="text" class="m_infoGC_input" name="email1" title="이메일 아이디">
							<span class="m_infoGC_inputEmail_goalbang">@</span> 
							<input type="text" class="m_infoGC_input" name ="email2" title="이메일 도메인" disabled="disabled">
							<select class="m_infoGC_select" name="email3" id="email3" title="이메일 도메인"
											onchange="this.form.email2.value=this.value">
								<option value="">이메일선택</option>
								<option value="naver.com">naver.con</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="daum.net">daum.net</option>
								<option value="nate.com">nate.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="hotmail.com">hotmail.com</option>
								<option value="yahoo.com">yahoo.com</option>
								<option value="dreamwiz.com">dreamwiz.com</option>
								
							</select>
							<br>
							
						</form>
						<br>
					</div>
					
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">아이디</p>
						<input type="text" class="m_infoGC_input" id="m_infoGC_id" title="아이디" > <br>
						<button class="m_infoGC_buttons" name="m_infoGC_checkID" id="m_infoGC_checkID">중복체크</button>
						<br>
						<br>
					</div>
					
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">비밀번호</p>
						<input type="password" class="m_infoGC_input" id="m_infoGC_pwText1" title="비밀번호" placeholder="영문, 숫자, 특수문자 포함하여 8자리 이상"> <br>
						<br>
					</div>
					
					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">비밀번호 확인</p>
						<input type="password" class="m_infoGC_input" id="m_infoGC_pwText2" title="비밀번호 확인" placeholder="영문, 숫자, 특수문자 포함하여 8자리 이상"> <br>
						<br>
					</div>
					

					<div class="m_infoGC_inputSub">
						<p class="m_infoGC_label">닉네임</p>
						<input type="text" class="m_infoGC_input"  title="닉네임"  id="m_infoGC_nickname" > <br>
						<br>
					</div>
					
					
				</div>
				<!-- m_infoGC_totalInfo -->

				<div class="m_infoGC_nextButton">
					<button id="m_infoGC_button">수정하기</button>
				</div>


				<!--  -->

			</div>
			<!-- m_infoGC_main -->
		</div>
		<!-- m_infoGC_box -->
	</section>


	<footer></footer>
</body>
</html>