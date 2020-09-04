package beepbeep.information.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.information.dto.PriceListView;
import beepbeep.information.service.PriceListService;

public class PriceListHandler implements CommandHandler {

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

      /* 페이징 */
      String pCurrentPage = request.getParameter("page");
      int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
      String searchWord = request.getParameter("searchWord");
      if (searchWord == null || searchWord.equals("")) searchWord = "*";
      
      int m_sub_seq = request.getParameter("m_sub_seq")==null? 0: Integer.parseInt(request.getParameter("m_sub_seq"));
      PriceListService pricelistservice = PriceListService.getInstance();
      PriceListView view = pricelistservice.selectList(currentPage, searchWord, m_sub_seq);
      request.setAttribute("view", view);
      
      view.setM_sub_seq(m_sub_seq);
      view.setSearchWord(searchWord);
      
      /* 가격 카테고리 */
      CategoryService categoryService = CategoryService.getInstance();
      List<CategoryDTO> categoryList = categoryService.CategoryList();
      request.setAttribute("categoryList", categoryList);
      
      /* 검색 */
      if(searchWord.equals("*")) searchWord="";
      request.setAttribute("searchWord", searchWord);

      return "/information/price_info";
   }

}