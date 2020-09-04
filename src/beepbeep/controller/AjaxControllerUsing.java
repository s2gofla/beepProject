package beepbeep.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.command.CommandHandler;



public class AjaxControllerUsing extends HttpServlet{
	
	private Map<String, AjaxHandler> AjaxHandlerMap = new HashMap<String, AjaxHandler>();
	@Override
	public void init() throws ServletException {
		String path = getInitParameter("configFile");
		String configFilePath = getServletContext().getRealPath(path);
		Properties prop = new Properties();
		try(FileReader fr = new FileReader(configFilePath)) {
			prop.load(fr);
		}catch(IOException e) {
			throw new ServletException(e);
		}
		Iterator<Object> ir = prop.keySet().iterator();
		while(ir.hasNext()) {
			String url = (String) ir.next(); 
			String handlerClassName = prop.getProperty(url);  
			
			Class<?> handlerClass;
			try {
				handlerClass = Class.forName(handlerClassName);
				AjaxHandler handlerInstance = (AjaxHandler) handlerClass.newInstance();
				AjaxHandlerMap.put(url, handlerInstance);

			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ajax > controller");
		String requestURI = request.getRequestURI();
		
		if(requestURI.indexOf(request.getContextPath()) == 0){
			requestURI = requestURI.substring(request.getContextPath().length());
		}
		AjaxHandler handler = AjaxHandlerMap.get(requestURI);

		try {
			handler.process(request, response);
			System.out.println("ajax controller 받아옴");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
