package edu.ycp.cs496.TeamManager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.*;

import com.google.appengine.api.urlfetch.HTTPResponse;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.control.VerifyLogin;

@SuppressWarnings("serial")
public class TeamManagerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String action = req.getParameter("action");
		
		if(action == null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().println("No action selected");
			return;
		}
		
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String action = req.getParameter("action");
		
		if(action == null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().println("No action selected");
			return;
		}
		
		if(action.equals("login")){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			//sanitize this
			LoginData data = new LoginData(username, password);
			
			VerifyLogin controller = new VerifyLogin();
			if(controller.verifyLogin(data)){
				Cookie logincookie = new Cookie("TeamManUser", username);
				logincookie.setMaxAge(-1);
				resp.addCookie(logincookie);
				
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("Welcome " + username);
				
				return;
			}
			
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.setContentType("text/plain");
			resp.getWriter().println("Incorrect Login Credentials");
			
		}
		if(action.equals("newUser")){
			resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			resp.getWriter().println("Creating a new user is not implemented yet");
		}
		if(action.equals("cookieCheck")){
			Cookie[] cookies = req.getCookies();
			
			if(cookies == null){
				return;
			}
			for (int i = 0; i < cookies.length; i++) {
				  String name = cookies[i].getName();
				  if(name.equals("TeamManUser")){
					  String username = cookies[i].getValue();
					  resp.getWriter().println("Welcome " + username + " You were identified by your cookies");
					  
				  }
			}
		}
		
		if(action.equals("logout")){
			Cookie logincookie = new Cookie("TeamManUser", "nouser");
			logincookie.setMaxAge(0);
			resp.addCookie(logincookie);
		}
	}
}
