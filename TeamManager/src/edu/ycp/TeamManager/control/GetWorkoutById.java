package edu.ycp.TeamManager.control;

import edu.ycp.TeamManager.Model.Workout;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetWorkoutById {
	public Workout getWorkoutById(String id) {
		IDatabase db = Database.getInstance();	
		return db.getWorkout(id);
	}

}
