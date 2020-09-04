$.ajaxSetup({
	type: "POST",
	async: true,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});


function checkValue(){
	if(document.userInfo.password.value != document.userInfo.passwordCheck.value){
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	// 숫자, 특수문자 1회이상, 영문은 2자이상 최소 8자리 이상 입력
	var regExpPw =  /(?=.*\d{1,10})(?=.*[~`!@#$%\^&*()-+=]{1,10})(?=.*[a-zA-Z]{2,15}).{8,20}$/;
	if(!regExpPw.test(document.userInfo.password.value)){
		alert("비밀번호는 숫자와 영문자 특수문자를 포함하여 8글자 이상 입니다.");
		return false;
	}
	if(!$('#m_infoG_id').prop("readonly")){
		alet("아이디 중복체크 해야합니다.");
		return false;
	}
	if(!$('#m_infoG_nickname').prop("readonly")){
		alet("닉네임 중복체크 해야합니다.");
		return false;
	}
	
	return true;
}

$("#m_infoG_checkID").on("click", function(e){
	e.preventDefault();
	var id = $("input[name=id]").val();
	// 아이디는 숫자와 영문자만 가능
	var regExpId = /^[0-9a-z]+$/;
	if(!regExpId.test(id)){
		$("#id-text1").css("display","inline");
		$("#id-text2").css("display","none");
		$('#m_infoG_id').css("border","red solid 2px");
	}else{
		$("#id-text1").css("display","none");
		$('#m_infoG_id').css("border","black solid 1px");
		idCheckAjax(id);
	}
})



$("#m_infoG_checkNickname").on("click", function(e){
	e.preventDefault();

		var nickname= $("input[name=nickname]").val();
		$('#m_infoG_nickname').css("border","black solid 1px");
		nicknameCheckAjax(nickname)
	
})



function idCheckAjax(id){
	$.ajax({
		url: "/beepPro/member/idCheck.ajax",
		data: "id="+id,
		success: function(data){
			console.log("success");
			idcheckOut(data.result);
		},
		error: function(data){
			console.log("진료과 리스트 error");
		},
		complete:function(){
			console.log("진료과 리스트 완료 후");
		}
	})
}



function nicknameCheckAjax(nickname){
	$.ajax({
		url: "/beepPro/member/nicknameCheck.ajax",
		data: "nickname="+nickname,
		success: function(data){
			console.log("success");
			nicknamecheckOut(data.result);
		},
		error: function(data){
			console.log("진료과 리스트 error");
		},
		complete:function(){
			console.log("진료과 리스트 완료 후");
		}
	})
}



function idcheckOut(result){
	if(result==1){
		alert("아이디가 중복됩니다.");
		$("#m_infoG_id").val("");
		$('#m_infoG_id').focus();
		$("#id-text2").css("display","none");
		$("#m_info_editId").css("display","none");
		inputDisabled();
		
	}else{
		$("#id-text2").css("display","inline");
		$("#m_info_editId").css("display","inline");
		$("#m_infoG_id").attr("readonly",true);
		$("#m_infoG_pwdText1").attr("disabled",false);
		$("#m_infoG_pwdText2").attr("disabled",false);
		$("#m_infoG_nickname").attr("disabled",false);
		$("#m_infoG_name").attr("disabled",false);
		$("#m_infoG_birth").attr("disabled",false);
		$("#email1").attr("disabled",false);
		$("#email2").attr("disabled",false);
		$("#m_infoD_licenseNo").attr("disabled",false);
		$("#m_info_lfile").attr("disabled",false);
	

		
		
	}

};

function nicknamecheckOut(result){
	if(result==1){
		alert("닉네임이 중복됩니다.");
		$("#m_infoG_nickname").val("");
		$('#m_infoG_nickname').focus();
		$("#nickname-text1").css("display","none");
		$("#m_info_editNickname").css("display","none");
		inputDisabled2();
		
	}else{
		$("#nickname-text1").css("display","inline");
		$("#m_info_editNickname").css("display","inline");
		$("#m_infoG_nickname").attr("readonly",true);
		$("#m_infoG_name").attr("disabled",false);
		$("#m_infoG_birth").attr("disabled",false);
		$("#email1").attr("disabled",false);
		$("#email2").attr("disabled",false);
		$("#m_infoD_licenseNo").attr("disabled",false);
		$("#m_info_lfile").attr("disabled",false);
	

		
		
	}

};

$("#m_info_editId").on('click',function(){
	$("#id-text2").css("display","none");
	$("#m_infoG_id").attr("readonly",false);
	$('#m_infoG_id').focus();
	inputDisabled();
})


$("#m_info_editNickname").on('click',function(){
	$("#nickname-text1").css("display","none");
	$("#m_infoG_nickname").attr("readonly",false);
	$('#m_infoG_nickname').focus();
	inputDisabled2();
})




function inputDisabled(){
	$("#m_infoG_pwdText1").attr("disabled",true);
	$("#m_infoG_pwdText2").attr("disabled",true);
	$("#m_infoG_nickname").attr("disabled",true);
	$("#m_infoG_name").attr("disabled",true);
	$("#m_infoG_birth").attr("disabled",true);
	$("#email1").attr("disabled",true);
	$("#email2").attr("disabled",true);
	$("#m_infoD_licenseNo").attr("disabled",true);
	$("#m_info_lfile").attr("disabled",true);
}

function inputDisabled2(){

	$("#m_infoG_name").attr("disabled",true);
	$("#m_infoG_birth").attr("disabled",true);
	$("#email1").attr("disabled",true);
	$("#email2").attr("disabled",true);
	$("#m_infoD_licenseNo").attr("disabled",true);
	$("#m_info_lfile").attr("disabled",true);
}

