/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 * @author dan
 *
 */
public class Event {
	
	
	private GregorianCalendar date;
	private SimpleDateFormat time;
	private String name;
	private String id;
	
	public Event(){
		setDate(new GregorianCalendar());
		setTime(new SimpleDateFormat());
		setName(" ");
		setId(" ");
	}
	
	public Event(int day, int month, int year, int hour, int min){
		
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public SimpleDateFormat getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(SimpleDateFormat time) {
		this.time = time;
	}
	

}
