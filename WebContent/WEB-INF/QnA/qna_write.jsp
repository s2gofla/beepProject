<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="QnA-style/qna_write-style.css?var=102" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<title>삐용삐용 - QnA:글쓰기</title>
<script>
$(document).ready( function() {
	$("header").load("<%= path %>/layout/header.jsp");  // 외부 헤더 삽입
	$("footer").load("<%= path %>/layout/footer.html");  // 외부 푸터 삽입
	});
</script>
</head>
<body>
<header></header>
<div class="qnaw-section">
	<div class="qnaw-title">
		<img src="<%= path %>/beep_images/qna-images/faq.svg" alt="" width="130px" />
		<span id="qnaw-title-content">&nbsp; &nbsp; Q&amp;A 작성하기 </span>
	</div>
	<div class="qnaw-main">
		<div class="qnaw-head" >
			<form id="qna-write-form1" method="post">
				<div class="qnaw-selectbox">
					<label for="">카테고리</label>
					<select name="qnaw-select" id="qnaw-select" >
						<option>진료과 선택</option>
							<c:forEach items="${ categoryList }" var="list">
								<option value="${ list.m_sub_seq }">${ list.m_sub_name }</option>
							</c:forEach>
					</select>
				</div>
				<c:if test="${ empty contentDTO }">
					<textarea name="qna-twrite" id="qna-twrite" cols="30" rows="1" placeholder="제목을 입력하세욤~"></textarea>
					<textarea name="qna-cwrite" id="qna-cwrite" cols="30" rows="10" placeholder="내용을 입력하세욤~"></textarea>
					<input type="submit" value="질문작성 완료"/>
				</c:if>
				<c:if test="${ !empty contentDTO }">
					<script>
						var select = "<c:out value="${contentDTO.m_sub_seq}" />";
						$('#qnaw-select').val(select).prop("selected",true);
						$('#qnaw-select').attr("disabled",true);
					</script>
					<textarea name="qna-twrite" id="qna-twrite" cols="30" rows="1" >${ contentDTO.q_title }</textarea>
					<textarea name="qna-cwrite" id="qna-cwrite" cols="30" rows="10" > ${ contentDTO.contents }</textarea>
					<input type="hidden" value="${ pq_seq }" name="pq_seq" />
					<input type="submit" value="수정 완료"/>
				</c:if>
			</form>
			<!-- <input type="text" id="qnaw-title-input" /> -->
		</div>
		<div class="qnaw-writeblock" >
			<!-- <img src="<%= path %>/beep_images/qna-images/pencil.svg" alt="" width="100px"/> -->
		</div>
	</div>
</div>
<footer></footer>
</body>
</html>