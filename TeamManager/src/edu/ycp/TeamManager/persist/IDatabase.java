package edu.ycp.TeamManager.persist;

import java.util.ArrayList;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;

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
	 * Returns the username of the users that the userId matches
	 * 
	 * 
	 * @param userId
	 * @return
	 */
	public String getUsernameById(String userId);
	
	
}
