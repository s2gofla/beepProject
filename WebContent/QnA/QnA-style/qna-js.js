$.ajaxSetup({
	type: "POST",
	async: false,
	dataType: "json",
	error: function(xhr){
		console.log("error html"+xhr.statusText);
	}
});


$('.qna-subblock').on("click", function(){
	var question = $(this).children('.qna-question');
	var height = $(this).height();

	if(height==150){
		question.css("white-space","normal");
		var question_h = question.height();
		$(this).animate({ height: question_h+77 }, 800);
	}else{

		$(this).animate({ height: "150" },1000);
		question.css("white-space","nowrap");
	}
});

/*beepPro/QnA/choicelist.ajax?m_sub_seq=${ list.m_sub_seq }*/
//$(".pageBlock a").on("click", function(e){
	$(document).on("click",".pageBlock a",function(e){
		console.log("1  ajax 접근");
		e.preventDefault();
		var url = $(this).attr('href');
		var val = $("#qna-search").val();
		console.log(url);
		selectListAjax(url,1,val);
		
	});

	$(document).on("click","#all-list",function(){
		console.log("ajax: 전체리스트 출력");
		var url="/beepPro/QnA/choicelist.ajax?page=1&m_sub_seq=0";
		selectListAjax(url, 1, val);
	})
/*	function PagingList(){
		console.log("2  ajax 접근");
		//e.preventDefault();
		var url = $(this).attr('href');
		console.log("2"+url);
		selectListAjax(url);
	};*/

	$(".qna-sortgory li a").on("click", function(e){
		e.preventDefault();
		var sortCode = $(this).attr("href");
		var sort = sortCode.substring(sortCode.length-1, sortCode.length);
		var seq = $(".qna-subtitle span").attr("id");
		var m_sub_seq = seq.substring(seq.length-1, seq.length);
		var val = $("#qna-search").val();
		url = '/beepPro/QnA/choicelist.ajax?page=1&m_sub_seq='+m_sub_seq;
		selectListAjax(url, sort, val);
	})
	
	$("#qna-category li a").on("click",function(e){
		e.preventDefault();	
		var id = $(this).attr('id');
		var m_sub_seq = id.substring(id.indexOf("e")+1, id.length);
		var val = $("#qna-search").val();
		url = '/beepPro/QnA/choicelist.ajax?page=1&m_sub_seq='+m_sub_seq;
		selectListAjax(url, 1, val);
	});
	
	function selectListAjax(url, sort, sWord){
		url += "&sort="+sort+"&sWord="+sWord;
		console.log(url);
		var data = null;
		$.ajax({
			url: url,
			data: data,
			success: function(data){
				console.log("success");
				/*console.log(data);*/
				console.log(data);
				selectList(data);
			},
			error: function(data){
				console.log("진료과 리스트 error");
			},
			complete:function(){
				console.log("진료과 리스트 완료 후");
			}
		})
	}
	function selectList(data){
		let html = "";
		
		if(data.view.qnaList.length > 0){
		$.each(data.view.qnaList, function(index, item){
				html += "<div class='qna-subblock' > ";
				html += "<img src='../beep_images/qna-images/question.svg' alt='' width='50'/>";
				html += "<div class='qna-question'>"+ item.q_title+"</div>";
				html += "<div class='qna-view'><img src='/beepPro/beep_images/qna-images/views.svg' alt='읽음' width='20px' />"+item.views+"<img src='/beepPro/beep_images/qna-images/paper.svg' alt='답글' width='25px' style='margin-left: 15px;'/>"+item.answerCnt
				html += "<img src=\"/beepPro/beep_images/community-images/redlike.svg\" alt=\"좋아요\" width=\"23px\" style=\"margin-left: 15px;\" />"+ item.likes +"</div>"
				html += "<a href='/beepPro/QnA/qna_view.do?pq_seq="+item.pq_seq+"'><div id='qna-goanswer'>&gt; 답변하러가기</div></a>";
				html += "<div class='qna-blockdate'>"+item.dates +"</div></div>";

		})
		} else{
			html += "<div class='no-register'><span> 등록된 Q&A가 없습니다.</span></div>";
		}
		var start = data.view.start;
		var end = data.view.end;
		var currentPage = data.view.currentPageNumber;
		console.log(start);
		console.log(end);
		html += "<div class='pageBlock'>";
		if(data.view.prev==true){
			console.log("prev");
			html += "<a href='/beepPro/QnA/choicelist.ajax?page="+ (start -1)+"&m_sub_seq="+data.m_sub_seq+"'>&laquo;</a>";
		}
		for (var i = start ; i < end+1; i++) {
			console.log("i: "+i);
			if( i == currentPage){
				html += "<span style='background: rgb(255,207,77)'>"+i+"</span>";
			}else{
				html += "<a href='/beepPro/QnA/choicelist.ajax?page="+i+"&m_sub_seq="+data.m_sub_seq+"' >"+i+"</a>";
			}
		}
		if(data.view.next==true){
			console.log("next");
			html += "<a href='/beepPro/QnA/choicelist.ajax?page="+ (end+1)+"&m_sub_seq="+data.m_sub_seq+"'>&raquo;</a>";
		}
		html += "</div>";
		
		let title = data.name+"&nbsp;&nbsp;&gt;&nbsp;";
		
		$(".qna-subtitle span").empty();
		$(".qna-subtitle span").html(title);
		$(".qna-subtitle span").attr("id","sub"+data.m_sub_seq);
		$(".qna-rightblock").empty();
        $(".qna-rightblock").html(html);
	}
	



var selectcate = 0;
$(".qna-selectcate").on("click", function () {
	var o_height = $('.qna-lefttop').height();

	if(selectcate==0){
		o_height += 450;
		$('.qna-lefttop').animate({height: o_height+30 }, 800);
		$('.qna-category').animate({height: "480" }, 800);
		selectcate=1;
	}else{
		$('.qna-lefttop').animate({height: o_height-480}, 900);
		$('.qna-category').animate({height: "0" }, 800);
		selectcate=0;
	}

});
var selectsort = 0;
$(".qna-selectsort").on("click", function () {
	var o_height = $('.qna-lefttop').height();

	if(selectsort==0){
		$('.qna-lefttop').animate({height: o_height+130 }, 800);
		$('.qna-sortgory').animate({height: "120" }, 800);
		selectsort = 1;
	}else{
		$('.qna-lefttop').animate({height: o_height-130 }, 900);
		$('.qna-sortgory').animate({height: "0" }, 800);
		selectsort = 0;
	}

});

$("#gowrite1").on("click", function(){
	alert("로그인 후 이용 가능합니다.");
	location.href='/beepPro/member/login.do';
})
$("#gowrite2").on("click",function(){
	location.href='/beepPro/QnA/qnawrite.do';
})

function searchFormbtn(){
	var val = $("#qna-search").val();
	if(val == "" || val == " ")
		alert("검색어를 입력하세요");
	else
		selectListAjax(0, 1, val);
	
}

