<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="cs_style/cs_content.css?var=1222" type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="cs_style/cs.css?var=1222" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
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
					<li><a href="/beepPro/cs/cs_notice_list.do"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항</a>
					</li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_ask_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />나의문의내역</a></li>
					<hr />
				
					

				</ul>
			</ul>
		</div>

		<div class="layout-right-block">
			<p id="smalltitle">&gt; 관리 &gt; 문의하기</p>
			<div id="write">
				    <c:if test="${authUser.mgrade_code eq 9 }">
                        <td><a href="customer_service_write.jsp">답변하기</a></td>    ||  
                        <td><a href="/beepPro/cs/cs_ask_delete.do?qna_seq=${askViewData.qna_seq }">삭제하기</a></td>
                       </c:if>
			</div>
			
			<div class="contentmem">
				<h1>${askViewData.title }</h1>
				<div class="mem">
					<img src="../beep_images/community-images/girl.svg" alt="" />${askViewData.id }
				</div>
				<span>${askViewData.dates }</span> <br />
				<hr />
				<br />
				<!-- 내용 -->
				<div class="contentcon">
					${askViewData.contents }
				</div>

				<br />
			

				<hr />
				<c:if test="${authUser.mgrade_code eq 9 }">
				<!-- 댓글달기 -->
				<br />
				<div class="contentcommentw">
					<textarea name="" id="commentArea" cols="30" rows="10"></textarea>
					<input type="submit" value="등록" />
				</div>
				<!-- 댓글달기 -->
				</c:if>
				<br />
				<hr />
				<br />

				<!-- 댓글목록 -->
				<%-- <div class="contentcommentl">
					
					<div>${askReply.id }</div>
					<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
					<div>${askReply.dates }</div>
					<br />
				</div>
				<!-- contentcommentl -->
				<div class="concom">${askReply.contents }</div>
				<br /> --%>
				
				
			<!-- 한 개의 댓글 -->
			
				<br />
				<hr />
				<br />
<!-- 
				<div class="contentcommentl">
					<img src="../beep_images/community-images/girl.svg" alt="" />
					<div>수현</div>
					<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
					<div>2020-07-08 20:00:00</div>
					<br />
				</div>
				contentcommentl
				<div class="concom">너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요</div>
				<br />
				<div class="combtns">
					<input type="submit" id="combtn" value="답글달기" /> 
					
					<img src="../beep_images/community-images/whitelike.svg" alt="" id="img1"  onclick="imgToggle1()" /> 
					<img src="../beep_images/community-images/redlike.svg" alt="" id="img2"/>
					
					<div class="likes">2</div>
					
					<img src="../beep_images/community-images/whitereport.svg" alt="" id="img3"  onclick="imgToggle2()" /> 
					<img src="../beep_images/community-images/redreport.svg" alt="" id="img4"/>
				</div>
 -->
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