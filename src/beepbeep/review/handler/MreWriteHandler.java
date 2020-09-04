package beepbeep.review.handler;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import beepbeep.command.CommandHandler;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.service.HreviewService;
import beepbeep.review.service.MreviewService;

public class MreWriteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		if (request.getMethod().equalsIgnoreCase("GET")) {

			return "/review/mrevWrite";
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			
			System.out.println("post로 요청됌!!");
			
			try {
				
				if (request.getParameter("rfile") != null) {
					
					String saveDirectory = request.getSession().getServletContext().getRealPath("/beep_images/mrev-images");
					//첨부파일 업로드 
					File saveDir = new File(saveDirectory);
					if (!saveDir.exists()) {
						saveDir.mkdir();
					}
					int maxPostSize = 1024*1024*5;
					String encoding = "UTF-8";
					
					FileRenamePolicy policy = new DefaultFileRenamePolicy();
					
					MultipartRequest mrequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
					
					//파일 경로 저장
					String fileName = mrequest.getFilesystemName("rfile");
					String picFile = saveDirectory + "/" + fileName;
					
					String id = mrequest.getParameter("id");
					int m_code = Integer.parseInt(mrequest.getParameter("m_code"));
					String score_kind = mrequest.getParameter("score_effect");
					String score_price = mrequest.getParameter("score_comfort");
					String score_result = mrequest.getParameter("score_price");
					int m_price = Integer.parseInt(mrequest.getParameter("m_price"));
					String content = mrequest.getParameter("content");
			
				} else {
					
					return processSubmit(request, response);
			
				}

				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return null;

	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("첨부파일 없음");
			
			
			String saveDirectory = request.getSession().getServletContext().getRealPath("/beep_images/hrev-images");
			MultipartRequest mrequest = new MultipartRequest(request, saveDirectory, "UTF-8");
			MreviewDTO dto = new MreviewDTO();
			
			
			//id 아직 세션안받음.. 임의로 설정
			
			String id = mrequest.getParameter("id");
			int m_code = Integer.parseInt(mrequest.getParameter("m_code"));
			int score_effect =Integer.parseInt( mrequest.getParameter("score_effect") );
			int score_price = Integer.parseInt( mrequest.getParameter("score_price") );
			int score_comfort = Integer.parseInt( mrequest.getParameter("score_comfort") );
			int m_price = Integer.parseInt(mrequest.getParameter("m_price"));
			String contents = mrequest.getParameter("content");
			
		
		
			dto.setId(id);
			dto.setM_code(m_code);
			dto.setScore_effect(score_effect);
			dto.setScore_price(score_price);
			dto.setScore_comfort(score_comfort);
			dto.setM_price(m_price);
			dto.setContents(contents);

			
			MreviewService service = MreviewService.getInstance();
			
			int result = service.write(dto);
			System.out.println("result"+ result);
			
			  if (result ==1) {
			  response.sendRedirect("../review/mreview_detail.do?m_code="+m_code); }
			 
			
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return null;
	}
	

}
