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
	
	private String password;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private LinkedList<String> teamsBelonging;
	private LinkedList<String> teamsOwned;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
