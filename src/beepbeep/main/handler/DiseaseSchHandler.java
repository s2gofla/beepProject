package beepbeep.main.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.information.dto.DiseaseListCategoryDTO;
import beepbeep.information.dto.DiseaseListView;
import beepbeep.information.service.DiseaseListCategoryService;
import beepbeep.information.service.DiseaseListService;

public class DiseaseSchHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String search = request.getParameter("search");
		System.out.println("search> "+search);
		int currentPage = 1;
		String searchWord = request.getParameter("searchWord");
		if (searchWord == null || searchWord.equals("")) searchWord = "*";
		
		DiseaseListService diseaselistservice = DiseaseListService.getInstance();
		DiseaseListView view = diseaselistservice.selectList(currentPage, search);				
		request.setAttribute("view", view);
		/* 카테고리 */
		DiseaseListCategoryService diseasecategoryService = DiseaseListCategoryService.getInstance();
		List<DiseaseListCategoryDTO> diseasecategoryList = diseasecategoryService.diseasecategoryList();
		request.setAttribute("diseasecategoryList", diseasecategoryList);
		request.setAttribute("searchWord", search);
		return "/information/disease_board";
	}

}
