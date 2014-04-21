/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author dan
 *
 */
public class Attendance {
	private ArrayList<String> usersAttending;
	private ArrayList<String> usersNotAttending;
	private Map<String, String> playerFeedback;
	private Map<String, String> managerFeedback;
	private String id;
	
	public Attendance(){
		setUsersAttending(new ArrayList<String>());
		setUsersNotAttending(new ArrayList<String>());
		setPlayerFeedback(new TreeMap<String, String>());
		setManagerFeedback(new TreeMap<String, String>());
		id = " ";
	}
	
	public Attendance(ArrayList<String> users, String id){
		setUsersAttending(new ArrayList<String>());
		usersAttending = users;
		setPlayerFeedback(new TreeMap<String, String>());
		setManagerFeedback(new TreeMap<String, String>());
		this.id = id;
		
	}

	/**
	 * @return the usersAttending
	 */
	public ArrayList<String> getUsersAttending() {
		return usersAttending;
	}

	/**
	 * @param usersAttending the usersAttending to set
	 */
	public void setUsersAttending(ArrayList<String> usersAttending) {
		this.usersAttending = usersAttending;
	}

	/**
	 * @return the usersNotAttending
	 */
	public ArrayList<String> getUsersNotAttending() {
		return usersNotAttending;
	}

	/**
	 * @param usersNotAttending the usersNotAttending to set
	 */
	public void setUsersNotAttending(ArrayList<String> usersNotAttending) {
		this.usersNotAttending = usersNotAttending;
	}

	/**
	 * @return the playerFeedback
	 */
	public Map<String, String> getPlayerFeedback() {
		return playerFeedback;
	}

	/**
	 * @param playerFeedback the playerFeedback to set
	 */
	public void setPlayerFeedback(Map<String, String> playerFeedback) {
		this.playerFeedback = playerFeedback;
	}

	/**
	 * @return the managerFeedback
	 */
	public Map<String, String> getManagerFeedback() {
		return managerFeedback;
	}

	/**
	 * @param managerFeedback the managerFeedback to set
	 */
	public void setManagerFeedback(Map<String, String> managerFeedback) {
		this.managerFeedback = managerFeedback;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	

}
