<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/admin_list.css?var=12022"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<title>삐용삐용-COMMUNITY</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.html"); // 원하는 파일 경로를 삽입하면 된다
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
					<img src="../beep_images/community-images/chat.svg" alt="" />관리
				</p>
				<ul class="community">
					<hr />
					<li><a href="admin_notice.jsp"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항</a>
					</li>
					<hr />
					<li><a href="admin_toptip.jsp"><img
							src="../beep_images/community-images/communication.svg" alt="" />꿀팁게시글</a></li>
					<hr />
					<li><a href="admin_faq.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />FAQ</a></li>
					<hr />
					<li><a href="admin_confirm.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />의사,병원,의약품,리뷰
							승인</a></li>
					<hr />
					<li><a href="admin_report.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />신고내역확인</a></li>
					<hr />
					<li><a href="admin_statics.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />통계</a></li>

				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 관리 &gt; 통계</p>
			<nav class="menu_bar">
				<a href="#" class="is-current">가입자수</a> <a href="#">병원리뷰수</a> <a
					href="#">의약품리뷰수</a> <a href="#">의사리뷰수</a> <a href="#">하루방문자수</a>

				<div class="nav-underline"></div>
			</nav>

			<div class="confirm_top">
				<h1>하루방문자수</h1>
			</div>
			<div>
			<!-- 그래프 -->
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