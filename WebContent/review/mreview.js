
$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});


$(".bookmarkCnt i").on("click", function() {
	
	var id = $(this).attr("id");
	var num = id.substr(id.lastIndexOf('k')+1);
	var m_code = $("input[name='m_code']").eq(num).val();
	var userid = $("#userid").val();
	console.log(userid);
	
	
	//로그인 했을 시, 북마크 활성화될수있도록 하기
	if (userid == "") {
		alert("로그인 후 이용가능합니다.")
		location.href = "../member/login.do";
	}else {
	
	//북마크를 선택하지 않았을시, 클릭했을때 insert되고, 색깔변경
	if ($(this).hasClass("far")) {
		
		$(this).toggleClass("far fas").css("color","#9ab3de");
		bookMarkAjaxInsert('../review/Mbookmarkbtn.ajax?book=0&num='+num+'&m_code='+m_code+"&id="+userid);

		

	}else if($(this).hasClass("fas")) {
		$(this).toggleClass("far fas").css("color","black")
		bookMarkAjaxDelete('../review/Mbookmarkbtn.ajax?book=-1&num='+num+'&m_code='+m_code+"&id="+userid);
		
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
				console.log("좋아요 delete완료");
				console.log(data.map.num);
				bookMarkAll(data.map.m_code, data.map.num)
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
				console.log("좋아요 delete 완료"+data.map.num)
				console.log(data.map.num);
				console.log(data.map.m_code);
				bookMarkAll(data.map.m_code, data.map.num);
			
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

function bookMarkAll(m_code, num) {
	
	var data = null;
	
	$.ajax({
		
		type : 'post',
		url : '../review/MallbookMarkCnt.ajax?m_code='+m_code+'&num='+num,
		data : data,
		success : function(data) {
			console.log("목록보여주기 완료")
			console.log(data.map.num)
			$('#bookCount'+num).text(data.map.books);
		},
		error: function(data) {
		console.log("보여주기 error")
		},
		complete: function() {
		console.log("완료")
		}	
	
	
	})
	
	
	
}


$(".fcheckbox-title").on("click", function() {
	
	var num = Number($(this).index())+1;
	
	var bpurpose_code = "bpurpose_code="+num;
	console.log(bpurpose_code);
	
	$(this).toggleClass("activeB");
	
	if ($(this).attr("id")== "title1") {
		
		$("#title2").removeClass("activeB");
		
	}else {
		$("#title1").removeClass("activeB");
	}
	
	$.ajax({
		
		type : 'post'
		,url : '../review/selectPurpose.ajax'
		,data : bpurpose_code
		,success : function(data) {
			console.log("약 용도정렬 성공");
			console.log(data.list);
			showHtml(data.list);
			
		},
		error : function(data) {
			console.log("약 용도 정렬 에러");
		}
		
	})
	
	
})


function showHtml(data) {
	
	let html = "";
	$.each(data, function(index, item) {
		html += '<li><label class="fcheckbox-option">';
		html +='<input type="checkbox" name= "mpurpose" class="fcheckbox-option" value="'+item.purpose_code+'"/>';
		html +='<span class="fcheckbox-name">'+item.purpose_name+'</span>';
		html +='</label></li>';
	})
	
	$('.fcheckbox-body ul').empty();
	$('.fcheckbox-body ul').html(html);
	
}

function getUrlParams() {
	var params = {};
	window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi,
			function(str, key, value) {
				params[key] = value;
			});
	return params;
}

var getParam = getUrlParams();

$("#mSearch").on("change", function() {

	var num = $('.mselect-option:selected').val(); //1,2,3
	var searchWord = getParam.searchWord == null ? "" : getParam.searchWord;
	var mpurpose = getParam.mpurpose == null ? "" : getParam.mpurpose;
	var currentP = getParam.currentP == null ? 1 : getParam.currentP;
	
	
	console.log(num);
	var url = "?num="+num+"&searchWord="+searchWord+"&mpurpose="+mpurpose+"&currentP="+currentP;
	
	location.href = '../review/mreview_list.do'+url;
	
})


