package beepbeep.information.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.information.dto.DiseaseListView;
import beepbeep.information.service.DiseaseListService;
import beepbeep.information.dto.DiseaseListCategoryDTO;
import beepbeep.information.service.DiseaseListCategoryService;

public class DiseaseListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* 페이징 */
		String pCurrentPage = request.getParameter("page");
		int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
		String searchWord = request.getParameter("searchWord");
		if (searchWord == null || searchWord.equals("")) searchWord = "*";
		
		DiseaseListService diseaselistservice = DiseaseListService.getInstance();
		DiseaseListView view = diseaselistservice.selectList(currentPage, searchWord);				
		request.setAttribute("view", view);

		/* 카테고리 */
		DiseaseListCategoryService diseasecategoryService = DiseaseListCategoryService.getInstance();
		List<DiseaseListCategoryDTO> diseasecategoryList = diseasecategoryService.diseasecategoryList();
		request.setAttribute("diseasecategoryList", diseasecategoryList);

		/* 검색 */
		if(searchWord.equals("*")) searchWord="";
		request.setAttribute("searchWord", searchWord);
		
		return "/information/disease_board";
	}

}
