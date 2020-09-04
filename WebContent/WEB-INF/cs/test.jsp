<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>




	<div class="pageBlock">
		<c:forEach var="pageNum" begin="${listData.start }" end="${ listData.end }">
			<c:if test="${ pageNum eq listData.currentPageNumber }">
				<span style='background: rgb(255, 207, 77)'>${ pageNum }</span>
			</c:if>
			<c:if test="${ not (pageNum eq listData.currentPageNumber) }">
				<a href="/beepPro/QnA/choicelist.ajax?page=${ pageNum }&m_sub_seq=0">${ pageNum }</a>
			</c:if>
		</c:forEach>
	</div>


	<div class="pagination">
			<c:forEach var="pageNum" begin="1" end="${ viewData.pageTotalCount }">
				<c:if test="${ pageNum eq viewData.currentPageNumber }">
					<span style='color: red'>${ pageNum }</span>
				</c:if>
				<c:if test="${ not (pageNum eq viewData.currentPageNumber) }">
					<a href="/beepPro/cs/cs_notice_list.do?page=${ pageNum }">${ pageNum }</a>
				</c:if>
			</c:forEach>
	</div>



</body>
</html>