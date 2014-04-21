package edu.ycp.cs496.TeamManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.*;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.control.AddTeam;
import edu.ycp.TeamManager.control.AddUser;
import edu.ycp.TeamManager.control.ConfimJoin;
import edu.ycp.TeamManager.control.RequestJoin;
import edu.ycp.TeamManager.control.VerifyLogin;
import edu.ycp.cs496.util.HashLoginData;

@SuppressWarnings("serial")
public class TeamManagerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("user");
		
		// if user does not have active session, then deny access
		if(username == null){
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.setContentType("text/plain");
			resp.getWriter().println("Session not active, please log in");
			return;
		}
		
		//if a username exists, then return welcome message
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/plain");
		resp.getWriter().println("Welcome " + username + " you were identified by session");
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String action = req.getParameter("action");
		
		// post request must have an action
		if(action == null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().println("No action selected");
			return;
		}
		
		// attempt to login to system
		if(action.equals("login")){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			if(username == null || password == null){
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.setContentType("text/plain");
				resp.getWriter().println("Invalid login");
				return;
			}
			
			
			//sanitizes input
			username = Jsoup.clean(username, Whitelist.basic());
			password = Jsoup.clean(password, Whitelist.basic());
			
			
			LoginData data = new LoginData(username, password);
			
			VerifyLogin controller = new VerifyLogin();
			if(controller.verifyLogin(data)){
				
				//stores username in session
				HttpSession session= req.getSession();
				session.setAttribute("user", data.getUsername());
				session.setMaxInactiveInterval(30*60);
				
				// returns welcome message
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("Welcome " + data.getUsername());
				
				return;
			}
			
			// unauthorized if login is not true
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.setContentType("text/plain");
			resp.getWriter().println("Incorrect Login Credentials");
			
		}
		// makes a new user
		if(action.equals("newUser")){
			if(req.getParameter("password1").equals(req.getParameter("password2"))){
				
				// gets info for new account
				String username = req.getParameter("username");
				String password = req.getParameter("password1");
				String firstname = req.getParameter("firstname");
				String lastname = req.getParameter("lastname");
				String email = req.getParameter("email");
				
				
				// sanitizes input
				username = Jsoup.clean(username, Whitelist.basic());
				password = Jsoup.clean(password, Whitelist.basic());
				firstname = Jsoup.clean(firstname, Whitelist.basic());
				lastname = Jsoup.clean(lastname, Whitelist.basic());
				email = Jsoup.clean(email, Whitelist.basic());
				// creates new user
				 
				 
				User newuse = new User(username, firstname, lastname, email, HashLoginData.hashData(password), new LinkedList<String>(), new LinkedList<String>());
				AddUser controller = new AddUser();
				
				//adds new user to account
				boolean test = controller.addUser(newuse);
				//checks if user was added
				if(test){
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("text/plain");
					resp.getWriter().println("New User: " + newuse.getUsername() + " created");
				}else{
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
					resp.setContentType("text/plain");
					resp.getWriter().println("User Creation Failed");
				}
				
			}else{
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().println("passwords do not match");
				return;
			}
			
			return;

		}
		
		//checkes the session
		if(action.equals("sessionCheck")){
			
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			
			// if user does not have active session, then deny access
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
			
			//if a username exists, then return welcome message
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/plain");
			resp.getWriter().println("Welcome " + username + " you were identified by session");
			
		}
		if(action.equals("newTeam")){
			
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			
			// if user does not have active session, then deny access
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
			
			String teamName = req.getParameter("TeamName");
			teamName = Jsoup.clean(teamName, Whitelist.basic());

			
			Team team = new Team(username, teamName, ""+System.currentTimeMillis());
			
			AddTeam controller = new AddTeam();
			Boolean check = controller.addTeam(team);
			
			if(check){
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("Team " + teamName + " created: " + team.getId());
			}
			else{
				resp.setStatus(HttpServletResponse.SC_CONFLICT);
				resp.setContentType("text/plain");
				resp.getWriter().println("Error Creating team");

			}
			
		}
		if(action.equals("requestJoin")){
			
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			
			// if user does not have active session, then deny access
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
			String teamId = req.getParameter("TeamID");
			teamId = Jsoup.clean(teamId, Whitelist.basic());
			
			RequestJoin control = new RequestJoin();
			boolean check = control.requestJoin(username, teamId);
			if(check){
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println(username + " requested to join team with Id:" + teamId);
			}
			else{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				resp.setContentType("text/plain");
				resp.getWriter().println("No team with Id: "+ teamId + " found");
			}
			return;
		}
		if(action.equals("confirmJoin")){
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			
			// if user does not have active session, then deny access
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
			String teamId = req.getParameter("TeamID");
			teamId = Jsoup.clean(teamId, Whitelist.basic());
			
			String confiruser = req.getParameter("UserConfirm");
			confiruser = Jsoup.clean(confiruser, Whitelist.basic());
			
			ConfimJoin control = new ConfimJoin();
			boolean check = control.confirmJoin(username, teamId, confiruser);
			
			if(check){
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println(username + " is now a part of " + teamId);
			}else{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				resp.setContentType("text/plain");
				resp.getWriter().println("Confirm player failure");
			}
			
		}
		
		
		//logs user out
		if(action.equals("logout")){
			HttpSession session = req.getSession();
			session.invalidate();
			resp.getWriter().println("Session cleared");
		}
	}
}
