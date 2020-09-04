<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="admin_style/admin_content.css?var=1222"
	type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="admin_style/admin_list.css?var=1222"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-게시글</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});
</script>
</head>
<body>
	<header id="header"></header>


	<div class="layout-main">
		<div class="layout-left-block">
			<ul>
				<p class="communitystyle">
					<img src="../beep_images/community-images/chat.svg" alt="" />관리
				</p>
				<ul class="community">
					<hr />
					<li><a href="/beepPro/cs/cs_notice_list.do" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항</a>
					</li>
					<hr />
					<li><a href="#"><img
							src="../beep_images/community-images/communication.svg" alt="" />꿀팁게시글</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/admin/admin_confirm_list.do"><img
							src="../beep_images/community-images/donation.svg" alt="" />의사,병원,의약품,리뷰
							승인</a></li>
					<hr />
					<li><a href="/beepPro/admin/admin_report_list.do"><img
							src="../beep_images/community-images/donation.svg" alt="" />신고내역확인</a></li>
					<hr />
					<li><a href="admin_statics.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />통계</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 관리 &gt; 신고내역확인</p>


			<div class="contentmem">
				
				<h1>${reportRelyViewData.report_name }</h1>
				<div class="mem">
					<img src="../beep_images/community-images/girl.svg" alt="" />${reportRelyViewData.id }
					
				</div>
				<span>${reportRelyViewData.dates } </span> <br />
				<span>${reportRelyViewData.name }</span>
				<hr />
				<br />
				<!-- 내용 -->
				<div class="contentcon">
					<div>${reportRelyViewData.contents}</div>

				</div>

				<br />
				<!-- 밑에 -->


				<hr />
			<%-- 	<c:if test="${authUser.mgrade_code eq 9 }"></c:if> --%>
				
				
				<hr />
			</div>
		</div>
		<!-- layout-right-block -->
	</div>
	<!-- layout-main -->

	<div id="footer"></div>

	<script>
	
	var selectcate = 0;
	$("#atag").click(function () {
	   //alert("hello");
	   if(selectcate==0){
	      $('.disease-tip').animate({height: "300px"}, 800);
	      selectcate=1;
	   }else{
	      $('.disease-tip').animate({height: "0" }, 800);
	      selectcate=0;
	   }
	   
	}); 
	
	</script>

</body>
</html>