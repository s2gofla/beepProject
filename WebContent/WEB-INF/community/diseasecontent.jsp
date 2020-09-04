<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="community-style/content.css?var=1222" type="text/css" charset="UTF-8" />
<link rel="stylesheet" href="community-style/list.css?var=1222" type="text/css"	charset="UTF-8" />
<link href="../beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>삐용삐용-게시글</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
		$("#footer").load("../layout/footer.html");
	});

</script>
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
					<li><a href="" id="atag"><img
							src="../beep_images/community-images/healthcare.svg" alt="" />치료를 위한
							Tip</a></li>
					<ul class="disease-tip">
					<c:forEach items="${categoryList}" var="list">
						<li><a href="">&gt; ${ list.m_sub_name }</a></li>
					</c:forEach>
					</ul>
					<hr />
					<li><a href="freelist.jsp"><img
							src="../beep_images/community-images/communication.svg" alt="" />자유게시판</a></li>
					<hr />
					<li><a href="donationlist.jsp"><img
							src="../beep_images/community-images/donation.svg" alt="" />기부/나눔</a></li>
					<hr />
				</ul>
			</ul>
		</div>

		<div class="layout-right-block">
			<p id="smalltitle">&gt; 치료를 위한 Tip</p>  <!-- &gt; 치과 -->

			<div class="contentmem">
				<h1>${dto.title }</h1>
				<div class="mem">
					<img src="${dto.photo}" alt="" />${dto.id }
					${dto.grade_name }
				</div>
				<span>${dto.sdates } | 조회 ${dto.views }</span>
				<input type="button" value="목록" onclick="location.href='/beepPro/community/disease_list.do?page=${param.page}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}'" />
				<input type="button" value="수정" onclick="location.href='/beepPro/community/disease_update.do?dtip_seq=${dto.dtip_seq}'" />
				<input type="button" value="삭제" onclick="location.href='/beepPro/community/disease_delete.do?dtip_seq=${dto.dtip_seq}'"/> <%-- onclick="location.href='/beepPro/community/disease_delete.do?dtip_seq=${dto.dtip_seq}'" --%> 
				<hr />
				<br />
				<!-- 내용 -->
				<div class="contentcon">
					<div>${dto.contents }</div>
				</div>

				<br />
				<!-- 밑에 -->
				<div class="conunder">
				<!-- 태그 -->
				<div class="contenthashtag">
				<c:forEach items="${dto.tagArr}" var="hash">
					<button type="button" onclick="location.href='/beepPro/community/disease_list.do?searchCondition=5&searchWord=${hash}'">
						<span>#${hash}</span>
					</button>
				</c:forEach>
				</div>
				<!-- 태그 -->
				
				<div class="combtns1" >
					<img src="../beep_images/community-images/whitereport.svg" alt="" id="content_report_btn" onclick="reportOpen(this);" /> 
				</div>
				
				</div> <!-- 밑 -->

				<div class="content_reportbox" id="content_reportbox" style="display: none">
				<hr />
         <form action="/beepPro/manager/reportPosts.do" onsubmit="return checkReport();">
            <select name="report_type" class="content_report_select">
               <option value="1">부적절한 홍보 게시글</option>
               <option value="2">악성코드 신고</option>
               <option value="3">음란성 또는 청소년에게 부적합한 내용</option>
               <option value="4">저작권 침해</option>
               <option value="5">명예훼손</option>
               <option value="6">기타</option>
            </select>
            <input type="hidden" name="board_seq" value="${dto.dtip_seq }" />
            <input type="hidden" name="all_board_seq" value="2" />
            <textarea name="contents"  class="content_report" cols="30" rows="10" placeholder="신고 내용을 입력하세요."></textarea>
            <input type="submit" value="보내기"  class="submit report_submit" />
         </form>
      </div>

				<hr />

				<!-- 댓글달기 -->
				<br />
				<form action="/beepPro/community/disease_comwrite.ajax" method="post" id="comment_form" class="disease_comment">
				<div class="contentcommentw">
				<input type="hidden" value="${dto.dtip_seq }" name="dtip_seq" />
					<textarea name="contents" class="commentbox2" id="commentArea" cols="30" rows="10"></textarea>
					<input type="submit" value="등록" />
				</div>
				</form>
				<!-- 댓글달기 -->

				<br />
				<hr />
				<br />

				<!-- 댓글목록 -->
				<div class="commentbox"  >
				<c:forEach items="${comdto}" var="comdto" varStatus="status">
				<br />
				<div id="disease_combox${comdto.seq}">
				<div class="contentcommentl">
					<img src="${comdto.photo }" alt="" />
					<div>${comdto.id }</div>
					<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
					<div>${comdto.sdates }
					<c:if test="${comdto.id  eq authUser.id}">
				<input type="button" value="삭제" onclick="commentDelete(this)" id="comdelete${comdto.seq}" /> 
				</c:if>
				</div>
					<br />
				</div>
				<!-- contentcommentl -->
				<div class="concom">${comdto.contents}</div>
				<br />
				<div class="combtns">
					<input type="submit" value="답글쓰기" class="reply_btn" id="replybtn00${comdto.seq}"  onclick="replyOpen(this);" /> 
					<c:if test="${ comdto.id_alikes eq 1 }">
						<img src="../beep_images/community-images/redlike.svg" alt="" class="like_btn" id="like_btn00${comdto.seq}"  onclick="likebtn(this);" /> 
					</c:if>
					<c:if test="${ comdto.id_alikes ne 1 }">					
						<img src="../beep_images/community-images/whitelike.svg" alt="" class="like_btn" id="like_btn00${comdto.seq}"  onclick="likebtn(this);" /> 
					</c:if>
					<div class="likes" id="liketext${comdto.seq}">${comdto.likes}</div>		
					
					<img src="../beep_images/community-images/whitereport.svg" alt="" class="report_btn" id="report_btn00${comdto.seq}"  onclick="reportOpen(this);" />
				</div>
				<br />
			
					<!-- 신고 -->
			      <div class="comment_reportbox" id="comment_reportbox00${comdto.seq}" style="display: none">
			         <form action="/beepPro/manager/reportComments.do" onsubmit="return checkReport();">
			            <select name="report_type" class="comment_report_select">
			               <option value="1">부적절한 홍보 게시글</option>
			               <option value="2">악성코드 신고</option>
			               <option value="3">음란성 또는 청소년에게 부적합한 내용</option>
			               <option value="4">저작권 침해</option>
			               <option value="5">명예훼손</option>
			               <option value="6">기타</option>
			            </select>
			            <input type="hidden" name="comment_seq" value="${comdto.seq }" />
			            <input type="hidden" name="all_board_seq" value="2" />
			            <textarea name="contents"  class="comment_report" cols="30" rows="10" placeholder="신고 내용을 입력하세요."></textarea>
			            <input type="submit" value="보내기"  class="submit report_submit" />
			         </form>
			      </div>
			      <!-- 신고 -->
			      
			      				<!-- 대댓글 쓰기 -->
				<!-- /beepPro/community/free_comwrite.ajax -->
				<form action="/beepPro/community/disease_ccomwrite.ajax" method="post" id="ccomment_form${comdto.seq}" class="disease_ccomment">
				<input type="hidden" name="id" value="${authUser.id }" />
				<input type="hidden" value="${comdto.seq }" name="seq" />
				<div style="display:none;" class="contentcommentw" id="reply_btn00${comdto.seq}">
					<textarea name="contents" class="commentbox2" id="commentArea" cols="30" rows="10"></textarea>
					<input type="button" value="등록" id="replybtn${comdto.seq }" onclick="submitcoco(this)" />
				</div>
				</form>
				<!-- 대댓글 -->
				
				<!-- 대댓글 목록 -->
				<div class="allccomment${comdto.seq}">
				<c:forEach items="${comdto.ccoList}" var="ccomdto" >
				<div class="commentbox" id="ccomentbox${ccomdto.direply_seq}">
				<br />
				<div class="contentcommentl">&nbsp;&nbsp;&nbsp;&nbsp;ㄴ
					<img src="${ccomdto.photo }" alt="" /> 
					<div>${ccomdto.id }</div>
					<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
					<div>${ccomdto.sdates }
					 
					
					<c:if test="${dto.id  eq authUser.id}">
				<input type="button" value="삭제" onclick="ccommentDelete(this)" id="ccomdelete${ccomdto.direply_seq}"/>
				</c:if>
				  
				</div>
					<br />
				</div>


				<!-- contentcommentl -->
				<div class="concom">${ccomdto.contents}</div>
				<br />

				</div>
				</c:forEach>
				</div>
				<!-- 대댓글 -->

				<hr />
 
</div>  <!-- 댓글 -->
				</c:forEach>
				</div>
				      <!--  -->
 

			</div>
			<!-- contentmem -->




		</div>
		<!-- layout-right-block -->
	</div>
	<!-- layout-main -->

	<div id="footer"></div>
	
	<script src = "/beepPro/community/community-style/dicomment.js?var=322">
	</script>
	
	<script>
	
	// 신고 권한
	function checkReport(){
		var authid = "<c:out value="${authUser.id}" />";
		if(authid==null||authid==""){
			alert("로그인 후 이용 가능합니다.");
			return false;
		}
	}
	
	function submitcoco(submit){
	      var btn_id = submit.id;
	      var co_seq = btn_id.substring(btn_id.indexOf("btn")+3, btn_id.length);
	      var id = $("input[name=id]").val();
	      if(id==null || id==""){
	         alert("로그인 후 이용 가능합니다.");
	      }else{
	         var formID = "#ccomment_form"+co_seq;
	         var coForm = $(formID).serialize(); // 아이디가 뭐고 등등
	         console.log(coForm);
	         $.ajax({
	            url: $(formID).attr('action'),
	            data: coForm,      
	            success: function(data){
	               console.log("success");
	               console.log(data);
	               if(data.map.result==1){
	                  console.log("ccomment가 정상적으로 insert 됨");
	                  //$('.commentbox2').val("");
	                  showHtm2(data.map.comments, co_seq);
	                  
	                  var comblockId="#reply_btn00"+co_seq;
	                  $(comblockId).css("display","none");
	               }
	            },
	            error: function(data){
	               console.log("댓글 insert  error");
	            }
	         })
	      };
	      
	      
   	  	function showHtm2(data) {
	        let html = "";
	        $.each(data, function(index, item) {
				html += "<div class='commentbox' id='ccomentbox"+item.direply_seq+"'>";
				html += "<br />";
				html += "<div class='contentcommentl'>&nbsp;&nbsp;&nbsp;&nbsp;ㄴ";
				html += "	<img src='"+item.photo+"' alt='' />";
				html += "	<div>"+item.id+"</div>";
				html += "	<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>";
				html += "	<div>"+item.sdates+"";

				html += "<c:if test='${"+item.id+" eq authUser.id}'>";	
				html += "<input type='button' value='삭제' onclick='ccommentDelete(this)' id='ccomdelete"+item.direply_seq+"' />";
				html += "</c:if>";

				html += "</div>";
				html += "<br />";
				html += "</div>";

				html += "<div class='concom'>"+item.contents+"</div>";
				html += "<br />";

				html += "</div>";
	        });
	        $(".allccomment"+co_seq).empty();
	        $(".allccomment"+co_seq).html(html);
	      };
	   };	   
	   
	   
	   function checkReport(){
	      var authid =  "<c:out value="${ authUser.id }" />";
	      if(authid==null||authid==""){
	         alert("로그인 후 이용 가능합니다.");
	         return false;
	      }
	   }
	   
	            function likebtn(like_btn){
	            	 var authid =  "<c:out value="${ authUser.id }" />";
	               if(authid==null||authid==""){
	                  alert("로그인 후 이용 가능합니다.");
	               }else{
	            		var id = like_btn.id;

	            		var seq = id.substring(id.indexOf('00')+2,id.length);
	            		var imageurl = $('#'+id).prop('src');
	            		var toggle = imageurl.substring(imageurl.length-9,imageurl.length-8)
	            		if(toggle=="e"){
	            			$('#'+id).attr("src","../beep_images/community-images/redlike.svg");
	            			likeAjaxConn('/beepPro/community/disease_likebtn.ajax?like=y&seq='+seq);
	            		}else if(toggle="d"){
	            			$('#'+id).attr("src","../beep_images/community-images/whitelike.svg");
	            			likeAjaxConn('/beepPro/community/disease_likebtn.ajax?like=n&seq='+seq);
	            		}
	               }
	            }
	         
	</script>
	

	
</body>
</html>