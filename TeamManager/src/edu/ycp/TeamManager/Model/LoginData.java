package edu.ycp.TeamManager.Model;

public class LoginData {
	private String username;
	private String password;
	private String passwordHash;
	/**
	 * Constructor for logindata
	 * 
	 * @param username
	 * @param pass
	 */
	public LoginData(String username, String pass){
		this.username = username;
		this.password = pass;
		this.setPasswordHash(new String(" "));
		
	}
	
	/**
	 * Empty constructor
	 */
	public LoginData(){
		username = new String(" ");
		password = new String(" ");
		this.setPasswordHash(new String(" "));
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
	
	
}
