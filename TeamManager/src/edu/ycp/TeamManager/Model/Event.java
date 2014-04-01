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
	
	public Event(){
		date = new GregorianCalendar();
		time = new SimpleDateFormat();
	}
	
	public Event(int day, int month, int year, int hour, int min){
		
		
	}
	
	public void name() {
		
	}

}
