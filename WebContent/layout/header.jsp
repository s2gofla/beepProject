<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="../layout/header-style.css?var=453" type="text/css" charset="UTF-8" /> -->
<style>
@charset "UTF-8";
@import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);

@media screen and (max-width: 767px ){
	.nav-menu li a{
		display: none;
	}
	.title img{
		display: none;
	}
}
body{
	padding: 130px 0 0 0 ;
	margin: 0;
	background: white;
}
.center-sort{
	margin: 0 auto;
}
 header{
	width: 90%;
}
a{
	text-decoration: none;
}
#tt-header{
	width: 100%;
	background: white;
	position: fixed; 
	top: 0;
	z-index: 1000;
}
#top-header{
	margin-top: 10px;
	display: flex;
	justify-content: flex-end;
}
.thd{
	margin: 0 10px;
	font-family: 'NanumSquare', sans-serif;
	font-weight: 400;
	font-size: 13px;	
}
.thd-a{
	color: gray;
}
.thd-b{
	font-family: 'NanumSquare', sans-serif;
	font-weight: 400;
	font-size: 13px;	
	color: black;
}
.thd-c{
	color: darkgray;
}
.thd-a:hover{
	color: lightgray;
}
.tt-header{
	position: fixed;
}
#header{
	display: flex;
	justify-content: flex-start;
}
.title{
	font-family: 'Jua', sans-serif;
	font-size: 25px;
	font-weight: 400;
	color: black;
	display: flex;
	align-items: baseline;
	padding-bottom: 5px;
	text-decoration: none;
}
.title:hover{
	text-decoration: none;
	color: black;
}
.title img{
	margin: 10px 30px 0 5px;
	width: 40px;
	height: 40px;
}
.nav{
	width: 70%;
	display: flex;
	justify-content: center;
}
.main-menu{
	list-style: none;
	width: 70%;
	display: flex;
	justify-content: space-between;
	margin-bottom: 0;
	padding-top: 10px;
	padding-left: 0;
	margin: 0 auto;
}
.main-menu > li{
	padding: 15px 10px 0 10px;
	 margin: 0 17px;
	/* margin: 0 20px; */
}
.main-menu > li > a{
	color: black;
	font-family: 'NanumSquare', sans-serif;
	font-size: 18px;
	font-weight: 500;
	text-align: center;
	display: block;
	margin-bottom: 5px;
	text-decoration: none;
}
.main-menu > li:hover > a{
	color: gray;
}
.main-menu> li:hover .sub-menu{
	height: 45px;
}
.main-menu> #admin-li:hover #admin-menu{
	height: 230px;
}
#admin-menu li{
	list-style: none;
    width: 100%;
    padding: 10px 0;
    margin: 0;
    text-align: center;
}
#admin-menu li a{
	color: black;
	font-family: 'NanumSquare', sans-serif;
}
#admin-menu{
	flex-direction: column;
	width: 150px;
	margin: 0;
	background: rgba(224,224,224,50);
}
.sub-menu{
	/* display: none; */
	/* opacity: 0; */
  	position: absolute;
	list-style: none;
	padding: 0;
	background: white;
	box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	z-index: 1;
	min-width: 140px;
	transition: height 1s;
	height: 0;
	overflow: hidden;
	display: flex;
}
.sub-menu> li{
	padding: 10px 10px;
	 margin: 0 17px;
}
.sub-menu > li > a{
	color: black;
	font-family: 'NanumSquare', sans-serif;
	font-size: 15px;
	text-decoration: none;
}
.sub-menu > li:hover{
	background: rgba(224,224,224,50);
	font-weight: 600;
	text-decoration: none;
}
#admin-menu > li:hover{
	background: rgba(120,120,120,0.8);
	font-weight: 600;
}
#admin-menu > li:hover a{
	color: white;
}
.bar-hr{
	border: 1px solid #d8e2f3;
	background: #d8e2f3;
	margin-bottom: 20px;
}

</style>
</head>
<body class="center-sort">
<div id="tt-header">
<header class="center-sort">
	<div id="top-header">
		<c:if test="${ empty authUser.nickname}">
			<a href="<%= path %>/member/login.do" class="thd-a thd">LOGIN</a>
			<a href="<%= path %>/member/signup.do" class="thd-a thd">SIGN-UP</a>
		</c:if>
		<c:if test="${!empty authUser.nickname }">
			<span class="thd-b">${authUser.nickname}</span><span class="thd-c thd">님 건강한 하루 되세요~</span>
			<a href="<%= path %>/member/mypage.do" class="thd-a thd">MYPAGE</a>
			<a href="<%= path %>/member/logout.do" class="thd-a thd">LOGOUT</a>
		</c:if>
		<a href="<%= path %>/cs/cs_notice_list.do" class="thd-a thd">SERVICE-CENTER</a>
	</div>
	<div id="header">
		<a href="<%= path %>/main/main.do" class="title">삐-용!&nbsp;삐-용! <img src="<%= path %>/beep_images/main-logo2.png" alt="" /></a>
		<div class= "nav">
		<ul class="main-menu">
			<li><a href="javascript:void(0)">INFORMATION</a>
				<ul class="sub-menu">
					<li><a href="<%= path %>/information/disease_board.do">증상별 정보</a></li>
					<li><a href="<%= path %>/information/price_info.do">가격 정보</a></li>
					<li><a href="<%= path %>/information/toptip_board.do">꿀팁 정보</a></li>
				</ul>
			</li>
			<li><a href="javascript:void(0)">REVIEW</a>
				<ul class="sub-menu">
					<li><a href="<%= path %>/review/hreview_list.do">병원 리뷰</a></li>
					<li><a href="<%= path %>/review/mreview_list.do">약 리뷰</a></li>
					<li><a href="<%= path %>/review/reviewSelect.do">리뷰작성</a></li>
				</ul>
			</li>			
			<li><a href="<%= path %>/QnA/qna_list.do">Q&amp;A</a></li>
			<li><a href="javascript:void(0)">COMMUNITY</a>
				<ul class="sub-menu">
               <li><a href="/beepPro/community/disease_list.do">치료를 위한 Tip</a></li>
               <li><a href="/beepPro/community/free_list.do">자유게시판</a></li>
               <li><a href="/beepPro/community/donation_list.do">기부/나눔</a></li>
				</ul>
			</li>
			<c:if test="${authUser.mgrade_code eq 9 }">
			<li id="admin-li"><a href="javascript:void(0)">ADMIN</a> 
				<ul id="admin-menu" class="sub-menu">
					<li><a href="/beepPro/cs/cs_notice_write.do">공지사항 작성</a></li>
					<li><a href="admin_toptip_wirte.jsp">꿀팁게시글 작성</a></li>
					<li><a href="/beepPro/cs/cs_faq_write.do">FAQ 작성</a></li>
					<li><a href="/beepPro/admin/admin_confirm_list.do">의사,병원,의약품 승인</a></li>
					<li><a href="/beepPro/admin/admin_report_list.do">신고내역확인</a></li>
					<li><a href="admin_statics.jsp">통계</a></li>
				</ul>
			</li>
			</c:if>
		</ul>
		</div>
	</div>
</header>
	<hr class="bar-hr" />
</div>

</body>
</html>