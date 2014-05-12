/**
 * 
 */
package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Workout;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

/**
 * @author dan
 *
 */
public class AddWorkout {
	public boolean addWorkout(Workout work, String teamid) {
		IDatabase db = Database.getInstance();
		return db.addWorkout(work, teamid);
	}
}
