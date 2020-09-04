<%@page import="beepbeep.member.dto.LoginDTO"%>
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
<link rel="stylesheet" href="m8_infoChange.css?var=13402" type="text/css" charset="UTF-8" />
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
<form action="changeInfo.do" method="post">
	<header id="header"></header>
	<section id="m_infoC_section1">
		<div class="m_infoC_box">
			<div class="m_infoC_main">


					<div class="m_infoC_top1">
						<h1 class="m_infoC_top1_text">나의 정보 수정하기</h1>
					</div>
					<!--m_infoC_top1  -->
					
	


				<!-- 본격적으로 -->
				<div class="m_infoC_totalInfo ">

					<div class="m_infoC_basic">
						<h2 id="m_infoC_title">개인정보</h2>
						<br>
					</div>


					<!-- 입력 -->
					<div class="m_infoC_inputSub">
						<p class="m_infoC_label">회원사진</p>
						
						
						<div class="m_infoC_filebox">
							<input type="file" id="m_info_file" name="newPhoto"/> 
						<!-- 	<input class="upload-name" value="선택된 파일이 없음" /> -->
						</div>

						<br> 
						<br>
					</div>

	
					<%

					LoginDTO dto = (LoginDTO)request.getSession().getAttribute("authUser");
					%>
					
					<div class="m_infoC_inputSub">
						<p class="m_infoC_label">휴대폰번호</p>
						<p class="m_infoC_label2">(예:010-1234-5789)</p>

						
						<input type="tel" class="m_infoC_input" name="newPhone" title="휴대폰번호"  value= <%=dto.getPhone()%> ><br>
						
						<br>
					</div>





					<div class="m_infoC_inputSub">
						<p class="m_infoC_label">비밀번호</p>
						<input type="password" class="m_infoC_input" name = "newPwd1" id="m_infoC_pwText1"
							title="비밀번호" placeholder="영문, 숫자, 특수문자 포함하여 8자리 이상"> <br>
						<br>
					</div>



					<div class="m_infoC_inputSub">
						<p class="m_infoC_label">비밀번호 확인</p>
						<input type="password" class="m_infoC_input" name="newPwd2" id="m_infoC_pwText2"
							title="비밀번호 확인" placeholder="영문, 숫자, 특수문자 포함하여 8자리 이상"> <br>
						<br>
					</div>
					
					
					<div class="m_infoC_inputSub">
						<p class="m_infoC_label">닉네임</p>
						<input type="text" class="m_infoC_input"  name="newNick" title="닉네임"  id="m_infoC_nickname" value= <%=dto.getNickname()%>> <br>
						<br>
					</div>


				</div>
				<!-- m_infoC_totalInfo -->

				<div class="m_infoC_nextButton">
					<button id="m_infoC_button" >수정하기</button>
				</div>


				<!--  -->

			</div>
			<!-- m_infoC_main -->
		</div>
		<!-- m_infoC_box -->
		
		

	</section>


</form>



	<footer></footer>

	   
<script src="m8_infoChange.js">


</script>
</body>
</html>