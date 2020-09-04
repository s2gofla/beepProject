$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});
/*$('#myModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget);
	  var recipient = button.data('whatever');
	  
	  var modal = $(this);
	  modal.find('.modal-title').text('New message to ' + recipient);
	  modal.find('.modal-body input').val(recipient);
	})*/
$("#wrap a").on("click", function(e){
	e.preventDefault();
	var id = $(this).attr("id");
	var code = id.substring(9,id.length);
	priceCodeAjax(code);
	$("#myModal").modal();
});

$("#mSearch").on("change" , function(e){
	//e.preventDefault();
	var optionV= $("#mSearch option:selected").val();
	console.log(optionV);
	subjectMatching(optionV);
})


function subjectMatching(optionV){
	var m_sub_seq = "m_sub_seq="+optionV;
	$.ajax({
		url: "/beepPro/information/subjectMatching.ajax",
		data:m_sub_seq,
		success:function(data){
			console.log(data);
			console.log("success");
			matching(data.view);
		},
		error:function(data){
			
			console.log("진료과목과 치료 매칭 실패")
		},
		complete:function(){
			console.log("매칭완료 후");
		}
	})
}

function matching(data){
	let html="";
	let page = "";
	if(data.list.length>0){
		$.each(data.list, function(index, item){
			html += "<a href='#' class='flatbtn' id='info-code'" +item.pinfo_code + ">";
			html += "<h3 class='stress'>" +item.pinfo_treatment+"</h3>";
			html += "</a>";	

		});
	    var start = data.start;
	    var end = data.end;
	    var currentPage = data.currentPageNumber;
	    console.log(start);
	    console.log(end);
	    
	    if(data.prev==true){
	       console.log("prev");
	       page += "/beepPro/information/price_info.ajax?page="+ (start -1) +"&searchWord="+ data.searchWord+"&m_sub_seq="+data.m_sub_seq;
	    }
	    for (var i = start ; i < end+1; i++) {
	       console.log("i: "+i);
	       if( i == currentPage){
	    	   page += "<span style='background: rgb(255,207,77)'>"+i+"</span>";
	       }else{
	    	   page += "<a href='/beepPro/information/price_info.ajax?page="+i+"&searchWord="+ data.searchWord+"&m_sub_seq="+data.m_sub_seq+"' >"+i+"</a>";
	       }
	    }
	    if(data.next==true){
	       console.log("next");
	       page += "<a href='/beepPro/information/price_info.ajax?page="+ (end+1)+"&searchWord="+ data.searchWord+"&m_sub_seq="+data.m_sub_seq+"'>&raquo;</a>";
	    }
	   
	}else{
		html += "<div class='no-register'>등록된 정보가 없습니다.</div>"
	}

	
	$(".pageBlock").empty();
	$(".pageBlock").html(page);

	$("#wrap").empty();
	$("#wrap").html(html);


}

function priceCodeAjax(code){
	var pinfo_code = "pinfo_code="+code;
	$.ajax({
		url: "/beepPro/information/price-info.ajax",
		data: pinfo_code,
		success: function(data){
			console.log("success");
			priceModal(data.dto);
		},
		error: function(data){
			console.log("가격정보 error");
		},
		complete:function(){
			console.log("가격정보 완료 후");
		}
	})
}

function priceModal(data){
	var max_price = comma(data.max_price);
	var avg_price = comma(data.avg_price);
	var min_price = comma(data.min_price);
	
	let html = "";
	html += "     <div class='modal-dialog' role='document'>";
	html += "<div class='modal-content'>";
	html += "<div class='modal-header'>";
	html += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
	html += "<h4 class='modal-title' id='myModalLabel'>"+data.pinfo_treatment+"</h4>";
	html += " </div>";
	html += " <div class='modal-body'>";
	html += "<div class='modal-price'>     <p><b>최대 가격:</b> 85000 원 <br /></p>";
	html += "        	<p><b>평균 가격:</b> 50000 원 <br /></p>";
	html += "        	<p><b>최소 가격:</b> 4800   원 <br /></p>";
    html += " 	</div> ";
	html += "<div class='modal-comment'> - 삐용삐용은 해당 가격정보로 인한 불이익에 법적인 책임이 없습니다. <br/> - 이 자료는 참고자료로 사용하세요. <br />  	- 치료항목에 대한 비용은 삐용삐용 해당리뷰 및 자체조사에 근거합니다.</div>";
	html += "</div>";
	html += " <div class='modal-footer'>";
	html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
	html += "</div>";
	html += " </div>";
	html += "</div>";
	
	$("#myModal").empty();
	$("#myModal").html(html);
	
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}	
	
	
