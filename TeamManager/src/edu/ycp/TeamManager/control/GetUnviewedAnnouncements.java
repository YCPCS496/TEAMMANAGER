package edu.ycp.TeamManager.control;

import java.util.ArrayList;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetUnviewedAnnouncements {
	public ArrayList<Announcement> getUnviewedAnnouncements(String username){
		IDatabase db = Database.getInstance();
		return db.getUnviewedAnnouncement(username);
	}

}
