package beepbeep.qna.handler.ajax;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.qna.dao.QnaAjaxDAO;
import beepbeep.qna.dto.QnaListDTO;
import beepbeep.qna.dto.QnaListView;
import net.sf.json.JSONObject;

public class ChoiceListHandler implements AjaxHandler{
	private static final int QNA_COUNT_PER_PAGE = 5;
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int sort = request.getParameter("sort")==null?1:Integer.parseInt(request.getParameter("sort"));
		int m_sub_seq = request.getParameter("m_sub_seq")==null? 0:Integer.parseInt(request.getParameter("m_sub_seq"));
		String sWord = request.getParameter("sWord");
		if(sWord==null || sWord.equals("")) sWord="*";
		String pCurrentPage = request.getParameter("page");
		int currentPage = 1;
		int numberOfPageBlocks = 10;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		QnaAjaxDAO dao = QnaAjaxDAO.getInstance();
		int qnaTotalCount = dao.selectCount(m_sub_seq, sWord);
		String m_sub_name = m_sub_seq==0? "전체" : dao.selectMname(m_sub_seq);
		System.out.println("sort   "+sort+" sWord   "+sWord+"m_sub " +m_sub_seq+"pcurrent  "+pCurrentPage+"current    "+currentPage );
		QnaListView view = null;
		List<QnaListDTO> qnaList = null;
		int firstRow = 0;
		int endRow = 0;
		if (qnaTotalCount > 0) {
			firstRow = (currentPage - 1) * QNA_COUNT_PER_PAGE + 1;
			endRow = firstRow + QNA_COUNT_PER_PAGE - 1;
			
			qnaList = dao.choiceList(firstRow, endRow , m_sub_seq, sort, sWord );
			view =  new QnaListView(qnaTotalCount, currentPage, qnaList,  QNA_COUNT_PER_PAGE, firstRow, endRow);
			int pageBlockStart = 1;
			int pageBlockEnd=10;
			pageBlockStart = (currentPage-1)/numberOfPageBlocks*numberOfPageBlocks+1;
			pageBlockEnd = pageBlockStart + numberOfPageBlocks-1;
			if(pageBlockEnd > view.getPageTotalCount()) pageBlockEnd = view.getPageTotalCount();
			
			view.setStart(pageBlockStart);
			view.setEnd(pageBlockEnd);
			
			view.setPrev(pageBlockStart != 1? true: false);
			view.setNext(pageBlockEnd != view.getPageTotalCount()?true:false);
			 
			
		} else {
			currentPage = 0;
			qnaList = Collections.emptyList();
			view =  new QnaListView(qnaTotalCount, currentPage, qnaList,  QNA_COUNT_PER_PAGE, firstRow, endRow);
		}
	
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("view", view);
		jsonObj.put("m_sub_seq", m_sub_seq);
		jsonObj.put("name", m_sub_name);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}
}
