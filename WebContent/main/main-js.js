$.ajaxSetup({
	type: "POST",
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});
$(".go-tip a").on("click",function(e){
	e.preventDefault();
	
	var id = $(this).attr("id");
	var code = id.substring(id.indexOf("pic")+3, id.length);
	ToptipAjax(code);
	$("#image1").ekkoLightbox();
});

function ToptipAjax(code){
	var tt_code = "tt_code="+code;
	$.ajax({
		url: "/beepPro/information/toptip_attach.ajax",
		data: tt_code,
		success: function(data){
			console.log("success");
			console.log(data);
			toptipModal(data);
			console.log("bb");
		},
		error: function(data){
			console.log("꿀팁사진 error");
		},
		complete:function(){
			console.log("꿀팁사진 완료 후");
		}
	});
}
function toptipModal(data){
	let content = "";
	$.each(data.dto.list, function(index, item){
		if(index==0){
			content += "<a href='"+item+"' data-toggle='lightbox' id='image1' data-gallery='example-gallery' class='col-sm-4' data-title='> "+data.dto.title+"'</a>";
		}else{
			content += "<a href='"+item+"' data-toggle='lightbox' data-gallery='example-gallery' class='col-sm-4'></a>";
		}
	})
	$("#image-md").empty();
	$("#image-md").html(content);
}


$(function(){
	
	$("#search1").autocomplete({
		source:function(request, response){
			$.ajax({
				url: "/beepPro/main/autocomplete_1.ajax",
				dataType:"json",
				data:{
					"autoWord":$("#search1").val()
				},
				success: function(data){
					response(
							$.map(data, function(item){
								return{
									label: item.data,
									value: item.data
								}
							}));
				},
				error: function(jqxhr, status, error){
					console.log(jqxhr.statusText+", "+status+", "+error);
				}
			})
		},
		autoFocus: true,
		matchContains: true,
		minLength: 1,
		delay: 100,
		select:function(event, ui){
			
		}
	})
})


$(document).ready(function() {
	var searchlink1 = document.getElementById("searchlink1");
	var search1 = document.getElementById("search1");
	searchlink1.addEventListener('click', function() {
		search1.focus();
	})
});
$('#image-map area').hover(
		
		function () { 
			var coords = $(this).attr('coords').split(','),
			width = $('.image-map-container').width(),
			height = $('.image-map-container').height();
			$('.image-map-container .map-selector').addClass('hover').css({
				'left': coords[0]+'px',
				'top': coords[1] + 'px',
				'right': width - coords[2],
				'bottom': height - coords[3]
			})
		},
		function () { 
			$('.image-map-container .map-selector').removeClass('hover').attr('style','');
		}
)
 $("#searchlink2").click(function(){
		var location = document.querySelector("#mainsection2").offsetTop-120;
		window.scrollTo({top:location, behavior:'smooth'});
		var search2 = document.getElementById("search2");
		search2.focus();
 });

function content_search1(search) {
	var content = search.title;
	var input = document.getElementById("search1");
	input.value = "#"+content;
	input.focus();
}

$(".search-td a").on("click", function(e){
	e.preventDefault();
	var id= $(this).attr("id");
	var m_sub_name = $(this).children().attr("alt");
	var m_sub_seq = id.substring(id.indexOf("hos")+3,id.length);
	msubNameAjax(m_sub_seq, m_sub_name);
	$("#myModal").modal();
})
function msubNameAjax(m_sub_seq, m_sub_name){
	var m_sub_seq = "m_sub_seq="+m_sub_seq+"&m_sub_name="+m_sub_name;
	$.ajax({
		url: "/beepPro/main/msubName.ajax",
		data: m_sub_seq,
		success: function(data){
			console.log("success");
			console.log(data);
			msubNameModal(data.dto);
		},
		error: function(data){
			console.log("msubname error");
		}
	})
}

function msubNameModal(data){
	var cnt = Math.ceil(data.cnt/2);
	let html = "";
	$("#myModalLabel").text("> "+data.m_sub_name+" 치료항목 찾기");
	
	for (var i = 1; i <= cnt; i++) {
		html +="<div class=\"modal-row\">";
		for (var j = i*2-2; j < i*2; j++) {
			html +="<div class=\"modal-col\"><span id=\"tr"+j+"\" onclick=\"colClick(this);\">#"+data.list[j]+"</span></div>"
		}
		html += "</div>";
	}
	$("#modal-tbody").empty();
	$("#modal-tbody").html(html);
	
}

function colClick(col){
	var id = col.id;
	var text = $("#"+id).text();
	$("#myModal").modal('hide');
	$("#search2").val(text);
}

function searchFLocation(){
	var val = $("#search1").val();
	var subValue = val.substring(1, val.length);
	console.log(subValue);
	location.href="/beepPro/main/diseaseSearch.do?search="+subValue;
}

function searchSLocation(){
	var val = $("#search2").val();
	var subValue = val.substring(1, val.length);
	console.log(subValue);
	location.href="/beepPro/main/treatSearch.do?search="+subValue;
}









