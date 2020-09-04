package beepbeep.qna.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.qna.dto.QnaListView;
import beepbeep.qna.service.QnaListService;

public class QnaListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("> QnAListHandler ");
		String pCurrentPage = request.getParameter("page");
		String delete_yn = request.getParameter("delete_yn");
		request.setAttribute("delete_yn", delete_yn);
		int currentPage = 1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		QnaListService qnaListService = QnaListService.getInstance();
		QnaListView listData= qnaListService.getQnaList(currentPage);
		request.setAttribute("listData", listData );
		
		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		return "/QnA/qna";
	}

}
