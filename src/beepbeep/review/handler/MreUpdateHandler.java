package beepbeep.review.handler;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import beepbeep.command.CommandHandler;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.service.HrevUpdateService;
import beepbeep.review.service.MrevUpdateService;

public class MreUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			
			System.out.println("mupdate핸들러 호출");
			
			int m_review_code = Integer.parseInt(request.getParameter("m_review_code"));
			int m_code = Integer.parseInt(request.getParameter("m_code"));
			
			MreviewDTO dto = new MreviewDTO();
			MrevUpdateService service = MrevUpdateService.getInstance();
			
			dto = service.getMReview(m_review_code);
			
			request.setAttribute("m_code", m_code);
			request.setAttribute("dto", dto);

			return "/review/mrevUpdate";
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			
			try {
				System.out.println("update 첨부파일 없음");
				
				
				String saveDirectory = request.getSession().getServletContext().getRealPath("/beep_images/hrev-images");
				MultipartRequest mrequest = new MultipartRequest(request, saveDirectory, "UTF-8");
				MreviewDTO dto = new MreviewDTO();
				
				
				int m_code = Integer.parseInt(mrequest.getParameter("m_code"));
				int m_review_code = Integer.parseInt(mrequest.getParameter("m_review_code"));
				String m_name = mrequest.getParameter("m_name");
				
				int score_effect =Integer.parseInt( mrequest.getParameter("score_effect") );
				int score_price = Integer.parseInt( mrequest.getParameter("score_price") );
				int score_comfort = Integer.parseInt( mrequest.getParameter("score_comfort") );
				int m_price = Integer.parseInt(mrequest.getParameter("m_price"));
				String contents = mrequest.getParameter("content");
				
				
				dto.setM_code(m_code);
				dto.setM_review_code(m_review_code);
				dto.setScore_effect(score_effect);
				dto.setScore_price(score_price);
				dto.setScore_comfort(score_comfort);
				dto.setM_price(m_price);
				dto.setContents(contents);
				
				MrevUpdateService service = MrevUpdateService.getInstance();
				
				int result = service.update(dto);
			
				if (result ==1) {
					m_name = URLEncoder.encode(m_name);
					response.sendRedirect("../review/mreview_detail.do?m_code="+m_code+"&m_name="+m_name+"&edit=success");
				}
				
				
				
			} catch (Exception e) {
					e.printStackTrace();
			}
			
		}
		
		
			return null;
		}


}
