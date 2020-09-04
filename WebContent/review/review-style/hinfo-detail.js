
$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});

function getUrlParams() {
	var params = {};
	window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi,
			function(str, key, value) {
				params[key] = value;
			});
	return params;
}

var getParam = getUrlParams();


var reviewList = function reviewList() {
	
	var num = $(".sort-option:selected").val();
	var h_code = getParam.h_code;
	var userid = $("#id").val();
	var searchWord = $('#searchWord').val();
	var currentPage = $("#currentPage").text();
	var clickPage = $(this).text();
	console.log(clickPage+"클릭페이지");
	console.log("인덱스값" + clickPage.indexOf("최신") );
	
	
	//클릭된 값이 옵션일때 혹은 없을때 currentpage를 1로 돌리기 위함
	if (clickPage.indexOf("최신") != -1 || clickPage == "") {
		clickPage = -1;
	}else if (clickPage.indexOf("다음") != -1) {
		currentPage = $(this).prev().text();
		clickPage = Number(currentPage)+1;
	
	}else if(clickPage.indexOf("이전") != -1 ){
		currentPage = $(this).next().text();
		clickPage = Number(currentPage)-1;
	}
	
	//console.log("병원코드"+h_code);
	//console.log("id"+userid);
	//console.log("옵션값"+num);
	console.log(currentPage+"현재페이지");
	//console.log(clickPage+"클릭페이지");
	//console.log("검색값"+searchWord);*/
	
	// 현재페이지와 클릭한페이지가 같을 경우 처리되지 않도록 하기 위함
	if (currentPage == clickPage) {
		return false;
	}
	
	var url = "num="+num+"&h_code="+h_code+"&currentPage="+clickPage+"&searchWord="+searchWord;

	//console.log(url);
	
	$.ajax({
		
		type : 'post'
		,url : '../review/arrayHinfoReview.ajax'
		,data : url
		,success : function(data) {
			console.log("병원리뷰정렬 성공");
			console.log(data.map);
			showHtml(data.map);
			
		},
		error : function(data) {
			console.log("병원리뷰 정렬 에러");
		}
		
	})
	
	
}


$("body").on("change", "#sort-select-box", reviewList);
$("body").on("click", ".paging b" , reviewList);
$("body").on("submit",'#search-form' ,function(e) {
	e.preventDefault();
	reviewList();
})


function showHtml(data) {
	
	let html = "";
	$.each(data.list, function(index, item) {
		html += '<div class="review-in-detail-text-box">';
		html += '<div class="review-in-detail-text-box-border" style="margin-top: 20px;">';
		html += '<div class="rowa" style="padding-top: 20px; border-top: 1px solid lightgray;">';
		html += '<div class="review-in-detail-text-box-profile col" style="width: 200px;">'
		html += '<div> <div class="reviewer_profile" style="width: 48px; height: 100%;">';
		html += '<img src="https://d23zwvh2kbhdec.cloudfront.net/media/public/customers/photos/animals/hamster.png" style="width: 100%; height: auto;">';
		html += '</div></div>';
		html += '<div class="reviewer-name" style="letter-spacing: -0.9px; font-size: 16px; font-weight: bold;">';
		html += item.nickname + '</div>';
		html += '<div class="mb"><div>';				
		html += '<i class="fas fa-star" style="color: #ffc107"></i>';
		html += '<span style="font-size: 18px; font-weight: bold; letter-spacing: -0.9px;">'+item.star_score+'</span>';
		html += '<span style="letter-spacing: -0.7px; color: #9b9b9b; font-size: 14px;"> / 5 </span>';						
		html += '</div></div>';
		html += '<div class=""> <div class="rowa mb" style="position: relative;">';
		html += '<div style="font-size: 15px; font-weight: 500;">친절도</div>';
		html += '<div style="margin-left: 8px; position: absolute; right: 0">';
		html += '<span style="display: block; text-align: center; font-size: 15px;">';
		for (var i = 1; i <= 5; i++) {
			if (i<=item.score_kind) {
				html += '<i class="fas fa-star"></i>';
			}else{
				html += '<i class="far fa-star" style="color: black;"></i>';
			}
		}
		html += '</span></div></div>';						
		html += '<div class="rowa mb" style="position: relative;">';
		html +='<div style="font-size: 15px; font-weight: 500;">금액</div>';
		html +='<div style="margin-left: 8px; position: absolute; right: 0">';
		html +='<span style="display: block; text-align: center; font-size: 15px;">';
		for (var i = 1; i <= 5; i++) {
			if (i<=item.score_price) {
				html += '<i class="fas fa-star"></i>';
			}else{
				html += '<i class="far fa-star" style="color: black;"></i>';
			}
		}									
		html += '</span></div></div>';								
		html += '<div class="rowa mb" style="position: relative;">';	
		html += '<div style="font-size: 15px; font-weight: 500;">결과</div>';
		html += '<div style="margin-left: 8px; position: absolute; right: 0">';
		html += '<span style="display: block; text-align: center; font-size: 15px;">';
		for (var i = 1; i <= 5; i++) {
			if (i<=item.score_result) {
				html += '<i class="fas fa-star"></i>';
			}else{
				html += '<i class="far fa-star" style="color: black;"></i>';
			}
		}	
		html += '</span></div></div>';							
		html += '<div class="rowa mb" style="position: relative;">';
		html += '<div style="font-size: 15px; font-weight: 500;">시설만족도</div>';
		html += '<div style="margin-left: 8px; position: absolute; right: 0;">';
		html += '<span style="display: block; text-align: center; font-size: 15px;">';								
		for (var i = 1; i <= 5; i++) {
			if (i<=item.score_satisfaction) {
				html += '<i class="fas fa-star"></i>';
			}else{
				html += '<i class="far fa-star" style="color: black;"></i>';
			}
		}						
		html +='</span></div></div></div></div>';				
		html +='<div class="review-in-detail-text-box-content col mtl" style="width: 80%; margin-left: 60px;">';							
		html +='<div class="rowa" style="height: 50px; justify-content: space-between;">';
		html +='<div class="">';								
		if (item.review_type == 1) {
			html +='<div class= "certi fc" style="background-color: #d8e2f3; font-size: 10px;">영수증 인증된 리뷰 </div>';
		}							
		html += '</div><div style="font-size: 13px; color: #9b9b9b;">'+item.ajaxDates+'</div></div>'									
		html +='<div class="mb treatment-tag-box" style="font-weight: bold; letter-spacing: -0.8px; font-size: 180">';					
		html +='받은 치료 : ';					
		$.each(item.treatment, function(indexa, itema) {
			html +='<span>'+itema.m_sub_name+'-'+itema.treatment_name+'</span>';
		})					
		html +='</div>';
		html +='<div class="content-box mb" style="font-size: 15px; font-weight: 300; letter-spacing: -0.6px;">';
		html += item.contents+'</div>';				
		html +='<p class="price-content-box mb" style="font-size: 15px; font-weight: 300; letter-spacing: -0.6px;">';							
		$.each(item.treatment, function(indexa, itema) {
			html +=itema.treatment_name+':'+itema.price+'원';
		})				
		html +='<br></p>';
		if (item.picture != null) {
			html += '<div class="mb rowa">';
			$.each(item.picture, function (indexa, itema) {
				html +='<img src='+itema.pic+'style="height: 150px" alt="" />';
			})
			html +='</div>';
		}				
		if (item.id == data.id) {
			html +='<div class="mb editDelete">';
			html +='<button type="button" class="editBtn" >수정하기';
			html +='<input type="hidden" name="review_code" value="'+item.h_review_code+'"/></button>';
			html +='<button type="button" class="deleteBtn">삭제하기</button></div>';
		}							
		html +='<div class="" style="display: flex; justify-content: space-between;">';	
		html +='<button type="button" value="" class="btn-report" style="color: #b00020; cursor:pointer; font-size: 14px; text-decoration: underline; border: none; background: none;">';
		html +='신고하기</button>';	
		if (item.userlike == 1) {
			html +='<button value="" type="button" class="btn-likes rowa fc" style="background: #ffd4dc">';
			html +='도움이 되었어요 <i class="fas fa-heart" style="margin-left: 10px; margin-right: 10px;" color: #f94d4d"></i>';				                                                                                                                                               
			html +='<div class="">'+item.likes+'</div></button></div>';
		}else {
		html +='<button value="" type="button" class="btn-likes rowa fc">';
		html +='도움이 되었어요 <i class="fas fa-heart" style="margin-left: 10px; margin-right: 10px;"></i>';				                                                                                                                                               
		html +='<div class="">'+item.likes+'</div></button></div>';
		}
		
		html += '<div class="reportbox" id="hreview_reportbox'+item.h_review_code+'" style="display: none">';
		html += '<form class="col" action="/beepPro/manager/reportPosts.do" method="post" onsubmit="return checkReport();">'; 
		html +=	'<select name="report_type" class="question_report_select">';
		html +=	'<option value="1">부적절한 홍보 게시글</option>';
		html +=	'<option value="2">악성코드 신고</option>';
		html +=	'<option value="3">음란성 또는 청소년에게 부적합한 내용</option>';
		html +=	'<option value="4">저작권 침해</option>';
		html +=	'<option value="5">명예훼손</option>';
		html +=	'<option value="6">기타</option>';
		html += '</select>'
		html +=	'<input type="hidden" name="board_seq" value="'+item.h_review_code+'"/>';
		html +=	'<input type="hidden" name="all_board_seq" value="1" />';
		html +=	'<textarea name="contents" class="hreview_report" cols="30" rows="10" placeholder="신고 내용을 입력하세요."></textarea>';
		html +=	'<input type="submit" value="보내기" class="report_submit submit" />';
		html += '</form>';
		html += '</div>';
		
		html +='</div></div></div>';
		html +='</div></div>';

	})
	
	html+='<div class="paging" style="justify-content: center ; display: flex;">';
	
	if (data.paging.prev) {
		html +='<b class="paging-num prev">이전</b>';
	}
	for (var i = data.paging.start; i <= data.paging.end; i++) {
		if (i ==data.paging.currentPage) {
			html +='<b class="paging-num" id="currentPage">'+i+'</b>';
		}else {
			html +='<b class="paging-num" id="page'+i+'">'+i+'</b>';
		}
	}
	if(data.paging.next){
		html +='<b class="paging-num next">다음</b>';
	}
	
	html +='</div>';
	
	$("#review-in-detail-text-box-frame").empty();
	$("#review-in-detail-text-box-frame").html(html);
	
	$('input').css("outline","none");
	$('button').css("outline","none");
	
}


//북마크 취소, 추가 기능

$("#bookmark").on("click", function() {
	console.log("클릭됌");
	var userid = $("#userid").val();
	var h_code = getParam.h_code;
	
	console.log(userid);
	console.log(h_code);
	
	//로그인 했을 시, 북마크 활성화될수있도록 하기
	if (userid == "") {
		alert("로그인 후 이용가능합니다.")
		location.href = "../member/login.do";
	}else {
	
	//북마크를 선택하지 않았을시, 클릭했을때 insert되고, 색깔변경
	if ($("#bookmark i").hasClass("far")) {
		
		$("#bookmark i").toggleClass("far fas").css("color","rgb(234,153,153)");
		$(".bookmark-box p:first").text("찜취소")
		bookMarkAjaxInsert('../review/bookmarkbtn.ajax?book=0&h_code='+h_code+"&id="+userid);

		
	//북마크를	
	}else if($("#bookmark i").hasClass("fas")) {
		$("#bookmark i").toggleClass("far fas").css("color","black")
		$(".bookmark-box p:first").text("찜하기");
		bookMarkAjaxDelete('../review/bookmarkbtn.ajax?book=-1&h_code='+h_code+"&id="+userid);
		
	}
	
	}
})



function bookMarkAjaxDelete(url) {
	
	var data = null;
	$.ajax({
		
		type : 'POST',
		url : url,
		data: data, 
			success : function(data) {
			console.log("success");
			
			if (data.map.result==1) {
				console.log("좋아요 update완료")
				bookMarkAll(data.map.h_code)
				
				/*$('#bookCount'+data.map.h_code).text(data.map.books);*/
			}
		},
		error: function(data) {
			console.log("error")
		},
		complete: function() {
			console.log("완료")
		}	
	
	})
	
	
	
}

function bookMarkAjaxInsert(url) {
	
	var data = null;
	$.ajax({
		
		type : 'POST',
		url : url,
		data: data, 
		success : function(data) {
			console.log("success");
			
			if (data.map.result==1) {
				console.log("좋아요 delete 완료")
				bookMarkAll(data.map.h_code)
				
				/*$('#bookCount'+data.map.h_code).text(data.map.books);*/
			}
		},
		error: function(data) {
			console.log("error")
		},
		complete: function() {
			console.log("완료")
		}	
	
	})
	
	
	
}

function bookMarkAll(h_code) {
	
	var data = null;
	
	$.ajax({
		
		type : 'post',
		url : '../review/allbookMarkCnt.ajax?h_code='+h_code,
		data : data,
		success : function(data) {
			console.log("목록보여주기 완료")
			$('#bookCount').text(data.map.books);
		},
		error: function(data) {
		console.log("보여주기 error")
		},
		complete: function() {
		console.log("완료")
		}	
	
	
	})
}


$("#form1").on("submit", function(e) {
		e.preventDefault();
		
		var formData = $(this).serialize();
		console.log(FormData);
		var text = $("#doctorText").val();
		var h_code = $("#docH_code").val();
		console.log(text);
		console.log(h_code);
})

/* 신고 블록 보이기 */

$("body").on("click", ".btn-report" , function() {
	

	var h_review_code = $(this).parent().next().attr('id');
	
	h_review_code = h_review_code.substr(h_review_code.indexOf('x')+1);
	
	$("#hreview_reportbox"+h_review_code).slideToggle();
	
})

//좋아요 클릭시 +1
$("body").on("click", '.btn-likes' , function() {
	
	var h_review_code = $(this).attr("id");
	h_review_code = h_review_code.substr(h_review_code.lastIndexOf('e')+1);

	var userid = $("#userid").val();
	
	//console.log(h_code);
	

	if (userid == "") {
		alert("로그인 후 이용가능합니다.")
	}else {
	

	if ($(this).hasClass("n")) {
		
		$(this).toggleClass("y n").css("background","#ffd4dc");
		$(this).find("i").css("color","#f94d4d");
		userLikeAjax('../review/hUserLikebtn.ajax?userlike=0&h_review_code='+h_review_code+"&id="+userid);

	
	}else if($(this).hasClass("y")) {
		$(this).toggleClass("y n").css("background","white")
		$(this).find("i").css("color","black");
		userLikeAjax('../review/hUserLikebtn.ajax?userlike=-1&h_review_code='+h_review_code+"&id="+userid);
		
	}
	
	}
});


function userLikeAjax(url) {
	
	var data = null;
	$.ajax({
		
		type : 'POST',
		url : url,
		data: data, 
		success : function(data) {
			console.log("success");
			
			if (data.map.result==1) {
				console.log("좋아요 update or delete 완료")
				console.log(data.map.allLikes+"all..");
				console.log(data.map.h_review_code);
				$('#userAllLikes'+data.map.h_review_code).text(data.map.allLikes);
			}
		},
		error: function(data) {
			console.log("error")
		},
		complete: function() {
			console.log("완료")
		}	
	
	})

}

/*function reportOpen(btn_id){
	var id = btn_id.id;
	var image = btn_id.src;
	var toggle = image.substring(image.length-11,image.length-10);
	var num = id.substring(id.indexOf("00"),id.length);
	var comblockId="#answer_reportbox"+num;
	if(toggle == "e"){
		$("#"+id).attr("src","../beep_images/community-images/redreport.svg");
		$(comblockId).css("display","block");
	}else if(toggle == "d"){
		$("#"+id).attr("src","../beep_images/community-images/whitereport.svg");
		$(comblockId).css("display","none");
	}
}*/