<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" 	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
 -->
<link rel="stylesheet" href="community-style/content.css?var=122" type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="community-style/list.css?var=122" type="text/css" charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-게시글</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});

	// 좋아요
	var cnt1 = 1;
	function imgToggle1() {
		var img1 = document.getElementById("img1");
		var imgi = document.getElementById("img2");
		if (cnt1 % 2 == 1) {
			img1.src = "../beep_images/community-images/redlike.svg";
			img2.src = "../beep_images/community-images/whitelike.svg";
		} else {
			img1.src = "../beep_images/community-images/whitelike.svg";
			img2.src = "../beep_images/community-images/redlike.svg";
		}
		cnt1++;
	}
	
	// 댓글 신고
	var cnt2 = 1;
	function imgToggle2() {
		var img3 = document.getElementById("img3");
		var imgii = document.getElementById("img4");
		if (cnt2 % 2 == 1) {
			img3.src = "../beep_images/community-images/redreport.svg";
			img4.src = "../beep_images/community-images/whitereport.svg";
		} else {
			img3.src = "../beep_images/community-images/whitereport.svg";
			img4.src = "../beep_images/community-images/redreport.svg";
		}
		cnt2++;
	}
	
	// 게시글 신고
		// 신고
	var cnt3 = 1;
	function imgToggle3() {
		var img5 = document.getElementById("img5");
		var imgiii = document.getElementById("img6");
		if (cnt3 % 2 == 1) {
			img5.src = "../beep_images/community-images/redreport.svg";
			img6.src = "../beep_images/community-images/whitereport.svg";
		} else {
			img5.src = "../beep_images/community-images/whitereport.svg";
			img6.src = "../beep_images/community-images/redreport.svg";
		}
		cnt3++;
	}
</script>

<style>
#modal {

	display:none;

  position:relative;
  width:100%;
  height:100%;
  z-index:1;
}

#modal2 h2 {
  margin:0;   
}

#modal2 input {
  display:inline-block;
  width:100px;
  margin-left:calc(100% - 100px - 10px);
}

#modal2 .modal_content {
  width:300px;
  margin:100px auto;
  padding:20px 10px;
  background:#fff;
  border:2px solid #666;
}

#modal2 .modal_layer {
  position:fixed;
  top:0;
  left:0;
  width:100%;
  height:100%;
  background:rgba(0, 0, 0, 0.5);
  z-index:-1;
}   
</style>

</head>
<body>
	<header id="header"></header>


	<div class="layout-main">
		<div class="layout-left-block">
			<ul>
				<p class="communitystyle">
					<img src="../beep_images/community-images/chat.svg" alt="" />COMMUNITY
				</p>
				<ul class="community">
					<hr />
					<li><a href="#" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />치료를 위한
							Tip</a></li>
					<ul class="disease-tip">
						<li><a href="#">&gt; 치과</a></li>
						<li><a href="">&gt; 피부과</a></li>
						<li><a href="">&gt; 성형외과</a></li>
						<li><a href="">&gt; 안과</a></li>
						<li><a href="">&gt; 산부인과</a></li>
						<li><a href="">&gt; 비뇨기과</a></li>
						<li><a href="">&gt; 정신건강의학과</a></li>
						<li><a href="">&gt; 정형외과</a></li>
						<li><a href="">&gt; 마취통증의학과</a></li>
						<li><a href="">&gt; 신경외과</a></li>
						<li><a href="">&gt; 재활의학과</a></li>
					</ul>
					<hr />
					<li><a href="#"><img
							src="../beep_images/community-images/communication.svg" alt="" />자유게시판</a></li>
					<hr />
					<li><a href="#"><img
							src="../beep_images/community-images/donation.svg" alt="" />기부/나눔</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">
			<p id="smalltitle">&gt; 치료를 위한 Tip &gt; 치과</p>

			<div class="contentmem">
				<h1>아파요</h1>
				<div class="mem">
					<img src="../beep_images/community-images/girl.svg" alt="" />sujin330
					중수고객
				</div>
				<span>2020-07-08 13:02:10 | 조회 5</span> 
				<input type="button" value="목록" onclick="location.href='list.jsp'" />
				<input type="button" value="수정" />
				<input type="button" value="삭제" id="btnModalDelete" data-toggle="modal" data-target="#myModal" />
				<hr />
				
				<!-- 삭제를 하기위한 모달창 div -->
			
<div id="dialog-form" align="center" title="삭제">
  <h2>삭제하기</h2>
  <form action="" method="post">   /jspPro/cstvsboard/delete.htm
<table>
  <tr>
    <td colspan="2" align="center"><b>글을 삭제합니다</b></td>
  </tr>
  <tr>
    <td align="center">비밀번호</td>
    <td>
      <input type="password" name="pwd" size="15" autofocus="autofocus">
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <input type="submit" value="삭제">&nbsp;&nbsp;
      <input type="button" id="cancel" value="취소">
    </td>
  </tr>
</table> 

<input type="hidden" name="seq" value="1" />
</form>
</div>

				<!-- 삭제를 하기위한 모달창 div -->
				
				
				<br />
				<!-- 내용 -->
				<div class="contentcon">
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
					<div>너무 졸려요</div>
				</div>

				<br />
				<!-- 밑에 -->
				<div class="conunder">
				<!-- 태그 -->
				<div class="contenthashtag">
					<button type="button" onclick="">
						<span>#안과</span>
					</button>
					<button type="button" onclick="">
						<span>#눈</span>
					</button>
					<button type="button" onclick="">
						<span>#피로</span>
					</button>
				</div>
				<!-- 태그 -->
				
				<div class="combtns1" >
					<img src="../beep_images/community-images/whitereport.svg" alt="" id="img5"  onclick="imgToggle3()" /> 
					<img src="../beep_images/community-images/redreport.svg" alt="" id="img6"/>
				</div>
				
				</div> <!-- 밑 -->

				<hr />

				<!-- 댓글달기 -->
				<br />
				<div class="contentcommentw">
					<textarea name="" id="commentArea" cols="30" rows="10"></textarea>
					<input type="submit" value="등록" />
				</div>
				<!-- 댓글달기 -->

				<br />
				<hr />
				<br />

				<!-- 댓글목록 -->
				<div class="contentcommentl">
					<img src="../beep_images/community-images/girl.svg" alt="" />
					<div>민정</div>
					<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
					<div>2020-07-08 20:00:00
				<input type="button" value="목록" />
				<input type="button" value="수정" />
				<input type="button" value="삭제" id="modal_open_btn" />
				</div>
				
				<!-- 모달창 -->
				<div id="modal2">
					<div class="modal_content">
						<h2>삭제</h2>
						<p>삭제하기</p>
						<input type="button" id="modal_close_btn" value="닫기" />
					</div>
					<div class="modal_layer"></div>
					
				</div>

				
					<br />
					
				<div id="loginmodal" style="display:none;">
	<h2>LOGIN</h2>
	<div class="p_c_text">회원이 되시면 여러가시 혜택을 누리실 수 있습니다.</div>
	<div class="login_line">
		<div class="box_in">
		<input type="text" name="id" id="id" size="23">
		<input type="text" name="pw" id="pw" size="23">
		</div>
		<span class="btn_login"><a href="">LOGIN</a></span>
	</div>
	<div class="find_join"><a href="">아이디 / 비밀번호 찾기</a> | <a href="">회원가입</a></div>
</div>
					
				</div>
				<!-- contentcommentl -->
				<div class="concom">너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요너무 졸리고
					힘들어요너무 졸리고 힘들어요너무 졸리고 힘들어요</div>
				<br />
				<div class="combtns">
					<input type="submit" id="combtn" value="답글달기" /> 
					
					<img src="../beep_images/community-images/whitelike.svg" alt="" id="img1"  onclick="imgToggle1()" /> 
					<img src="../beep_images/community-images/redlike.svg" alt="" id="img2"/>
					
					<div class="likes">2</div>
					
					<img src="../beep_images/community-images/whitereport.svg" alt="" id="img3"  onclick="imgToggle2()" /> 
					<img src="../beep_images/community-images/redreport.svg" alt="" id="img4"/>
				</div>

			</div>
			<!-- contentmem -->





		</div>
		<!-- layout-right-block -->
	</div>
	<!-- layout-main -->

	<div id="footer"></div>
	
	<script>
	
	var selectcate = 0;
	$("#atag").click(function () {
	   //alert("hello");
	   if(selectcate==0){
	      $('.disease-tip').animate({height: "300px"}, 800);
	      selectcate=1;
	   }else{
	      $('.disease-tip').animate({height: "0" }, 800);
	      selectcate=0;
	   }
	   
	}); 
	
	
	// 모달창
	
 $(function (){
	 $("#cancel").click(function (){
		 // dialog 객체 dialog 메서드
		 dialog.dialog( "close" );
	 });
	 $("#btnModalDelete").click(function (){
		 dialog.dialog( "open" );
	 });
	 
	 //function addUser(){ }
	 
	 dialog = $( "#dialog-form" ).dialog({
	      autoOpen: false, // true라고 하면 모달창 바로 보임
	      height: 400,
	      width: 350,
	      modal: true,
	      buttons: {
	        /* "Create an account": addUser,
	        Cancel: function() {
	          dialog.dialog( "close" );
	        } */
	      },
	      close: function() {
	         form[ 0 ].reset();
	         //allFields.removeClass( "ui-state-error" );
	      }
	    });
	 
	 form = dialog.find( "form" );
 })
	
	
	// 모달 2
	
	</script>
	
	<script>
    document.getElementById("modal_opne_btn").onclick = function() {
        document.getElementById("modal2").style.display="block";
    }
   
    document.getElementById("modal_close_btn").onclick = function() {
        document.getElementById("modal2").style.display="none";
    }   
</script>
	
</body>
</html>