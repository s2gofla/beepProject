<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
 table , td, th {
   border:solid 1px gray;
 }
 table{
     border-spacing: 3px;
     border-collapse: separate;
   }
   table,  tr, td {
    /* border-radius: 3px; */
    /* padding:3px;  */
   }
 table{
    width: 600px;
 }
</style>
</head>
<body>


<div align="center">
  <h2>삭제하기</h2>
  <form action="/jspPro/cstvsboard/delete.htm" method="post">
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
      <!-- <input type="button" onClick="javascript:history.back();" value="취소"> -->
      <input type="button" id="cancel" value="취소">
    </td>
  </tr>
</table> 

<input type="hidden" name="seq" value="${ param.seq }" />
</form>
</div>
<%
	String error = (String)request.getAttribute("error");
	if ( error != null ){
%>
<script>
	alert( "<%= error %>");
</script>
<%		
	}
%>

<script>
	//$(document).ready(function(){})
	// 위에랑 밑에랑 같음
	$(function(){
		$("#cancel").click(function(){
			location.href = ""; <%-- /jspPro/cstvsboard/content.htm?seq=<%= request.getParameter("seq") %> --%>
		});
	});
</script>

</body>
</html>