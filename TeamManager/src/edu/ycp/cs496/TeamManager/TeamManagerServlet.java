package edu.ycp.cs496.TeamManager;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.http.*;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.fasterxml.jackson.databind.JsonMappingException;

import edu.ycp.TeamManager.JSON.JSON;
import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.Model.Workout;
import edu.ycp.TeamManager.control.AddAnnouncement;
import edu.ycp.TeamManager.control.AddTeam;
import edu.ycp.TeamManager.control.AddUser;
import edu.ycp.TeamManager.control.AddWorkout;
import edu.ycp.TeamManager.control.ConfimJoin;
import edu.ycp.TeamManager.control.GetAllTeamsClean;
import edu.ycp.TeamManager.control.GetAllUsersClean;
import edu.ycp.TeamManager.control.GetAnnouncementById;
import edu.ycp.TeamManager.control.GetTeamById;
import edu.ycp.TeamManager.control.GetUserById;
import edu.ycp.TeamManager.control.GetWorkoutById;
import edu.ycp.TeamManager.control.RequestJoin;
import edu.ycp.TeamManager.control.VerifyLogin;
import edu.ycp.TeamManager.control.ViewAnnouncement;
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
		
		// gets action 
		String path = req.getPathInfo();
		if(path == null){
			return;
		}
			
		
		path = path.substring(1);
		int slashparam = path.indexOf('/');
		String reqString = path;
		
		if(slashparam == -1 || slashparam == (path.length()-1)){
			if(reqString.contains("/")){
				reqString = reqString.substring(0, reqString.length()-1);
			}
			reqString = Jsoup.clean(reqString, Whitelist.basic());
			
			if (reqString.equals("")) {
				resp.setContentType("text/plain");
				resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
				resp.getWriter().println("Not implemented yet: getting all: funny thing that I dont know what to do with");
				return;
			}
			
			if(reqString.equals("users")){
				GetAllUsersClean control = new GetAllUsersClean();
				resp.setContentType("application/json");
				JSON.getObjectMapper().writeValue(resp.getWriter(), control.getAllUsersClean());
				resp.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			if(reqString.equals("teams")){
				GetAllTeamsClean control = new GetAllTeamsClean();
				resp.setContentType("application/json");
				JSON.getObjectMapper().writeValue(resp.getWriter(), control.getAllTeamsClean());
				resp.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			
			
			
			resp.setContentType("text/plain");
			resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
			resp.getWriter().println("Not implemented yet: getting all: |"+reqString+"|");
			return;
		}
		
		reqString = path.substring(0, slashparam);
		
		
		//gets target object
		String target = path.substring(slashparam+1);
		
		//cleans input
		reqString = Jsoup.clean(reqString, Whitelist.basic());
		target = Jsoup.clean(target, Whitelist.basic());
		
		if(reqString.equals("users")){
			String targetuser = target;
			GetUserById control = new GetUserById();
			User retuser = control.getUserById(targetuser);
			if(retuser == null){
				resp.setContentType("text/plain");
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			retuser.setPasswordHash("NULL");
			
			
			
			JSON.getObjectMapper().writeValue(resp.getWriter(), retuser);
			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		if(reqString.equals("teams")){
			String targetteam = target;
			
			GetTeamById control = new GetTeamById();
			Team retTeam =  control.getTeamById(targetteam);
			
			// if the user is not part of the team, then clear
			//the team data except owners names and team name
			/*
			if(!retTeam.getUserids().contains(username)){
				retTeam.getEventids().clear();
				retTeam.getUserids().clear();
				retTeam.getAnnouncmentids().clear();
				retTeam.getEventids().clear();
				retTeam.getWorkoutids().clear();
			}
			*/
			
			JSON.getObjectMapper().writeValue(resp.getWriter(), retTeam);
			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
			
		}
		if(reqString.equals("workouts")){
			String targetworkout = target;
			GetWorkoutById control = new GetWorkoutById();
			Workout retwork = control.getWorkoutById(targetworkout);
			
			JSON.getObjectMapper().writeValue(resp.getWriter(), retwork);
			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		if(reqString.equals("announcements")){
			//TODO: get tarteted announcement and return it in json
			String targetannouncemnet = target;
			
			GetAnnouncementById control = new GetAnnouncementById();
			Announcement retAnnouncement = control.getAnnouncementById(targetannouncemnet);
			
			JSON.getObjectMapper().writeValue(resp.getWriter(), retAnnouncement);
			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		//if a username exists, then return welcome message
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		resp.setContentType("text/plain");
		resp.getWriter().println("Welcome " + username + " you were identified by session: " + target);
		
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
			if(username == null || password == null || username == "" || password == ""){
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
			if(req.getParameter("password1") == null || req.getParameter("password2") == null){
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				resp.setContentType("text/plain");
				resp.getWriter().println("User Creation Failed: incomplete request");
				return;
			}
			if(req.getParameter("password1").equals(req.getParameter("password2"))){
				
				// gets info for new account
				String username = req.getParameter("username");
				String password = req.getParameter("password1");
				String firstname = req.getParameter("firstname");
				String lastname = req.getParameter("lastname");
				String email = req.getParameter("email");
				
				System.out.println(email);
				
				if(username == null || password == null || firstname == null || lastname == null || email == null){
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
					resp.setContentType("text/plain");
					resp.getWriter().println("Incomplete request");
					return;
				}
				if(username == " " || password == " " || firstname == " " || lastname == " " || email == " "){
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
					resp.setContentType("text/plain");
					resp.getWriter().println("Incomplete request");
					return;
				}
				if(username == "" || password == ""|| firstname == "" || lastname == "" || email == ""){
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
					resp.setContentType("text/plain");
					resp.getWriter().println("Incomplete request");
					return;
				}
				
				
			
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
			
			if(teamName == "" || teamName == null){
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				resp.setContentType("text/plain");
				resp.getWriter().println("Incomplete request");
				return;
			}
			teamName = Jsoup.clean(teamName, Whitelist.basic());

			
			Team team = new Team(username, teamName, ""+System.currentTimeMillis());
			
			AddTeam controller = new AddTeam();
			Boolean check = controller.addTeam(team);
			
			if(check){
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("Team " + teamName + " created: ");
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
			if(teamId == null || teamId == ""){
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.setContentType("text/plain");
				resp.getWriter().println("you must include a team ID in your request");
				return;
			}
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
			
			if(teamId == "" || teamId == null || confiruser == null || confiruser == ""){
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				resp.setContentType("text/plain");
				resp.getWriter().println("Incomplete request");
				return;
			}
			
			ConfimJoin control = new ConfimJoin();
			boolean check = control.confirmJoin(username, teamId, confiruser);
			
			if(check){
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println(confiruser + " is now a part of " + teamId);
			}else{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				resp.setContentType("text/plain");
				resp.getWriter().println("Confirm player failure");
			}
			
		}
		if(action.equals("newAnnouncement")){

			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");

			// if user does not have active session, then deny access
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
						
			

			
			try{
				Announcement proposedann = JSON.getObjectMapper().readValue(req.getInputStream(), Announcement.class);
				if(proposedann == null){
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					resp.setContentType("text/plain");
					resp.getWriter().println("bad request, did you send the value in JSON?");
					return;
				}
				User tryus = new GetUserById().getUserById(username);
				if(tryus.getTeamsOwned().size()==0){
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					resp.setContentType("text/plain");
					resp.getWriter().println("bad request, does the user own a team?");
					return;
				}
				AddAnnouncement control = new AddAnnouncement();
				boolean check = control.addAnnouncement(proposedann, tryus.getTeamsOwned().get(1));
				if(check){
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("text/plain");
					resp.getWriter().println("Announcement added successfully");
					return;
				}
				else{
					resp.setStatus(HttpServletResponse.SC_CONFLICT);
					resp.setContentType("text/plain");
					resp.getWriter().println("Data was valid, but data store failed");
					return;
				}
			}catch(JsonMappingException e){
				e.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.setContentType("text/plain");
				resp.getWriter().println("bad request, did you send the value in JSON?");
				return;
			}
			
		}
		
			
		
		if(action.equals("newWorkout")){
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			
			// if user does not have active session, then deny access
			if(username == null){
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setContentType("text/plain");
				resp.getWriter().println("Session not active, please log in");
				return;
			}
			
			
			try{
				Workout proposedwork = JSON.getObjectMapper().readValue(req.getInputStream(), Workout.class);
				if(proposedwork == null){
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					resp.setContentType("text/plain");
					resp.getWriter().println("bad request, did you send the value in JSON?");
					return;
				}
				User tryus = new GetUserById().getUserById(username);
				if(tryus.getTeamsOwned().size()==0){
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					resp.setContentType("text/plain");
					resp.getWriter().println("bad request, does the user own a team?");
					return;
				}
				AddWorkout work = new AddWorkout();
				if(tryus.getTeamsOwned().size() ==1){
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					resp.setContentType("text/plain");
					resp.getWriter().println("bad request, does the user own a team?");
					return;
				}
				boolean check = work.addWorkout(proposedwork, tryus.getTeamsOwned().get(1));
				if(check){
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setContentType("text/plain");
					resp.getWriter().println("Workout added successfully");
					return;
				}
				else{
					resp.setStatus(HttpServletResponse.SC_CONFLICT);
					resp.setContentType("text/plain");
					resp.getWriter().println("Data was valid, but data store failed");
					return;
				}
			}catch(JsonMappingException e){
				e.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.setContentType("text/plain");
				resp.getWriter().println("bad request, did you send the value in JSON?" + e.getStackTrace().toString());
				return;
			}
			
		}
		
		if(action.equals("viewAnnouncement")){
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("user");
			String announcementId = req.getParameter("announcementId");
			if(username == null || announcementId == null){
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.setContentType("text/plain");
				resp.getWriter().println("bad request");
				resp.getWriter().println("viewAnnoucement takes the following parameters:");
				resp.getWriter().println("userId and announcementId");
				return;
			}
			if(username.equals("") || announcementId.equals("")){
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.setContentType("text/plain");
				resp.getWriter().println("bad request");
				resp.getWriter().println("viewAnnoucement takes the following parameters:");
				resp.getWriter().println("userId and announcementId");
				return;
			}
			ViewAnnouncement controll = new ViewAnnouncement();
			boolean check = controll.viewAnnouncement(announcementId, username);
			if(check){
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("announcement marked as viewed");
				return;
			}
			
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			resp.getWriter().println("Good request, but something went wrong");
			return;
			
			
		}
		
		//logs user out
		if(action.equals("logout")){
			HttpSession session = req.getSession();
			session.invalidate();
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println("Logged Out");
			return;
		}
	}
}
