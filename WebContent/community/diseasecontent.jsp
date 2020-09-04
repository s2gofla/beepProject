<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="community-style/content.css?var=1222" type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="community-style/list.css?var=1222" type="text/css"	charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-게시글</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.html"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});

	// 좋아요
	var cnt1 = 1;
	function imgToggle1() {
		var img1 = document.getElementById("img1");
		var imgi = document.getElementById("img2");
		if (cnt1 % 2 == 1) {
			img1.src = "../beep_images/community-images/redlike.svg";
			img2.src = "../beep_images/community-images/whitelike.svg";
		} else {
			img1.src = "../beep_images/community-images/whitelike.svg";
			img2.src = "../beep_images/community-images/redlike.svg";
		}
		cnt1++;
	}
	
	// 댓글 신고
	var cnt2 = 1;
	function imgToggle2() {
		var img3 = document.getElementById("img3");
		var imgii = document.getElementById("img4");
		if (cnt2 % 2 == 1) {
			img3.src = "../beep_images/community-images/redreport.svg";
			img4.src = "../beep_images/community-images/whitereport.svg";
		} else {
			img3.src = "../beep_images/community-images/whitereport.svg";
			img4.src = "../beep_images/community-images/redreport.svg";
		}
		cnt2++;
	}
	
	// 게시글 신고
		// 신고
	var cnt3 = 1;
	function imgToggle3() {
		var img5 = document.getElementById("img5");
		var imgiii = document.getElementById("img6");
		if (cnt3 % 2 == 1) {
			img5.src = "../beep_images/community-images/redreport.svg";
			img6.src = "../beep_images/community-images/whitereport.svg";
		} else {
			img5.src = "../beep_images/community-images/whitereport.svg";
			img6.src = "../beep_images/community-images/redreport.svg";
		}
		cnt3++;
	}
</script>
</head>
<body>
	<header id="header"></header>


	<div class="layout-main">
		<div class="layout-left-block">
			<ul>
				<p class="communitystyle">
					<img src="../beep_images/community-images/chat.svg" alt="" />COMMUNITY
				</p>
				<ul class="community">
					<hr />
					<li><a href="" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />치료를 위한
							Tip</a></li>
					<ul class="disease-tip">
						<li><a href="disease-tip">&gt; 치과</a></li>
						<li><a href="">&gt; 피부과</a></li>
						<li><a href="">&gt; 성형외과</a></li>
						<li><a href="">&gt; 안과</a></li>
						<li><a href="">&gt; 산부인과</a></li>
						<li><a href="">&gt; 비뇨기과</a></li>
						<li><a href="">&gt; 정신건강의학과</a></li>
						<li><a href="">&gt; 정형외과</a></li>
						<li><a href="">&gt; 마취통증의학과</a></li>
						<li><a href="">&gt; 신경외과</a></li>
						<li><a href="">&gt; 재활의학과</a></li>
					</ul>
					<hr />
					<li><a href="freelist.jsp"><img
							src="../beep_images/community-images/communication.svg" alt="" />자유게시판</a></li>
					<hr />
					<li><a href="donationlist.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />기부/나눔</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">
			<p id="smalltitle">&gt; 치료를 위한 Tip &gt; 치과</p>

			<div class="contentmem">
				<h1>아파요</h1>
				<div class="mem">
					<img src="../beep_images/community-images/girl.svg" alt="" />sujin330
					중수고객
				</div>
				<span>2020-07-08 13:02:10 | 조회 5</span> <br />
				<hr />
				<br />
				<!-- 내용 -->
				<div class="contentcon">
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
				</div>

				<br />
				<!-- 밑에 -->
				<div class="conunder">
				<!-- 태그 -->
				<div class="contenthashtag">
					<button type="button" onclick="">
						<span>#안과</span>
					</button>
					<button type="button" onclick="">
						<span>#눈</span>
					</button>
					<button type="button" onclick="">
						<span>#피로</span>
					</button>
				</div>
				<!-- 태그 -->
				
				<div class="combtns1" >
					<img src="../beep_images/community-images/whitereport.svg" alt="" id="img5"  onclick="imgToggle3()" /> 
					<img src="../beep_images/community-images/redreport.svg" alt="" id="img6"/>
				</div>
				
				</div> <!-- 밑 -->

				<hr />

				<!-- 댓글달기 -->
				<br />
				<div class="contentcommentw">
					<textarea name="" id="commentArea" cols="30" rows="10"></textarea>
					<input type="submit" value="등록" />
				</div>
				<!-- 댓글달기 -->

				<br />
				<hr />
				<br />

				<!-- 댓글목록 -->
				<div class="contentcommentl">
					<img src="../beep_images/community-images/girl.svg" alt="" />
					<div>민정</div>
					<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
					<div>2020-07-08 20:00:00</div>
					<br />
				</div>
				<!-- contentcommentl -->
				<div class="concom">너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요</div>
				<br />
				<div class="combtns">
					<input type="submit" id="combtn" value="답글달기" /> 
					
					<img src="../beep_images/community-images/whitelike.svg" alt="" id="img1"  onclick="imgToggle1()" /> 
					<img src="../beep_images/community-images/redlike.svg" alt="" id="img2"/>
					
					<div class="likes">2</div>
					
					<img src="../beep_images/community-images/whitereport.svg" alt="" id="img3"  onclick="imgToggle2()" /> 
					<img src="../beep_images/community-images/redreport.svg" alt="" id="img4"/>
				</div>

			</div>
			<!-- contentmem -->





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