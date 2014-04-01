package edu.ycp.TeamManager.persist;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.User;


public class FakeDatabase implements IDatabase {
	
	ArrayList<User> users;
	
	public FakeDatabase(){
		users = new ArrayList<User>();
		users.add(new User("dmashuda", "daniel", "mashuda", "dmashuda@ycp.edu", "abc123", new LinkedList<String>(), new LinkedList<String>()));
	}
	
	@Override
	public boolean makeuser(User use) {
		for(User us: users){
			// make sure username does not exist
			if(us.getUsername().equals(use.getUsername())){
				return false;
			}
		}
		
		users.add(use);
		return true;
	}

	@Override
	public Boolean Login(LoginData login) {
		
		for(User user: users){
			// find the user in the db
			if(user.getUsername().equals(login.getUsername())){
				// verify password
				if(login.getPasswordHash().equals(user.getPasswordHash())){
					
					return true;
				}
			}
			
		}
		return false;
	}

}
	

