
<!-- GPC : General Phone Check -->

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
<link rel="stylesheet" href="m11_changePWD.css?var=13402787878" type="text/css" charset="UTF-8" />
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
					<form action ="/beepPro/member/changeCheck.do?change=${c }" method="post" >
						<div class="m_GPC_content">
							
							<div class="m_GPC_text">
							<c:if test="${c eq 1 }">
								<h2 class="m_GPC_h2">정보수정</h2>
							</c:if>
							
							<c:if test="${c eq 2 }">
								<h2 class="m_GPC_h2">약관 수정</h2>
							</c:if>
							
								 <p class="m_GPC_p1">비밀번호 입력</p> 
								 
								<div class="m_GPC_text2">
									<input type="password" class="m_GPC_input" name="curPwd1" id="curPwd1" autofocus="autofocus">
									<div id="cif">
									<c:if test="${errors.badCurPwd}" ><span>암호가 불일치 합니다</span></c:if>
									</div>

								</div><!-- m_GPC_text2 -->
							</div>
							
							</div><!-- m_GPC_content -->
							
							<div class="m_GPC_bottom">
								<input type="submit" value="확인" id = "m_CPB" class="m_GPC_button1" disabled="true">
							</div>	<!-- m_GPC_bottom -->
							</form>					
						
					</div><!-- m_GPC_borderMid -->    
				
				</div><!-- m_GPC_totalMid -->




			</div>
			<!-- m_GPC_main -->
		</div>
		<!-- m_GPC_box -->
	</section>

	<footer></footer>
<script>
$(function () {
	$("#curPwd1").on('input',function(){
		if($("curPwd1").val() == ''){
		$("#m_CPB").attr("disabled",true);
		
	}
	else{
		$("#m_CPB").attr("disabled",false);
		$("#m_CPB").css("background-color","rgb(224,102,102)");
	}
	})
})





</script>
</body>
</html>