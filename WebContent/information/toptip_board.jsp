<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="toptip_board.css?var=1305" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf-8"	src="js/jquery.leanModal.min.js?var=2"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {
		$("#headers").load("../layout/header.html"); // 외부 헤더 삽입
		$("footer").load("../layout/footer.html"); // 외부 푸터 삽입
	});
</script>
</head>
<body>
	<div id="headers"></div>
	<!---------------------------------------------------------------------------------------------->



	<div class="layout-main">

		<h3 class="headword">
			<img src="inf_images/tip.jpg" alt="">꿀팁 정보
		</h3>

		<div class="medicine-search">
			<form id="search-form" class="search">
				<div class="white_window" style="border-color: white; width: 100%;">
					<input type="text" id="searchWord" name="searchWord" title="검색"
						placeholder="검색어를 입력하세요" />
					<button id="searchBtn" value="검색">
						<i class="fas fa-search"
							style="color: rgb(234, 153, 153); font-size: 35px;"></i>
					</button>
				</div>
			</form>
		</div>

		<div class="medicine-info">

			<div class="medicine-info-filter">
				<div class="medicine-info-option">
					<select class="mselect" title="정렬 조건" onchange="search(1)"
						id="mSearch" name="mSearch">
						<option value="v0" selected="selected">전체</option>
						<option value="v1">건강/질병</option>
						<option value="v2">다이어트</option>
						<option value="v3">뷰티/성형</option>
						<option value="v4">성지식</option>
						<option value="v5">음식/영앙</option>
						<option value="v6">임신/육아</option>
						<option value="v7">생활정보</option>
					</select>
				</div>
			</div>


			<div>
				<ul class="medcine-info-list">
					<li class="mlist-body"><a href="#loginmodal" class="flatbtn"
						id="modaltrigger">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="mlist-body"><a href="#loginmodal" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="mlist-body"><a href="#loginmodal" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="mlist-body"><a href="#" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="mlist-body"><a href="#" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="mlist-body"><a href="#" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					</li>
					<li class="mlist-body"><a href="#" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>
					</li>
					<li class="mlist-body"><a href="#" class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<div class="medicine-img" style="height: 100%">
									<img src="inf_images/1_t.jpg" width="100%" height="100%" alt="">
								</div>
								<div class="medicine-body">
									<div class="mname">혹시 면역력 강화에 도움되는 이것도 챙기셨나요</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span class="se_publishDate">2020.04.15. 18:00</span> <i
											class="se_divide_line"></i> <span class="se_view" style="">12,787
											읽음 </span>
									</div>
								</div>
							</div>
					</a></li>

				</ul>
			</div>
		</div>

		<div class="paging">
			<a href="#">1</a> <a href="#">2</a> <a href="#" class="active">3</a>
			<a href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#">7</a>
			<a href="#">8</a> <a href="#">9</a> <a href="#">10</a> <a href="#">&gt;</a>
			<a href="#">&raquo;</a>

		</div>
	</div>


	<div id="loginmodal" style="display: none;">
		<div id="carousel-example-generic" class="carousel slide">
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="1"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				<li data-target="#carousel-example-generic" data-slide-to="3"></li>
				<li data-target="#carousel-example-generic" data-slide-to="4"></li>
				<li data-target="#carousel-example-generic" data-slide-to="5"></li>
				<li data-target="#carousel-example-generic" data-slide-to="6"></li>
				<li data-target="#carousel-example-generic" data-slide-to="7"></li>
			</ol>

			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="inf_images/1_1.jpg">
				</div>
				<div class="item">
					<img src="inf_images/1_2.jpg">
				</div>
				<div class="item">
					<img src="inf_images/1_3.jpg">
				</div>
				<div class="item">
					<img src="inf_images/1_4.jpg">
				</div>
				<div class="item">
					<img src="inf_images/1_5.jpg">
				</div>
				<div class="item">
					<img src="inf_images/1_6.jpg">
				</div>
				<div class="item">
					<img src="inf_images/1_7.jpg">
				</div>
			</div>

			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			</a>
		</div>

	</div>



	<!--모달윈도우부분-->
	<script type="text/javascript">
		$(function() {
			$('#loginform').submit(function(e) {
				return false;
			});

			$('#modaltrigger').leanModal({
				top : 110,
				overlay : 0.8,
				closeButton : ".hidemodal"
			});
		});
	</script>
	<!--//모달윈도우부분-->

	<!---------------------------------------------------------------------------------------------->
	<footer></footer>
</body>
</html>