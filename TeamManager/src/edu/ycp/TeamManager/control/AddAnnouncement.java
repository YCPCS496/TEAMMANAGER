package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class AddAnnouncement {
	public boolean addAnnouncement(Announcement ann, String teamid){
		IDatabase db = Database.getInstance();
		return db.addAnnouncement(ann, teamid);
	}
}
