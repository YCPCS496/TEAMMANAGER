/**
 * 
 */
package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;


/**
 * @author dan
 *
 */

/**
 * 
 * controller to add a new user to the database
 *
 */
public class AddUser {
	public boolean addUser(User use){
		IDatabase db = Database.getInstance();
		return db.makeuser(use);
	}

}
