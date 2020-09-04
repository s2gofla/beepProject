$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});

$(function(){
	var form = $('form#qcomment_form');
	$(form).submit(function(e){
		e.preventDefault();
		var id = $("input[name=id]").val();
		if(id==null || id==""){
			alert("로그인 후 이용 가능합니다.");
		}else{
			var formData = $(form).serialize();
			console.log(formData);
			$.ajax({
				url: $(form).attr('action'),
				data: formData,			
				success: function(data){
					console.log("success");
					console.log(data);
					if(data.map.result==1){
						/*console.log("comment가 정상적으로 insert 됨");*/
						$('.question_comment').val("");
						showHtml(data.map.comments);
					}
				},
				error: function(data){
					console.log("댓글 insert  error");
				}
			})
		}
	})
	
 function showHtml(data) {
        let html = "";
        $.each(data, function(index, item) {
        	html += "<div class='qna-answer-box id='qna-answer-box"+item.ps_seq+"'><div class='qna-answer-title'>";
            html += "<div class='answer-member photo' style='background-image: url("+item.imageurl+")'></div><div class='qna-member-title'>";
            html += "<div class='qna-answermem'>"+item.nickname+" 님 답변</div>";
            html +=		"<span>"+item.mgrade_name+"</span>";
           	html +=		"<span>채택 답변수 0 </span>";
           	html +=	"</div><div class='like_btn' id='likebtn00"+item.ps_seq+"' onclick='likebtn(this);'>";
           	html +=	"<img src='../beep_images/community-images/whitelike.svg' id='likeimage00"+item.ps_seq+"' alt='' width='40px'/>";
			html += "<span>좋아요</span><span id='liketext"+item.ps_seq+"'>"+ item.likes +"</span>";
			html += "</div>	</div>";
			html += "<div class='qna-answer-content'>"+item.contents+"</div>";
            html += "<div class='qna-answer-foot'>";
			html +=	"<span>"+item.dates+"</span>";
			html +=	"<div class='icon-block2' id='a'"+item.ps_seq+">";
			html +=	"<img id='answer_comment_btn00"+item.ps_seq+"'  onclick='commentOpen(this);'  src='../beep_images/qna-images/chat_o.svg' alt='댓글달기' width='30px' />";
			html +=	"<img id='answer_report_btn00"+item.ps_seq+"'  onclick='reportOpen(this);' src='../beep_images/community-images/whitereport.svg' alt='신고하기' width='33px'/>";
			html +=	"</div>	</div>";
			html += "<div class='commentbox' id='answer_commentbox00"+item.ps_seq+"' >";
			html += "<form action=''>";
			html += "<textarea name='question_comment' class='question_comment' cols='30' rows='10' placeholder='댓글을 달아주세요!'></textarea>";
			html += "<input type='submit' value='등록' class='submit comment_submit'/>";
			html += "</form>";
			html += "<div class='comm_register'>";
			html += "<div class='comment_head'><span>메롱 님</span><span>신고</span></div>";
			html += "<div class='comment_body'>댓글달기</div>";
			html += "<div class='comment_footer'>2020.07.13 15:54:21 </div>";
			html += "</div></div>";
			html += "<div class='reportbox' id='answer_reportbox00"+item.ps_seq+"' '>";
			html += "<form action=''>";
			html += "<select name='question_report_select' class='question_report_select'>";
			html += "<option value='1'>부적절한 홍보 게시글</option>";
			html += "<option value='2'>악성코드 신고</option>"
			html += "<option value='3'>음란성 또는 청소년에게 부적합한 내용</option>";
			html += "<option value='4'>기타..</option>";
			html += "</select>";
			html += "<textarea name='question_report'  class='question_report' cols='30' rows='10' placeholder='신고 내용을 입력하세요.'></textarea>";
			html += "<input type='submit' value='보내기'  class='submit report_submit' />";
			html += "</form> </div> </div>";
        });
        $(".qna-box2").empty();
        $(".qna-box2").html(html);
    }
		

})

   

$(function(){
	var toggle1 = 1;
	var toggle2 = 1;
	$("#question_comment_btn").on("click", function(){
		if (toggle1 ==  1) {
			$("#question_comment_btn").attr("src","../beep_images/qna-images/chat_c.svg");
			$("#question_commentbox").css("display", "block");
			toggle1 = 0;
		} else {
			$("#question_comment_btn").attr("src","../beep_images/qna-images/chat_o.svg");
			$("#question_commentbox").css("display", "none");
			toggle1 = 1;
		}
	});
	$("#question_report_btn").on("click", function(){
		if (toggle2 ==  1) {
			$("#question_report_btn").attr("src","../beep_images/community-images/redreport.svg");
			$("#question_reportbox").css("display", "block");
			toggle2 = 0;
		} else {
			$("#question_report_btn").attr("src","../beep_images/community-images/whitereport.svg");
			$("#question_reportbox").css("display", "none");
			toggle2 = 1;
		}
	});
	var toggle3 = 1;
	
});
/* 댓글 블록 보이기 */
function commentOpen(btn_id){
	var id = btn_id.id;
	var image = btn_id.src;
	var toggle = image.substring(image.length-5,image.length-4);
	var num = id.substring(id.indexOf("00")+2,id.length);
	var comblockId="#answer_commentbox"+num;
	if(toggle == "o"){
		$("#"+id).attr("src","../beep_images/qna-images/chat_c.svg");
		$(comblockId).css("display","block");
	}else if(toggle == "c"){
		$("#"+id).attr("src","../beep_images/qna-images/chat_o.svg");
		$(comblockId).css("display","none");
	}
}


function qLikeAjax(url){
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
				$('#qliketext1').text(data.map.likes);
			}
		},
		error: function(data){
			console.log("좋아요 버튼 error");
		},
		complete:function(){
			console.log("좋아요 버튼 완료 후");
		}
	})
	
}
/* 댓글 좋아요 버튼 함수  */

function aLikeAjax(url){
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
					$('#aliketext'+data.map.ps_seq).text(data.map.likes);
				}
			},
			error: function(data){
				console.log("좋아요 버튼 error");
			},
			complete:function(){
				console.log("좋아요 버튼 완료 후");
			}
		})
		
	}

/* 신고 블록 보이기 */
function reportOpen(btn_id){
	var id = btn_id.id;
	var image = btn_id.src;
	var toggle = image.substring(image.length-11,image.length-10);
	var num = id.substring(id.indexOf("00"),id.length);
	var comblockId="#answer_reportbox"+num;
	if(toggle == "e"){
		$("#"+id).attr("src","../beep_images/community-images/redreport.svg");
		$(comblockId).css("display","block");
	}else if(toggle == "d"){
		$("#"+id).attr("src","../beep_images/community-images/whitereport.svg");
		$(comblockId).css("display","none");
	}
}

$(".write-delete").on("click",function(){
	$("#myModal").modal();
})


	





