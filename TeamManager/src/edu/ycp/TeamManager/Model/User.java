/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.LinkedList;

/**
 * @author Dan
 *
 */
public class User {
	
	private String passwordHash;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String sessionHash;
	private LinkedList<String> teamsBelonging;
	private LinkedList<String> teamsOwned;
	
	/**
	 * 
	 * @param username
	 * @param first
	 * @param last
	 * @param email
	 * @param belonged
	 * @param owned
	 */
	public User(String username, String first, String last, String email, String passHash,LinkedList<String> belonged, LinkedList<String> owned){
		this.username = username;
		this.passwordHash = passHash;
		this.firstname = first;
		this.lastname = last;
		this.email = email;
		this.teamsBelonging.addAll(belonged);
		this.teamsOwned.addAll(owned);
		this.sessionHash = new String("");
	}
	
	/**
	 * constructs an empty user
	 */
	public User(){
		username = new String(" ");
		passwordHash = new String(" ");
		firstname = new String(" ");
		lastname = new String(" ");
		email = new String(" ");
		teamsBelonging = new LinkedList<String>();
		teamsOwned = new LinkedList<String>();
	}
	/**
	 * 
	 * @return user name
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return First Name
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * 
	 * @return user's Last Name
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}
	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * 
	 * @param teamName team that User belongs to
	 */
	public void addTeamBelong(String teamName) {
		teamsBelonging.add(teamName);
	}
	
	/**
	 * 
	 * @param teamName team name that is owned by user
	 */
	public void addTeamOwned(String teamName) {
		teamsOwned.add(teamName);
	}

	/**
	 * @return the sessionHash
	 */
	public String getSessionHash() {
		return sessionHash;
	}

	/**
	 * @param sessionHash the sessionHash to set
	 */
	public void setSessionHash(String sessionHash) {
		this.sessionHash = sessionHash;
	}
	
}
