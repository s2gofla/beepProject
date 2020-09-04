<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="cs_style/cs_write.css?var=1222"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-글쓰기</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});
</script>
</head>
<body>
	<header id="header"></header>

	<div align="center">
		<p class="write">
			<img src="../beep_images/community-images/boardpencil.svg" alt="" />FAQ
			글쓰기
		</p>
		<form method="post">
			<div class="contentBody">
				
				<div class="wrtieTitle">
					<input type="text" placeholder="제목을 입력해주세요." name="title"
						class="faq_title" value="${dto.title }" />
				</div>


				<div class="writecontent">
					<textarea placeholder="내용을 입력해주세요." name="contents">${dto.contents}</textarea>
				</div>


				<input type="hidden" name="title" value="${ param.title }">
				<input type="hidden" name="contents" value="${ param.contents }">
				<div class="btns">
					<input type="submit" value="작성 완료"> <input type="reset"
						value="다시 작성"> <input type="button" value="홈으로"
						onclick="location.href='cs_faq_list.do'">
				</div>
			</div>
		</form>
	</div>

	<script>
		
	</script>
	<div id="footer"></div>
</body>
</html>