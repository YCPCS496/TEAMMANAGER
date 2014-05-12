package edu.ycp.TeamManager.persist;

import java.util.ArrayList;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.Model.Event;
import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.Model.Workout;

public interface IDatabase {
	/**
	 * Creates a single {@link User} with inputed information
	 * 
	 * @param use {@link User} to create
	 * @return if user was made
	 */
	public boolean makeuser(User use);
	
	/**
	 *  Logs the user in to the system
	 * 
	 * @param login {@link LoginData}
	 * @return if credentials are valid returns true
	 */
	public Boolean Login(LoginData login);
	
	/**
	 * 
	 * @param team
	 */
	public Boolean makeTeam(Team team);
	
	/**
	 * 
	 * @param userId
	 * @param teamId
	 * @return 
	 */
	public boolean requestJoinTeam(String userId, String teamId);
	
	/**
	 * 
	 * @param userId
	 * @param teamId
	 * @return
	 */
	public boolean confirmPlayer(String userId, String teamId);
	
	/**
	 * Gets arraylist of playerids for players requesting to join team
	 * 
	 * @param teamId
	 * @return 
	 */
	public ArrayList<String> waitingconfirmations(String teamId);
	
	/**
	 * 
	 * @param teamId
	 * @param userId
	 * @return
	 */
	public boolean isTeamMember(String teamId, String userId);
	
	/**
	 * 
	 * @param teamId
	 * @param userId
	 * @return
	 */
	public boolean isTeamAdmin(String teamId, String userId);
	
	/**
	 * Returns the list of all of the userids for 
	 * <br />
	 * the teamId that is passed
	 * 
	 * @param teamId
	 * @return
	 */
	public ArrayList<String> getUserIds(String teamId);
	
	
	/**
	 * 
	 * @param userId
	 * @return {@link User}
	 */
	public User getUserById(String userId);
	
	/**
	 * 
	 * 
	 * @param teamId
	 * @return {@link Team}
	 */
	public Team getTeamById(String teamId);
	
	/**
	 * 
	 * @param work
	 * @return
	 */
	public boolean addWorkout(Workout work, String teamId);
	
	/**
	 * 
	 * @param workoutId
	 * @return
	 */
	public Workout getWorkout(String workoutId); 
	
	/**
	 * 
	 * @param ann
	 * @return
	 */
	public boolean addAnnouncement(Announcement ann, String teamId);
	
	/**
	 * 
	 * @param annId
	 * @return
	 */
	public Announcement getAnnouncementById(String annId);
	
	
	/**
	 * 
	 * @param announcementid
	 * @param playerid
	 * @return
	 */
	public boolean viewAnnouncement(String announcementid, String playerid);
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public ArrayList<Announcement> getUnviewedAnnouncement(String userid);
	
	/**
	 * 
	 * @return all of the users
	 */
	public ArrayList<User> getAllUsers();
	
	
	/**
	 * 
	 * @return all of the teams
	 */
	public ArrayList<Team> geteAllTeams();
	
	
}
