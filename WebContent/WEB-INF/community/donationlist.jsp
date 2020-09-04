<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<img src="../beep_images/community-images/chat.svg" alt="" />COMMUNITY
				</p>
				<ul class="community">
					<hr />
					<li><a href="#" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />치료를 위한
							Tip</a>
					<ul class="disease-tip">
					<c:forEach items="${categoryList}" var="list">
						<li><a href="">&gt; ${ list.m_sub_name }</a></li>
					</c:forEach>
					</ul>
					</li>
					<hr />
					<li><a href="/beepPro/community/free_list.do"><img src="../beep_images/community-images/communication.svg"
							alt="" />자유게시판</a></li>
					<hr />
					<li><a href="/beepPro/community/donation_list.do"><img src="../beep_images/community-images/donation.svg"
							alt="" />기부/나눔</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 기부/나눔</p>
			<div id="write">
				
				<c:if test="${not empty authUser.id}">
				<a href="/beepPro/community/donation_write.do?id=${authUser.id}">
				<img src="../beep_images/community-images/pencil.svg" alt="" />글쓰기</a>
				</c:if>
				<c:if test="${empty authUser.id}">
				<a href="/beepPro/member/login.do" onclick="alert('로그인 후 이용해주세요')">
				<img src="../beep_images/community-images/pencil.svg" alt="" />글쓰기</a>
				</c:if>
				
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
				<c:forEach items="${view.messageList }" var="msg">
					<tr>
						<th>${msg.no}</th>
						<th>[ ${msg.header} ] <a href="/beepPro/community/donation_content.do?d_seq=${msg.d_seq }&page=${view.currentPageNumber}&searchCondition=${searchCondition}&searchWord=${searchWord}" class="listtitle">${msg.title }</a>
						<c:if test="${msg.newImg }"><img src="../beep_images/community-images/new.svg" alt="" /></c:if></th>
						<th>${msg.id }</th>
						<th>${msg.dates }</th>
						<th>${msg.views }</th>
					</tr>
				
				</c:forEach>				</tbody>
				<!-- 
				<tr align="center">
					<td colspan="5" height="300px" align="center">등록된 게시물이 없습니다</td>
				</tr>
 -->
			</table>


			<br>
			<%-- 현재페이지 : ${ viewData.currentPageNumber }<br> --%>
			<div class="pagination" style="text-align: center">
					<c:if test="${view.prev }"><a href="/beepPro/community/donation_list.do?page=${ view.pageBlockStart-1 }&searchCondition=${searchCondition}&searchWord=${searchWord}">
					<img src="../beep_images/community-images/pink_left.svg" alt="" style="height: 20px; width: 20px"/></a></c:if>
				<c:forEach var="pageNum" begin="${ view.pageBlockStart }"	end="${ view.pageBlockEnd }" >
						<c:if test="${ pageNum eq view.currentPageNumber }">
							<span style='color: red'>${ pageNum }</span>
						</c:if>
						<c:if test="${ not (pageNum eq view.currentPageNumber) }">
							<c:if test=""></c:if>
							<a href="/beepPro/community/donation_list.do?page=${ pageNum }&searchCondition=${searchCondition}&searchWord=${searchWord}">${ pageNum }</a>
						</c:if>
				</c:forEach>
					<c:if test="${view.next }"><a href="/beepPro/community/donation_list.do?page=${ view.pageBlockEnd+1 }&searchCondition=${searchCondition}&searchWord=${searchWord}">
					<img src="../beep_images/community-images/pink_right.svg" alt="" style="height: 20px; width: 20px"/></a></c:if>
			</div>

			<div>
				<form action="" class="box">
					<select name="searchCondition" id="searchCondition">

						<option value="1">제목</option>
						<option value="2">작성자</option>
						<option value="3">내용</option>
						<option value="4">제목+내용</option>
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
		      $('.disease-tip').animate({height: "530px"}, 800);
		      selectcate=1;
		   }else{
		      $('.disease-tip').animate({height: "0" }, 800);
		      selectcate=0;
		   }
		   
		}); 

		</script>
</body>
</html>