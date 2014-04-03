/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dan
 *
 */
public class Announcement {
	
	private List<String> usersViewed;
	private List<String> usersNotViewed;
	private String title;
	private String message;
	private String id;
	
	/**
	 * constructs an empty announcement
	 */
	public Announcement(){
		usersNotViewed = new LinkedList<String>();
		usersViewed = new LinkedList<String>();
		title = new String(" ");
		message = new String(" ");
		id = " ";
		
	}
	
	/**
	 * Constructs an announcement
	 * 
	 * @param title
	 * @param message
	 * @param usersNotViewed
	 * @param usersViewed
	 */
	public Announcement(String id, String title, String message, List<String> usersNotViewed, List<String> usersViewed){
		this.title = title;
		this.message = message;
		this.usersNotViewed = usersNotViewed;
		this.usersViewed = usersViewed;
		this.id = id;
	}
	/**
	 * 
	 * @param usersViewed
	 */
	public void setUsersViewed(List<String> usersViewed){
		this.usersViewed = usersViewed;
	}
	
	/**
	 * 
	 * @param usersNotViewed
	 */
	public void setUsersNotViewed(List<String> usersNotViewed){
		this.usersNotViewed = usersNotViewed;
	}
	
	/**
	 * 
	 * @param username
	 * @returns true if user was added
	 */
	public boolean addUserViewed(String username) {
		return usersViewed.add(username);
	}
	
	/**
	 * 
	 * @param username
	 * @returns true if user was added
	 */
	public boolean addUserNotViewed(String username){
		return usersNotViewed.add(username);
	}
	
	/**
	 * 
	 * @param username
	 * @returns true if user was removed from not viewed and added to viewed
	 */
	public boolean userViewedMessage(String username){
		if(usersNotViewed.remove(username)){
			usersViewed.add(username);
			return true;
		}
		return false;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
