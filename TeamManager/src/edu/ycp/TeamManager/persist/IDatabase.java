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
	 *  Logs the user in to the syste
	 * 
	 * @param login {@link LoginData}
	 * @return if credentials are valid returns true
	 */
	public Boolean Login(LoginData login);
	
}
