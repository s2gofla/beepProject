<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">	
<link href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="main-style.css?var=1356505" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐이용삐이용</title>
<script>
$(document).ready( function() {
	$("#header").load("<%= path %>/layout/header.jsp?var=210");  // 원하는 파일 경로를 삽입하면 된다
	$("footer").load("<%= path %>/layout/footer.html");
});
</script>
</head>
<body>
<header id="header"></header>
<c:if test="${authUser.mgrade_code eq 9 }">
      <div id="floatdiv">
		<ul>
			<li><a href="/beepPro/cs/cs_notice_write.do">공지사항 작성</a></li>
			<li><a href="admin_toptip_wirte.jsp">꿀팁게시글 작성</a></li>
			<li><a href="/beepPro/cs/cs_faq_write.do">FAQ 작성</a></li>
			<li><a href="/beepPro/admin/admin_confirm_list.do">의사,병원,의약품 승인</a></li>
			<li><a href="/beepPro/admin/admin_report_list.do">신고내역확인</a></li>
			<li><a href="admin_statics.jsp">통계</a></li>
      	</ul>
      </div>


      <script type="text/javascript">
         var stmnLEFT = 10; // 오른쪽 여백 
         var stmnGAP1 = 0; // 위쪽 여백 
         var stmnGAP2 = 150; // 스크롤시 브라우저 위쪽과 떨어지는 거리 
         var stmnBASE = 150; // 스크롤 시작위치 
         var stmnActivateSpeed = 35; //스크롤을 인식하는 딜레이 (숫자가 클수록 느리게 인식) 
         var stmnScrollSpeed = 20; //스크롤 속도 (클수록 느림)
         var stmnTimer;

         function RefreshStaticMenu() {
            var stmnStartPoint, stmnEndPoint;
            stmnStartPoint = parseInt(
                  document.getElementById('STATICMENU').style.top, 10);
            stmnEndPoint = Math.max(document.documentElement.scrollTop,
                  document.body.scrollTop)
                  + stmnGAP2;
            if (stmnEndPoint < stmnGAP1)
               stmnEndPoint = stmnGAP1;
            if (stmnStartPoint != stmnEndPoint) {
               stmnScrollAmount = Math.ceil(Math.abs(stmnEndPoint
                     - stmnStartPoint) / 15);
               document.getElementById('STATICMENU').style.top = parseInt(
                     document.getElementById('STATICMENU').style.top, 10)
                     + ((stmnEndPoint < stmnStartPoint) ? -stmnScrollAmount
                           : stmnScrollAmount) + 'px';
               stmnRefreshTimer = stmnScrollSpeed;
            }
            stmnTimer = setTimeout("RefreshStaticMenu();",
                  stmnActivateSpeed);
         }

         function InitializeStaticMenu() {
            document.getElementById('STATICMENU').style.right = stmnLEFT
                  + 'px'; //처음에 오른쪽에 위치. left로 바꿔도. 
            document.getElementById('STATICMENU').style.top = document.body.scrollTop
                  + stmnBASE + 'px';
            RefreshStaticMenu();
         }
      </script>
   </c:if>
<section id="section1">
	<div class = "mainsection1" >
		 <div class = "right_block1" >
			<p>어디가</p>
			<p style="padding-left: 60px;">아프세요?</p>
			<div class="button-block1">
				<button class="block-btn search-btn" id="searchlink1">부위/증상 검색</button>
<!-- 				<button class="block1-btn search-btn" id="searchlink2" onclick="$('html, body').stop().animate( { scrollTop : '+=640' } );">병명 검색</button> -->
				<button class="block1-btn search-btn" id="searchlink2" >치료항목 검색</button>
			</div>
			<div class="search-block1">
				<input type="text" value="#" id="search1"/>
				<input type="button" class="search_submit1"  title="검색" value="" onclick="searchFLocation();"/>
			</div>
		</div>
		<div class="left_block1">
			<!-- <div id="image-msgbox">마우스를 올려 아픈 부위를 찾아보세요.</div> -->
			<div class="image-map-container">
				<img src="<%= path %>/beep_images/man.png" usemap="#image-map" alt="" width="500px" height="500px"/>
				<div class="map-selector"></div>
			</div>
			<map name="image-map" id="image-map">
				<area shape="rect" title="머리" alt="머리"  coords="220,53,278,72" href="javascript:void(0);" target="" onclick="content_search1(this);"/>
				<area shape="rect" title="눈" alt="눈"  coords="220,85,243,100" href="javascript:void(0);" target="" onclick="content_search1(this);" />
				<area shape="rect" title="눈" alt="눈"  coords="258,85,278,100" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="코" alt="코"  coords="247,90,255,112" href="javascript:void(0);" target="" onclick="content_search1(this);" />
				<area shape="rect" title="치아" alt="치아"  coords="236,117,265,132" href="javascript:void(0);" target="" onclick="content_search1(this);" />
				<area shape="rect" title="목" alt="목"  coords="237,135,265,150" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="어깨" alt="어깨"  coords="215,150,236,161" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="어깨" alt="어깨"  coords="272,148,292,160" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="배" alt="배"  coords="227,228,274,262" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="허리" alt="허리"  coords="281,228,290,260" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="허리" alt="허리"  coords="207,230,223,262" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="팔뚝" alt="팔뚝"  coords="160,157,205,180" href="javascript:void(0);" target="" onclick="content_search1(this);" />
				<area shape="rect" title="손목" alt="손목"  coords="99,165,120,177" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="팔뚝" alt="팔뚝"  coords="302,158,347,181" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="손목" alt="손목"  coords="383,164,403,178" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="가슴" alt="가슴"  coords="225,174,282,206" href="javascript:void(0);" target="" onclick="content_search1(this);" />
				<area shape="rect" title="손가락" alt="손가락"  coords="427,170,467,192" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="손가락" alt="손가락"  coords="40,176,75,191" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="허벅지" alt="허벅지"  coords="212,307,242,355" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="허벅지" alt="허벅지"  coords="263,309,295,360" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="무릎" alt="무릎"  coords="267,365,292,388" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="무릎" alt="무릎"  coords="204,361,239,386" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="다리" alt="다리"  coords="204,395,238,450" href="javascript:void(0);" target="" onclick="content_search1(this);" />
				<area shape="rect" title="다리" alt="다리"  coords="268,398,300,452" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="발" alt="발"  coords="201,458,233,478" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
				<area shape="rect" title="발" alt="발"  coords="275,460,293,478" href="javascript:void(0);" target=""  onclick="content_search1(this);" />
			</map>
		</div>
	</div>
</section>
<section>
	<div class="mainsection2" id="mainsection2">
		<div class="search_text">치료항목으로 검색하기</div>
		<div class="top_block1">무엇이 궁금하세요?</div>
		<div class="flex-center">
			<div class="search-block2">
				<input type="text" value="#" id="search2"/>
				<input type="button" class="search_submit2" value=""  title="검색" onclick="searchSLocation();"  />
			</div>
		
		</div>
		<div class="table-div center-sort">
		<table class="table-hos">
		<tbody>
			<tr>
			<td class="search-td"><a href="#" id="hos1"><img src="<%= path %>/beep_images/tooth.svg" alt="치과" class="hos-img" /><div class="hos-list">치과</div></a></td>
			<td class="search-td"><a href="#" id="hos2"><img src="<%= path %>/beep_images/rash.svg" alt="피부과" class="hos-img" /><div class="hos-list">피부과</div></a></td>
			<td class="search-td"><a href="#" id="hos3"><img src="<%= path %>/beep_images/mouth.svg" alt="성형외과" class="hos-img" /><div class="hos-list">성형외과</div></a></td>
			<td class="search-td"><a href="#" id="hos4"><img src="<%= path %>/beep_images/eye.svg" alt="안과" class="hos-img" /><div class="hos-list">안과</div></a></td>
			<td class="search-td"><a href="#" id="hos5"><img src="<%= path %>/beep_images/pregnant1.svg" alt="산부인과" class="hos-img" /><div class="hos-list">산부인과</div></a></td>
			<td class="search-td"><a href="#" id="hos6"><img src="<%= path %>/beep_images/prostate.svg" alt="비뇨기과" class="hos-img" /><div class="hos-list">비뇨기과</div></a></td>
			<td class="search-td"><a href="#" id="hos7"><img src="<%= path %>/beep_images/sad.svg" alt="정신건강의학과" class="hos-img" /><div class="hos-list">정신건강의학과</div></a></td>
			</tr>
			<tr><!-- 정형외과 crutch? 외과 scalpel 산부인과 pregnant-->
			<td class="search-td"><a href="#" id="hos8"><img src="<%= path %>/beep_images/patient.svg" alt="정형외과" class="hos-img" /><div class="hos-list">정형외과</div></a></td>
			<td class="search-td"><a href="#" id="hos9"><img src="<%= path %>/beep_images/vaccine.svg" alt="마취통증의학과" class="hos-img" /><div class="hos-list">마취통증의학과</div></a></td>
			<td class="search-td"><a href="#" id="hos10"><img src="<%= path %>/beep_images/brain.svg" alt="신경외과" class="hos-img" /><div class="hos-list">신경외과</div></a></td>
			<td class="search-td"><a href="#" id="hos11"><img src="<%= path %>/beep_images/crutch.svg" alt="재활의학과" class="hos-img" /><div class="hos-list">재활의학과</div></a></td>
			<td class="search-td"><a href="#" id="hos12"><img src="<%= path %>/beep_images/skeleton.svg" alt="재활의학과" class="hos-img" /><div class="hos-list">영상의학과</div></a></td>
			<td class="search-td"><a href="#" id="hos13"><img src="<%= path %>/beep_images/surgeon.svg" alt="외과" class="hos-img" /><div class="hos-list">외과</div></a></td>
			<td class="search-td"><a href="#" id="hos14"><img src="<%= path %>/beep_images/neuron.svg" alt="신경과" class="hos-img" /><div class="hos-list">신경과</div></a></td>
			</tr>
			<tr>
			<td class="search-td"><a href="#" id="hos15"><img src="<%= path %>/beep_images/child.svg" alt="소아과" class="hos-img" /><div class="hos-list">소아과</div></a></td>
			<td class="search-td"><a href="#" id="hos16"><img src="<%= path %>/beep_images/stomach.svg" alt="내과" class="hos-img" /><div class="hos-list">내과</div></a></td>
			<td class="search-td"><a href="#" id="hos17"><img src="<%= path %>/beep_images/ear.svg" alt="이비인후과" class="hos-img" /><div class="hos-list">이비인후과</div></a></td>
			<td class="search-td"><a href="#" id="hos18"><img src="<%= path %>/beep_images/doctor.svg" alt="가정의과" class="hos-img" /><div class="hos-list">가정의학과</div></a></td>
			</tr>
		</tbody>
		</table>
		</div>
	</div>
</section>
<section class="clearfix">
<div class="mainsection3">
	<article class="article1">
		<div class="right_block">
		<div class="main3-textblock">
			<span class="underline1"><span class="main3-text">우리들의 솔직한 리뷰 </span></span>
			<img src="<%= path %>/beep_images/chat-box.svg" id="message_img" alt="" />
		</div>	
		</div>
		<div class="left_block">
			<div class= "review-block">
				<div class= "go">
					<a href="<%= path %>/review/hreview_list.do"><div class="circle circle1 center_sort"></div>
					<span class="circle-text">&gt; 병원리뷰보러가기</span></a>
				</div>
				<div class= "go">
					<a href="<%= path %>/review/mreview_list.do"><div class="circle circle2 center_sort"></div>
					<span class="circle-text">&gt; 약 리뷰보러가기</span></a>
				</div>
				<div class= "go">
					<a href="<%= path %>/review/reviewSelect.do"><div class="circle circle3 center_sort"></div>
					<span class="circle-text">&gt; 리뷰 쓰러가기</span></a>
				</div>
			</div>
		</div>
	</article>
	<article class="article2">
		<div class= "right_block">
			<div class="main3-textblock"><span class="underline2"><span class="main3-text">우리를 위한 건강Tip</span></span>
			<img src="<%= path %>/beep_images/tip.svg" id="tip_img" alt="" />
			<br /><span class="underline3"><span class="main3-subtext">&gt;&gt;&#32;이 주의 Tip</span></span>
			</div>
		</div>
		<div class="left_block">
			<div class="tip-block">
				<c:forEach items="${ list }" var="list">
					<div class="go-tip">
						<a href="#" id="pic${ list.tt_code }">
							<%-- <div class="tip-img" style="background-image: url('${ list.pic}')"></div> --%>
							<img src="${ list.pic }" alt="tip" width="100%" />
							<span class="tip-text">&gt; ${ list.title }</span>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</article>
</div>
</section>
<footer></footer>
<!-- 모달창 -->
 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
     <div class="modal-dialog" role="document">
       <div class="modal-content" id="mcontent">
         <div class="modal-header" id="mhead">
           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title" id="myModalLabel">&gt;  치과 치료항목 찾기</h4>
         </div>
         <div class="modal-body" id="mbody">
         	<div id="modal-table">
         		<div id="modal-tbody">
         			<div class="modal-row">
         				<div class="modal-col"><span>#치통</span></div>
         				<div class="modal-col">#충치</div>
         			</div>
         			<div class="modal-row">
         				<div class="modal-col">#치아 변색</div>
         				<div class="modal-col">#매복치</div>
         			</div>
         		</div>
         	</div>
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
         </div>
       </div>
     </div>
</div>

<div class="row justify-content-center">
    <div class="col-md-8">
        <div class="row" id="image-md">
            <a href="https://unsplash.it/1200/768.jpg?image=251" data-toggle="lightbox" data-gallery="example-gallery" class="col-sm-4" data-title="모달 제목" data-footer="모달 푸터내용"></a>
            <a href="https://unsplash.it/1200/768.jpg?image=252" data-toggle="lightbox" data-gallery="example-gallery" class="col-sm-4"></a>
            <a href="https://unsplash.it/1200/768.jpg?image=253" data-toggle="lightbox" data-gallery="example-gallery" class="col-sm-4"></a>
        </div>
    </div>
</div>


<script type="text/javascript" src="main-js.js?var=14525"></script>

</body>
</html>