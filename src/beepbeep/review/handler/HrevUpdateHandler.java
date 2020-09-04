package beepbeep.review.handler;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import beepbeep.command.CommandHandler;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;
import beepbeep.review.service.HrevUpdateService;
import beepbeep.review.service.HreviewService;

public class HrevUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
	if (request.getMethod().equalsIgnoreCase("GET")) {
		
		System.out.println("update핸들러 호출");
		
		int h_review_code = Integer.parseInt(request.getParameter("h_review_code"));
		int h_code = Integer.parseInt(request.getParameter("h_code"));
		
		HreviewDTO dto = new HreviewDTO();
		HrevUpdateService service = HrevUpdateService.getInstance();
		
		dto = service.getReview(h_review_code);
		
		request.setAttribute("h_code", h_code);
		request.setAttribute("dto", dto);

		return "/review/hrevUpdate";
		
	}else if(request.getMethod().equalsIgnoreCase("POST")) {
		
		try {
			System.out.println("update 첨부파일 없음");
			
			
			String saveDirectory = request.getSession().getServletContext().getRealPath("/beep_images/hrev-images");
			MultipartRequest mrequest = new MultipartRequest(request, saveDirectory, "UTF-8");
			HreviewDTO dto = new HreviewDTO();
			
			
			int h_code = Integer.parseInt(mrequest.getParameter("h_code"));
			int h_review_code = Integer.parseInt(mrequest.getParameter("h_review_code"));
			String h_name = mrequest.getParameter("h_name");
			
			int score_kind =Integer.parseInt( mrequest.getParameter("score_kind") );
			int score_price = Integer.parseInt( mrequest.getParameter("score_price") );
			int score_result = Integer.parseInt( mrequest.getParameter("score_result") );
			int score_satisfaction = Integer.parseInt(  mrequest.getParameter("score_satisfaction") );
			
			String [] rtreatment = mrequest.getParameterValues("rtreatment_seq");
			String [] treatment =  mrequest.getParameterValues("treatment_code");
			String [] price = mrequest.getParameterValues("price");
			ArrayList<HreviewTreatDTO> tlist = new ArrayList<>();
			
			
			if (rtreatment != null) {
				System.out.println("여기");
				for (int i = 0; i < treatment.length; i++) { 
					HreviewTreatDTO tdto = new
							HreviewTreatDTO(); 
					tdto.setRtreatment_seq(Integer.parseInt(rtreatment[i]));
					tdto.setTreatment_code(Integer.parseInt(treatment[i]));
					tdto.setPrice(Integer.parseInt(price[i]));
					tlist.add(tdto); 
				}
			}
		
			
			
			
			// int review_type = Integer.parseInt(mrequest.getParameter("review_type"));
			String contents = mrequest.getParameter("content");
			
			dto.setTreatment(tlist);
			dto.setH_code(h_code);
			dto.setH_review_code(h_review_code);
			dto.setScore_kind(score_kind);
			dto.setScore_price(score_price);
			dto.setScore_result(score_result);
			dto.setScore_satisfaction(score_satisfaction);
			// dto.setReview_type(review_type);
			dto.setContents(contents);
			
			HrevUpdateService service = HrevUpdateService.getInstance();
			
			int result = service.update(dto);
		
			if (result ==1) {
				h_name = URLEncoder.encode(h_name);
				response.sendRedirect("../review/hreview_detail.do?h_code="+h_code+"&h_name="+h_name+"&edit=success");
			}
			
			
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	
	
		return null;
	}

}
