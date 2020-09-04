<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="community-style/list.css?var=12022" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
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
					<img src="../beep_images/community-images/chat.svg" alt="" />COMMUNITY
				</p>
				<ul class="community">
					<hr />
					<li><a href="#" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />치료를 위한
							Tip</a>
					<ul class="disease-tip">
						<li><a href="diseaselist.jsp">&gt; 치과</a></li>
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
					</li>
					<hr />
					<li><a href="freelist.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />자유게시판</a></li>
					<hr />
					<li><a href="donationlist.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />기부/나눔</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 기부/나눔</p>
			<div id="write">
				<a href="donationwrite.jsp"><img src="../beep_images/community-images/pencil.svg" alt="" />글쓰기</a>
			</div>
			<table class="board">
				<thead>
					<tr id="TableTitle">
						<th scope="col"><span>번호</span></th>
						<th scope="col"><span>제목</span></th>
						<th scope="col"><span>ID</span></th>
						<th scope="col"><span>등록일</span></th>
						<th scope="col"><span>조회</span></th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<th>1</th>
						<th>아파요</th>
						<th>sujin330</th>
						<th>2020.07.08</th>
						<th>3</th>
					</tr>
					<tr>
						<th>2</th>
						<th>졸려요</th>
						<th>미인정</th>
						<th>2020.07.08</th>
						<th>5</th>
					</tr>
					<tr>
						<th>3</th>
						<th>아파요</th>
						<th>sujin330</th>
						<th>2020.07.08</th>
						<th>3</th>
					</tr>
					<tr>
						<th>4</th>
						<th>졸려요</th>
						<th>미인정</th>
						<th>2020.07.08</th>
						<th>5</th>
					</tr>
					<tr>
						<th>5</th>
						<th>아파요</th>
						<th>sujin330</th>
						<th>2020.07.08</th>
						<th>3</th>
					</tr>
					<tr>
						<th>6</th>
						<th>졸려요</th>
						<th>미인정</th>
						<th>2020.07.08</th>
						<th>5</th>
					</tr>
				</tbody>
				<!-- 
				<tr align="center">
					<td colspan="5" height="300px" align="center">등록된 게시물이 없습니다</td>
				</tr>
 -->
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