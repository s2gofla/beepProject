<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/admin_write.css?var=1222" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-글쓰기</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.html"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});
</script>
</head>
<body>
	<header id="header"></header>

	<div align="center">
		<p class="write">
			<img src="../beep_images/community-images/boardpencil.svg" alt="" />꿀팁 글쓰기
		</p>
		<!-- <form method="post"> -->
		<div class="contentBody">
			<div class="wrtieTitle">
				<input type="text" placeholder="제목을 입력해주세요." name="maintitle" />
			</div>


			<div class="writecontent">
				<textarea placeholder="내용을 입력해주세요."></textarea>
			</div>

			

			<div class="btns">
				<input type="submit" value="작성 완료">
				<input type="reset" value="다시 작성" >
				<input type="button" value="목록" >
			</div>
		</div>
		<!-- contentBody -->
		<!-- 
		<table style="padding: 2px; width: 600px">
			<tr>
				<td colspan="2" align="center"><b>글을 적어주세요</b></td>
			</tr>
			<tr>
				<td align="center">이름</td>
				<td><input type="text" name="writer" size="15"
					autofocus="autofocus" required="required"></td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" name="pwd" size="15" required="required"></td>
			</tr>
			<tr>
				<td align="center">Email</td>
				<td><input type="email" name="email" size="50" ></td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td><input type="text" name="title" size="50" required="required"></td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td><textarea name="content" cols="50" rows="10"></textarea></td>
			</tr>
			<tr>
				<td align="center">HTML</td>
				<td><input type="radio" name="tag" value="1" checked>적용
					<input type="radio" name="tag" value="0">비적용</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				  <input type="submit" value="작성 완료" >
				  &nbsp;&nbsp;&nbsp; 띄어쓰기
				  <input type="reset" value="다시 작성">
				  &nbsp;&nbsp;&nbsp; 
				  <a href="list.jsp">Home</a>
				</td>
			</tr>
		</table>
		 -->

		<!-- </form> -->
	</div>

<script>
</script>
	<div id="footer"></div>
</body>
</html>