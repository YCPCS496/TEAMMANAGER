package edu.ycp.cs496.TeamManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.*;

import com.google.appengine.api.urlfetch.HTTPResponse;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.control.VerifyLogin;
import edu.ycp.cs496.util.BCrypt;

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
			
			//TODO: sanitize this data
			LoginData data = new LoginData(username, password);
			
			VerifyLogin controller = new VerifyLogin();
			if(controller.verifyLogin(data)){
				HttpSession session= req.getSession();
				session.setAttribute("user", data.getUsername());
				session.setMaxInactiveInterval(30*60);
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("Welcome " + data.getUsername());
				
				return;
			}
			
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.setContentType("text/plain");
			resp.getWriter().println("Incorrect Login Credentials");
			
		}
		if(action.equals("newUser")){
			resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			User newuse = new User("danm", "dan", "mash", "dmashuda.adm@ycp.edu", BCrypt.hashpw("abc123", BCrypt.gensalt()), new LinkedList<String>(), new LinkedList<String>());
			User new2us = new User();
			resp.getWriter().println("Creating a new user is not implemented yet");
		}
		if(action.equals("sessionCheck")){
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/plain");
			resp.getWriter().println("Welcome " + username + " you were identified by session");
			
		}
		
		if(action.equals("logout")){
			HttpSession session = req.getSession();
			session.invalidate();
			resp.getWriter().println("Session cleared");
		}
	}
}
