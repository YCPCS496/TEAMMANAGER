package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Event;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class AddEvent {
	public boolean addEvent(Event even){
		IDatabase db = Database.getInstance();
		return db.addEvent(even);
	}

}
