package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetUserById {
	public User getUserById(String userId) {
		IDatabase db = Database.getInstance();	
		return db.getUserById(userId);

	}

}
