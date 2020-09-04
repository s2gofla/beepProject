package beepbeep.member.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.service.MoreViewService;

public class MoreViewHandler implements CommandHandler{
   private static final String FORM_VIEW="/member/m7_myPage";

@Override
public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 LoginDTO dto = (LoginDTO)request.getSession().getAttribute("authUser");
     
     Map<String, Boolean> errors = new HashMap<>();
     request.setAttribute("errors", errors);
     
     
     String  m = request.getParameter("more");
     System.out.println(m);
     request.setAttribute("m", m);
     
     
     try {
        
        System.out.println(m);
        
        if(m.equals("1")) { //병원 북마크
       	 
       	response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
           return null;
        }
        else if(m.equals("2")) { //약 북마크
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("3")) { //병원 리뷰
           
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }         
        else if(m.equals("4")) { //약 리뷰
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("5")) { //QnA 질문
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("6")) { //QnA 답변
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("7")) { // 치료 팁 글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("8")) { // 치료 팁 댓글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("9")) {  //기부/나눔 글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("10")) { //기부/나눔 댓글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("11")) { // 자유게시판 글
        	
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("12")) { //자유게시판 댓글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("13")) { //신고 글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("14")) { //신고 댓글
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        else if(m.equals("15")) { //문의하기
       	 response.sendRedirect(request.getContextPath()+"/community/free_list.do?searchCondition=2&searchWord="+dto.getId()); 
            return null;
        }
        
        
        else {
           return  FORM_VIEW;

        }
     }catch (Exception e) {
        e.printStackTrace();
        return  FORM_VIEW;
     }

     
  }

}