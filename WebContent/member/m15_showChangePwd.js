
function checkValue(){
	if(document.userInfo.newPwd1.value != document.userInfo.newPwd1.value){
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	// 숫자, 특수문자 1회이상, 영문은 2자이상 최소 8자리 이상 입력
	var regExpPw =  /(?=.*\d{1,10})(?=.*[~`!@#$%\^&*()-+=]{1,10})(?=.*[a-zA-Z]{2,15}).{8,20}$/;
	if(!regExpPw.test(document.userInfo.newPwd1.value)){
		alert("비밀번호는 숫자와 영문자 특수문자를 포함하여 8글자 이상 입니다.");
		return false;
	}
	return true;
};






