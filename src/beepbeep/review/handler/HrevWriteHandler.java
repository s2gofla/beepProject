package beepbeep.review.handler;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.review.model.CategoryModalDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreivewPicDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;
import beepbeep.review.service.HreviewService;

public class HrevWriteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
				
			HreviewService service = HreviewService.getInstance();
		
			ArrayList<HinfoDTO> list = null;
			List<CategoryModalDTO> clist = null;
			List<CategoryDTO> subTypeList = null;
			
			int h_code = request.getParameter("h_code")== null ?  -1 :Integer.parseInt(request.getParameter("h_code"));
			
			
			list = service.selectHname();
			
			if (h_code != -1) {
				clist = service.selectCategory(h_code);
				subTypeList = service.CaategryList(h_code);
				request.setAttribute("clist", clist);
				request.setAttribute("subTypeList", subTypeList);
				
			}
			

			
			request.setAttribute("list", list);
			
			return "/review/hrevWrite";
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			
			System.out.println("post로 요청됌!!");
			
			try {
				
				
					
					String saveDirectory = request.getSession().getServletContext().getRealPath("/beep_images/hrev-images");
					//첨부파일 업로드 
					File saveDir = new File(saveDirectory);
					if (!saveDir.exists()) {
						saveDir.mkdir();
					}
					int maxPostSize = 1024*1024*5;
					String encoding = "UTF-8";
					
					FileRenamePolicy policy = new DefaultFileRenamePolicy();
					
					MultipartRequest mrequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
					HreviewDTO dto = new HreviewDTO();
					
					String id = mrequest.getParameter("id");
					String h_name = mrequest.getParameter("h_name");
					
					
					int h_code = Integer.parseInt(mrequest.getParameter("h_code"));
					int score_kind =Integer.parseInt( mrequest.getParameter("score_kind") );
					int score_price = Integer.parseInt( mrequest.getParameter("score_price") );
					int score_result = Integer.parseInt( mrequest.getParameter("score_result") );
					int score_satisfaction = Integer.parseInt(  mrequest.getParameter("score_satisfaction") );
					int review_type = Integer.parseInt(mrequest.getParameter("review_type"));
					String contents = mrequest.getParameter("content");
					String[] treatment_codes = mrequest.getParameterValues("treatment_code");
					int[] treatment_code =  Arrays.stream(treatment_codes).mapToInt(Integer::parseInt).toArray();					
					String[] prices = mrequest.getParameterValues("price");
					int[] price =  Arrays.stream(prices).mapToInt(Integer::parseInt).toArray();		
					
					//글쓸 정보들 저장
					dto.setH_code(h_code);
					dto.setId(id);
					dto.setScore_kind(score_kind);
					dto.setScore_price(score_price);
					dto.setScore_result(score_result);
					dto.setScore_satisfaction(score_satisfaction);
					dto.setReview_type(review_type);
					dto.setContents(contents);				
					
					//파일 경로 저장
					String fileName = mrequest.getFilesystemName("rfile");
					String cfileName = mrequest.getFilesystemName("cfile");
					
					if (fileName != null) {
						
						HreivewPicDTO pdto = new HreivewPicDTO();
						ArrayList<HreivewPicDTO> piclist = new ArrayList<HreivewPicDTO>();
						pdto.setPic(fileName);
						piclist.add(pdto);
						
						dto.setPicture(piclist);
						
						System.out.println(pdto.getPic());
					}
					
					//인증 첨부파일 저장
					if (review_type == 1) {
						dto.setCertification(cfileName);
					}
					
					//치료항목 저장..
						HreviewTreatDTO tdto = new HreviewTreatDTO();
						ArrayList<HreviewTreatDTO> treatList = new ArrayList<HreviewTreatDTO>();
						for (int i = 0; i < price.length; i++) {
							
							tdto.setTreatment_code(treatment_code[i]);
							tdto.setPrice(price[i]);
							
							treatList.add(tdto);
						}
						
						dto.setTreatment(treatList);
						

					
					
					HreviewService service = HreviewService.getInstance();
					
					int result = service.write(dto);
					
					
					
					if (result ==1) {
						h_name = URLEncoder.encode(h_name);
						response.sendRedirect("../review/hreview_detail.do?h_code="+h_code+"&h_name="+h_name);
					}
					
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return null;

	}


}
