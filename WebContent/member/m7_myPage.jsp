<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
	rel="stylesheet">
<link href="../beep_images/wound.png" rel="shortcut icon"
	type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>PPIYONPPIYONG</title>
<script>
	$(document).ready(function() {
		$("#header").load("../layout/header.html"); // 원하는 파일 경로를 삽입하면 된다
		$("footer").load("../layout/footer.html");
	});
</script>
<style>
* {
	font-family: 'NanumSquare', sans-serif;
}

.m_myPage_box {
	
	display: flex;
	justify-content: center;
}

.m_myPage_main {
	width: 65%;
	height: 100%;
	border: solid 1px #E06666;
	display: flex;
	flex-direction: column;
	align-items: center;
	padding-top: 30px;
	padding-bottom: 30px;
}
/* ////////////////////////////// */
.m_myPage_top {
	width: 100%;
	height: 70px;
	background-color: rgb(245, 207, 207);
 	margin-top: -30px;  

	display: flex;
	justify-content: center;
	align-items: center;
}

#m_myPage_top_text {
	font-size: 30px;
	color: gray;
}


#m_myPage_info{
	display: flex;
	
}

.m_myPage_img1{
	height:100%;
	width:100px;
	display: flex;
	align-items: center;

}

.m_myPage_img2{
	border: dotted 1px rgb(245,207,207);
	border-radius: 50%;
	width: 100px;
	height: 100px;
	margin-left:7px;
	
	display: flex;
	justify-content: center;
	align-items: center;
}
#m_myPage_faceImg{
	width: 80%;
	height: 80%;
}

.m_myPage_info{
	margin-left: 20px;
	font-size: 20px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.m_myPage_showInfoGrade{
	display:flex;
	align-items:center;
	font-size:13px;
}

#m_myPage_gradeImg{
	width:10px;
	height: 10px;
}

#m_myPage_showInfo1{
	margin-top: -5px;
}

#m_myPage_showInfo2{
	margin-top: -15px;
}

.m_myPage_infoButtons{
	display: flex;
	align-items: center;
	margin-left: 60px;
	padding-left: 30px;
	padding-right: 30px;
}

#m_myPage_infoChangeButton{
	width: 150px;
	height: 30px;
	background-color: rgb(224,102,102);
	border: solid 1px rgb(224,102,102);
	border-radius: 3px;
	color: white;
}

.m_myPage_clauseChange{
	margin-left: 30px;
}

#m_myPage_clauseChangeButton{

	width: 150px;
	height: 30px;
	background-color: rgb(224,102,102);
	border: solid 1px rgb(224,102,102);
	border-radius: 3px;
	color: white;
}

/* //////////////////////////////////// */

.m_myPage_midBox {
	margin-top: 20px;
	width: 70%;
	height: 100%;
	border: solid 1px rgb(245,207,207);
	display: flex;
	border-radius: 3px;
}

.m_myPage_mid1{
	
	width: 50%;
	height: 100%;
}

.m_myPage_mid2{
	border-left: dotted 2px  rgb(245,207,207);
	width: 50%;
	height: 100%;
}


.m_myPage_T{
	margin-left: 8px;
}


.m_myPage_hr{
	/* 왜 안됨? 태그안에  넣어줌 */
}

.m_myPage_record{
	margin-left: 3px;
}

.m_myPage_moreView{
	margin-bottom: 6px;
	margin-left: 321px;
	
	
}	

.m_myPage_moreButton{
	background-color: #e7e6e657;
	border: solid 1px lightgray;
	border-radius: 3px;
}

</style>
</head>
<body>
	<header id="header"></header>
	<section id="m_myPage_section1">
		<div class="m_myPage_box">
			<div class="m_myPage_main">

				<div class="m_myPage_top">
					<p id="m_myPage_top_text">마이페이지</p>
				</div>

				<div class="m_myPage_midBox" id="m_myPage_info">
					<div class="m_myPage_img1">
						<div class="m_myPage_img2">
							<img id="m_myPage_faceImg" alt="회원사진"
								src="../beep_images/member-images/girl.svg">
						</div>
					</div>
					<!-- m_myPage_img1 -->



					<div class="m_myPage_info">
						<div class="m_myPage_showInfoGrade">
							<img id="m_myPage_gradeImg" alt="등급사진" src="../beep_images/member-images/heart.svg">
							<p id="m_myPage_gradeText">지존의사</p>
						</div>
						
						<p id="m_myPage_showInfo1">임수현 님!</p>
						<p id="m_myPage_showInfo2">반갑습니다^__^</p>
					</div>



					<div class="m_myPage_infoButtons">

						
						<div class="m_myPage_infoChange">
							<input type="button" value="나의 정보 수정하기" id="m_myPage_infoChangeButton">
						</div><!-- m_myPage_infoChange -->
						
						<div class="m_myPage_clauseChange">
							<input type="button" value="나의 약관 수정하기" id="m_myPage_clauseChangeButton">
						</div><!-- m_myPage_clauseChange -->
						
					</div>
					<!-- m_myPage_infoButtons -->
								
					
				</div>
				<!-- m_myPage_info -->


				<!-- //////////////////////////// -->
				
				
				<div class="m_myPage_midBox" id="m_myPage_bookMarkBox">
				
				
					<div class="m_myPage_mid1" id="m_myPage_H_bookMark">
						<div class="m_myPage_HB_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;병원 북마크</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_H_bookMark -->
					
					
					<div class="m_myPage_mid2" id="m_myPage_M_bookMark">
						<div class="m_myPage_MB_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;약 북마크</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_M_bookMark -->
					
					
				</div>
				<!-- m_myPage_bookMarkBox -->




				<div class="m_myPage_midBox" id="m_myPage_reviewBox">
				
					<div class="m_myPage_mid1" id="m_myPage_H_review">
						<div class="m_myPage_HR_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;병원 리뷰</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_H_review -->
					
					
					<div class="m_myPage_mid2" id="m_myPage_M_review">
						<div class="m_myPage_MR_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;약 리뷰</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_M_review -->
					
					
				</div>
				<!-- m_myPage_reviewBox -->




				<div class="m_myPage_midBox" id="m_myPage_Q&ABox">
				
					<div class="m_myPage_mid1" id="m_myPage_Q&A_question ">
						<div class="m_myPage_Q&A_Q_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;Q&A질문</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_Q&A_question -->
					
					<div class="m_myPage_mid2" id="m_myPage_Q&A_comment">
						<div class="m_myPage_Q&A_C_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;Q&A답변</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_Q&A_comment -->
					
					
				</div>
				<!-- m_myPage_reviewBox -->




				<div class="m_myPage_midBox" id="m_myPage_tipBox">
				
					<div class="m_myPage_mid1" id="m_myPage_tipWriting ">
						<div class="m_myPage_TW_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;치료Tip 글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_tipWriting -->
					
					
					<div class="m_myPage_mid2" id="m_myPage_tipComment">
						<div class="m_myPage_TC_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;치료Tip 댓글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_tipComment -->
					
				</div>
				<!-- m_myPage_tipBox -->




				<div class="m_myPage_midBox" id="m_myPage_donationBox">
				
					<div class="m_myPage_mid1" id= "m_myPage_donationWriting ">
						<div class="m_myPage_DW_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;기부/나눔 글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_donationWriting -->
					
					
					<div class="m_myPage_mid2" id="m_myPage_donationComment">
						<div class="m_myPage_DW_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;기부/나눔 댓글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
						
					</div><!-- m_myPage_donationComment -->
					
				</div>
				<!-- m_myPage_donationBox -->




				<div class="m_myPage_midBox" id="m_myPage_freeBox">
				
					<div class="m_myPage_mid1" id="m_myPage_freeWriting ">
						<div class="m_myPage_FW_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;자유게시판 글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_freeWriting -->
					
					<div class="m_myPage_mid2" id="m_myPage_freeComment">
						<div class="m_myPage_FC_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;자유게시판 댓글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_freeComment -->
					
				</div>
				<!-- m_myPage_freeBox -->


<!--  -->

				
				<div class="m_myPage_midBox" id="m_myPage_reportBox">
				
					<div class="m_myPage_mid1" id="m_myPage_reportWriting ">
						<div class="m_myPage_RW_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;신고 글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_reportWriting -->
					
					<div class="m_myPage_mid2" id="m_myPage_reportComment">
						<div class="m_myPage_RC_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;신고 댓글</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_reportComment -->
					
				</div>
				<!-- m_myPage_reportBox -->


				
				
				<div class="m_myPage_midBox" id="m_myPage_inquiretBox">
				
					<div class="m_myPage_mid1" id="m_myPage_inquireWriting ">
						<div class="m_myPage_IW_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;문의하기</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_inquiretWriting -->
					
					<div class="m_myPage_mid2" id="m_myPage_inquireComment">
						<div class="m_myPage_IC_title">
							<p class="m_myPage_T">MY&nbsp;&nbsp;문의하기 답변</p>
							<hr class="m_myPage_hr" width="100%" color=#E06666 size="2">
						</div>
						<div class="m_myPage_myRecodBox">
							<div class="m_myPage_record">
								<p class="m_myPage_record1">
									***병원
								</p>
								<p class="m_myPage_record2">
									@@@병원
								</p>
							</div>
							<div class="m_myPage_moreView">
								<input type="button" value="더 보기" class="m_myPage_moreButton">
							</div>
						</div><!-- m_myPage_myRecodBox -->
					</div><!-- m_myPage_inquireComment -->
					
				</div>
				<!-- m_myPage_inquiretBox -->


			</div>
			<!--m_myPage_main  -->
		</div>
		<!-- m_myPage_box -->
	</section>

	<footer></footer>
</body>
</html>