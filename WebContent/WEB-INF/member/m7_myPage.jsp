<%@page import="com.util.JdbcUtil"%>
<%@page import="com.util.ConnectionProvider"%>
<%@page import="beepbeep.member.dto.LoginDTO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.util.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
 src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="m7_myPage.css?var=188" type="text/css"
 charset="UTF-8" />
<link
 href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic+Coding:wght@400;700&family=Titillium+Web:wght@400;600&display=swap"
 rel="stylesheet">
<link href="<%= path %>/beep_images/wound.png" rel="shortcut icon"
 type="image/x-icon">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<title>PPIYONPPIYONG</title>
<script>
 $(document).ready(function() {
  $("#header").load("<%= path %>/layout/header.jsp"); // 원하는 파일 경로를 삽입하면 된다
  $("footer").load("<%= path %>/layout/footer.html");
 });
</script>

</head>
<body>

 <header id="header"></header>
 <section id="m_myPage_section1">
  <div class="m_myPage_box">
   <div class="m_myPage_main">

    <div class="m_myPage_top">
     <p id="m_myPage_top_text">My Page</p>
    </div>

    <div class="m_myPage_midBox" id="m_myPage_info">
     <div class="m_myPage_img1">
      <div class="m_myPage_img2">
       <c:if test="${not empty authUser.photo  }">
        <img id="m_myPage_faceImg" alt="회원사진" src="${authUser.photo}">
       </c:if>

       <c:if test="${empty authUser.photo }">
        <img id="m_myPage_faceImg" alt="회원사진"
         src="<%= path %>/beep_images/member-images/dinosaur.svg">
       </c:if>
      </div>
     </div>
     <!-- m_myPage_img1 -->

 

     <div class="m_myPage_info">
      <div class="m_myPage_showInfoGrade">

       <c:choose>
        <c:when test="${grade eq 1 || grade eq 2 || grade eq 3 || grade eq 4}">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/clover${grade}.svg">
        </c:when>
        <%-- 
        <c:when test="${grade eq 2 }">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/clover${grade}.svg">
        </c:when>
        <c:when test="${grade eq 3 }">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images//grade-images/clover${grade}.svg">
        </c:when>
        <c:when test="${grade eq 4 }">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/clover${grade}.svg">
        </c:when>
         --%>
        
        
        <c:when test="${grade eq 5  || grade eq 6 || grade eq 7 || grade eq 8}">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/number${grade}.svg">
        </c:when>
        
        <%-- 
        <c:when test="${grade eq 6 }">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/number${grade}.svg">
        </c:when>
        <c:when test="${grade eq 7 }">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/number${grade}.svg">
        </c:when>
        <c:when test="${grade eq 8 }">
         <img id="m_myPage_gradeImg" alt="등급사진"
          src="<%= path %>/beep_images/grade-images/number${grade}.svg">
        </c:when>
         --%>
        
       </c:choose>


       <p id="m_myPage_gradeText">${gradeName}</p>
      </div>

      <p id="m_myPage_showInfo1">${authUser.nickname}님</p>
      <p id="m_myPage_showInfo2">반갑습니다^__^</p>
     </div>

 

     <div class="m_myPage_infoButtons">


      <div class="m_myPage_infoChange">
       <input type="button" value="나의 정보 수정하기"
        id="m_myPage_infoChangeButton"
        onclick="location.href='<%= path %>/member/changeCheck.do?change=1'">
      </div>
      <!-- m_myPage_infoChange -->

 

      <div class="m_myPage_clauseChange">
       <input type="button" value="나의 약관 수정하기"
        id="m_myPage_clauseChangeButton"
        onclick="location.href='<%= path %>/member/changeCheck.do?change=2'">
      </div>
      <!-- m_myPage_clauseChange -->

 

      <div class="m_myPage_withdrawal">
       <input type="button" value="회원 탈퇴하기"
        id="m_myPage_withdrawalButton"
        onclick="location.href='<%= path %>/member/changeCheck.do?change=3'">
      </div>
      <!-- m_myPage_clauseChange -->

     </div>
     <!-- m_myPage_infoButtons -->


    </div>
    <!-- m_myPage_info -->


    <!-- ///////////////////////////////////////////// -->


    <div class="m_myPage_midBox" id="m_myPage_bookMarkBox">


     <div class="m_myPage_mid1 mypageMid" id="m_myPage_H_bookMark">
      <div class="m_myPage_HB_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;병원 북마크</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
        
        
        
         <%
         LoginDTO dto = (LoginDTO) request.getSession().getAttribute("authUser"); 
         
         
         Connection conn = null;
         PreparedStatement pstmt= null;
         PreparedStatement pstmt2= null;
         PreparedStatement pstmt3= null;
         ResultSet rs = null;
         ResultSet rs2 = null;
         ResultSet rs3 = null;
         try{
          
          conn = ConnectionProvider.getConnection();
          
          String sql = "select h_code from h_bookmark where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
                                      
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           int h_code= rs.getInt("h_code");
           
           
           String sql2 = "select h_name from h_info where h_code=? ";
           
           pstmt2=conn.prepareStatement(sql2);
           
           pstmt2.setInt(1, h_code);
           
           rs2 = pstmt2.executeQuery();
           
           while(rs2.next()){
            
            String h_name = rs2.getString("h_name");
           
            %><p  >
            <%= h_name %>
            </p>
            <%
           }
          }
          
          
          /* 
          
          String sql3 = "select count(*)   from h_bookmark where id=?";
          
          pstmt3=conn.prepareStatement(sql3); 
       
         
         pstmt3.setString(1, id);
         
         
         rs3 = pstmt3.executeQuery();
         
         rs3.next();
         
         int count_hname= rs3.getInt(1);
         
        
        
        
        if( count_hname >3 ){
           
           여%기에 주석안되서 이렇게 ><p style="color: red; font-size: 3px;" >
           <!-- <br><br>
         !!더 보실경우 더보기를 눌러주세요!! -->
            </p>
            <여%기에
        }
                
         */
         
         
         
         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(rs2);
             JdbcUtil.close(rs);
             JdbcUtil.close(pstmt2);
             JdbcUtil.close(pstmt);
             JdbcUtil.close(conn);

           
         }
         
         %>
         
         
         
       
       </div>

      </div>
      <!-- m_myPage_myRecodBox -->
      <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=1'">
       </div>
     </div>
     <!-- m_myPage_H_bookMark -->


     <div class="m_myPage_mid2 mypageMid" id="m_myPage_M_bookMark">
      <div class="m_myPage_MB_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;약 북마크</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       
       <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select m_code from m_bookmark where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                            
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           int m_code= rs.getInt("m_code");
           
           
           String sql2 = "select m_name from m_info where m_code=? ";
           
           pstmt2=conn.prepareStatement(sql2);
           
           pstmt2.setInt(1, m_code);
           
           rs2 = pstmt2.executeQuery();
           
           while(rs2.next()){
            String m_name = rs2.getString("m_name");
           
            %><p  >
            <%= m_name %>
            </p>
            <%
           }
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(rs2);
             JdbcUtil.close(rs);
             JdbcUtil.close(pstmt2);
             JdbcUtil.close(pstmt);
             JdbcUtil.close(conn);

           
         }
         %>
         
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=2'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_M_bookMark -->


    </div>
    <!-- m_myPage_bookMarkBox -->

 


    <div class="m_myPage_midBox" id="m_myPage_reviewBox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_H_review">
      <div class="m_myPage_HR_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;병원 리뷰</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
    
       <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select contents from h_review where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                              
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String contents3= rs.getString("contents");
           if(contents3.equals("null")){
            contents3 = "";
           }
                                                 
            %><p   >
            <%= contents3 %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=3'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_H_review -->


     <div class="m_myPage_mid2 mypageMid" id="m_myPage_M_review">
      <div class="m_myPage_MR_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;약 리뷰</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select contents from m_review where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                            
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String contents4= rs.getString("contents");
                                                 
            %><p   >
            <%= contents4 %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=4'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_M_review -->


    </div>
    <!-- m_myPage_reviewBox -->

 


    <div class="m_myPage_midBox" id="m_myPage_Q&ABox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_Q&A_question ">
      <div class="m_myPage_Q&A_Q_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;Q&A질문</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select q_title from personal_question where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                              
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String q_title= rs.getString("q_title");
                                                 
            %><p   >
            <%= q_title %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=5'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_Q&A_question -->

     <div class="m_myPage_mid2 mypageMid" id="m_myPage_Q&A_comment">
      <div class="m_myPage_Q&A_C_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;Q&A답변</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

       try{
        
        conn = ConnectionProvider.getConnection();
        String sql = "select pq_seq from personal_answer where id=?  and rownum <=3 ";
        pstmt=conn.prepareStatement(sql);
        
        String id = dto.getId();
        pstmt.setString(1, id);
        
                          
        rs = pstmt.executeQuery();
       
        while (rs.next()) {
         
         int pq_seq= rs.getInt("pq_seq");
                  
         String sql2 = "select q_title from personal_question where pq_seq=? ";
         
         pstmt2=conn.prepareStatement(sql2);
         
         pstmt2.setInt(1, pq_seq);
         
         rs2 = pstmt2.executeQuery();
         
         while(rs2.next()){
          
          String q_title = rs2.getString("q_title");
          %><p   >
          <%= q_title %>
          </p>
          <%
         }
        }

       }catch (Exception e) {
        e.printStackTrace();
       }finally{
           JdbcUtil.close(pstmt2);
           JdbcUtil.close(pstmt);
           JdbcUtil.close(rs2);
       	   JdbcUtil.close(rs);
           JdbcUtil.close(conn);

         
       }
       %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=6'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_Q&A_comment -->


    </div>
    <!-- m_myPage_reviewBox -->

 


    <div class="m_myPage_midBox" id="m_myPage_tipBox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_tipWriting ">
      <div class="m_myPage_TW_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;치료Tip 글</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select title from disease_tip where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                              
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String title1= rs.getString("title");
                                           
            %><p   >
            <%= title1 %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=7'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_tipWriting -->


     <div class="m_myPage_mid2 mypageMid" id="m_myPage_tipComment">
      <div class="m_myPage_TC_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;치료Tip 댓글</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

       try{
        
        conn = ConnectionProvider.getConnection();
        String sql = "select dtip_seq from disease_tip_comment where id=?  and rownum <=3 ";
        pstmt=conn.prepareStatement(sql);
        
        String id = dto.getId();
        pstmt.setString(1, id);
        
                          
        rs = pstmt.executeQuery();
       
        while (rs.next()) {
         
         int dtip_seq= rs.getInt("dtip_seq");
                  
         String sql2 = "select title from disease_tip where dtip_seq=? ";
         
         pstmt2=conn.prepareStatement(sql2);
         
         pstmt2.setInt(1,dtip_seq);
         
         rs2 = pstmt2.executeQuery();
         
         while(rs2.next()){
          
          String title2 = rs2.getString("title");
          %><p   >
          <%= title2 %>
          </p>
          <%
         }
        }

       }catch (Exception e) {
        e.printStackTrace();
       }finally{
           JdbcUtil.close(pstmt2);
           JdbcUtil.close(pstmt);
           JdbcUtil.close(rs2);
      	  JdbcUtil.close(rs);
           JdbcUtil.close(conn);

         
       }
       %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=8'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_tipComment -->

    </div>
    <!-- m_myPage_tipBox -->

 


    <div class="m_myPage_midBox" id="m_myPage_donationBox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_donationWriting ">
      <div class="m_myPage_DW_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;기부/나눔 글</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
        <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select title from donation_board where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                             
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String title3= rs.getString("title");
                                                 
            %><p   >
            <%= title3 %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=9'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_donationWriting -->


     <div class="m_myPage_mid2 mypageMid" id="m_myPage_donationComment">
      <div class="m_myPage_DW_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;기부/나눔 댓글</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

       try{
        
        conn = ConnectionProvider.getConnection();
        String sql = "select d_seq from donation_comment where id=?  and rownum <=3 ";
        pstmt=conn.prepareStatement(sql);
        
        String id = dto.getId();
        pstmt.setString(1, id);
        
                          
        rs = pstmt.executeQuery();
       
        while (rs.next()) {
         
         int d_seq= rs.getInt("d_seq");
                  
         String sql2 = "select title from donation_board where d_seq=? ";
         
         pstmt2=conn.prepareStatement(sql2);
         
         pstmt2.setInt(1,d_seq);
         
         rs2 = pstmt2.executeQuery();
         
         while(rs2.next()){
          
          String title4 = rs2.getString("title");
          %><p   >
          <%= title4 %>
          </p>
          <%
         }
        }

       }catch (Exception e) {
        e.printStackTrace();
       }finally{
           JdbcUtil.close(pstmt2);
           JdbcUtil.close(pstmt);
           JdbcUtil.close(rs2);
        JdbcUtil.close(rs);
           JdbcUtil.close(conn);

         
       }
       %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=10'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->

     </div>
     <!-- m_myPage_donationComment -->

    </div>
    <!-- m_myPage_donationBox -->

 


    <div class="m_myPage_midBox" id="m_myPage_freeBox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_freeWriting ">
      <div class="m_myPage_FW_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;자유게시판 글</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
         <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select title from free_board where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                              
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String title5= rs.getString("title");
                                                 
            %><p   >
            <%= title5 %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=11'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_freeWriting -->

     <div class="m_myPage_mid2 mypageMid" id="m_myPage_freeComment">
      <div class="m_myPage_FC_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;자유게시판 댓글</p>
      
      </div>
      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <%    

       try{
        
        conn = ConnectionProvider.getConnection();
        String sql = "select fboard_seq from free_comment where id=?  and rownum <=3 ";
        pstmt=conn.prepareStatement(sql);
        
        String id = dto.getId();
        pstmt.setString(1, id);
        
                          
        rs = pstmt.executeQuery();
       
        while (rs.next()) {
         
         int fboard_seq= rs.getInt("fboard_seq");
                  
         String sql2 = "select title from free_board where fboard_seq=? ";
         
         pstmt2=conn.prepareStatement(sql2);
         
         pstmt2.setInt(1,fboard_seq);
         
         rs2 = pstmt2.executeQuery();
         
         while(rs2.next()){
          
          String title6 = rs2.getString("title");
          %><p   >
          <%= title6 %>
          </p>
          <%
         }
        }

       }catch (Exception e) {
        e.printStackTrace();
       }finally{
           JdbcUtil.close(pstmt2);
           JdbcUtil.close(pstmt);
           JdbcUtil.close(rs2);
        JdbcUtil.close(rs);
           JdbcUtil.close(conn);

         
       }
       %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton" 
        onclick="location.href='<%= path %>/member/moreView.do?more=12'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_freeComment -->

    </div>
    <!-- m_myPage_freeBox -->


    <!--  -->


    <div class="m_myPage_midBox" id="m_myPage_reportBox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_reportWriting ">
      <div class="m_myPage_RW_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;신고 글</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
         <%    

         try{
          
          conn = ConnectionProvider.getConnection();
          String sql = "select contents from report where id=?  and rownum <=3 ";
          pstmt=conn.prepareStatement(sql);
          
          String id = dto.getId();
          pstmt.setString(1, id);
          
                             
          rs = pstmt.executeQuery();
         
          while (rs.next()) {
           
           String contents1= rs.getString("contents");
                                                 
            %><p   >
            <%= contents1 %>
            </p>
            <%
           
          }

         }catch (Exception e) {
          e.printStackTrace();
         }finally{
             JdbcUtil.close(pstmt);
             JdbcUtil.close(rs);
            JdbcUtil.close(conn);

           
         }
         %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=13'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_reportWriting -->

     <div class="m_myPage_mid2 mypageMid" id="m_myPage_reportComment">
      <div class="m_myPage_RC_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;신고 댓글</p>
      
      </div>
      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
        <%    

        try{
         
         conn = ConnectionProvider.getConnection();
         String sql = "select contents from report_comment where id=?  and rownum <=3 ";
         pstmt=conn.prepareStatement(sql);
         
         String id = dto.getId();
         pstmt.setString(1, id);
         
                           
         rs = pstmt.executeQuery();
        
         while (rs.next()) {
          
          String contents2= rs.getString("contents");
                                                
           %><p   >
           <%= contents2 %>
           </p>
           <%
          
         }

        }catch (Exception e) {
         e.printStackTrace();
        }finally{
            JdbcUtil.close(pstmt);
            JdbcUtil.close(rs);
           JdbcUtil.close(conn);

          
        }
        %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=14'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_reportComment -->

    </div>
    <!-- m_myPage_reportBox -->

 


    <div class="m_myPage_midBox" id="m_myPage_inquiretBox">

     <div class="m_myPage_mid1 mypageMid" id="m_myPage_inquireWriting ">
      <div class="m_myPage_IW_title">
       <p class="m_myPage_T">MY&nbsp;&nbsp;문의하기</p>
      
      </div>

      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
        <%    

        try{
         
         conn = ConnectionProvider.getConnection();
         String sql = "select title from qna where id=?  and rownum <=3 ";
         pstmt=conn.prepareStatement(sql);
         
         String id = dto.getId();
         pstmt.setString(1, id);
         
                            
         rs = pstmt.executeQuery();
        
         while (rs.next()) {
          
          String title9= rs.getString("title");
                                                
           %><p   >
           <%= title9 %>
           </p>
           <%
          
         }
         

        }catch (Exception e) {
         e.printStackTrace();
        }finally{
            JdbcUtil.close(pstmt);
            JdbcUtil.close(rs);
           JdbcUtil.close(conn);

          
        }
        %>
       </div>
       <div class="m_myPage_moreView">
        <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=15'">
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_inquiretWriting -->

     <div class="m_myPage_mid2 mypageMid" id="m_myPage_inquireComment">
      <div class="m_myPage_IC_title">
       <p class="m_myPage_T">준비중입니다..</p>
      
      </div>
      <div class="m_myPage_myRecodBox">
       <div class="m_myPage_record">
       <p>&nbsp;</p>
       <p>&nbsp;</p>
        <!--
        <p class="m_myPage_record1">***병원</p>
        <p class="m_myPage_record2">@@@병원</p> 
        -->
       </div>
       <div class="m_myPage_moreView">
        <!-- <input type="button" value="더 보기" class="m_myPage_moreButton"
        onclick="location.href='<%= path %>/member/moreView.do?more=16'"> -->
       </div>
      </div>
      <!-- m_myPage_myRecodBox -->
     </div>
     <!-- m_myPage_inquireComment -->

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