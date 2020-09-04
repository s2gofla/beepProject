package beepbeep.community.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.community.dto.DonationContentDTO;
import beepbeep.community.dto.DonationListDTO;
import beepbeep.community.service.DonationContentService;
import beepbeep.member.dto.LoginDTO;

public class DonationContentHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("donationContentHandler 요청");
		int d_seq = Integer.parseInt(request.getParameter("d_seq"));
		
		DonationContentService donationContentService = new DonationContentService();
		DonationListDTO dto = donationContentService.selectOne(d_seq);
		request.setAttribute("dto", dto);
		
		LoginDTO dto2 = (LoginDTO)request.getSession().getAttribute("authUser");
		String id = dto2==null?"NONE":dto2.getId();
		
		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		List<DonationContentDTO> comdto = DonationContentService.selectCom(d_seq, id);
		
		request.setAttribute("comdto", comdto);
		
		//System.out.println(dto);
		//System.out.println(comdto);
		
		return "/community/donationcontent";
	}

}
