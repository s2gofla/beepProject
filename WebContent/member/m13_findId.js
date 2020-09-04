$.ajaxSetup({
	type: "POST",
	async: true,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});

	
	
$("#m_findID_btn").on("click", function(e){
	e.preventDefault();
	
	var tel= $("input[name=tel]").val();

		idCheckAjax(tel);
	
})

function idCheckAjax(tel){
	$.ajax({
		
		url: "/beepPro/member/findId.ajax",
		data: "tel="+tel, 
		
		success: function(data){
			console.log("success");
			pcheckOut(data.result);
		},
		error: function(data){
			console.log("진료과 리스트 error");
		},
		complete:function(){
			console.log("진료과 리스트 완료 후");
		}
	})
}

	function pcheckOut(result){
	
		
		
		if( result == " " || result == null ){
			
			 $("<div style='color:red;'><p class='pp1'>등록된 번호가 없습니다.</p></div>").insertAfter("#tel");
			
			 
		
		}else{
			 $(".pp1").hide();
			 $("<div style='color:red;'><p class='pp2'>아이디는 [ " +  result  + " ]입니다.</p></div>").insertAfter("#tel");
			 
			
		}
	};




