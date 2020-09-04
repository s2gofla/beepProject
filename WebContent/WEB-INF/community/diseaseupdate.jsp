<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="community-style/write.css?var=1222" type="text/css" charset="UTF-8" />
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
			<img src="../beep_images/community-images/boardpencil.svg" alt="" />치료를 위한 Tip 수정하기
		</p>
		<form method="post">
		<div class="contentBody">
			<div class="wrtieTitle">
				<input type="text" placeholder="제목을 입력해주세요." name="title" value="${dto.title }" />
			</div>

			<div>
					<select name="m_sub_seq" id="m_sub_seq">
					<option value="0">카테고리 선택</option>
						<c:forEach items="${categoryList}" var="list">
							<option value="${list.m_sub_seq }">${ list.m_sub_name }</option>
						</c:forEach>

					</select>

			</div>

			<div class="writecontent">
				<textarea placeholder="내용을 입력해주세요." name="contents" >${dto.contents }</textarea>
			</div>

			<div class="writehashtag">
				<textarea placeholder="#태그를 입력해주세요. ex) #치과 #충치 (#을 쓰고 띄어쓰기로 구분해주세요.)" name="hashtag">${dto.hashtag}</textarea>
			</div>

			<div class="btns">
				<input type="submit" value="작성 완료">
				<input type="reset" value="다시 작성" onclick="location.href='/beepPro/community/disease_write.do'">
				<input type="button" value="목록" onclick="location.href='/beepPro/community/disease_list.do'">
			</div>
		</div>
		<!-- contentBody -->

		</form>
	</div>

	<script>
                  var select = "<c:out value="${dto.m_sub_seq}" />";
                  $('#m_sub_seq').val(select).prop("selected",true);
                  //$('#m_sub_seq').attr("disabled",true);
               </script>
               
	<div id="footer"></div>
</body>
</html>