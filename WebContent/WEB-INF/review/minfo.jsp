<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--*******  경로맞춰서 css style 부여 -->
<link rel="stylesheet" href="review-style/minfo-style.css?var=183562" type="text/css"
	charset="UTF-8" />
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<script src="https://kit.fontawesome.com/46a4c87d21.js"
	crossorigin="anonymous"></script>
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {

		$("#headers").load("../layout/header.jsp"); // 외부 헤더 삽입
		$("footer").load("../layout/footer.html"); // 외부 푸터 삽입
		
		$("layout-main").css("cursor","default");
		$("input").css("outline","none");
		$("button").css({"outline":"none", "cursor":"pointer"});
		
		$(".fcheckbox-title").css("cursor","pointer");
		
		/* 검색바 값 초기화 */	
		$("#searchWord").click(function() {
			$("#searchWord").val('');
		
		})
		
		/*  필터 - 약용도 전체 값 체크, 해제 */
		$("input[name=allPurCheck]").click(function() {
			
			if ($(this).is(":checked")) {
					
			
				$("input[name='mpurpose']").each(function() {
					$(this).prop("checked", true);
				});
					
				} else {
				
					$("input[name='mpurpose']").each(function() {
						$(this).prop("checked", false);
				});
			}//else
				
			});
		
		//체크박스 값 하나라도 해제 시, 전체 체크값 해제
		$("input[name='mpurpose']").on("click", function() {
			
			if ($("input[name=allPurCheck]").is(":checked")) {
				$("input[name=allPurCheck]").prop("checked",false);
			}
			
		})
		
		
		
		//요청 url 파라미터 값 가져오는 함수
		function getUrlParams() {
					var params = {};
					window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi,
							function(str, key, value) {
								params[key] = value;
							});
					return params;
				}
			
		
		//필터 적용 누르면 그 값에 따라 목록 보여주기
		//필터 체크박스 값 배열로 저장
		var send_purArray = new Array();
		var getParam = getUrlParams();
		var currentP = $("input[name='currentP']").val();
		
		$("#searchBtn").click(function() {
		
				$("input:checkbox[name='mpurpose']:checked").each(function() {
					send_purArray.push(this.value);			
				});

				if ($("input[name='searchWord']").val()==null) {
					$(this).val("");
				}else {
					var searchWord = $($("input[name='searchWord']")).val();
				}
				
				var num = $('.mselect-option:selected').val(); //1,2,3
				var currentP = getParam.currentP == null ? 1 : getParam.currentP;
				
				location.href = "../review/mreview_list.do?num="+num+"&searchWord="+searchWord+"&mpurpose="+send_purArray+"&currentP="+currentP;
			})
		
			$("#searchWord").keydown(function() {
				
				if (event.which==13) {
					
					$("input:checkbox[name='mpurpose']:checked").each(function() {
						send_purArray.push(this.value);			
					});

					/* if (getParam.searchWord == null) {
						getParam.searchWord = " ";
					} */
					
					if ($("input[name='searchWord']").val()==null) {
						$(this).val("");
					}else {
						var searchWord = $(this).val()
					}
					
					
					location.href = "../review/mreview_list.do?searchWord="+ searchWord+"&mpurpose="+send_purArray
							+"&currentP="+currentP;
				}
				
			});
		
		
		$(".mselect-option").eq(getParam.num == "" ? 0 : getParam.num -1).attr("selected","selected");
		
		
		//넘어온 체크박스 값을 표시해주기
		
		var purpose_array = getParam.mpurpose.split(",");

		
		if (purpose_array.length != 0) {
			
			for (var i = 1; i <= purpose_array.length; i++) {
				var num = purpose_array[i-1];
				
				$("input[name='mpurpose'][value="+num+"]").prop("checked",true);
			}
		}


	
	});//function
	
	
</script>
</head>
<body>
	<div id="headers"></div>
	<div class="layout-main">
	<!-- 검색  -->
		<div class="medicine-search">
			<form id="search-form" onsubmit="return false" class="search">
				<div class="white_window" style="border-color: white; width: 100%;">
					<input type="text" id="searchWord" name="searchWord" title="검색"
						value="검색어를 입력하세요" />
					<button type="button" id="searchBtn" value="검색">
						<i class="fas fa-search"
							style="color: rgb(234, 153, 153); font-size: 35px;"></i>
					</button>
						<input type="hidden" name="mpurpose" value="${param.mpurpose }"/>
						<input type="hidden" name="currentP" value="${empty currentP? 1: param.currentP }" />
				</div>
			</form>
			<!-- 필터바 -->
			<div class="filter-body">

				<form action="" class="filter-body-Form" onsubmit="return false" >
					<div class="filter-item-group">
						<div class="filter-item">
							<strong class="filter-strong">대분류</strong>
							<div class="filter-item-box">
								<div class="fcheckbox-title" id="title1">
									<i class="fas fa-capsules"></i>
									<div class="flabel">일반의약품</div>
								</div>
								<div class="fcheckbox-title" id="title2">
									<i class="fas fa-prescription-bottle-alt"></i>
									<div class="flabel">건강기능상품</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 필터바-용도분류 -->
					<div class="filter-item-group">
						<div class="filter-item">
							<strong class="filter-strong">약 용도별 분류</strong>
							<div class="fcheckbox-head" style="width: 300px">
								<label class="fcheckbox"> <input type="checkbox" name="allPurCheck"
									class="fcheckbox-all" /> <span class="flabel">전체선택</span>
								</label>
							</div>
							<div class="fcheckbox-body" style="width: 300px">
								<ul>
									<c:if test="${not empty mpurList }">
									<c:forEach items="${mpurList }" var="mpur">
									<li><label class="fcheckbox-option"> <input
											type="checkbox" name= "mpurpose" class="fcheckbox-option" value="${mpur.purpose_code}"/> <span
											class="fcheckbox-name">${mpur.purpose_name}</span>
									</label></li>
									</c:forEach>
									</c:if>
								</ul>
						
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 검색조건에서 필터  -->
		<div class="medicine-info">
			<div class="medicine-info-filter">
				<div class="medicine-info-option">
					<select class="mselect" title="정렬 조건" id="mSearch" name="mSearch">
						<option class="mselect-option" value="1" selected="selected">리뷰 많은 순</option>
						<option class="mselect-option" value="2">평점 낮은 순</option>
						<option class="mselect-option" value="3">평점 높은 순</option>
					</select>

				</div>
			</div>
			<!-- 약 목록 -->
			<div>
				<ul class="medcine-info-list">
				<c:if test="${empty list }"><li class="mlist-body">찾는 목록이 없습니다</li></c:if>
					<c:if test="${not empty list }">
					<c:forEach items="${list }" var = "dto" varStatus="status">
					<li class="mlist-body">
						<!-- item --> <div class="medicine-item-li">
							<div class="medicine-item" style="width: 100%; height: 60%">
								<a href="../review/mreview_detail.do?m_code=${dto.m_code }">
								<div class="medicine-img" style="height: 200px">
									<img src="${dto.m_pic }" width="100%"
										height="100%" alt="">
								</div>
								</a>
								<div class="medicine-body">
									<strong class="minfo-title">${dto.m_name }</strong> 
									<input type="hidden" name="m_code" value="${dto.m_code }" />
									<span class="bookmarkCnt">
									<!--  <input type="hidden" /> -->
									 	<c:choose>
									 		<c:when test="${dto.isBookMark eq 1}">
									 		<i class="fas fa-bookmark" id="bookmark${status.index}" style="cursor: pointer; color:#9ab3de" ></i> 
									 		</c:when>
									 		<c:otherwise>
									 		
									 		<i class="far fa-bookmark" id="bookmark${status.index}" style="cursor: pointer;" ></i> 
									 		</c:otherwise>
									 	</c:choose>
									 	<span id="bookCount${status.index}" style="font-size: 14px; vertical-align: top;">
									 		${dto.bookmark_count }
										</span>
									</span>
									<c:set value="${dto.mpurpose }" var="mpur"></c:set>
									<div class="mname">${mpur.purpose_name }</div>
									<div class="mname">${dto.m_enterprise }</div>
									<div class="minfo-star" style="padding-left: 10px;">
										<span> 
										<c:forEach begin="1" end="5" step="1" var="i">
										<c:if test="${i le dto.star_score}">							
										<i class="fas fa-star"></i> <!-- 색깔별 -->		
										</c:if>
										<c:if test="${i gt dto.star_score }">
										<i class="far fa-star" style="color: black;"></i>
										</c:if>
										</c:forEach>  
										</span> <span style="padding-left: 8px; color: #6c757d;">${dto.star_score }</span> <span
											style="font-size: 12px; color: #6c757d;">(${dto.reviewer})</span>
									</div>
								</div>
							</div>
					</div> <!-- //item -->
					</li>
					</c:forEach>
					</c:if>
					
				</ul>
			</div>
		</div>
		<!-- 페이징 -->
		<div class="paging">
	
			<c:if test="${paging.prev eq true }">
			<a href="../review/mreview_list.do?num=${param.num }&searchWord=${param.searchWord }&mpurpose=${param.mpurpose}&currentP=${paging.start -1}">&laquo;</a>
			</c:if>
			<c:forEach begin="${paging.start }" end="${paging.end }" var="p">
			<c:choose>
				<c:when test="${ p== paging.currentPage }">
				<b>${p}</b>
				</c:when>
				<c:when test="${p!= paging.currentPage }">
				<a href="../review/mreview_list.do?num=${param.num }&searchWord=${param.searchWord }&mpurpose=${param.mpurpose}&currentP=${p}">${p}</a>
				</c:when>	
			</c:choose>
			</c:forEach>			
			<c:if test="${paging.next eq true }">
			<a href="../review/mreview_list.do?num=${param.num }&searchWord=${param.searchWord }&mpurpose=${param.mpurpose}&currentP=${paging.end +1}">&raquo;</a>
			</c:if>
	
		</div>
	</div>
	<footer></footer>
	<input type="hidden" id="userid" value="${authUser.id}" />
	<script type="text/javascript" src="review-style/mreview.js?var=5"></script>
	<!-- js파일연결 body태그 하단에 해줘야함 -->
</body>
</html>