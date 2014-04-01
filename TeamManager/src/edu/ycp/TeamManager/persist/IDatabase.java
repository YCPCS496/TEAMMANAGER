package edu.ycp.TeamManager.persist;

import edu.ycp.TeamManager.Model.LoginData;
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
	 *  Logs the user in to the system, returns session ID and stores session ID
	 * 
	 * @param login
	 * @return session id if matches, 0 if not
	 */
	public String Login(LoginData login);
	
}
