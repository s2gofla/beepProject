<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="beepbeep.cs.model.Cs_AskDTO" %>
<%@ page import="beepbeep.cs.service.ServiceException" %>
<%@ page import="beepbeep.cs.service.GetAskListService" %>
<%@ page import="beepbeep.cs.service.AskListView" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="cs_style/cs.css?var=12022" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<title>삐용삐용-고객센터</title>
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
					<img src="../beep_images/community-images/chat.svg" alt="" />고객센터
				</p>
				<ul class="community">
					<hr />
					<li><a href="/beepPro/cs/cs_notice_list.do" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />공지사항
							</a>
					
					</li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img src="../beep_images/community-images/communication.svg"
							alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_ask_list.do"><img src="../beep_images/community-images/donation.svg"
							alt="" />나의문의 내역</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 고객센터 &gt; 나의문의 내역</p>
			<c:if test="${! empty authUser.id}">
			<div id="write">
				<span><a href="/beepPro/cs/cs_ask_write.do"><img src="../beep_images/community-images/pencil.svg" alt="" />문의하기
				</a></span>
			</div>
			</c:if>
			
            <table class="type10">
                <thead>
                    <tr>
                        <th scope="cols">순번</th>
                        <th scope="cols">분류</th>
                        <th scope="cols">제목</th>
                        <th scope="cols">ID</th>
                        <th scope="cols">상태</th>
                        <th scope="cols">등록일</th>
                        <th scope="cols">수정</th>
                       
						
				
                    </tr>
                </thead>
              
                <tbody>
                <c:forEach items="${viewData.askList }" var="ask">
                
                <tr>
                        <th scope="row">${ask.qna_seq }</th>
                        <td>${ask.q_name }</td>
                        <td style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><a href="/beepPro/cs/cs_ask_content.do?qna_seq=${ask.qna_seq}">${ask.title }</a></td>
                        <td>${ask.id }</td>
                        <td>${ask.condition }</td>
                        <td>${ask.dates }</td>
                     <!--    <td><button><a href="/beepPro/cs/cs_ask_edit.do">수정
				</a></button></td> -->
                    </tr>
                  
                </c:forEach>
                    
                </tbody>
            </table>
   		
			<div class="pagination">
				<c:forEach var="pageNum" begin="1"	end="${ viewData.pageTotalCount }">
					<c:if test="${ pageNum eq viewData.currentPageNumber }">
						<span style='color: red'>${ pageNum }</span>
					</c:if>
					<c:if test="${ not (pageNum eq viewData.currentPageNumber) }">
						<a href="/beepPro/cs/cs_ask_list.do?page=${ pageNum }">${ pageNum }</a>
					</c:if>
				</c:forEach>
			</div>
		
		</div>
	</div>


	<div id="footer"></div>

	
</body>
</html>