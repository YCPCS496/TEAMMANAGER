/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.ArrayList;

/**
 * @author dan
 *
 */
public class Practice extends Event {
	private ArrayList<String> workoutid;
	
	/**
	 * constructor for empty practice
	 */
	public Practice(){
		super();
		workoutid = new ArrayList<String>();
	}
	
	public Practice(String name, int day, int month, int year, int hour, int min, ArrayList<String> workoutIds) {
		super(name, day, month, year, hour, min);
		for(String s: workoutIds){
			workoutid.add(s);
		}
	}
	
	public void addworkout(String workout) {
		this.workoutid.add(workout);
	}

}
