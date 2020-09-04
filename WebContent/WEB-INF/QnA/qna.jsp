<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%  String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="QnA-style/qna-style.css?var=125350" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<title>삐용삐용 - QnA</title>
<script>
$(document).ready( function() {
	$("#headers").load("<%= path %>/layout/header.jsp");  // 외부 헤더 삽입
	$("footer").load("<%= path %>/layout/footer.html");  // 외부 푸터 삽입
	var delete_yn = "<c:out value="${delete_yn}" />";
	if( delete_yn == 'y' ) alert("글 삭제 완료");
	});
</script>
</head>

<body>
<div id="headers"></div>
<div class="qna-section">
	<div class="qna-title">
		<div class="qna-subtitle">
	 		<span id="sub0">전체&nbsp;&nbsp;&gt;&nbsp;</span>
		</div> 
		<img src="<%= path %>/beep_images/qna-images/faq.svg" alt="" width="130px" />
		<span id="qna-title-content">&nbsp; &nbsp; 궁금해도 물어보지 못했던 질문들 우리동네 의사선생님께 직접 물어보세요! </span>
	</div>

	<div class ="qna-main">
		<div class ="qna-rightblock">
		<c:if test="${ not listData.qnaList.isEmpty() }">
			<c:forEach items="${ listData.qnaList }" var="list">
				<div class="qna-subblock" >
					<img src="<%= path %>/beep_images/qna-images/question.svg" alt="" width="50"/>
					<div class="qna-question">${ list.q_title }</div>
					<div class="qna-view">
						<img src="<%= path %>/beep_images/qna-images/views.svg" alt="읽음" width="20px" />${ list.views }
						<img src="<%= path %>/beep_images/qna-images/paper.svg" alt="답글" width="25px" style="margin-left: 15px;"/>${ list.answerCnt }
						<img src="<%= path %>/beep_images/community-images/redlike.svg"" alt="좋아요" width="23px" style="margin-left: 15px;" /> ${ list.likes }
						</div>
					<a href="<%= path %>/QnA/qna_view.do?pq_seq=${list.pq_seq }"><span id="qna-goanswer">&gt; 답변하러가기</span></a>
					<div class="qna-blockdate">${ list.dates }</div>
				</div>
			</c:forEach>
		</c:if>
		<div class="pageBlock">
			<c:if test="${listData.prev eq true}">
				<a href="<%= path %>/QnA/choiceList.ajax?page=${ listData.start -1 }">&laquo;</a>
			</c:if>
			<c:forEach var="pageNum" begin="${listData.start }"	end="${ listData.end }">
				<c:if test="${ pageNum eq listData.currentPageNumber and pageNum ne 0 }">
					<span style='background: rgb(255,207,77); border-radius: 10px; color: white; font-weight: 600;'>${ pageNum }</span>
				</c:if>
				<c:if test="${ not (pageNum eq listData.currentPageNumber) and pageNum ne 0 }">
					<a href="<%= path %>/QnA/choicelist.ajax?page=${ pageNum }&m_sub_seq=0">${ pageNum }</a>
				</c:if>
			</c:forEach>
			<c:if test="${listData.next eq true }">
				<a href="<%= path %>/QnA/choiceList.ajax?page=${listData.end+1 }">&raquo;</a>
			</c:if>
		</div>
	</div>
		<div class = "qna-leftblock">
			<div class="qna-lefttop">
			<div class="qna-catetitle">Q&amp;A  카테고리</div>
			<div class="qna-catecontent">
				<p id="all-list">전체</p>
				<hr />
				<p class="qna-selectcate">진료과 선택</p>
				<hr />
				<ul class="qna-category" id="qna-category">
				
				<c:forEach items="${ categoryList }" var="list">
					<li><a href="#" id="cate${ list.m_sub_seq }" >&gt; ${ list.m_sub_name }</a></li>
				</c:forEach>
				</ul>
				<p class="qna-selectsort">정렬하기</p>
				<hr />
				<ul class="qna-sortgory">
					<li><a href="<%= path %>/QnA/qna_list.do?sort=1">&gt; 최신순</a></li>
					<li><a href="<%= path %>/QnA/qna_list.do?sort=2">&gt; 좋아요순</a></li>
					<li><a href="<%= path %>/QnA/qna_list.do?sort=3">&gt; 조회수순</a></li>
					<li><a href="<%= path %>/QnA/qna_list.do?sort=4">&gt; 오래된순</a></li>
				</ul>
				<div class="qna-searchform">
					<input type="text" id="qna-search" placeholder="검색어 입력"/>
					<input type="button" class="search_submit1" value="" onclick="searchFormbtn();" title="검색"/>			
				</div>
				</div>
			</div>
				<c:if test="${empty authUser.id }">
					<div class="qna-leftbottom" id="gowrite1">
					질문하러가기
					</div>				
				</c:if>
				<c:if test="${!empty authUser.id }">
					<div class="qna-leftbottom" id="gowrite2">
					질문하러가기
					</div>
				</c:if>
			
	</div>
	</div>

</div>
<script type="text/javascript" src="QnA-style/qna-js.js?var=4550"></script>
<footer></footer>	
</body>
</html>