package edu.ycp.cs496.TeamManager;

import java.io.IOException;

import javax.servlet.http.*;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.control.VerifyLogin;

@SuppressWarnings("serial")
public class TeamManagerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String action = req.getParameter("action");
		
		if(action.equals("login")){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			//sanitize this
			LoginData data = new LoginData(username, password);
			
			VerifyLogin controller = new VerifyLogin();
			if(controller.verifyLogin(data)){
				resp.addCookie(new Cookie("user", username));
			}
			
		}
	}
}
