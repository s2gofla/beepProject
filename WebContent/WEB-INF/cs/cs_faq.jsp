<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beepbeep.cs.model.Cs_FaqDTO"%>
<%@ page import="beepbeep.cs.service.ServiceException"%>
<%@ page import="beepbeep.cs.service.GetFaqListService"%>
<%@ page import="beepbeep.cs.service.FaqListView"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="cs_style/cs_faq.css?var=hey"
    
	type="text/css" charset="UTF-8" />
	<link rel="stylesheet" href="cs_style/modal_style.css?var=1229"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
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
					</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_faq_list.do"><img
							src="../beep_images/community-images/communication.svg" alt="" />FAQ</a></li>
					<hr />
					<li><a href="/beepPro/cs/cs_ask_list.do"><img
							src="../beep_images/community-images/donation.svg" alt="" />나의문의
							내역</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 고객센터 &gt; FAQ</p>
			<div id="write">
				<c:if test="${authUser.mgrade_code eq 9 }">
				<span><a href="/beepPro/cs/cs_faq_write.do"><img
						src="../beep_images/community-images/pencil.svg" alt="" />FAQ등록 </a></span>
			</c:if>
			</div>
			<div id="lnb">
				<c:forEach items="${viewData.faqList }" var="faq">
					<c:if test="${empty viewData.faqList}">
						<td colspan="5" height="300px" align="center">등록된 게시가 없습니다</td>
					</c:if>
					<ul>
						<li class="question"><a href="#">${faq.title}</a>
							<ul>
								<li class="answer"><span>${faq.contents }</span>
								<c:if test="${authUser.mgrade_code eq 9 }">
								
								<button id="cs_delete" >삭제</button> 
								<button onclick="location.href='/beepPro/cs/cs_faq_edit.do?faq_list_seq=${faq.faq_list_seq }'">수정</button></c:if></li>
							</ul></li>
					</ul>
							<div id="myModal" class="modal">
							<!-- Modal content -->
							<div class="modal-content">
								<p style="text-align: center;">
									<span style="font-size: 14pt;"><b><span
											style="font-size: 24pt;">삭제</span></b></span>
								</p>
								<p style="text-align: center; line-height: 1.5;">
									<br />삭제하시겠습니까?
								</p>
								<p>
									<br />
								</p>
								<div
									style="cursor: pointer; background-color: #DDDDDD; text-align: center; padding-bottom: 10px; padding-top: 10px; border-bottom: 1px solid black;">
									<span class="pop_bt" style="font-size: 13pt;"><a
										href="/beepPro/cs/cs_faq_delete.do?faq_list_seq=${faq.faq_list_seq }">
											승인</a> </span>
								</div>
								<div
									style="cursor: pointer; background-color: #DDDDDD; text-align: center; padding-bottom: 10px; padding-top: 10px;"
									onClick="close_pop();">
									<span class="pop_bt" style="font-size: 13pt;"> 취소 </span>
								</div>
							</div>

						</div>
					
					
				</c:forEach>
			</div>



			<div class="pagination">
				<c:forEach var="pageNum" begin="1"	end="${ viewData.pageTotalCount }">
					<c:if test="${ pageNum eq viewData.currentPageNumber }">
						<span style='color: red'>${ pageNum }</span>
					</c:if>
					<c:if test="${ not (pageNum eq viewData.currentPageNumber) }">
						<a href="/beepPro/cs/cs_faq_list.do?page=${ pageNum }">${ pageNum }</a>
					</c:if>
				</c:forEach>
			</div>

		</div>
	</div>
	<script>
		$(document).ready(function() {
			$('#cs_delete').click(function() {
				$('#myModal').show();
			});

		});
		//팝업 Close 기능
		function close_pop(flag) {
			$('#myModal').hide();
		};
	</script>
	
	<script>
		(function($) {

			var lnbUI = {
				click : function(target, speed) {
					var _self = this, $target = $(target);
					_self.speed = speed || 300;

					$target.each(function() {
						if (findChildren($(this))) {
							return;
						}
						$(this).addClass('noDepth');
					});

					function findChildren(obj) {
						return obj.find('> ul').length > 0;
					}

					$target.on('click', 'a',
							function(e) {
								e.stopPropagation();
								var $this = $(this), $depthTarget = $this
										.next(), $siblings = $this.parent()
										.siblings();

								$this.parent('li').find('ul li').removeClass(
										'on');
								$siblings.removeClass('on');
								$siblings.find('ul').slideUp(250);

								if ($depthTarget.css('display') == 'none') {
									_self.activeOn($this);
									$depthTarget.slideDown(_self.speed);
								} else {
									$depthTarget.slideUp(_self.speed);
									_self.activeOff($this);
								}

							})

				},
				activeOff : function($target) {
					$target.parent().removeClass('on');
				},
				activeOn : function($target) {
					$target.parent().addClass('on');
				}
			};

			// Call lnbUI
			$(function() {
				lnbUI.click('#lnb li', 300)
			});

		}(jQuery));
	</script>

	<div id="footer"></div>
</body>
</html>