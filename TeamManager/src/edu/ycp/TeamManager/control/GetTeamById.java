/**
 * 
 */
package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

/**
 * @author dan
 *
 */
public class GetTeamById {
	public Team getTeamById(String teamId){
		IDatabase db = Database.getInstance();	
		return db.getTeamById(teamId);
	}
}
