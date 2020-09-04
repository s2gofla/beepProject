<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="disease.css?var=1330" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {
	$("#headers").load("../layout/header.html");  // 외부 헤더 삽입
	$("footer").load("../layout/footer.html");  // 외부 푸터 삽입
});
</script>
</head>
<body>
<div id="headers"></div>
<!---------------------------------------------------------------------------------------------->
 
 

<div id="all">
	<div id="content">
		<div class="section_wrap ">
			
	 
			<div class="headword_title"> 				
				<h2 class="headword">
				<img src="../beep_images/inf_images/dis.jpg" alt="">${dto.d_name}
				</h2>
			</div>
			
 		<div id="inf">
			<ul>				 
				<li class="inf_li"><h3 class="d_title">정의</h3></li>
				<p class="d_content">${ dto.definition}</p>
				<li class="inf_li"><h3 class="d_title">원인</h3></li>
				<p class="d_content">${dto.cause }</p>
				<li class="inf_li"><h3 class="d_title">증상 상세내용</h3></li>
				<p class="d_content">${dto.symptom }</p>
				<li class="inf_li"><h3 class="d_title">진단&치료</h3></li>
				<p class="d_content">${dto.diagnosis_treatment }</p>
				<li class="inf_li"><h3 class="d_title">예방방법</h3></li>
				<p class="d_content">${dto.prevention}</p>
			</ul>
			</div>   
			 
			
			<button class="back_btn" onclick="history.back()">뒤로 가기</button>
		</div>
		
	</div>
	
</div>


<!---------------------------------------------------------------------------------------------->
<footer></footer>
<script type="text/javascript" src="layout-js.js"></script><!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>