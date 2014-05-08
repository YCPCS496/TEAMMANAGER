/**
 * 
 */
package edu.ycp.TeamManager.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.TeamManager.Model.Announcement;
import edu.ycp.TeamManager.Model.Event;
import edu.ycp.TeamManager.Model.LoginData;
import edu.ycp.TeamManager.Model.Team;
import edu.ycp.TeamManager.Model.User;
import edu.ycp.TeamManager.Model.Workout;

/**
 * @author dan mashuda
 *
 */
public class DerbyDatabase implements IDatabase {
	// Load JDBC Driver
		static {
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			} catch (Exception e) {
				throw new IllegalStateException("Could not load Derby JDBC driver");
			}
		}
		
		// The main method creates the database tables and loads the initial data.
		public static void main(String[] args) throws IOException {
			
			PreparedStatement stmt = null;
			PreparedStatement makeworkout = null;
			PreparedStatement makeuser = null;
			PreparedStatement maketeam = null;
			PreparedStatement makeannouncement = null;
			
			
			try {
				// 1. Open a connection
				Connection conn = DriverManager.getConnection("jdbc:derby:userdata.db;create=true");
				System.out.println("Connected database successfully...");
				
				// 2. Execute a query to create userdata table
				/*
				stmt = conn.prepareStatement(
						" create table userdata (" +
						"	username varchar(100) primary key," +
						"	email varchar(100)," +
						"	password varchar(100)" +
						")");
				*/
				makeworkout = conn.prepareStatement(
						" create table workoutdata (" +
						"	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
						"	durationMin int," +
						"	intensity int," +
						"	reps int," +
						"	title varchar(100),"+
						"	notes varchar(1000)"+
						")");
				makeuser = conn.prepareStatement(
						" create table userdata (" +
						"	username varchar(100) primary key,"+
						"	email varchar(100)," +
						"	firstname varchar(100)," +
						"	passwordhash varchar(100)," +
						"	teamsBelonging varchar(1000),"+
						"	teamsOwned varchar(1000)"+
						")");
				/*
				maketeam = conn.prepareStatement(
						" create table teamdata (" +
						"	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
						"	durationMin int," +
						"	intensity int," +
						"	reps int," +
						"	title varchar(100),"+
						"	notes varchar(1000)"+
						")");
						*/
				
				makeworkout.executeUpdate();
				makeuser.executeUpdate();
				maketeam.executeUpdate();
				makeannouncement.executeUpdate();
				//stmt.executeUpdate();
				System.out.println("Created table in given database...");
			} 
			catch (SQLException se) {
				se.printStackTrace();
			}
			finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// ignore
					}
				}
			}	
		}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#makeuser(edu.ycp.TeamManager.Model.User)
	 */
	@Override
	public boolean makeuser(User use) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#Login(edu.ycp.TeamManager.Model.LoginData)
	 */
	@Override
	public Boolean Login(LoginData login) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#makeTeam(edu.ycp.TeamManager.Model.Team)
	 */
	@Override
	public Boolean makeTeam(Team team) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#requestJoinTeam(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean requestJoinTeam(String userId, String teamId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#confirmPlayer(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean confirmPlayer(String userId, String teamId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#waitingconfirmations(java.lang.String)
	 */
	@Override
	public ArrayList<String> waitingconfirmations(String teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#isTeamMember(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isTeamMember(String teamId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#isTeamAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isTeamAdmin(String teamId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUserIds(java.lang.String)
	 */
	@Override
	public ArrayList<String> getUserIds(String teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUsernameById(java.lang.String)
	 */
	@Override
	public String getUsernameById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getTeamById(java.lang.String)
	 */
	@Override
	public Team getTeamById(String teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#addWorkout(edu.ycp.TeamManager.Model.Workout)
	 */
	@Override
	public boolean addWorkout(Workout work) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getWorkout(java.lang.String)
	 */
	@Override
	public Workout getWorkout(String workoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#addAnnouncement(edu.ycp.TeamManager.Model.Announcement)
	 */
	@Override
	public boolean addAnnouncement(Announcement ann) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getAnnouncementById(java.lang.String)
	 */
	@Override
	public Announcement getAnnouncementById(String annId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#addEvent(edu.ycp.TeamManager.Model.Event)
	 */
	@Override
	public boolean addEvent(Event even) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getEventById(java.lang.String)
	 */
	@Override
	public Event getEventById(String evid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#viewAnnouncement(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean viewAnnouncement(String announcementid, String playerid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getUnviewedAnnouncement(java.lang.String)
	 */
	@Override
	public ArrayList<Announcement> getUnviewedAnnouncement(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#getAllUsers()
	 */
	@Override
	public ArrayList<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ycp.TeamManager.persist.IDatabase#geteAllTeams()
	 */
	@Override
	public ArrayList<Team> geteAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}

}
