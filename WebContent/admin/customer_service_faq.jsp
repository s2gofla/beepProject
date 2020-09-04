<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="admin_style/cs_faq.css?var=12022"
	type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
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
					</a></li>
					<hr />
					<li><a href="customer_service_faq.jsp"><img
							src="../beep_images/community-images/communication.svg" alt="" />FAQ</a></li>
					<hr />
					<li><a href="customer_service_ask.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />나의문의
							내역</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">

			<p id="smalltitle">&gt; 고객센터 &gt; FAQ</p>

			<div id="lnb">
				<ul>
					<li class="question"><a href="#">이거는 어떻게하나요?</a>
						<ul>
							<li class="answer"><span>잘</span></li>
						</ul>
					<li class="question"><a href="#">저거는 어떻게하나요?</a>
						<ul>
							<li class="answer"><span>잘</span></li>
						</ul>
					<li class="question"><a href="#">도를 아세요?</a>
						<ul>
							<li class="answer"><span>아니요</span></li>
						</ul></li>
					<li class="question"><a href="#">도민정을 아세요?</a>
						<ul>
							<li class="answer"><span>아니요</span></li>
						</ul></li>
				</ul>
			</div>



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