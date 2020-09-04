<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="community-style/write.css?var=1222" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-삭제하기</title>
<% String result = request.getAttribute("result")==null? "-1":String.valueOf(request.getAttribute("result"));
%>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});
</script>
<script>
	$(function(){
		var result = "<%= result %>";
		if(<%= result %> == '0'){
			alert("글삭제 실패");
		}
	})
</script>
<style>
input.passwd {
    background-color: lightgoldenrodyellow;
    font-family:  sans-serif;
}
</style>

</head>
<body>
	<header id="header"></header>

	<div align="center">
		<p class="write">
			<img src="../beep_images/community-images/delete.svg" alt="" />삭제하기
		</p>
		<form method="post">
		<div class="contentBody">
			<div class="delete">
				<input type="text" value="비밀번호 입력" />
				<input type="password" placeholder="비밀번호를 입력해주세요." class="passwd" name="passwd"/>
			</div>
	
			<div class="btns">
				<input type="submit" value="삭제">
				<input type="reset" value="취소">
			</div>
		</div>
	</form>
	</div>





<script>
</script>
	<div id="footer"></div>
</body>
</html>