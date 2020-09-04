<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/admin_list.css?var=12022" type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="admin_style/modal_style.css?var=12022"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
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
					<img src="../beep_images/community-images/chat.svg" alt="" />관리
				</p>
				<ul class="community">
					<hr />
					<li><a href="/beepPro/cs/cs_notice_list.do"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항</a>
					</li>
					<hr />
					<li><a href="#"><img src="../beep_images/community-images/communication.svg"
							alt="" />꿀팁게시글</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img src="../beep_images/community-images/communication.svg"
							alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/admin/admin_confirm_list.do"><img src="../beep_images/community-images/donation.svg"
							alt="" />의사,병원,의약품,리뷰 승인</a></li>
					<hr />
					<li><a href="/beepPro/admin/admin_report_list.do"><img src="../beep_images/community-images/donation.svg"
							alt="" />신고내역확인</a></li>
					<hr />
					<li><a href="admin_statics.jsp"><img src="../beep_images/community-images/donation.svg"
							alt="" />통계</a></li>
						<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">
			
			<table>
			<thead>
			<th>&gt; 관리 &gt; 신고내역확인
						<select name="type"onchange="location.href=this.value">
						<option value="/beepPro/admin/admin_report_list.do"  selected="selected">게시글</option>
						<option value="/beepPro/admin/admin_reportReply_list.do" >댓글	</option>
						</select></th>
			</thead>
			</table>
			<table class="type10">
			<thead>
				<tr>
					<th scope="cols">순번</th>
					
					<th scope="cols">신고유형</a></th>
					<th scope="cols">내용</th>
					<th scope="cols">ID</th>
					<th scope="cols">날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${viewData.admin_report_List}" var="report">
				<tr>
					<th scope="row">${report.report_seq }</th>
					
					<td>${report.report_name }</td>
					<td><a href="/beepPro/admin/admin_report_content.do?report_seq=${report.report_seq }">${report.contents }</a></td>
					<td>${report.id }</td>
					<td>${report.dates }</td>
			
				</tr>
			</c:forEach>
			</tbody>
		</table>
	


				<div class="pagination">
			<div style="text-align: center">
				<c:forEach var="pageNum" begin="1"	end="${ viewData.pageTotalCount }">
					<c:if test="${ pageNum eq viewData.currentPageNumber }">
						<span style='color: red'>${ pageNum }</span>
					</c:if>
					<c:if test="${ not (pageNum eq viewData.currentPageNumber) }">
						<a href="/beepPro/admin/admin_report_list.do?page=${ pageNum }">${ pageNum }</a>
					</c:if>
				</c:forEach>
			</div>
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
		<script>
		function openModal(modalname) {
			document.get
			$("#modal").fadeIn(300);
			$("." + modalname).fadeIn(300);
		}

		$("#modal, .close").on('click', function() {
			$("#modal").fadeOut(300);
			$(".modal-con").fadeOut(300);
		});
	</script>
</body>
</html>