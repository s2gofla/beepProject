package beepbeep.information.handler;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.information.dto.ToptipListView;
import beepbeep.information.service.ToptipListService;
import beepbeep.information.dto.ToptipCategoryDTO;
import beepbeep.information.service.ToptipCategoryService;

public class ToptipListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 페이징 */
		String pCurrentPage = request.getParameter("page");
		int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
		String searchWord = request.getParameter("searchWord");
		if (searchWord == null || searchWord.equals("")) searchWord = "*";
 
		ToptipListService toptiplistservice = ToptipListService.getInstance();
		ToptipListView view = toptiplistservice.selectList(currentPage, searchWord);
		request.setAttribute("view", view);
 
		/* 꿀팁 카테고리 */
		ToptipCategoryService toptipcategoryService = ToptipCategoryService.getInstance();
		List<ToptipCategoryDTO> toptipcategoryList = toptipcategoryService.toptipCategoryList();
		request.setAttribute("toptipcategoryList", toptipcategoryList);
		
		/* 검색 */
		if(searchWord.equals("*")) searchWord="";
		request.setAttribute("searchWord", searchWord);
		
		return "/information/toptip_board";
	}

}
