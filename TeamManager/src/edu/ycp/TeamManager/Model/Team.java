/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.ArrayList;

/**
 * @author dan
 *
 */
public class Team {
	
	private ArrayList<String> userids;
	private ArrayList<String> useridRequests;
	private ArrayList<String> owners;
	private ArrayList<String> announcmentids;
	private ArrayList<String> eventids;
	private ArrayList<String> workoutids;
	private String id;
	private String teamName;
	
	
	public Team(){
		userids = new ArrayList<String>();
		useridRequests = new ArrayList<String>();
		owners = new ArrayList<String>();
		announcmentids = new ArrayList<String>();
		eventids = new ArrayList<String>();
		workoutids = new ArrayList<String>();
		id = new String(" ");
		teamName = new String(" ");
	
	}
	
	public Team(String ownerid, String teamName, String teamId){
		userids = new ArrayList<String>();
		useridRequests = new ArrayList<String>();
		owners = new ArrayList<String>();
		announcmentids = new ArrayList<String>();
		eventids = new ArrayList<String>();
		workoutids = new ArrayList<String>();
		id = teamId;
		this.teamName = teamName;
		
		userids.add(ownerid);
		owners.add(ownerid);
	}
	
	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	/**
	 * @return the workoutids
	 */
	public ArrayList<String> getWorkoutids() {
		return workoutids;
	}
	/**
	 * @param workoutids the workoutids to set
	 */
	public void setWorkoutids(ArrayList<String> workoutids) {
		this.workoutids = workoutids;
	}
	/**
	 * @return the eventids
	 */
	public ArrayList<String> getEventids() {
		return eventids;
	}
	/**
	 * @param eventids the eventids to set
	 */
	public void setEventids(ArrayList<String> eventids) {
		this.eventids = eventids;
	}
	/**
	 * @return the announcmentids
	 */
	public ArrayList<String> getAnnouncmentids() {
		return announcmentids;
	}
	/**
	 * @param announcmentids the announcmentids to set
	 */
	public void setAnnouncmentids(ArrayList<String> announcmentids) {
		this.announcmentids = announcmentids;
	}
	/**
	 * @return the owners
	 */
	public ArrayList<String> getOwners() {
		return owners;
	}
	/**
	 * @param owners the owners to set
	 */
	public void setOwners(ArrayList<String> owners) {
		this.owners = owners;
	}
	/**
	 * @return the usernameRequests
	 */
	public ArrayList<String> getUseridRequests() {
		return useridRequests;
	}
	/**
	 * @param usernameRequests the usernameRequests to set
	 */
	public void setUseridRequests(ArrayList<String> usernameRequests) {
		this.useridRequests = usernameRequests;
	}
	/**
	 * @return the usernames
	 */
	public ArrayList<String> getUserids() {
		return userids;
	}
	/**
	 * @param usernames the usernames to set
	 */
	public void setUserids(ArrayList<String> usernames) {
		this.userids = usernames;
	}

}
