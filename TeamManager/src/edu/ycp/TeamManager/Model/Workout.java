/**
 * 
 */
package edu.ycp.TeamManager.Model;

/**
 * @author dan
 *
 */
public class Workout {
	
	private String title;
	private String notes;
	private int durationMin;
	private int intensity;
	private int reps;
	
	/**
	 * Constructs empty workout
	 * 
	 */
	public Workout(){
		this.title = new String(" ");
		this.notes = new String(" ");
		this.durationMin = 0;
		this.intensity = 0;
		this.reps =  0;
		
	}
	/**
	 * Constructor for workout
	 * 
	 * @param title
	 * @param notes
	 * @param durationMin
	 * @param intensity
	 * @param reps
	 */
	public Workout(String title, String notes, int durationMin, int intensity, int reps){
		this.title = title;
		this.notes = notes;
		this.durationMin = durationMin;
		this.intensity = intensity;
		this.reps = reps;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the durationMin
	 */
	public int getDurationMin() {
		return durationMin;
	}

	/**
	 * @param durationMin the durationMin to set
	 */
	public void setDurationMin(int durationMin) {
		this.durationMin = durationMin;
	}

	/**
	 * @return the intensity
	 */
	public int getIntensity() {
		return intensity;
	}

	/**
	 * @param intensity the intensity to set
	 */
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	/**
	 * @return the reps
	 */
	public int getReps() {
		return reps;
	}

	/**
	 * @param reps the reps to set
	 */
	public void setReps(int reps) {
		this.reps = reps;
	}

}
