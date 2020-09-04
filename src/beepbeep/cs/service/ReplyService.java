package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_AskDAO;
import beepbeep.cs.model.Cs_AskDTO;

public class ReplyService {
	Connection conn =null;
	public List<Cs_AskDTO> select(){
		Cs_AskDAO dao = Cs_AskDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()){
			List<Cs_AskDTO> list = dao.showRelpy(conn);
			return list;
		} catch (SQLException  | NamingException e) {
			throw new ServiceException("목록 구하기 실패:  " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
