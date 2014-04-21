/**
 * 
 */
package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

/**
 * @author dan
 *
 */
public class RequestJoin {
	public boolean requestJoin(String username, String teamId){
		IDatabase db = Database.getInstance();		
		return db.requestJoinTeam(username, teamId);
	}
}
