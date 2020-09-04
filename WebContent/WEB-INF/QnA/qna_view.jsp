<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 	String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="QnA-style/qna_view-style.css?var=21552" type="text/css" charset="UTF-8" />
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap" rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon" type="image/x-icon">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>삐용삐용 - QnA</title>
</head>
<body>
	<script>
		$(document).ready(function() {
			$("header").load("<%= path %>/layout/header.jsp"); // 외부 헤더 삽입
			$("footer").load("<%= path %>/layout/footer.html"); // 외부 푸터 삽입
			var edit = "<c:out value="${edit}" />";
			if (edit == 'success')
				alert("수정 완료");
			else if (edit == 'fail')
				alert("수정 실패. 관리자에게 문의하세요.");

			var reportPost = "<c:out value="${reportPost}" />";
			var reportComment = "<c:out value="${reportComment}" />";
			if (reportPost == 1)
				alert("게시물 신고 완료");
			else if (reportPost == -1)
				alert("게시물 신고 실패. 관리자에게 문의하세요.");
			if (reportComment == 1)
				alert("댓글 신고 완료");
			else if (reportComment == -1)
				alert("댓글 신고실패. 관리자에게 문의하세요.")
		});
	</script>
	<header></header>
	<div class="qna-section">
		<div class="qna-box">
			<div class="qna-title">
				<img src="<%= path %>/beep_images/qna-images/faq.svg" alt="" width="130px" />
				<span id="qna-title-content">&nbsp; &nbsp; ${ contentview.contentDTO.q_title }
				</span>
				<c:if test="${contentview.contentDTO.id eq authUser.id }">
					<button class="write-edit tt-btn"
						onclick="location.href = '<%= path %>/QnA/question_edit.do?pq_seq=${ contentview.contentDTO.pq_seq }'">글
						수정하기</button>
					<button class="write-delete tt-btn">글 삭제하기</button>
				</c:if>

			</div>
			<div class="qna-main">${ contentview.contentDTO.contents }</div>
			<div class="qna-tag">
				<a href="#" id="qna-tag"> <img
					src="<%= path %>/beep_images/qna-images/menu.svg" alt="카테고리" width="15px" /><span>&nbsp;&nbsp;
						${contentview.contentDTO.m_sub_name }</span>
				</a>
			</div>
			<div class="qna-foot">
				<div class="qprofile">
					<div class="question-member photo"
						style="background-image: url('${contentview.contentDTO.imageurl}')"></div>
					<span style="margin-left: 5px;">${ contentview.contentDTO.nickname }</span>
				</div>
				<span>${ contentview.contentDTO.dates }</span> <span>조회수
					${contentview.contentDTO.views }</span>
				<div class="icon-block1">
					<img id="question_comment_btn"
						src="<%= path %>/beep_images/qna-images/chat_o.svg" alt="" width="30px" />
					<img id="question_report_btn"
						src="<%= path %>/beep_images/community-images/whitereport.svg" alt=""
						width="33px" />
					<c:if test="${ contentview.id_like eq 1 }">
						<img class="question_like_btn"
							src="<%= path %>/beep_images/community-images/redlike.svg" alt="좋아요"
							width="33px" id="queslike${ contentview.contentDTO.pq_seq }"
							onclick="questionlike(this);" />
					</c:if>
					<c:if test="${ contentview.id_like ne 1 }">
						<img class="question_like_btn"
							id="queslike${ contentview.contentDTO.pq_seq }"
							src="<%= path %>/beep_images/community-images/whitelike.svg" alt="좋아요"
							width="33px" onclick="questionlike(this);" />
					</c:if>
					<span id="qliketext1">${ contentview.contentDTO.likes }</span>
				</div>
			</div>
			<script>
				var authid = "<c:out value="${ authUser.id }" />";
				function questionlike(like_btn) {
					if (authid == null || authid == "") {
						alert("로그인 후 이용 가능합니다.");
					} else {
						var id = like_btn.id;
						var pq_seq = id.substring(id.indexOf("ke") + 2,
								id.length);
						var image = like_btn.src;
						var toggle = image.substring(image.length - 9,
								image.length - 8);
						if (toggle == "e") {
							$("#" + id)
									.attr("src",
											"<%= path %>/beep_images/community-images/redlike.svg");
							qLikeAjax("<%= path %>/QnA/qlikebtn.ajax?like=y&pq_seq="
									+ pq_seq);
						} else if (toggle == "d") {
							$("#" + id)
									.attr("src",
											"<%= path %>/beep_images/community-images/whitelike.svg");
							qLikeAjax("<%= path %>/QnA/qlikebtn.ajax?like=n&pq_seq="
									+ pq_seq);
						}
					}
				}
			</script>
			<!-- 댓글박스 -->
			<div class="commentbox" id="question_commentbox">
				<form action="<%= path %>/QnA/qcomment_write.ajax" id="qcomment_form">
					<textarea name="question_comment" class="question_comment"
						cols="30" rows="10" placeholder="답변을 달아주세요^.^"></textarea>
					<input type="hidden" name="id" value="${ authUser.id }" /> <input
						type="hidden" name="pq_seq"
						value="${ contentview.contentDTO.pq_seq }" /> <input
						type="submit" value="등록" class="qcomment_submit submit" />
				</form>
			</div>

			<!-- 신고박스 -->
			<div class="reportbox" id="question_reportbox">
				<form action="<%= path %>/manager/reportPosts.do" method="post"
					onsubmit="return checkReport();">
					<!-- onsubmit 서브밋되기전 거치는 함수 로그인 안되있으면 리턴 false -->
					<select name="report_type" class="question_report_select">
						<option value="1">부적절한 홍보 게시글</option>
						<option value="2">악성코드 신고</option>
						<option value="3">음란성 또는 청소년에게 부적합한 내용</option>
						<option value="4">저작권 침해</option>
						<option value="5">명예훼손</option>
						<option value="6">기타</option>
					</select> <input type="hidden" name="board_seq"
						value="${ contentview.contentDTO.pq_seq }" />
					<!-- 본인 게시판 게시글번호  -->
					<input type="hidden" name="all_board_seq" value="3" />
					<!-- 올려드린 게시판 번호 ex)1.병원리뷰게시판  -->
					<textarea name="contents" class="question_report" cols="30"
						rows="10" placeholder="신고 내용을 입력하세요."></textarea>
					<input type="submit" value="보내기" class="report_submit submit" />
				</form>
			</div>
		</div>

		<!-- ajax 처리 *****************  -->
		<div class="qna-box2">

			<c:forEach items="${ contentview.commentList }" var="comment"
				varStatus="status">
				<div class="qna-answer-box" id="qna-answer-box${comment.ps_seq}">
					<div class="qna-answer-title">
						<div class="answer-member photo"
							style="background-image: url('${comment.imageurl}')"></div>
						<div class="qna-member-title">
							<div class="qna-answermem">${ comment.nickname }님답변</div>
							<span>${ comment.mgrade_name }</span>
						</div>
						<c:if test="${comment.id eq authUser.id }">
							<button class="comment-edit cc-btn"
								id="comment_edit${comment.ps_seq}" onclick="commentEdit(this);">답변
								수정</button>
							<button class="comment-delete cc-btn" id="comment_delete${comment.ps_seq}" onclick="commentDelete(this);">답변 삭제</button>
							<button class="comment-editcomplete cc-btn"
								id="edit_complete${comment.ps_seq}" style="display: none;"
								onclick="commentUpdate(this);">수정 완료</button>
						</c:if>
						<div class="like_btn" id="likebtn0${comment.ps_seq}">
							<c:if test="${ comment.likes_yn eq 1 }">
								<img src="<%= path %>/beep_images/community-images/redlike.svg"
									id="likeimage0${comment.ps_seq}" alt="" width="40px"
									onclick="likebtn(this);" />
							</c:if>
							<c:if test="${ comment.likes_yn ne 1 }">
								<img src="<%= path %>/beep_images/community-images/whitelike.svg"
									id="likeimage0${comment.ps_seq}" alt="" width="40px"
									onclick="likebtn(this);" />
							</c:if>
							<span>좋아요<span id="aliketext${comment.ps_seq }"> ${ comment.likes }</span></span>
						</div>

					</div>
					<div class="qna-answer-content"
						id="answer-content${comment.ps_seq}">${ comment.contents }</div>
					<div class="qna-answer-foot">
						<span>${comment.dates}</span>
						<div class="icon-block2">
							<img id="answer_comment_btn00${comment.ps_seq}"
								onclick="commentOpen(this);"
								src="<%= path %>/beep_images/qna-images/chat_o.svg" alt="댓글달기"
								width="30px" /> <img id="answer_report_btn00${comment.ps_seq}"
								onclick="reportOpen(this);"
								src="<%= path %>/beep_images/community-images/whitereport.svg" alt="신고하기"
								width="33px" />
						</div>
					</div>
					<!-- 대댓글 달기 -->
					<div class="commentbox" id="answer_commentbox${comment.ps_seq}">
						<form action="<%= path %>/QnA/acomment_write.ajax"
							id="acomment_form${comment.ps_seq}">
							<input type="hidden" name="id" value="${ authUser.id }" /> <input
								type="hidden" name="ps_seq" value="${ comment.ps_seq }" />
							<textarea name="answer_comment" class="question_comment"
								cols="30" rows="10" placeholder="댓글을 달아주세요!"></textarea>
							<input type="button" value="등록" class="submit comment_submit"
								id="acbtn${comment.ps_seq}" onclick="submitcoco(this);" />
						</form>
						<div class="qna-box3${comment.ps_seq}">
							<c:forEach items="${ comment.cocoList }" var="cocolist">
								<!-- 대댓글  -->
								<div class="comm_register">
									<div class="comment_head">
										<span> ${ cocolist.nickname }&nbsp;&nbsp;님</span><span>신고</span>
									</div>
									<div class="comment_body">${ cocolist.contents }</div>
									<div class="comment_footer">${ cocolist.dates }</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="reportbox" id="answer_reportbox00${comment.ps_seq}">
						<form action="<%= path %>/manager/reportComments.do" method="post"
							onsubmit="return checkReport();">
							<select name="report_type" class="question_report_select">
								<option value="1">부적절한 홍보 게시글</option>
								<option value="2">악성코드 신고</option>
								<option value="3">음란성 또는 청소년에게 부적합한 내용</option>
								<option value="4">저작권 침해</option>
								<option value="5">명예훼손</option>
								<option value="6">기타..</option>
							</select> <input type="hidden" name="comment_seq"
								value="${ comment.ps_seq }" /> <input type="hidden"
								name="all_board_seq" value="3" />
							<textarea name="contents" class="question_report" cols="30"
								rows="10" placeholder="신고 내용을 입력하세요."></textarea>
							<input type="submit" value="보내기" class="submit report_submit" />
						</form>
					</div>

				</div>

			</c:forEach>
			<script>
				/* 대댓글 insert */
				function submitcoco(submit) {
					var btn_id = submit.id;
					var co_seq = btn_id.substring(btn_id.indexOf("btn") + 3,
							btn_id.length);
					var id = $("input[name=id]").val();
					if (id == null || id == "") {
						alert("로그인 후 이용 가능합니다.");
					} else {
						var formID = "#acomment_form" + co_seq;
						var coForm = $(formID).serialize();
						console.log(coForm);
						$.ajax({
							url : $(formID).attr('action'),
							data : coForm,
							success : function(data) {
								console.log("success");
								console.log(data);
								if (data.map.result == 1) {
									console.log("comment가 정상적으로 insert 됨");
									$('.question_comment').val("");
									showAComment(data.map.comments, co_seq);
								}
							},
							error : function(data) {
								console.log("댓글 insert  error");
							}
						})
					}
					;
					function showAComment(data, co_seq) {
						let html = "";
						$.each(data, function(index, item) {
							html += "<div class=\"comm_register\">";
							html += "<div class=\"comment_head\"><span>"
									+ item.nickname
									+ "님</span><span>신고</span></div>";
							html += "<div class=\"comment_body\">"
									+ item.contents + "</div>";
							html += "<div class=\"comment_footer\">"
									+ item.dates + "</div>";
							html += "</div>";
						});
						$(".qna-box3" + co_seq).empty();
						$(".qna-box3" + co_seq).html(html);
					}
					;
				};
				function checkReport() {
					var authid = "<c:out value="${ authUser.id }" />";
					if (authid == null || authid == "") {
						alert("로그인 후 이용 가능합니다.");
						return false;
					}
				}
				function likebtn(like_btn) {
					if (authid == null || authid == "") {
						alert("로그인 후 이용 가능합니다.");
					} else {
						var id = like_btn.id;
						var ps_seq = id.substring(id.indexOf("0") + 1,
								id.length);
						var image = like_btn.src;
						var toggle = image.substring(image.length - 9,
								image.length - 8);
						if (toggle == "e") {
							$("#" + id)
									.attr("src",
											"<%= path %>/beep_images/community-images/redlike.svg");
							aLikeAjax('<%= path %>/QnA/alikebtn.ajax?like=y&ps_seq='
									+ ps_seq);
						} else if (toggle = "d") {
							$("#" + id)
									.attr("src",
											"<%= path %>/beep_images/community-images/whitelike.svg");
							aLikeAjax('<%= path %>/QnA/alikebtn.ajax?like=n&ps_seq='
									+ ps_seq);
						}
					}
				}
				/*   댓글 수정하기     */
				function commentEdit(editbtn) {
					var id = editbtn.id;
					var ps_seq = id
							.substring(id.indexOf("edit") + 4, id.length);
					var contentsID = "#answer-content" + ps_seq;
					var contents = $(contentsID).text();
					$(".comment-editcomplete").css("display", "block");
					$(contentsID)
							.replaceWith(
									"<input type=\"text\" class=\"edit-input qna-answer-content\"	id=\"edit-content"+ps_seq+"\" value=\""+contents+"\"/>");
					$(".edit-input").focus();
				}
				function commentUpdate(edit_btn) {
					var id = edit_btn.id;
					var ps_seq = id.substring(id.indexOf("ete") + 3, id.length);
					var contentsID = "#edit-content" + ps_seq;
					var contentsVAL = $(contentsID).val();
					$(contentsID).replaceWith(
							"<div class=\"qna-answer-content\" id=\"answer-content"+ps_seq+"\">"+ contentsVAL + "</div>");
					$(".comment-editcomplete").css("display", "none");
					cocoUpdateAjax(contentsVAL, ps_seq);
				}

				function cocoUpdateAjax(contents, ps_seq) {
					var data = null;
					$.ajax({
						type : 'POST',
						url : "<%= path %>/QnA/answerUpdate.ajax?contents="	+ contents + "&ps_seq=" + ps_seq,
						data : data,
						success : function(data) {
							//console.log("답변수정 보내기 성공");
							//console.log(data);
							if (data.result == 1) {
								console.log("답변 수정 성공");
							}
						},
						error : function(data) {
							console.log("답변 수정 error");
						}
					})
				}
				function commentDelete(deletebtn){
					if(confirm("정말 삭제하시겠습니까")){
						var id = deletebtn.id;
						var ps_seq = id.substring(id.indexOf("ete")+3,id.length);
						var answerbox = "#qna-answer-box"+ps_seq;
						commentDeleteAjax(ps_seq);
						$(answerbox).remove();
					}else{
						return false;
					}
				}
				function commentDeleteAjax(ps_seq){
					var data = null;
					$.ajax({
						type : 'POST',
						url : "<%= path %>/QnA/answerDelete.ajax?" + "ps_seq=" + ps_seq,
						data : data,
						success : function(data) {
							//console.log("답변삭제 보내기 성공");
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
				
			</script>
		</div>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">삭제-비밀번호 확인</h4>
				</div>
				<div class="modal-body">
					<form action="<%= path %>/QnA/questionDelete.do">
						<input type="hidden" name="pq_seq"
							value="${ contentview.contentDTO.pq_seq }" /> <input
							type="password" name="password" placeholder="비밀번호를 입력하세요." /> <input
							type="submit" value="확인" />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 답변 댓글 수정 -->
	<script type="text/javascript" src="QnA-style/qna_view-js.js?var=155"></script>
	<footer></footer>
</body>
</html>