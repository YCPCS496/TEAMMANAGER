package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

/**
 * 
 * @author dan
 *
 */
public class AddTeam {
	public boolean addTeam(Team team) {
		IDatabase db = Database.getInstance();
		return db.makeTeam(team);
	}
}
