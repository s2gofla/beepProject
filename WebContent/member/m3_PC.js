$.ajaxSetup({
	type: "POST",
	async: true,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});

$(function(){

	$(".m_GPC_button1").on('click', function(){

		var patt = new RegExp("[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}");
		var tel = $("#tel").val();
		var res = patt.test( tel );

		if( !patt.test( tel ) ){
			alert("전화번호를 정확히 입력하여 주십시오.");
		}else{
			phoneCheckAjax(tel);
		}

	});
	function phoneCheckAjax(tel){
		$.ajax({
			url: "/beepPro/member/phoneCheck.ajax",
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
	};
	function pcheckOut(result){
		if(result==1){
			alert("번호가 중복됩니다. 다시 확인해주세요");
		}else{
			alert("가입 가능한 휴대폰번호 입니다. 다음버튼을 눌러 진행하세요.");
			$("#m_GPC_button2").attr("disabled",false);
			$("#m_GPC_button2").css("background-color","rgb(224,102,102)");
		}

	};
	$(".m_GPC_button2").on('click', function(){
		var ctype = $('input[name=ctype]').val();
		var mcode = $('input[name=mcode]').val();
		var gcode = $('input[name=gcode]').val();
		var tel = $('input[name=tel]').val();
		
		location.href="/beepPro/member/signup.do?ctype="+ctype+"&mcode="+mcode+"&gcode="+gcode+"&tel="+tel;	
	});
	 

});
