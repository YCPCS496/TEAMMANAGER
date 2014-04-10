/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author dan
 *
 */
public class Lineup {
	private Map<String, String> playersWithPosition;
	private String linupId;
	
	public Lineup(){
		setPlayersWithPosition(new TreeMap<String, String>());
		linupId =  new String(System.currentTimeMillis() + "");
	}
	
	public Lineup(String id) {
		setPlayersWithPosition(new TreeMap<String, String>());
		linupId =  id;
	}

	/**
	 * @return the playersWithPosition
	 */
	public Map<String, String> getPlayersWithPosition() {
		return playersWithPosition;
	}

	/**
	 * @param playersWithPosition the playersWithPosition to set
	 */
	public void setPlayersWithPosition(Map<String, String> playersWithPosition) {
		this.playersWithPosition = playersWithPosition;
	}
	/**
	 * 
	 * @param userId
	 * @param position
	 * @return the playerid that was added
	 */
	public String addPlayer(String userId, String position) {
		return this.playersWithPosition.put(userId, position);
	}
	
	/**
	 * 
	 * @param userId
	 * @return the userId that was removed
	 */
	public String removePlayer(String userId) {
		return this.playersWithPosition.remove(userId);
	}
	
	/**
	 * 
	 * @param userId
	 * @param newPosition
	 */
	public void changePlayerPosition(String userId, String newPosition) {
		this.playersWithPosition.put(userId, newPosition);
	}
}
