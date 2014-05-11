/**
 * 
 */
package edu.ycp.TeamManager.persist;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.Model.Workout;
import edu.ycp.cs496.util.BCrypt;

/**
 * @author dan
 *
 */
public class GoogleDataStoreDatabase implements IDatabase {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#makeuser(edu.ycp.TeamManager.Model.User)
	 */
	@Override
	public boolean makeuser(User use) {
		Entity newuser = new Entity("User");
		newuser.setProperty("username", use.getUsername());
		newuser.setProperty("passwordhash", use.getPasswordHash());
		newuser.setProperty("firstname", use.getFirstname());
		newuser.setProperty("lastname", use.getLastname());
		newuser.setProperty("email", use.getEmail());
		newuser.setProperty("teamsbelonging", use.getTeamsBelonging());
		newuser.setProperty("teamsowned", use.getTeamsOwned());
		Key check = datastore.put(newuser);
		if(check == null){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#Login(edu.ycp.TeamManager.Model.LoginData)
	 */
	@Override
	public Boolean Login(LoginData login) {
	
		Query q = new Query("User").setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, login.getUsername()));
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(5);
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		for(Entity e: results){
			String hash = (String) e.getProperty("passwordhash");
			if(BCrypt.checkpw(login.getPassword(), hash)){
				return true;
			}
		}
		
		
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#makeTeam(edu.ycp.TeamManager.Model.Team)
	 */
	@Override
	public Boolean makeTeam(Team team) {
		Entity newteam = new Entity("Team");
		newteam.setProperty("userids", team.getUserids());
		newteam.setProperty("useridRequests", team.getUseridRequests());
		newteam.setProperty("owners", team.getOwners());
		newteam.setProperty("announcmentids", team.getAnnouncmentids());
		newteam.setProperty("eventids", team.getEventids());
		newteam.setProperty("workoutids", team.getWorkoutids());
		newteam.setProperty("teamName", team.getTeamName());
		
		Key check = datastore.put(newteam);
		if(check == null){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#requestJoinTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean requestJoinTeam(String userId, String teamId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#confirmPlayer(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean confirmPlayer(String userId, String teamId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#waitingconfirmations(java.lang.String)
	 */
	@Override
	public ArrayList<String> waitingconfirmations(String teamId) {
		Team t = getTeamById(teamId);
		
		return t.getUseridRequests();
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#isTeamMember(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isTeamMember(String teamId, String userId) {
		Team t = getTeamById(teamId);
		if(t.getUserids().contains(userId)){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#isTeamAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isTeamAdmin(String teamId, String userId) {
		Team t = getTeamById(teamId);
		if(t.getOwners().contains(userId)){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUserIds(java.lang.String)
	 */
	@Override
	public ArrayList<String> getUserIds(String teamId) {
		Team t = getTeamById(teamId);
		return t.getUserids();
	}


	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String userId) {
		ArrayList<User> users = new ArrayList<User>();
		Query q = new Query("User").setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, userId));
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		for(Entity e: results){
			
			User u = new User();
			
			u.setPasswordHash((String)e.getProperty("passwordhash"));
			u.setUsername((String)e.getProperty("username"));
			u.setFirstname((String)e.getProperty("firstname"));
			u.setLastname((String)e.getProperty("lastname"));
			u.setEmail( (String) e.getProperty("email"));
			u.setTeamsBelonging((ArrayList<String>)e.getProperty("teamsbelonging"));
			u.setTeamsOwned((ArrayList<String>)e.getProperty("teamsowned"));
			
			
			users.add(u);
			
		}
		if(users.size()  == 1){
			return users.get(0);
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getTeamById(java.lang.String)
	 */
	@Override
	public Team getTeamById(String teamId) {
		ArrayList<Team> teams = new ArrayList<Team>();
		//System.out.println(KeyFactory.createKey("Team", teamId));
		Query q = new Query("Team").setFilter(new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, KeyFactory.createKey("Team", Long.parseLong(teamId))));
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		//System.out.println(results.size());
		for(Entity e: results){
			
			Team t = new Team();
			t.setAnnouncmentids((ArrayList<String>)e.getProperty("announcmentids"));
			t.setUserids((ArrayList<String>) e.getProperty("userids"));
			t.setUseridRequests((ArrayList<String>) e.getProperty("useridRequests"));
			t.setEventids((ArrayList<String>) e.getProperty("eventids"));
			t.setWorkoutids((ArrayList<String>) e.getProperty("workoutids"));
			t.setId(String.valueOf(e.getKey().getId()));
			t.setTeamName((String) e.getProperty("teamName"));
			
			teams.add(t);
			
		}
		if(teams.size() == 1){
			return teams.get(0);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#addWorkout(edu.ycp.TeamManager.Model.Workout)
	 */
	@Override
	public boolean addWorkout(Workout work) {
		Entity workout = new Entity("workout");
		workout.setProperty("title", work.getTitle());
		workout.setProperty("notes", work.getNotes());
		workout.setProperty("durationmin", work.getDurationMin());
		workout.setProperty("intensity", work.getIntensity());
		workout.setProperty("reps", work.getReps());
		
		Key check = datastore.put(workout);
		if(check == null){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getWorkout(java.lang.String)
	 */
	@Override
	public Workout getWorkout(String workoutId) {
		ArrayList<Workout> work = new ArrayList<Workout>();
		Query q = new Query("announcement").setFilter(new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, KeyFactory.createKey("workout", Long.parseLong(workoutId))));
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		
		for(Entity e: results){
			Workout w = new Workout();
			w.setTitle((String)e.getProperty("title"));
			w.setNotes((String)e.getProperty("notes"));
			w.setDurationMin((Integer) e.getProperty("durationmin"));
			w.setIntensity((Integer)e.getProperty("intensity"));
			w.setReps((Integer)e.getProperty("reps"));
			w.setId(String.valueOf(e.getKey().getId()));
		}
		if(work.size() == 1){
			return work.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#addAnnouncement(edu.ycp.TeamManager.Model.Announcement)
	 */
	@Override
	public boolean addAnnouncement(Announcement ann) {
		Entity announce = new Entity("announcement");
		announce.setProperty("usersviewed", ann.getUsersViewed());
		announce.setProperty("usersnotviewed", ann.getUsersNotViewed());
		announce.setProperty("title", ann.getTitle());
		announce.setProperty("message", ann.getMessage());
		
		Key check = datastore.put(announce);
		if(check == null){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getAnnouncementById(java.lang.String)
	 */
	@Override
	public Announcement getAnnouncementById(String annId) {
		ArrayList<Announcement> anns = new ArrayList<Announcement>();
		//System.out.println(KeyFactory.createKey("Team", teamId));
		Query q = new Query("announcement").setFilter(new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, KeyFactory.createKey("announcement", Long.parseLong(annId))));
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		//System.out.println(results.size());
		for(Entity e: results){
			
			Announcement a = new Announcement();
			a.setUsersViewed((ArrayList<String>)e.getProperty("usersviewed"));
			a.setUsersNotViewed((ArrayList<String>) e.getProperty("usersnotviewed"));
			a.setTitle((String) e.getProperty("title"));
			a.setMessage((String)e.getProperty("message"));
			a.setId(String.valueOf(e.getKey().getId()));
			
			
			anns.add(a);
			
		}
		if(anns.size() == 1){
			return anns.get(0);
		}
		
		return null;
	}


	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#viewAnnouncement(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean viewAnnouncement(String announcementid, String playerid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUnviewedAnnouncement(java.lang.String)
	 */
	@Override
	public ArrayList<Announcement> getUnviewedAnnouncement(String userid) {
		ArrayList<Announcement> announce = new ArrayList<Announcement>();
		
		for(String teamid :getUserById(userid).getTeamsBelonging()){
			Team t = getTeamById(teamid);
			for(String a: t.getAnnouncmentids()){
				Announcement checkann = getAnnouncementById(a);
				if(checkann.getUsersNotViewed().contains(userid)){
					announce.add(checkann);
				}
				
			}
		}
		return announce;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getAllUsers()
	 */
	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		for(Entity e: results){
			
			User u = new User();
			
			u.setPasswordHash((String)e.getProperty("passwordhash"));
			u.setUsername((String)e.getProperty("username"));
			u.setFirstname((String)e.getProperty("firstname"));
			u.setLastname((String)e.getProperty("lastname"));
			u.setEmail( (String) e.getProperty("email"));
			u.setTeamsBelonging((ArrayList<String>)e.getProperty("teamsbelonging"));
			u.setTeamsOwned((ArrayList<String>)e.getProperty("teamsowned"));
			
			
			users.add(u);
			
		}
		
		return users;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#geteAllTeams()
	 */
	@Override
	public ArrayList<Team> geteAllTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		Query q = new Query("Team");
		PreparedQuery pq = datastore.prepare(q);
		
		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
		for(Entity e: results){
			
			Team t = new Team();
			t.setAnnouncmentids((ArrayList<String>)e.getProperty("announcmentids"));
			t.setUserids((ArrayList<String>) e.getProperty("userids"));
			t.setUseridRequests((ArrayList<String>) e.getProperty("useridRequests"));
			t.setEventids((ArrayList<String>) e.getProperty("eventids"));
			t.setWorkoutids((ArrayList<String>) e.getProperty("workoutids"));
			t.setId(String.valueOf(e.getKey().getId()));
			t.setTeamName((String) e.getProperty("teamName"));
			
			teams.add(t);
			
		}
		return teams;
	}

}
