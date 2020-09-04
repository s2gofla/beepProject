<%@page import="java.util.Iterator"%>
<%-- <%@page import="beepbeep.cs.Page"%> --%>
<%@page import="java.util.ArrayList"%>
<%@page import="beepbeep.cs.model.Cs_NoticeDTO"%>
<%@page import="beepbeep.cs.service.ServiceException"%>
<%@page import="beepbeep.cs.service.GetNoticeListService"%>
<%@page import="beepbeep.cs.service.NoticeListView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="cs_style/cs.css?var=1219" type="text/css"
	charset="UTF-8" />
<link rel="stylesheet" href="cs_style/modal_style.css?var=1111"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<title>삐용삐용-고객센터</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});
</script>
</head>
<body>
	<header id="header"></header>

	<div class="llayout-main">
		<div class="layout-left-block">
			<ul>
				<p class="communitystyle">
					<img src="../beep_images/community-images/chat.svg" alt="" />고객센터
				</p>
				<ul class="community">
					<hr />
					<li><a href="/beepPro/cs/cs_notice_list.do"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항
					</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_ask_list.do"><img
							src="../beep_images/community-images/donation.svg" alt="" />나의문의
							내역</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">
			<c:set value="${noticeViewData }" var="dto"></c:set>
			<p id="smalltitle">&gt; 고객센터 &gt; 공지사항</p>
			<div id="write">
				<c:if test="${authUser.mgrade_code eq 9 }">
					<span><a href="/beepPro/cs/cs_notice_write.do"><img
							src="../beep_images/community-images/pencil.svg" alt="" />공지사항
							글쓰기 </a></span>
				</c:if>
			</div>
			<table class="type10">
				<thead>
					<tr>
						<th scope="cols">순번</th>
						<th scope="cols">제목</a></th>
						<th scope="cols">글쓴이</th>
						<th scope="cols">날짜</th>
						<th scope="cols">조회수</th>
						<c:if test="${authUser.mgrade_code eq 9 }">
							<th scope="cols">삭제</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty viewData}">
						<tr>
							<td colspan="5" height="300px" align="center">등록된 게시가 없습니다</td>
						</tr>
					</c:if>

					<c:forEach items="${viewData.noticeList}" var="notice">
						<tr>
							<th scope="row">${notice.notice_seq}</th>
							<td><a
								href="/beepPro/cs/cs_notice_content.do?notice_seq=${notice.notice_seq}">${notice.title}</a></td>
							<td>${notice.id }</td>
							<td>${ notice.dates}</td>
							<td>${notice.views }</td>
							<c:if test="${authUser.mgrade_code eq 9 }">
								<td ><button id="cs_delete">삭제</button></td>
							</c:if>
						</tr>
						<div id="myModal" class="modal">
							<!-- Modal content -->
							<div class="modal-content">
								<p style="text-align: center;">
									<span style="font-size: 14pt;"><b><span
											style="font-size: 24pt;">삭제</span></b></span>
								</p>
								<p style="text-align: center; line-height: 1.5;">
									<br />삭제하시겠습니까?
								</p>
								<p>
									<br />
								</p>
								<div
									style="cursor: pointer; background-color: #DDDDDD; text-align: center; padding-bottom: 10px; padding-top: 10px; border-bottom: 1px solid black;">
									<span class="pop_bt" style="font-size: 13pt;"><a
										href="/beepPro/cs/cs_notice_delete.do?notice_seq=${notice.notice_seq }">
											승인</a> </span>
								</div>
								<div
									style="cursor: pointer; background-color: #DDDDDD; text-align: center; padding-bottom: 10px; padding-top: 10px;"
									onClick="close_pop();">
									<span class="pop_bt" style="font-size: 13pt;"> 취소 </span>
								</div>
							</div>

						</div>

					</c:forEach>

				</tbody>
			</table>

			<div class="pagination">
				<div >
					<c:forEach var="pageNum" begin="1" end="${ viewData.pageTotalCount }">
						<c:if test="${ pageNum eq viewData.currentPageNumber }">
							<span style='color: red'>${ pageNum }</span>
						</c:if>
						<c:if test="${ not (pageNum eq viewData.currentPageNumber) }">
							<a href="/beepPro/cs/cs_notice_list.do?page=${ pageNum }">${ pageNum }</a>
						</c:if>
					</c:forEach>
				</div>
			</div>

			<div>
				<form action="" class="box">
					<select name="searchCondition" id="searchCondition">
						<option value="1">제목</option>
						<option value="2">작성자</option>
						<option value="3">내용</option>
						<option value="4">제목+내용</option>
					</select> <input type="text" name="searchWord" id="searchWord" value="" />
					<input type="submit" class="search_submit1" value="검색" title="검색" />
				</form>
			</div>

		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#cs_delete").click(function() {
				$("#myModal").show();
			});

		});
		//팝업 Close 기능
		function close_pop(flag) {
			$('#myModal').hide();
		};
	</script>

	<div id="footer"></div>


</body>
</html>