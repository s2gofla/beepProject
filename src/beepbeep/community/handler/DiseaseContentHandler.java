package beepbeep.community.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.community.dto.DiseaseContentDTO;
import beepbeep.community.dto.DiseaseListDTO;
import beepbeep.community.service.DiseaseContentService;
import beepbeep.community.service.DiseaseListService;
import beepbeep.member.dto.LoginDTO;

public class DiseaseContentHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("diseaseContentHandler 요청");
		int dtip_seq = Integer.parseInt(request.getParameter("dtip_seq"));
		
		DiseaseContentService diseaseContentService = new DiseaseContentService();
		DiseaseListDTO dto = diseaseContentService.selectOne(dtip_seq);
		request.setAttribute("dto", dto);
		
		LoginDTO dto2 = (LoginDTO)request.getSession().getAttribute("authUser");
		String id = dto2==null?"NONE":dto2.getId();
		
		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		List<DiseaseContentDTO> comdto = DiseaseContentService.selectCom(dtip_seq, id);
		
		request.setAttribute("comdto", comdto);
		
		return "/community/diseasecontent";
	}

}
