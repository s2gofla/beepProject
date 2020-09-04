
$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});


$("body").on("click", '.bookmarkCnt i' , function() {
	
	var id = $(this).attr("id");
	console.log('id값'+id);
	var num = id.substr(id.lastIndexOf('k')+1);
	
	var h_code = $("input[name='h_code']").eq(num-1).val();
	//console.log(h_code+"h_code")
	var userid = $("#userid").val();
	console.log(userid);
	//console.log(h_code);
	
	//로그인 했을 시, 북마크 활성화될수있도록 하기
	if (userid == "") {
		alert("로그인 후 이용가능합니다.")
		location.href = "../member/login.do";
	}else {
	
	//북마크를 선택하지 않았을시, 클릭했을때 insert되고, 색깔변경
	if ($(this).hasClass("far")) {
		
		$(this).toggleClass("far fas").css("color","#9ab3de");
		bookMarkAjaxInsert('../review/bookmarkbtn.ajax?book=0&h_code='+num+"&id="+userid);

		
	//북마크를	
	}else if($(this).hasClass("fas")) {
		$(this).toggleClass("far fas").css("color","black")
		bookMarkAjaxDelete('../review/bookmarkbtn.ajax?book=-1&h_code='+num+"&id="+userid);
		
	}
	
	}
});


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
				
				bookMarkAll(data.map.h_code);
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
			$('#bookCount'+data.map.h_code).text(data.map.books);
		},
		error: function(data) {
		console.log("보여주기 error")
		},
		complete: function() {
		console.log("완료")
		}	
	
	
	})
	
	
	
}


// ajax 정렬 처리
$(".search-btn-group button").click(function() {
	
	var num = $(this).index(); //0, 1, 2
	var url = $(location).attr('href');
	console.log("url:  "+url);
	if (url.indexOf('?')!= -1) {
		url = url.substr(url.indexOf("?")+1);
		url +="&type="+num;
		console.log(url);
	
	}else {
		url ="type="+num;
		console.log(url);
	}
	console.log(url);
	
	
	$.ajax({
	
		type : 'post'
		,url : '../review/arrayHinfo.ajax'
		,data : url
		,success : function(data) {
			console.log("병원정렬 성공");
			console.log(data.list);
			showHtml(data.list);
			
		},
		error : function(data) {
			console.log("병원 정렬 에러");
		}
		
	})
	
	
})

function showHtml(data) {
	let html = "";
	$.each(data, function(index, item) {
		html += '<div class="hinfo-box">';
		html +=	'<div class="hinfo-title">';
		html +=	'<input type="hidden" name="h_code" value="'+item.h_code+'" />';
		html +=	'<a href="./hreview_detail.do?h_code='+item.h_code+'&h_name='+item.h_name+'" style="color: black;">'+item.h_name+'</a>';
		html += '<span class="bookmarkCnt">';
		if(item.isBookMark == 1){
			html += '<i class="fas fa-bookmark" style="cursor: pointer; color:#9ab3de;" id="bookmark'+item.h_code+'"'+' ></i>'; 
		}else {
			html += '<i class="far fa-bookmark" style="cursor: pointer;" id="bookmark'+item.h_code+'"'+' ></i>';
		}
		html += '<span id="bookCount'+item.h_code+'" style="font-size: 14px; vertical-align: top;">'+item.bookmark_count+'</span>';
		html += '</span></div>';
		html += '<div style="font-size: 13px; padding: 5px 0; margin-top: 5px 0">';
		$.each(item.sub, function(indexa, itema) {
				html += '<span style="font-weight: bold; background-color: #dbf2ff;">'+itema.m_sub_name+'</span>';
		})	
		html +='</div><p class="hinfo-adress">';
		html +='<i class="fas fa-map-marker-alt"></i>'+item.h_address+'</p>';
		html +='<div class="hinfo-star" style="margin: 6px 0;"><span>';
		for (var i = 1; i <= 5; i++) {
			if (i<=item.star_score) {
				html += '<i class="fas fa-star"></i>';
			}else{
				html += '<i class="far fa-star" style="color: black;"></i>';
			}
		}
		html +='</span> <span style="padding-left: 8px; color: #6c757d; margin-right:10px;">'+item.star_score+'</span>';
		html +='<span style="font-size: 12px; color: #6c757d;">('+item.reviewer+')</span></div>';	
		html +='<div style="font-size: 13px; padding: 5px 0;">';
		$.each(item.special, function(indexa, itema) {
			html += '<span style="font-weight: bold; background-color:#ffe6ed;">'+ itema.specialty_name +'</span>';
		})
	
		html +="</div></div>";		

	})
	$("#hinfoList-box").empty();
	
	$("#hinfoList-box").html(html);
	$(".hinfo-adress").css("cursor","pointer");
	$(".bookmarkCnt i").css( {"cursor":"pointer", "padding-right":"5px"});
	$(".hinfo-star i").css("padding-right","5px");
	
	
}
