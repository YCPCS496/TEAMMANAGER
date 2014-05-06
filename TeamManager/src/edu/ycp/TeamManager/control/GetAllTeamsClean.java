package edu.ycp.TeamManager.control;

import java.util.ArrayList;

import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.persist.Database;
import edu.ycp.TeamManager.persist.IDatabase;

public class GetAllTeamsClean {
	public ArrayList<Team> getAllTeamsClean() {
		IDatabase db = Database.getInstance();
		ArrayList<Team> teams = db.geteAllTeams();
		
		for(Team t: teams){
			t.getAnnouncmentids().clear();
			t.getUseridRequests().clear();
			t.getUserids().clear();
			t.getEventids().clear();
			t.getWorkoutids().clear();
		}
		
		return teams;
	}

}
