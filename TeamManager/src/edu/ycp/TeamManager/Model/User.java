/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.ArrayList;
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
	//private String sessionHash;
	private ArrayList<String> teamsBelonging;
	private ArrayList<String> teamsOwned;
	
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
		teamsBelonging = new ArrayList<String>();
		teamsBelonging.add(" ");
		teamsOwned = new ArrayList<String>();
		for(String team: belonged){
			this.teamsBelonging.add(team);
		}
		for(String team: owned){
			this.teamsOwned.add(team);
		}
		//this.sessionHash = new String("");
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
		teamsBelonging = new ArrayList<String>();
		teamsOwned = new ArrayList<String>();
	}
	public ArrayList<String> getTeamsBelonging() {
		return teamsBelonging;
	}

	public void setTeamsBelonging(ArrayList<String> teamsBelonging) {
		this.teamsBelonging = teamsBelonging;
	}

	public ArrayList<String> getTeamsOwned() {
		return teamsOwned;
	}

	public void setTeamsOwned(ArrayList<String> teamsOwned) {
		this.teamsOwned = teamsOwned;
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

	
	
}
