/**
 * 
 */
package edu.ycp.TeamManager.Model;

/**
 * @author dan
 *
 */
public class Game extends Event {
	private String lineupId;
	
	public Game() {
		super();
	}
	
	public Game(String name, int day, int month,int year, int hour, int min, String lineupid){
		super(name, day, month, year, hour, min);
		this.setLineupId(lineupid);
	}

	/**
	 * @return the lineupId
	 */
	public String getLineupId() {
		return lineupId;
	}

	/**
	 * @param lineupId the lineupId to set
	 */
	public void setLineupId(String lineupId) {
		this.lineupId = lineupId;
	}
	
}
