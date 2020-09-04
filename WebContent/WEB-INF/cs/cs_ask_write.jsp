<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="cs_style/cs_write.css?var=1222" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
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
		</p>
	<form method="post" onsubmit="return checkValue();" name="ask_write">
		<div class="contentBody">
			<div class="wrtieTitle">
				<input type="text" placeholder="제목을 입력해주세요." name="title" />
				<input type="text" placeholder="${authUser.id }" value="${authUser.id }" name="id" readonly="readonly">
			</div>
			<div>
					<select name="qtype_seq"  id="qtype_seq"  onchange="SetSelectBox();">
						<option value="1">로그인</option>
						<option value="2">회원가입</option>
						<option value="3">병원/의사회원신청</option>
						<option value="4">리뷰</option>
						<option value="5">기타</option>
					</select> 
			</div>
		
			<div class="writecontent">
				<textarea placeholder="내용을 입력해주세요." name="contents"></textarea>
			</div>

			

			<div class="btns">
				<input type="submit" value="작성 완료">
				<input type="reset" value="다시 작성" >
				<input type="button" value="목록"  onclick="location.href='cs_ask_list.do'">
			</div>
		</div>
		


		</form>
	</div>
<!-- <script>
$(document).ready(function () {
	$("#qtype_seq").change(function () {
		$(option).
	})
})
</script> -->
<script>
function SetSelectBox(){
    var qtype_seq = $("#qtype_seq option:selected").val();
	
}
</script>
<script>
function checkValue() {
	if(!document.ask_write.title.value){
		alert("제목을 입력하세요");
		return false;
	}
	if(!document.ask_write.qtype_seq.value){
		alert("문의 타입을 선택하세요");
		return false;
	}
	if(!document.ask_write.contents.value){
		alert("내용을 입력하세요");
		return false;
	}
	
}
</script>
	<div id="footer"></div>
</body>
</html>