package beepbeep.community.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import beepbeep.command.CommandHandler;
import beepbeep.community.dto.DonationContentDTO;
import beepbeep.community.service.DonationWriteService;

public class DonationWriteHandler implements CommandHandler {



	   @Override
	   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      System.out.println("donationWriteHandler 요청");
	         


	      
	      if(request.getMethod().equalsIgnoreCase("GET")) {
	          return "/community/donationwrite";
	      }else if(request.getMethod().equalsIgnoreCase("POST")) {
	    	  return postProcess(request, response);
	      }
	      
	      else {
	          response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	             return null;
	      }
	   }
	   
	   
	   private String postProcess(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, IOException, ServletException {

	         
	         String path = request.getSession().getServletContext().getRealPath("beep_images/donation-images");
	            System.out.println(path);
	            int size = 1024*1024*10;
	            
	            MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8",new DefaultFileRenamePolicy());
	            
	            Enumeration files = multi.getFileNames();
	            
	            String photo = "/beepPro/beep_images/donation-images/";
	            
	            String str = (String) files.nextElement();
	            String multiphoto= multi.getFilesystemName(str);   
	            
	            photo =multiphoto == null? null: multiphoto;
	        

	            
	            DonationContentDTO dto = new DonationContentDTO();

	            //java.sql.Date d = java.sql.Date.valueOf(day);
		   
		   String id = multi.getParameter("id");
	       String header = multi.getParameter("header");
	       String title = multi.getParameter("writetitle"); // 입력된거
	       String contents = multi.getParameter("writecontent");
	       String pic = multi.getParameter("pic");
	       //System.out.println("hi");
	       
	       dto.setId(id);
	       dto.setHeader(header);
	       dto.setTitle(title);
	       dto.setContents(contents);
	       dto.setPic(pic);
	       //id, title, header, contents, d_seq, pic
	       DonationWriteService donationWriteService = DonationWriteService.getInstance();
	       // 됐는지 숫자로 받아옴
	       dto = new DonationContentDTO(id, header, title, contents, pic);
	       int result = donationWriteService.donationwrite(dto);
	       String viewPage = "/beepPro/community/donation_list.do";
	       if(result==1) {
	             response.sendRedirect(viewPage+"?on=success");
	          }else {
	             response.sendRedirect(viewPage+"?on=fail");
	          }
	          RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	          dispatcher.forward(request, response);
	          return "";
	       
	    }
	   
	   
	   }
	   
	   
	   
	     


