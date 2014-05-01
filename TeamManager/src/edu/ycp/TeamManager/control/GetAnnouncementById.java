package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetAnnouncementById {
	public Announcement getAnnouncementById(String id){
		IDatabase db = Database.getInstance();
		return db.getAnnouncementById(id);
	}
}
