$.ajaxSetup({
   type: "POST",
   async: true,
   dataType: "json",
   error: function(xhr){
      console.log("error html"+xhr.statusText);
   }
});

var selectcate = 0;
$("#atag").click(function () {
	//alert("hello");
	if(selectcate==0){
		$('.disease-tip').animate({height: "530px"}, 800);
		selectcate=1;
	}else{
		$('.disease-tip').animate({height: "0" }, 800);
		selectcate=0;
	}

}); 

// 게시글 신고
var toggle2 = 0;
$("#content_report_btn").on("click", function(){
	if (toggle2 ==  1) {
		//alert("게시글 신고하기");
		$("#content_report_btn").attr("src","../beep_images/community-images/redreport.svg");
		$("#content_reportbox").css("display", "block");
		toggle2 = 0;
	} else {
		$("#content_report_btn").attr("src","../beep_images/community-images/whitereport.svg");
		$("#content_reportbox").css("display", "none");
		toggle2 = 1;
	}
});

// 댓글 신고하기 버튼
function reportOpen(report_btn){
	var id = report_btn.id;
	//var image = btn_id.src;
	//var toggle = image.substring(image.length-11,image.length-10);
	var fcoment_seq = id.substring(id.indexOf('00')+2,id.length);
	var imageurl = $('#'+id).prop('src');
	var toggle = imageurl.substring(imageurl.length-11,imageurl.length-10)
	//var num = id.substring(-4,-1);
	var comblockId="#comment_reportbox00"+fcoment_seq;
	if(toggle == "e"){
		$("#"+id).attr("src","../beep_images/community-images/redreport.svg"); // id 앞에 선택자 # 붙여줘야함
		$(comblockId).css("display","block");
	}else if(toggle == "d"){
		$("#"+id).attr("src","../beep_images/community-images/whitereport.svg");
		$(comblockId).css("display","none");
	}
}

// 답글쓰기

var replybtn = 1;
function replyOpen(reply_btn){
	var id = reply_btn.id;
	var fcoment_seq = id.substring(id.indexOf('00')+2,id.length);
	console.log(fcoment_seq);
	var comblockId="#reply_btn00"+fcoment_seq;
	if(replybtn == 1){
		$(comblockId).css("display","flex");
		replybtn = 0;
	}else if(replybtn == 0){
		$(comblockId).css("display","none");
		replybtn = 1;
	}
}



/*
var replybtn = 0;
$(".reply_btn").on("click", function(){
	var id = reply_btn.id;
	var fcoment_seq = id.substring(id.indexOf('00')+2,id.length);
	var comblockId="#reply_btn00"+fcoment_seq;
	if (replybtn ==  1) {
		$('.free_ccomment').css("display","block");
		replybtn = 0;
	} else {
		$('.free_ccomment').css("display","none");
		replybtn = 1;
	}
});
*/


// 좋아요
function likebtn(like_btn){
	var id = like_btn.id;

	var fcoment_seq = id.substring(id.indexOf('00')+2,id.length);
	var imageurl = $('#'+id).prop('src');
	var toggle = imageurl.substring(imageurl.length-9,imageurl.length-8)
	if(toggle=="e"){
		$('#'+id).attr("src","../beep_images/community-images/redlike.svg");
		likeAjaxConn('/beepPro/community/free_likebtn.ajax?like=y&fcoment_seq='+fcoment_seq);
	}else if(toggle="d"){
		$('#'+id).attr("src","../beep_images/community-images/whitelike.svg");
		likeAjaxConn('/beepPro/community/free_likebtn.ajax?like=n&fcoment_seq='+fcoment_seq);
	}
     
}


function likeAjaxConn(url){
   
      var data = null;
      $.ajax({
         type: 'POST',
         url: url,
         data: data,
         success: function(data){
            console.log("success");
            console.log(data);
            console.log(data.map.result);
               console.log(data.map.likes);

            if(data.map.result==1){
               console.log("좋아요 update 완료");
               /*likeout(data.map);*/
               $('#liketext'+data.map.fcoment_seq).text(data.map.likes);
            }
         },
         error: function(data){
            console.log("좋아요 버튼 error");
         },
         complete:function(){
            console.log("좋아요 버튼 완료 후");
         }
      })
      
   };

// 댓글
$(function(){
	var form = $('form#comment_form');
	
	$(form).submit(function(e){
		e.preventDefault();
		
		var formData = $(form).serialize();
		//formData += "&id=domjjang";
		console.log(formData);
		$.ajax({
			url: $(form).attr('action'),
			data: formData,			
			success: function(data){
				console.log("success");
				console.log(data);
				if(data.map.result==1){
					/*console.log("comment가 정상적으로 insert 됨");*/
					$('.free_comment').val("");
					showHtml(data.map); /*comments*/
				} 
			},
			error: function(data){
				console.log("댓글 insert  error");
			}
		})
		
	})
	
	// 이거 수정
	function showHtml(data) {
        let html = "";
        $.each(data.comments, function(index, item) {

        	 //str+=  ' <c:if test="${auth.type eq 9 }"><button class="deleteBtn"  value="'+seqWrite+'">삭제</button></div><br> </c:if>   
        	
            html += "<br />";
            html += "<div class='contentcommentl'>";
           	html += 	"<img src='"+item.photo+"'alt='' />";
           	html += 	"<div>"+item.id+"</div>";
           	html += 	"<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>";
			html += 	"<div>"+item.date_ajax;
			//html += "<c:if test='${dto.id  eq authUser.id}'>";		
			//html += '<c:if test="${comdto.id  eq authUser.id}">';
			//html += '<c:if test="${comdto.id  eq authUser.id}">';
			if (item.id == data.id) {				
				html += "<input type='button' value='삭제' onclick='commentDelete(this)' id='comdelete"+item.fcoment_seq+"' />";
			}
			//html += '</c:if>';
            html += "</div>";
			html += 	"<br />";
			html += "</div>";
			html += "<div class='concom'>"+item.contents+"</div>";
			html += "<br />";
			html += "<div class='combtns'>";
			html += 	"<input type='submit' id='replybtn00"+item.fcoment_seq+"' class='reply_btn' value='답글쓰기' onclick='replyOpen(this);'  />" ;
	
			//html += "<c:if test='${"+item.id_alikes+" eq 1}'>";
			//html += '<c:if test="${comdto.id_alikes eq 1}">';

			if (item.id_alikes==1) {				
			html += 	"<img src='../beep_images/community-images/redlike.svg' alt='' class='like_btn' id='like_btn00"+item.fcoment_seq+"' onclick='likebtn(this);' />"; 
			}

			//html += "</c:if>";
			//html += "<c:if test='${"+item.id_alikes+" ne 1}'>";
			//html += '<c:if test="${comdto.id_alikes ne 1}">';
			if (item.id_alikes!=1) {				
			html += 	"<img src='../beep_images/community-images/whitelike.svg' alt='' class='like_btn' id='like_btn00"+item.fcoment_seq+"' onclick='likebtn(this);' />"; 
			}
			//html += "</c:if>";
	
			html += 	"<div class='likes' id='liketext"+item.fcoment_seq+"'>"+item.likes+"</div>";
			html += 	"<img src='../beep_images/community-images/whitereport.svg' alt='' class='report_btn' id='report_btn00"+item.fcoment_seq+"' onclick='reportOpen(this);' />" ;
			html += "</div>";
			html += "<br />";
			html += "<div class='comment_reportbox' id='comment_reportbox00"+item.fcoment_seq+"' style='display: none'>";
			html += "<form action='/beepPro/manager/reportPosts.do' onsubmit='return checkReport();'>";
			html += "<select name='report_type' class='comment_report_select'>";
			html += "<option value='1'>부적절한 홍보 게시글</option>";
			html += "<option value='2'>악성코드 신고</option>";
			html += "<option value='3'>음란성 또는 청소년에게 부적합한 내용</option>";
			html += "<option value='4'>저작권 침해</option>";
			html += "<option value='5'>명예훼손</option>";
			html += "<option value='6'>기타</option>";
			html += "</select>";
			html += "<input type='hidden' name='fcoment_seq' value='"+item.fcoment_seq+"' />";
			html += "<input type='hidden' name='all_board_seq' value='3' />";
			html += "<textarea name='contents'  class='comment_report' cols='30' rows='10' placeholder='신고 내용을 입력하세요.'></textarea>";
			html += "<input type='submit' value='보내기'  class='submit report_submit' />";
			html += "</form> </div> ";
			
			html += "<form action='/beepPro/community/free_ccomwrite.ajax' method='post' id='ccomment_form"+item.fcoment_seq+"' class='free_ccomment'>";
			html += "<input type='hidden' name='id' value='"+item.id+"' />";
			html += "<input type='hidden' value='"+item.fcoment_seq+"' name='fcoment_seq' />";
			html += "<div style='display:none;' class='contentcommentw' id='reply_btn00"+item.fcoment_seq+"'>";
			html += "<textarea name='contents' class='commentbox2' id='commentArea' cols='30' rows='10'></textarea>";
			html += "<input type='button' value='등록' id='replybtn"+item.fcoment_seq+"' onclick='submitcoco(this)' />";
			html += "</div>";
			html += "</form>";


			
			
			html += "<hr />";
        });
        $(".commentbox2").empty();
        $(".commentbox").html(html);
    }	
	
})



/*
	function showHtm2(data) {
        let html = "";
        $.each(data, function(index, item) {
			html += "<div class='commentbox' id='ccomentbox'>";
			html += "<br />";
			html += "<div class='contentcommentl'>";
			html += "	<img src='"+item.photo+"' alt='' />";
			html += "	<div>"+item.id+"</div>";
			html += "	<div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>";
			html += "	<div>"+item.sdates+"";

			//html += "	<c:if test="${dto.id  eq authUser.id}">";
			html += "<input type='button' value='삭제' />";
			//html += "</c:if>";

			html += "</div>";
			html += "<br />";
			html += "</div>";

			html += "<div class='concom'>"+item.contents+"</div>";
			html += "<br />";

			html += "</div>";
        });
        $(".contentcommentw").empty();
        $("#ccomentbox").html(html);
    }	
	
*/

	            // 댓글 삭제 
	            function commentDelete(deletebtn){
	                if(confirm("정말 삭제하시겠습니까")){
	                   var id = deletebtn.id;
	                   var fcoment_seq = id.substring(id.indexOf("ete")+3,id.length);
	                   var commentbox = "#free_combox"+fcoment_seq;
	                   commentDeleteAjax(fcoment_seq);
	                   $(commentbox).remove();
	                }else{
	                   return false;
	                }
	             }
	             function commentDeleteAjax(fcoment_seq){
	                var data = null;
	                $.ajax({
	                   type : 'POST',
	                   url : "/beepPro/community/free_comdelete.ajax?" + "fcoment_seq=" + fcoment_seq,
	                   data : data,
	                   success : function(data) {
	                      console.log("답변삭제 보내기 성공");
	                      console.log(data);
	                      if (data.result == 1) {
	                         alert("답변 삭제 성공");
	                         
	                      }
	                   },
	                   error : function(data) {
	                      console.log("답변 삭제 error");
	                   }
	                })
	             }


        // 답글 삭제
		            function ccommentDelete(deletebtn){
		                if(confirm("정말 삭제하시겠습니까")){
		                   var id = deletebtn.id;
		                   var freply_seq = id.substring(id.indexOf("ete")+3,id.length);
		                   var ccommentbox = "#ccomentbox"+freply_seq;
		                   console.log(freply_seq);
		                   console.log(ccommentbox);
		                   ccommentDeleteAjax(freply_seq);
		                   $(ccommentbox).remove();
		                }else{
		                   return false;
		                }
		             }
		             function ccommentDeleteAjax(freply_seq){
		                var data = null;
		                $.ajax({
		                   type : 'POST',
		                   url : "/beepPro/community/free_ccomdelete.ajax?" + "freply_seq=" + freply_seq,
		                   data : data,
		                   success : function(data) {
		                      console.log("답변삭제 보내기 성공");
		                      console.log(data);
		                      if (data.result == 1) {
		                         alert("답변 삭제 성공");
		                         
		                      }
		                   },
		                   error : function(data) {
		                      console.log("답변 삭제 error");
		                   }
		                })
		             }