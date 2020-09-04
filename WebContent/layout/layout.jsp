<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="../layout/layout-style.css?var=132" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<title>PPIYONPPIYONG</title>
<script>
$(document).ready( function() {

	$("#headers").load("header.html");  // 외부 헤더 삽입
	$("footer").load("footer.html");  // 외부 푸터 삽입
});
</script>
</head>
<body>
<div id="headers"></div>

<div class="layout-main">
	<div class="layout-left-block">
		<div class="layout-lefttop">
		
		</div>
	</div>
	<div class="layout-right-block">
	
	</div>
</div>
<img src="/beepPro/information/inf_images/1_1.jpg" alt="" />
<footer></footer>
<script type="text/javascript" src="layout-js.js"></script><!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>