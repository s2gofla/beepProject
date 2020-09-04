$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});
 


$("#hospital-select").on("click", function() {
   		$("#hospitalModal").modal("show");
   		
   		
   		
   		$("#doctorText").val("");
   })
   
   $("#btnSubmit").on("click", function(e) {
		e.preventDefault();
		$("#hSearch").val("검색어를 입력하세요");
		
		var text = $("#hSearch").val();
		var h_code = $(".h_select:selected").val();
		var h_name = $(".h_select:selected").text();
		
		console.log(text);
		console.log(h_code);
		console.log(h_name);
		
		
		var result = $("#hospital-name").text(h_name);
		$('input[name="h_name"]').val(h_name);
		//h_code를 위한 태그 추가
		$("input[name='h_code']").val(h_code);
		console.log('값확인:'+result);
		
		$("#hospitalModal").modal('hide');
})

$("#hSearch").on("click", function() {
	
	$(this).val("");
})

$("body").on("keyup",'#hSearch' ,function(e) {
	
	if (e.keyCode == 13) {
		e.preventDefault();
		
		console.log("지금");
	}
	
	
})


   $("#category-select").on("click", function() {
	   
	   var h_code = "h_code="+$("input[name='h_code']").val();
	   
	   if (h_code == "h_code=" || h_code == undefined ) {
			alert("병원 이름 입력 후 선택하세요");
			return false;
		   }
	   
	   console.log(h_code);

 	   $.ajax({
 		   
 		   type : 'post',
 		   url : '../review/selectAllTreat.ajax',
 		   data : h_code,
 		   success : function(data) {
 			   console.log("모든치료항목 셀렉 성공");
 			   showSubHtml(data.map);
 		},
 		error : function(data) {
 			console.log("모든치료항목 에러");
 		}
 	   
 	   })
 	   
   		
   		
 	   $("#hospitalSubModal").modal("show");
 	   
   })
   
  
   $("#hsub_select").on("change", function() {
   	
	   
	   var m_sub_seq = "m_sub_seq="+$(this).val();
	   console.log(m_sub_seq);
	   
	   $.ajax({
		   
		   type : 'post',
		   url : '../review/selectTreat.ajax',
		   data : m_sub_seq,
		   success : function(data) {
			   console.log("치료항목 셀렉 성공");
			   showHtml(data.list);
		},
		error : function(data) {
			console.log("치료항목 에러");
		}
	   
	   })
	   
	   
	   
	   
  })
  
  
  function showHtml(data) {
	let html = "";
	
	$.each(data, function(index, item) {
		
		
		html += '<option  class="h_treatselect" value="'+item.treatment_code+'">'+item.treatment_name+'</option>'
		
	})
	
	$('#htreat_select').empty();
	$('#htreat_select').html(html);
	
	
}
  
$("#btnTreatSubmit").on("click", function(e) {
	e.preventDefault();

	
	var treatment_code = $(".h_treatselect:selected").val();
	var treatment_name = $(".h_treatselect:selected").text();
	if (treatment_code == null) {
		alert("치료항목을 선택하십시오");
		return false;
	}


	console.log(treatment_code);
	console.log(treatment_name);
	$('#price').append('<div class="price-box odd"><input type="text" value="'+treatment_name+'" readonly="readonly" /><input type="text" name="price" value="가격을 입력하세요" /></div>')
	$('.price-box').append('<input type="hidden" name="treatment_code" value="'+treatment_code+'"/>');
	
	
	$("#hospitalSubModal").modal("hide");
	$('#category-select').text("치료항목 추가");
})


$("body").on("click","input[name=price]" ,function() {
	
	console.log("클릭완료");
	$(this).val("");
})


$('#WriteForm').submit(function(e) {
		
	var kind = $('input[name=score_kind]').val()
	var price = $('input[name=score_price]').val()
	var result = $('input[name=score_result]').val()
	var satis = $('input[name=score_satisfaction]').val()
	
	console.log(kind +"," +price +"," + result +"," + satis)
	
		if ( kind && price && result && satis !="") {
			
		}else {
			alert("점수를 입력해주세요")
			return false;
		}
	
})


  function showSubHtml(data) {
	let html = "";
	
	$.each(data.subTypeList, function(index, item) {
		
		
		html += '<option  class="hsub_select" value="'+item.m_sub_seq+'">'+item.m_sub_name+'</option>'
		
	})
	
	$('#hsub_select').empty();
	$('#hsub_select').html(html);
	
	html = "";
	
	$.each(data.clist, function(index, item) {
		
		
		html += '<option  class="h_treatselect" value="'+item.treatment_code+'">'+item.treatment_name+'</option>'
		
	})
	
	$('#htreat_select').empty();
	$('#htreat_select').html(html);
	
	
}
   