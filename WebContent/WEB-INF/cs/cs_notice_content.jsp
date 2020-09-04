<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beepbeep.cs.model.Cs_NoticeDTO"%>
<%@ page import="beepbeep.cs.service.Notice_Content"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="cs_style/cs.css?var=12022" type="text/css"
	charset="UTF-8" />
<link rel="stylesheet" href="cs_style/cs_content.css?var=12023"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<title>삐용삐용-공지사항</title>
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
					<img src="../beep_images/community-images/chat.svg" alt="" />공지사항
				</p>
				<ul class="community">
					<hr />
					<li><a href="/beepPro/cs/cs_notice_list.do"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항</a>
					</li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_ask_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />나의문의내역</a></li>
					<hr />
					

				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 관리 &gt; 공지사항</p>
			<div id="write">
				<c:if test="${authUser.mgrade_code eq 9 }">
					<span><a href="customer_service_write.jsp">○ 수정하기 </a></span>
					<span><a href="customer_service_write.jsp">○ 삭제하기 </a></span>
				</c:if>
			</div>

			<div class="contentmem">

				<h1>${noticeViewData.title}</h1>
				<hr>
				<div class="mem">${noticeViewData.id }</div>
				<span>${noticeViewData.dates } | ${noticeViewData.views }</span> <br />
				<hr />
				<br />
				<!-- 내용 -->
				<div class="contentcon">
					<div>${noticeViewData.contents }</div>
				</div>
			<hr />
			<div>
			<span>
			이전글 : <a href="#">asd</a>
			</span>
			</div>
			<div>
			<span>
			다음글 : <a href="#">bcs</a>
			</span>
			</div>
			<hr />
			<div>
				<button><a href="/beepPro/cs/cs_notice_list.do">목록으로</a></button>
			</div>
			<hr />
		
			


			</div>

		</div>
	</div>

	<div id="footer"></div>
	<!-- 
 	<script>
			$(document).ready(function() {
					$('.disease-tip').hide();
				$('.community').hover(function() {
					$(this).find('.disease-tip').show(1000);
				}, function() {
					$(this).find('.disease-tip').hide(1000);
				});
			});

		</script>  -->
	<script>
		var selectcate = 0;
		$("#atag").click(function() {
			//alert("hello");
			if (selectcate == 0) {
				$('.disease-tip').animate({
					height : "300px"
				}, 800);
				selectcate = 1;
			} else {
				$('.disease-tip').animate({
					height : "0"
				}, 800);
				selectcate = 0;
			}

		});
	</script>
</body>
</html>