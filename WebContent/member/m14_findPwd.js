$.ajaxSetup({
	type: "POST",
	async: true,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});



	
$("#m_findPWD_btn").on("click", function(e){
	e.preventDefault();
	var id= $("input[name=id]").val();
	var name= $("input[name=name]").val();
	var birth= $("input[name=birth]").val();



	pwdCheckAjax(name, birth, id);
	
})

function pwdCheckAjax(name, birth, id){
	$.ajax({
		
		
		url: "/beepPro/member/findPwd.ajax",
		data: "name=" + name + "&birth=" + birth + "&id=" + id, 
		
		success: function(data){
			console.log("success");
			pwdcheckOut(data.result);
		},
		error: function(data){
			console.log("진료과 리스트 error");
		},
		complete:function(){
			console.log("진료과 리스트 완료 후");
		}
	})
}

	function pwdcheckOut(result){
	
	
		
		if( result == " " || result == null ){
			 $("<div style='color:red;'><p class='pp1'>등록된 회원 정보가 없습니다.</p></div>").insertAfter("#id");
			 
		
		}else{
			 $(".pp1").hide();
			var id= $("input[name=id]").val();
			
			 $("#m_findPWD_btn").attr('value','변경하러가기');
			 
			 $("#m_findPWD_btn").on("click", function(e){
				 location.href = '/beepPro/member/showChangePwd.do?id='+id ;
			 });
		}
	};
	



