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
<link rel="stylesheet" href="m4_info.css?var=13402777777888" type="text/css" charset="UTF-8" />
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
	String pccode = request.getParameter("pccode");
	
%>


</head>
<body>
	<header id="header"></header>
	<section id="m_infoG_section1">
		<div class="m_infoG_box">
			<div class="m_infoG_main">


				
					<div class="m_infoG_top1">
						<h1 class="m_infoG_top1_text">회원가입</h1>
					</div>
					<!--m_infoG_top1  -->

					<div class="m_infoG_top2">
						<!-- 순서도 숫자  시작-->

						<!-- 1 -->
						<div class="m_infoG_numSub">
							<div class="m_infoG_top2_numCircle">
								<p id=m_infoG_num>1</p>
							</div>
							<div class="m_infoG_numBottonText">약관동의</div>
						</div>
						<!--m_infoG_numSub  -->


						<!-- 2 -->
						<div class="m_infoG_numSub">
							<div class="m_infoG_top2_numCircle">
								<p id=m_infoG_num>2</p>
							</div>
							<div class="m_infoG_numBottonText">본인인증</div>
						</div>
						<!--m_infoG_numSub"  -->


						<!-- 3 -->
						<div class="m_infoG_numSub">
							<div class="m_infoG_top2_numCircle3">
								<p id=m_infoG_num>3</p>
							</div>
							<div class="m_infoG_numBottonText">[ 정보입력 ]</div>
						</div>
						<!--m_infoG_numSub"  -->


						<!-- 4 -->
						<div class="m_infoG_numSub">
							<div class="m_infoG_top2_numCircle">
								<p id=m_infoG_num>4</p>
							</div>
							<div class="m_infoG_numBottonText">가입완료</div>


						</div>
						<!--m_infoG_numSub -->
						<!-- 순서도 숫자 끝 -->


					</div>
					<!-- m_infoG_top2 -->

				

		<form method="post" name="userInfo" onsubmit="return checkValue();" enctype="multipart/form-data" >
				<!-- 본격적으로 -->
				<input type="hidden" name="ctype" value="<%= ctype %>" />
				<input type="hidden" name="mcode" value="<%= mcode %>" />
				<input type="hidden" name="gcode" value="<%= gcode %>" />
				<input type="hidden" name="pccode" value="<%= pccode %>" />
				<div class="m_infoG_totalInfo ">
					<div class="m_infoG_basic">
						<h2 id="m_infoG_title">개인정보</h2>
						<br>
					</div>

					
					<!-- 입력 -->
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">회원사진</p>

						<div class="m_infoD_filebox">
							<input type="file" multiple="multiple" id="m_info_file" name="photo" /> 
						<!-- 	<input class="upload-name" value="선택된 파일이 없음" /> -->
						</div>
						
						<br>	
					</div>
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 아이디</p>
						<input type="text" class="m_infoG_input" id="m_infoG_id" name="id" title="아이디" required>
						<span id="id-text1" class="id-text" style="color:red;"  >※  아이디는 숫자와 영소문자만 가능합니다.</span>
						<span id="id-text2" class="id-text" style="color:blue;"> ID 사용 가능 </span>
						<br />
						<button class="m_infoG_buttons" id="m_infoG_checkID" style="display:inline; margin: 7px 15px 10px 0;">중복체크</button>
						<input type="button" class="m_infoG_buttons" id="m_info_editId" style="display: none; margin: 7px 15px 10px 0;" value="아이디수정">
						<br>
					</div>
					
					
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 비밀번호</p> 
						<input type="password" class="m_infoG_input" id="m_infoG_pwdText1" name="password" title="비밀번호" disabled required placeholder="영문, 숫자, 특수문자 포함하여 8자리 이상"> 
						 <br>
						<br>
					</div>
					
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 비밀번호 확인</p>
						<input type="password" class="m_infoG_input" id="m_infoG_pwdText2" name="passwordCheck" disabled required title="비밀번호 확인" placeholder="영문, 숫자, 특수문자 포함하여 8자리 이상"> <br>
						<br>
					</div>
			
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 닉네임</p>
						<input type="text" class="m_infoG_input"  title="닉네임" disabled required id="m_infoG_nickname" name="nickname">
						<span id="nickname-text1" class="id-text" style="color:blue;"> 닉네임 사용 가능 </span>
						<br />
						<button class="m_infoG_buttons" id="m_infoG_checkNickname" style="display:inline; margin: 7px 15px 10px 0;">중복체크</button>
						<input type="button" class="m_infoG_buttons" id="m_info_editNickname" style="display: none; margin: 7px 15px 10px 0;" value="닉네임수정"> <br>
						<br>
					</div>
										
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 성명</p>
						<input type="text"  class="m_infoG_input" title="성명" disabled required id="m_infoG_name" name="name" autofocus="autofocus"> <br>
						
						<br>
					</div>

					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 생년월일</p>
						<input type="date" min="1900-01-01" max="9999-12-31" class="m_infoG_input" id="m_infoG_birth" disabled required  name = "birth"  title="생년월일"> <br>
						<br>
					</div>
					
					
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 성별</p>
						<div>
						<!-- <p id="m_infoG_S">남자</p> -->
						<input type="radio" class="m_infoG_input2"  title="남자"  name="sex" required  value="남자">남자 &nbsp;
						
						<!-- <p id="m_infoG_S">여자</p> -->
						<input type="radio" class="m_infoG_input2" title="여자" name="sex"  value="여자"> 여자
						
						</div>
						<br>
						<br>
					</div>
					
					
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 휴대폰번호</p>
						<p class="m_infoG_label2">(예:010-1234-5789)</p>
						<input type="text" class="m_infoG_input" id="m_infoG_phone" name="phone" readonly required title="휴대폰번호" value="${ tel }"> <br>
						<br>
					</div>
					
					<div class="m_infoG_inputSub">
						<p class="m_infoG_label">* 이메일</p>
							<input type="text" class="m_infoG_input" name="email1" id="email1" name="email1"  title="이메일 아이디" disabled required>	@
							<input type="text" class="m_infoG_input" name ="email2" id="email2" name="email2" title="이메일 도메인" disabled required >
							<select class="m_infoG_select" name="email3" id="email3" title="이메일 도메인"
											onchange="this.form.email2.value=this.value">
								<option value="">이메일선택</option>
								<option value="naver.com">naver.com</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="daum.net">daum.net</option>
								<option value="nate.com">nate.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="hotmail.com">hotmail.com</option>
								<option value="yahoo.com">yahoo.com</option>
								<option value="dreamwiz.com">dreamwiz.com</option>
							</select>
							<br>

						<br>
					</div>
					
					<c:if test="${ param.ctype eq '1'}">

						<div class="m_infoD_inputSub">
							<p class="1">* 의사면허번호</p>
							<input type="password" class="m_infoD_input" id="m_infoD_licenseNo" required disabled name="licenseNo" title="의사면허번호"> <br> <br>
						</div>
	
	
						<div class="m_infoD_inputSub">
							<p class="m_infoD_label">* 증빙자료 제출</p>
							<p class="m_infoD_label2"> (의사면허등록증)</p>
							<div class="m_infoD_filebox">
								<input type="file" id="m_info_lfile" multiple="multiple" required disabled name="licenseFile" /> 
							<!-- 	<input class="upload-name" value="선택된 파일이 없음" /> -->
							</div>
						</div>					
					
					</c:if>
				<div class="m_infoG_nextButton">
					<input type="submit" id="m_infoG_button"/>
				</div>

				</div>
				<!-- m_infoG_totalInfo -->
			</form>

				<!--  -->

			</div>
			<!-- m_infoG_main -->
		<!-- m_infoG_box -->
	</section>


	<footer></footer>

<script src="m4_info.js?var=234077777888889999">


</script>
</body>
</html>