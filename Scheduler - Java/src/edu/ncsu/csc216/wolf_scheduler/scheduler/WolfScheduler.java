package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler reads in and stores as a list all of the Course records stored
 * in a file. Additionally, WolfScheduler creates a schedule for the current
 * user (a student) and provides functionality for naming the schedule, adding a
 * Course to the schedule, removing a Course from the schedule, resetting the
 * schedule.
 * 
 * @author Jack Farrell
 */
public class WolfScheduler {

	/** ArrayList for all the courses */
	public ArrayList<Course> catalog;
	/** ArrayList for all the courses in the schedule */
	public ArrayList<Activity> schedule;
	/** The title name for the schedule */
	public String title;

	/**
	 * Constructor for scheduler that stores course records for chosen classes
	 * 
	 * @param fileName the file of designated classes to be utilized 
	 * @throws IllegalArgumentException throws if cannot find file
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<Activity>();
		setScheduleTitle("My Schedule");

		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
		
	}

	/**
	 * This method creates a 2D array of the catalog
	 * 
	 * @return returns a string array of the catalog
	 */
	public String[][] getCourseCatalog() {
        String [][] catalogArray = new String[catalog.size()][];
        for (int i = 0; i < catalog.size(); i++) {
            Course c = catalog.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
    }

	/**
	 * This method creates a 2D array of the schedule 
	 * 
	 * @return returns a string array of the schedule
	 */
	public String[][] getScheduledActivities() {
        String [][] returnArray = new String[schedule.size()][];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            returnArray[i] = c.getShortDisplayArray();
        }
		return returnArray;
	}

	/**
	 * This method creates a 2D array of the full course's strings inside the schedule
	 * 
	 * @return returns a string array of the course's 
	 */
	public String[][] getFullScheduledActivities() {
		String [][] returnArray = new String[schedule.size()][];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            returnArray[i] = c.getLongDisplayArray();
        }
		return returnArray;
	}
	
	
	/**
	 * Checks for Course with passed in name and section strings and returns it
	 * 
	 * @param name String name of class
	 * @param section String section number
	 * @return returns the Course in the catalog with the same name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Adds a Course to the schedule and throws if a Course in the schedule has the same name and section
	 * 
	 * @param name String name of class
	 * @param section String section number
	 * @throws IllegalArgumentException throws when the Course trying to be added has the same name and section as another Course in the Catalog
	 * @return returns false if name and section equal null and returns true if the method does as intended
	 */
	public boolean addCourseToSchedule(String name, String section) {
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(getCourseFromCatalog(name, section))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
			try {
				schedule.get(i).checkConflict(getCourseFromCatalog(name, section));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		if(getCourseFromCatalog(name, section) == null) {
			return false;
		}
		schedule.add(getCourseFromCatalog(name, section));
		return true;
	}
	
	/**
	 * Adds a new event to the schedule
	 * @param eventTitle the String title of the event
	 * @param eventMeetingDays string event's meeting day
	 * @param eventStartTime int of the event's start time
	 * @param eventEndTime int of the event's end time
	 * @param eventDetails string of what the event is
	 * @throws IllegalArgumentException when there is already an event with the same title
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event newEvent = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(newEvent)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			try {
				schedule.get(i).checkConflict(newEvent);
			} catch(ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(newEvent);
	}
	
	/**
	 * Removes a Course from the schedule 
	 * @param idx the index of the activity that is being removed
	 * 
	 * @return returns true or false depending on if the method does what it intends to or not
	 */
	public boolean removeActivityFromSchedule(int idx) {
//		if(idx < 0) {
//			return false;
//		}
		try {
			schedule.remove(idx);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
	}

	/**
	 * Method sets Schedule to an empty array
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}

	/**
	 * Method sets the Schedule title
	 * 
	 * @param title sets the title field from the parameter
	 * @throws IllegalArgumentException throws if title equals null
	 */
	public void setScheduleTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Returns the title of the schedule
	 * 
	 * @return the title of the schedule
	 */
	public String getScheduleTitle() {
		return title;
	}
	
	/**
	 * Exports the changes from the input file to a new output file
	 * 
	 * @param fileName the input file
	 * @throws IllegalArgumentException throws if the file cannot be found
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch(IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}


}
