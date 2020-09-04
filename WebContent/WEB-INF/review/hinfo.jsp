<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" href="review-style/hinfo-style.css?after=5" type="text/css" charset="UTF-8" />
<script src="https://kit.fontawesome.com/46a4c87d21.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {

		$("#headers").load("../layout/header.jsp"); // 외부 헤더 삽입
		$("footer").load("../layout/footer.html"); // 외부 푸터 삽입
		$(".hinfo-adress").css("cursor","pointer");
		 
		
		/* 토글 아이콘 변경 */
		$("#fbtnToggle").click(function() {
			$("#filterForm").slideToggle();
			
			$(this).attr('class') == 'fas fa-angle-down'? $(this).attr('class','fas fa-angle-up'):$(this).attr('class','fas fa-angle-down');
		});
			
		/* 검색바 값 초기화 */	
		$("#searchWord").click(function() {
			$("#searchWord").val('');
		
		})
		
		/*  필터 - 진료과목 전체 값 체크, 해제 */
		$("input[name=allSubCheck]").click(function() {
			
		if ($(this).is(":checked")) {
				
		
			$("input[name='subOption']").each(function() {
				$(this).prop("checked", true);
			});
				
			} else {
			
				$("input[name='subOption']").each(function() {
					$(this).prop("checked", false);
			});
		}//else
			
		});
		/* 필터 - 특이사항 전체 값 체크 */
		$("input[name=allSpeCheck]").click(function() {
			
			if ($(this).is(":checked")) {
					
			
				$("input[name='specialOption']").each(function() {
					$(this).prop("checked", true);
				});
					
				} else {
				
					$("input[name='specialOption']").each(function() {
						$(this).prop("checked", false);
				});
			}//else
				
			});
		
		//체크박스 값 하나라도 해제 시, 전체 체크값 해제
		$("input[name='specialOption']").on("click", function() {
			
			if ($("input[name=allSpeCheck]").is(":checked")) {
				$("input[name=allSpeCheck]").prop("checked",false);
			}
			
		})
		
		$("input[name='subOption']").on("click", function() {
			
			if ($("input[name=allSubCheck]").is(":checked")) {
				$("input[name=allSubCheck]").prop("checked",false);
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
			var send_subArray = new Array();
			var send_specialArray = new Array();
			var getParam = getUrlParams();
			
				$("#fbtnSearchs, #searchBtn").click(function() {
					$("input:checkbox[name=subOption]:checked").each(function() {
						send_subArray.push(this.value);			
					});
					$("input:checkbox[name=specialOption]:checked").each(function() {
						send_specialArray.push(this.value);			
					});
					
					if (getParam.searchWord == null) {
						getParam.searchWord = " ";
					}
					
					location.href = "../review/hreview_list.do?searchWord="+ getParam.searchWord+"&sub="+send_subArray+"&special="+send_specialArray;
				})


				
				
			
				
				//초기화버튼 클릭시 모두 초기화
				$('#fbtnReset').on('click', function() {
					
					$("input[name='allSpeCheck']").each(function() {
						$(this).prop("checked", false);
					});
					
					$("input[name='allSubCheck']").each(function() {
						$(this).prop("checked", false);
					});
					
			
					$("input[name='specialOption']").each(function() {
						$(this).prop("checked", false);
					});
					
					$("input[name='subOption']").each(function() {
						$(this).prop("checked", false);
					});
				
				})
				
				//넘어온 체크박스 값을 표시해주기
				if (getParam.sub != null && getParam.sub !="") {
				var sub_array = getParam.sub.split(",");
				if (sub_array.length != 0) {
					
					for (var i = 1; i <= sub_array.length; i++) {
						var num = sub_array[i-1];
						console.log(num)
						$("input[name='subOption'][value="+num+"]").prop("checked",true);
					}
				}
					
				}
				
				if(getParam.special != null && getParam.special !=""){
				var special_array = getParam.special.split(",");
					
				
				if(special_array.length !=0) {
					
					for (var i = 1; i <= special_array.length; i++) {
					
						var num = special_array[i-1]; //1
						console.log("num값"+num)
				
						$("input[name='specialOption'][value="+num+"]").prop("checked",true);
					}
				}
				
					
				}
				
				

					});//function
</script>

<script>
	
	function myMap() { 
		
	 	
	 	var seoul = { lat: 37.5642135 ,lng: 127.0016985 };
		
		map = new google.maps.Map( document.getElementById('map'), 
		
				{ zoom: 13, center: seoul });
	
		marker = new google.maps.Marker({ position: seoul, map: map });
		
	
	}

	
	
  $(function () {
		
	  $(".hinfo-adress").on("click", function() {

			
			var centerp = {lat:37.519956 , lng: 127.034867};
			
			//map.Marker({position: {lat: centerp, map: map}); 
			marker.setPosition(centerp);
			map.setZoom(18);
			map.panTo(centerp);
	  })
	
	
	}) 
</script>

</head>
<body>
	<div id="headers"></div>
	
	<div class="layout-main">
	
		<!-- 병원정보 목록 -->
		<div class="layout-left-block">
			<!-- 병원정보 서치 -->
			<div class="hospital-search">
				<form id="search-form" class="search">
					<div class="white_window" style="border-color: white; width: 100%;">
						<input type="text" id="searchWord" name="searchWord" title="검색"
							value="검색어를 입력하세요" />
						<button id="searchBtn" value="검색">
							<i class="fas fa-search"
								style="color: rgb(234, 153, 153); font-size: 20px; cursor: pointer;"></i>
						</button>
						<input type="hidden" name="sub" id="sub" value="${param.sub }" />
						<input type="hidden" name="special" id="special" value="${param.special }" />
					</div>
				</form>
			</div>
			<!-- 검색필터 버튼  -->
			<form action="" style="margin-bottom: 0;">
			<div class="search-btn-group">
				<button type="button" class="search-btn" id="sbtn0">리뷰많은
					순</button>
				<button type="button" class="search-btn" id="sbtn1"
					style="border-left: 1px solid pink; border-right: 1px solid pink;">인증리뷰
					많은 순</button>
				<button type="button" class="search-btn" id="sbtn2">평점
					높은 순</button>

			</div>
			</form>
			<!-- 병원정보 리스트 -->
			<form action="">
			<ul id="hinfoList-box">
			<c:if test="${empty list}"><li style="display: flex; justify-content: center;">찾는 목록이 없습니다</li></c:if>
			<c:if test="${ not empty list }">
				<c:forEach items="${list}" var="dto" varStatus="status">
				<div class="hinfo-box">
					<div class="hinfo-title">
						<input type="hidden" name="h_code" value="${dto.h_code }" />
						<a href="./hreview_detail.do?h_code=${dto.h_code }&h_name=${dto.h_name }" style="color: black;">${dto.h_name }</a>
						
						<span class="bookmarkCnt">
						<!--  <input type="hidden" /> -->
						 	<c:choose>
						 		<c:when test="${dto.isBookMark eq 1}">
						 		<i class="fas fa-bookmark"  style="cursor: pointer; color:#9ab3de" id="bookmark${dto.h_code}" ></i> 
						 		</c:when>
						 		<c:otherwise>
						 		<i class="far fa-bookmark"  style="cursor: pointer;" id="bookmark${dto.h_code}" ></i> 
						 		</c:otherwise>
						 	</c:choose>
						 	<span id="bookCount${dto.h_code}" style="font-size: 14px; vertical-align: top;">
						 		${dto.bookmark_count }
							</span>
						</span>
					</div>
					<div style="font-size: 13px; padding: 5px 0; margin-top: 5px 0">
						
							<c:forEach items="${dto.sub}" var="subDto">
								
									<span style="font-weight: bold; background-color: #dbf2ff;">${subDto.m_sub_name }</span>
							
							</c:forEach>
						
					</div>
					<p class="hinfo-adress" id="address1">
						<i class="fas fa-map-marker-alt"></i>${dto.h_address}
					</p>
					<div class="hinfo-star" style="margin: 6px 0;">
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
						<div style="font-size: 13px; padding: 5px 0; margin-top: 5px 0">
						
							<c:forEach items="${dto.special}" var="speDto">
								
									<span style="font-weight: bold; background-color:#ffe6ed;">${speDto.specialty_name }</span>
							
							</c:forEach>
						
						</div>
					
				</div>
				</c:forEach>
			</c:if>
			</ul>
			</form>
		</div>
		<!-- 지도 영역  -->
		<div class="layout-right-block">
			<div id="map" style="height: 100% ; width: 100%"></div>
			<div class="map-filter-form">
				<div class="filter-title">
					<!-- 필터바 영역 - 진료과목 -->
					<div class="fsearch-toggle">조건검색</div>
					<span><i class="fas fa-angle-down" id="fbtnToggle"
						style="position: absolute; font-size: 30px; left: 120px; top: 20px; cursor: pointer;"></i></span>
				

				</div>
		
					<div class="filter-body"  id="filterForm" > 
					
					<div class="filter-btn-group" >
						<button type="button" class="filter-btn" id="fbtnReset" 
							style="background: #9ab3de">초기화</button>
						<!-- <input type="button" class="filter-btn" id="fbtnSearchs" name="fbtnSearch" id="fbtnSearch"
							style="background: rgb(224, 102, 102)" onclick="" value="적용"/> -->
					 <button class="filter-btn" id="fbtnSearchs" name="fbtnSearchs"
							style="background: rgb(224, 102, 102)" >적용</button>  
					</div>
					
					<form action="" class="filter-body-Form" id="filterBodyForm">
						<div class="filter-item-group">
							<div class="filter-item">
								<strong class="filter-strong">진료과목</strong>
								<div class="fcheckbox-head">
									<label class="fcheckbox"> <input type="checkbox"
										class="fcheckbox-all" name="allSubCheck" /> <span class="flabel">전체선택</span>
									</label>
								</div>
								<div class="fcheckbox-body">
									<ul>
										<c:if test="${not empty subTypeList }">
										<c:forEach items="${subTypeList}" var="subType" >
										<li><label class="fcheckbox-option"> <input
												type="checkbox" class="fcheckbox-option" name="subOption" value="${subType.m_sub_seq}" /> <span
												class="fcheckbox-name">${subType.m_sub_name}</span> 
										</label></li>
										</c:forEach>
										</c:if>
									</ul>
										

								</div>
							</div>
						</div>
						<!-- 필터바 영역-특이사항  -->
						<div class="filter-item-group">
							<div class="filter-item">
								<strong class="filter-strong">특이사항</strong>
								<div class="fcheckbox-head">
									<label class="fcheckbox"> <input type="checkbox"
										class="fcheckbox-all" name="allSpeCheck" id="allSpeCheck" /> <span class="flabel">전체선택</span>
									</label>
								</div>
								<div class="fcheckbox-body">
									<ul>
										<c:if test="${not empty specialList }" >
										<c:forEach items="${specialList}" var="special">
										<li><label class="fcheckbox-option">

												 <input
												type="checkbox" class="fscheckbox-option" name="specialOption" value="${special.st_code}" /> <span
												class="fcheckbox-name">${special.specialty_name}</span>
												
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
		</div>

	</div>
	<footer></footer>
	
	<input type="hidden" id="userid" value="${authUser.id}" />

	<!-- js파일연결 body태그 하단에 해줘야함 -->
	<script type="text/javascript" src="review-style/hreview.js?var=143"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAEvrLe8odZwW_y03Mt9HrnAa-xsS_OPD0&callback=initMap&libraries=&v=weekly&callback=myMap">
</script>
</body>
</html>