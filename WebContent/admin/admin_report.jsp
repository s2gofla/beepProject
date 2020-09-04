<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/admin_list.css?var=12022" type="text/css" charset="UTF-8" />
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
					<li><a href="admin_toptip.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />꿀팁게시글</a></li>
					<hr />
					<li><a href="admin_faq.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />FAQ</a></li>
					<hr />
					<li><a href="admin_confirm.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />의사,병원,의약품,리뷰 승인</a></li>
					<hr />
					<li><a href="admin_report.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />신고내역확인</a></li>
					<hr />
					<li><a href="admin_statics.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />통계</a></li>
					
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 관리 &gt; 신고내역확인</p>
			
			<table class="type10">
			<thead>
				<tr>
					<th scope="cols">순번</th>
					<th scope="cols">제목</a></th>
					<th scope="cols">글쓴이</th>
					<th scope="cols">날짜</th>
					<th scope="cols">조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">5</th>
					<td><a href="#">제목</a></td>
					<td>jdh123</td>
					<td>2020-07-10</td>
					<td><input type="button" value="승인" /> <input type="button" value="삭제" /></td>
				</tr>
				<tr>
					<th scope="row" class="even">4</th>
					<td class="even"><a href="#">제목</a></td>
					<td class="even">jdh123</td>
					<td class="even">2020-07-09</td>
					<td class="even"><input type="button" value="승인" /> <input type="button" value="삭제" /></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td><a href="#">제목</a></td>
					<td>jdh123</td>
					<td>2020-07-08</td>
					<td><input type="button" value="승인" /> <input type="button" value="삭제" /></td>
				</tr>
				<tr>
					<th scope="row" class="even">2</th>
					<td class="even"><a href="#">제목</a></td>
					<td class="even">jdh123</td>
					<td class="even">2020-07-08</td>
					<td class="even"><input type="button" value="승인" /> <input type="button" value="삭제" /></td>
				</tr>
				<tr>
					<th scope="row">1</th>
					<td><a href="#">제목</a></td>
					<td>jdh123</td>
					<td>2020-07-07</td>
					<td><input type="button" value="승인" /> <input type="button" value="삭제" /></td>
				</tr>
			</tbody>
		</table>



			<div class="pagination">
				<a href="">1</a> <a href="">2</a> <a href="">3</a> <a href="">4</a>
				<a href="">5</a> <a href="">6</a> <a href="">7</a> <a href="">8</a>
				<a href="">9</a> <a href="">10</a> <a href="">&gt;</a>

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