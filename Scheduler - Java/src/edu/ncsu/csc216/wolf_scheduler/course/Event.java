/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class inherits from activity and is used to update events on the GUI schedule
 */
public class Event extends Activity {

	/**
	 * String that details what the event will include
	 */
	private String eventDetails;

	/**
	 * Constructor for event 
	 * @param title string title for event
	 * @param meetingDays string meeting day for event
	 * @param startTime int for start time
	 * @param endTime int for end time
	 * @param eventDetails string for event details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Setter method for event details
	 * 
	 * @param eventDetails String that holds the event details
	 * @throws IllegalArgumentException throws when eventDetails equals null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * sets the meeting days and time for the event
	 * 
	 * @param meetingDays the meeting days
	 * @param startTime   the start time
	 * @param endTime     the end time
	 * @throws IllegalArgumentException if meeting days is null or empty string
	 * @throws IllegalArgumentException if meeting day entered is not valid or is
	 *                                  repeated
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		int monday = 0;
		int tuesday = 0;
		int wednesday = 0;
		int thursday = 0;
		int friday = 0;
		int saturday = 0;
		int sunday = 0;
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) == 'M') {
				monday++;
			} else if (meetingDays.charAt(i) == 'T') {
				tuesday++;
			} else if (meetingDays.charAt(i) == 'W') {
				wednesday++;
			} else if (meetingDays.charAt(i) == 'H') {
				thursday++;
			} else if (meetingDays.charAt(i) == 'F') {
				friday++;
			} else if (meetingDays.charAt(i) == 'S') {
				saturday++;
			} else if (meetingDays.charAt(i) == 'U') {
				sunday++;
			} else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		if (monday > 1 || tuesday > 1 || wednesday > 1 || thursday > 1 || friday > 1 || saturday > 1 || sunday > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Displays what is needed for the GUI in an event on the short display
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] returnArray = new String[4];
		returnArray[0] = "";
		returnArray[1] = "";
		returnArray[2] = getTitle();
		returnArray[3] = getMeetingString();

		return returnArray;
	}

	/**
	 * Displays what is needed for the GUI in an event on the long display
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] returnArray = new String[7];
		returnArray[0] = "";
		returnArray[1] = "";
		returnArray[2] = getTitle();
		returnArray[3] = "";
		returnArray[4] = "";
		returnArray[5] = getMeetingString();
		returnArray[6] = this.eventDetails;

		return returnArray;
	}

	/**
	 * Produces a string of the title, meeting days, start time, end time, and event
	 * details
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;
	}

	/**
	 * Getter method for the event details
	 * 
	 * @return returns the event details
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Checks if activity is a duplicate
	 * 
	 * @param activity the object being passed
	 * @return returns true if it is a duplicate or returns false if it isn't
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event checkActivityTitle = (Event) activity;
			Event checkEventTitle = this;
			if (checkActivityTitle.getTitle().equals(checkEventTitle.getTitle())) {
				return true;
			}
		}
		return false;
	}

}
