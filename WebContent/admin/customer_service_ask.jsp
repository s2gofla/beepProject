<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/cs.css?var=12022" type="text/css" charset="UTF-8" />
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
					<img src="../beep_images/community-images/chat.svg" alt="" />고객센터
				</p>
				<ul class="community">
					<hr />
					<li><a href="customer_service_notice.jsp" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항
							</a>
					
					</li>
					<hr />
					<li><a href="customer_service_faq.jsp"><img src="../beep_images/community-images/communication.svg"
							alt="" />FAQ</a></li>
					<hr />
					<li><a href="customer_service_ask.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />나의문의 내역</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 고객센터 &gt; 나의문의 내역</p>
			<div id="write">
				<a href="customer_service_write.jsp"><img src="../beep_images/community-images/pencil.svg" alt="" />문의하기
				</a>
			</div>
            <table class="type10">
                <thead>
                    <tr>
                        <th scope="cols">순번</th>
                        <th scope="cols">제목</a>
                        </th>
                        <th scope="cols">상태</th>
                        <th scope="cols">등록일</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td><a href="#">제목</a></td>
                        <td>답변완료</td>
                        <td>2020-07-10</td>
                    </tr>
                    <tr>
                        <th scope="row" class="even">2</th>
                        <td class="even"><a href="#">제목</a></td>
                        <td class="even">답변대기중</td>
                        <td class="even">2020-07-09</td>
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

	
</body>
</html>