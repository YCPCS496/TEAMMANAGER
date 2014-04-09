/**
 * 
 */
package edu.ycp.TeamManager.Model;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.google.api.server.spi.types.SimpleDate;
/**
 * @author dan
 *
 */
public class Event {
	
	
	private GregorianCalendar date;
	private String name;
	private String id;
	
	public Event(){
		setDate(new GregorianCalendar());
		setName(" ");
		setId(" ");
	}
	
	public Event(String name, int day, int month, int year, int hour, int min){
		date = new GregorianCalendar(year, month, day, hour, min);
		this.name = name;
		setId(System.currentTimeMillis() + "");
		
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

}
