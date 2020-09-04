package beepbeep.information.handler.ajax;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.information.dto.PriceContentDTO;
import beepbeep.information.dto.PriceListDTO;
import beepbeep.information.dto.PriceListView;
import beepbeep.information.service.PriceListService;
import net.sf.json.JSONObject;

public class SubjectMatchingHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		
		String pCurrentPage = request.getParameter("page");
		int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
		String searchWord = request.getParameter("searchWord");
		if (searchWord == null || searchWord.equals("")) searchWord = "*";
		
		
		int m_sub_seq = request.getParameter("m_sub_seq")==null? 0: Integer.parseInt(request.getParameter("m_sub_seq"));
		PriceListService pricelistservice = PriceListService.getInstance();
		PriceListView view = pricelistservice.selectList(currentPage, searchWord, m_sub_seq);
		
		view.setM_sub_seq(m_sub_seq);
		view.setSearchWord(searchWord);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("view", view);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);

	}

}
