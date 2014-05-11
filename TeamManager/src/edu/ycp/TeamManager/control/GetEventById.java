package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Event;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetEventById {
	public Event getEventById(String id){
		IDatabase db = Database.getInstance();
		//return db.getEventById(id);
		return null;
	}

}
