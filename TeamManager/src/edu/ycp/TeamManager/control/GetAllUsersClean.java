package edu.ycp.TeamManager.control;

import java.util.ArrayList;

import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetAllUsersClean {
	public ArrayList<User> getAllUsersClean(){
		IDatabase db = Database.getInstance();
		ArrayList<User> users = db.getAllUsers();
		
		//cleans information for non public viewing
		for(User u: users){
			u.setPasswordHash("NO");
			u.setFirstname("NOT AUTHORIZED");
			u.setEmail("NOT AUTHORIZED");
			u.getTeamsBelonging().clear();
		}

		return users;
	}

}
