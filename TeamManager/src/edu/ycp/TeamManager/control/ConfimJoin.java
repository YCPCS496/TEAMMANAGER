package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

/**
 * 
 * @author dan
 *
 */
public class ConfimJoin {
	public boolean confirmJoin(String ownerName, String teamId, String targetuserId) {
		IDatabase db = Database.getInstance();
		if(db.isTeamAdmin(teamId, ownerName)){
			return db.confirmPlayer(targetuserId, teamId);
		}
		return false;
	}

}
