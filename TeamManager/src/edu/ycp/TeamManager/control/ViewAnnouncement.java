package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class ViewAnnouncement {
	public boolean viewAnnouncement(String annId, String username){
		IDatabase db = Database.getInstance();
		return db.viewAnnouncement(annId, username);
	}
}
