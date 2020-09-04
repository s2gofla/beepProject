package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import net.sf.json.JSONObject;

public class UserlikeHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("userlike 접속");
		
		String book = request.getParameter("userlike");
		int h_review_code = request.getParameter("h_review_code") == null ? -1 : Integer.parseInt(request.getParameter("h_review_code"));
		int mreview_seq = request.getParameter("mreview_seq") == null ? -1 : Integer.parseInt(request.getParameter("mreview_seq"));
		String id = request.getParameter("id");
		
		
		HashMap<String, Object> map = new HashMap<>();
		
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		int result = 0;
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			if (book.equals("0")) {
				
				result = mreview_seq == -1 ? dao.insertUserLike(con, h_review_code, id) : dao.insertMUserLike(con, mreview_seq, id);
				
			}else if(book.equals("-1")){
				
				result = mreview_seq == -1 ? dao.deleteUserLike(con, h_review_code, id) : dao.deleteMUserLike(con, mreview_seq, id);

			}
			if (mreview_seq == -1) {
				dao.updateLike(con, h_review_code, book);
			}else {
				dao.MupdateLike(con,mreview_seq, book) ;
			}
			con.commit();
			
			int allLikes =  mreview_seq == -1 ? dao.selectLikes(con, h_review_code) : dao.selectMLikes( con, mreview_seq);
			System.out.println("카운트해온수:"+allLikes);
			
			map.put("result", result);
			//map.put("books",books);
			map.put("h_review_code",h_review_code);
			map.put("mreview_seq",mreview_seq);
			map.put("allLikes", allLikes);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(con);
		}
		
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map",map);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
	}

}
