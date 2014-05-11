package edu.ycp.TeamManager.persist;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.Model.Event;
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
	ArrayList<Event> events; 
	
	
	public FakeDatabase(){
		users = new ArrayList<User>();
		teams = new ArrayList<Team>();
		events = new ArrayList<Event>();
		users.add(new User("dmashuda", "daniel", "mashuda", "dmashuda@ycp.edu", HashLoginData.hashData("abc123"), new LinkedList<String>(), new LinkedList<String>()));
		workouts = new ArrayList<Workout>();
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
					for(User u: users){
						if(u.getUsername().equals(userId)){
							u.addTeamBelong(teamId);
						}
					}
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
	public User getUserById(String userId) {
		for(User u: users){
			if(u.getUsername().equals(userId)){
				return u;
			}
		}
		return null;
	}

	@Override
	public Team getTeamById(String teamId) {
		for(Team t: teams){
			if(t.getId().equals(teamId)){
				return t;
			}
		}
		return null;
	}

	@Override
	public boolean addWorkout(Workout work) {
		for(Workout w: workouts){
			if(w.getId().equals(work.getId())){
				return false;
			}
		}
		workouts.add(work);
		return true;
	}

	@Override
	public Workout getWorkout(String workoutId) {
		for(Workout w: workouts){
			if(workoutId.equals(w.getId())){
				return w;
			}
		}
		return null;
	}

	@Override
	public boolean addAnnouncement(Announcement ann) {
		for(Announcement announce: announcements){
			if(ann.getId().equals(announce.getId())){
				return false;
			}
		}
		announcements.add(ann);
		return true;
	}

	@Override
	public Announcement getAnnouncementById(String annId) {
		for(Announcement ann: announcements){
			if(ann.getId().equals(annId)){
				return ann;
			}
		}
		return null;
	}


	@Override
	public boolean viewAnnouncement(String announcementid, String playerid) {
		for(Announcement a: announcements){
			if(a.getId().equals(announcementid)){
				return a.addUserViewed(playerid);
			}
		}
		return false;
	}

	@Override
	public ArrayList<Announcement> getUnviewedAnnouncement(String userid) {
		ArrayList<Announcement> anns = new ArrayList<Announcement>();
		for(Announcement a: announcements){
			if(!a.userViewedMessage(userid)){
				anns.add(a);
			}
		}
		return anns;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		return users;
	}

	@Override
	public ArrayList<Team> geteAllTeams() {
		return teams;
	}

}
	

