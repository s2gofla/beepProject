package beepbeep.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AjaxHandler {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
