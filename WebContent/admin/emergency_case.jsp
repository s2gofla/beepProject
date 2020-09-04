<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/emergency.css?var=12022" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
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
			<p class="communitystyle">
				<img src="../beep_images/community-images/chat.svg" alt="" />
				응급처치가이드
			</p>
			<ul class="community">
				<hr />
				<li><a href="first_aid_board_main.jsp"><img
						src="../beep_images/community-images/healthcare.svg" alt="" />응급상황</a>
				</li>
				<hr />
				<li><a href="emergency_basic.jsp"><img
						src="../beep_images/community-images/communication.svg" alt="" />기본응급처치</a></li>
				<hr />
				<li id="atag"><a href="#"><img
						src="../beep_images/community-images/donation.svg" alt="" />상황별
						응급처치</a>
					<ul class="case_firstaid">
						<li><a href="emergency_case.jsp">&gt; 동물/곤충에 물렸을 때</a></li>
						<li><a href="">&gt; 이물질</a></li>
						<li><a href="">&gt; 독극물중독</a></li>
						<li><a href="">&gt; 열/냉에 의한 손상</a></li>
						<li><a href="">&gt; 열/냉에 의한 손상</a></li>
						<li><a href="">&gt; 기타 응급 증상</a></li>
						<li><a href="">&gt; 물놀이 안전사고 대비</a></li>
						<li><a href="">&gt; 붉은불개미 대처</a></li>
					</ul></li>
				<hr />
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 응급처치가이드 &gt; 상황별 응급처치 &gt; 동물/곤충에 물렸을 때</p>
			<nav class="menu_bar">
                    <a href="#" class="is-current">개 등에 물렸을 때</a>
                     <a href="#">뱀에게 물렸을때</a>
                     <a href="#">동물/곤충에 물렸을 때</a>
                    <div id="content"> </div>
             </nav>
			<div>
			<!-- 내용  -->
			
			
			</div>
		</div>
	</div>


	<div id="footer"></div>

 	<script>
			$(document).ready(function() {
					$('.case_firstaid').hide();
				$('#atag').hover(function() {
					$(this).find('.case_firstaid').show(1000);
				}, function() {
					$(this).find('.case_firstaid').hide(1000);
				});
			});

		</script>
	
</body>
</html>