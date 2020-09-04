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
					<li><a href="#" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항</a>
					</li>
					<hr />
					<li><a href="freelist.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />꿀팁게시글</a></li>
					<hr />
					<li><a href="freelist.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />FAQ</a></li>
					<hr />
					<li><a href="donationlist.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />의사,병원,의약품,리뷰 승인</a></li>
					<hr />
					<li><a href="donationlist.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />신고내역확인</a></li>
					<hr />
					<li><a href="donationlist.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />통계</a></li>
					
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 관리 &gt; 꿀팁게시글</p>
			<div id="write">
				<a href="diseasewrite.jsp"><img src="../beep_images/community-images/pencil.svg" alt="" />글쓰기</a>
			</div>
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
					<td>운영자</td>
					<td>2020-07-10</td>
					<td>385</td>
				</tr>
				<tr>
					<th scope="row" class="even">4</th>
					<td class="even"><a href="#">제목</a></td>
					<td class="even">운영자</td>
					<td class="even">2020-07-09</td>
					<td class="even">332</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td><a href="#">제목</a></td>
					<td>운영자</td>
					<td>2020-07-08</td>
					<td>112</td>
				</tr>
				<tr>
					<th scope="row" class="even">2</th>
					<td class="even"><a href="#">제목</a></td>
					<td class="even">운영자</td>
					<td class="even">2020-07-08</td>
					<td class="even">322</td>
				</tr>
				<tr>
					<th scope="row">1</th>
					<td><a href="#">제목</a></td>
					<td>운영자</td>
					<td>2020-07-07</td>
					<td>512</td>
				</tr>
			</tbody>
		</table>



			<div class="pagination">
				<a href="">1</a> <a href="">2</a> <a href="">3</a> <a href="">4</a>
				<a href="">5</a> <a href="">6</a> <a href="">7</a> <a href="">8</a>
				<a href="">9</a> <a href="">10</a> <a href="">&gt;</a>

			</div>

			<div>
				<form action="" class="box">
					<select name="searchCondition" id="searchCondition">

						<option value="1">제목</option>
						<option value="2">작성자</option>
						<option value="3">내용</option>
						<option value="4">제목+내용</option>
						<option value="5">해시태그</option>
					</select> <input type="text" name="searchWord" id="searchWord" value="" />

					<input type="submit" class="search_submit1" value="" title="검색" />
				</form>


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