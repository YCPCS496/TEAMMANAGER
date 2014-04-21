package edu.ycp.TeamManager.persist;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.Model.Workout;
import edu.ycp.cs496.util.BCrypt;
import edu.ycp.cs496.util.HashLoginData;


public class FakeDatabase implements IDatabase {
	
	ArrayList<User> users;
	ArrayList<Team> teams;
	ArrayList<Workout> workouts;
	ArrayList<Announcement> announcements;
	
	
	public FakeDatabase(){
		users = new ArrayList<User>();
		teams = new ArrayList<Team>();
		users.add(new User("dmashuda", "daniel", "mashuda", "dmashuda@ycp.edu", HashLoginData.hashData("abc123"), new LinkedList<String>(), new LinkedList<String>()));
		
		Team testteam = new Team("dmashuda", "Ice Dragons", "12345");
		teams.add(testteam);
		
	}
	
	@Override
	public boolean makeuser(User use) {
		for(User us: users){
			// make sure username does not exist
			if(us.getUsername().equals(use.getUsername())){
				return false;
			}
		}
		
		users.add(use);
		return true;
	}

	@Override
	public Boolean Login(LoginData login) {
		
		for(User user: users){
			// find the user in the db
			if(user.getUsername().equals(login.getUsername())){
				// verify password
				if(BCrypt.checkpw(login.getPassword(), user.getPasswordHash())){
					return true;
				}
			}
			
		}
		return false;
	}

	@Override
	public Boolean makeTeam(Team team) {
		
		for(Team t: teams){
			if(t.getId().equals(team.getId())){
				return false;
			}
		}
		
		return teams.add(team);		
	}

	@Override
	public boolean requestJoinTeam(String userId, String teamId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				t.getUseridRequests().add(userId);
				return true;
			}
		}
		return false;
		
	}

	@Override
	public boolean confirmPlayer(String userId, String teamId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				if(t.getUseridRequests().contains(userId)){
					t.getUseridRequests().remove(userId);
					t.getUserids().add(userId);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> waitingconfirmations(String teamId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				return t.getUseridRequests();
			}
		}
		return null;
	}

	@Override
	public boolean isTeamMember(String teamId, String userId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				for(String id: t.getUserids()){
					if(id.equals(userId)){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isTeamAdmin(String teamId, String userId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				for(String id: t.getOwners()){
					if(id.equals(userId)){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> getUserIds(String teamId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				return t.getUserids();
			}
		}
		return null;
	}

	@Override
	public String getUsernameById(String userId) {
		
		for(User u: users){
			if(u.getUsername().equals(userId)){
				return u.getFirstname() + " " + u.getLastname();
			}
		}
		return null;
	}

}
	

