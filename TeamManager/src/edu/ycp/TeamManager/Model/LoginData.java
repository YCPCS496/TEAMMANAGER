package edu.ycp.TeamManager.Model;

public class LoginData {
	private String username;
	private String password;
	/**
	 * Constructor for logindata
	 * 
	 * @param username
	 * @param pass
	 */
	public LoginData(String username, String pass){
		this.username = username;
		this.password = pass;
		
	}
	
	/**
	 * Empty constructor
	 */
	public LoginData(){
		username = new String(" ");
		password = new String(" ");
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
