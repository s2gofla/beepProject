package beepbeep.community.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.community.dto.FreeCommentDTO;
import beepbeep.community.dto.FreeListDTO;
import beepbeep.community.dto.FreeListView;
import beepbeep.community.service.FreeContentService;
import beepbeep.community.service.FreeListService;
import beepbeep.member.dto.LoginDTO;

public class FreeContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeContentHandler 요청");
		int fboard_seq = Integer.parseInt(request.getParameter("fboard_seq"));
		// FreeContentService
		FreeContentService freeContentService = new FreeContentService();
		FreeListDTO dto = freeContentService.selectOne(fboard_seq);
		request.setAttribute("dto", dto);
		
		LoginDTO dto2 = (LoginDTO)request.getSession().getAttribute("authUser");
		String id = dto2==null?"NONE":dto2.getId();

		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		List<FreeCommentDTO> comdto = freeContentService.selectCom(fboard_seq, id);
		
		request.setAttribute("comdto", comdto);
		
		
		
		return "/community/freecontent";
	}

}
