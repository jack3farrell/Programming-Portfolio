package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The abstract parent class Activity that holds the fields title, meetingDays,
 * startTime, and endTime
 * 
 * @author Jack Farrell
 */
public abstract class Activity implements Conflict {

	/** Upper limit for hours in a day. */
	private static final int UPPER_HOUR = 24;
	/** Upper limit for minutes in a single hour. */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Constructs the Activity object which is a parent class to Event and Course
	 *
	 * @param title       Activity title
	 * @param meetingDays Activity meeting days
	 * @param startTime   Activity starting time
	 * @param endTime     Activity ending times
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Abstract method header for get short display in GUI. The short display array
	 * is used to populate the rows of the course catalog and student schedule.
	 * 
	 * @return returns display
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Abstract method header for get long display in GUI. The full display array is
	 * used to display the final schedule.
	 * 
	 * @return returns display
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks for duplicates
	 * 
	 * @param activity allows the child classes to check for duplicates
	 * @return is duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set's the Course's title and makes sure the title isn't null or an empty
	 * string
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or an empty string
	 * @throws IllegalArgumentException if the title length is equal to 0
	 */
	public void setTitle(String title) {
		// Conditional 1:
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		// Conditional 2:
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}
	// /**
	// * Sets the Course's meeting days
	// * @param meetingDays the meetingDays to set
	// */
	// public void setMeetingDays(String meetingDays) {
	// this.meetingDays = meetingDays;
	// }

	/**
	 * Returns the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}
	// /**
	// * Sets the Course's start time
	// * @param startTime the startTime to set
	// */
	// public void setStartTime(int startTime) {
	// this.startTime = startTime;
	// }

	/**
	 * Returns the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	// /**
	// * Sets the Course's end time
	// * @param endTime the endTime to set
	// */
	// public void setEndTime(int endTime) {
	// this.endTime = endTime;
	// }

	/**
	 * Method deals with start time and end time for two child classes
	 * 
	 * @param meetingDays day that activities meet
	 * @param startTime   start time that activities begin
	 * @param endTime     end time that activities finish
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Turns the meeting time into a string for the Course class to send off to
	 * other classes If the meeting days are A, the details view shows Arranged.
	 * Otherwise,the meeting information shows the meeting days followed by the
	 * start time in standard time (e.g., 1:30PM), a dash, and the end time in
	 * standard time. Only AM and PM are used.
	 * 
	 * @return String containing the meeting times of a course
	 */
	public String getMeetingString() {
		int startHour = getStartTime() / 100;
		int startMin = getStartTime() % 100;
		int endHour = getEndTime() / 100;
		int endMin = getEndTime() % 100;

		if (getMeetingDays().equals("A")) {
			return "Arranged";
		}

		String startTime1 = "";
		String endTime1 = "";

		// If the start hour is in the morning
		if (startHour < 12) {
			startTime1 = startTime1 + startHour + ":";
			if (startMin <= 9) {
				startTime1 += "0" + startMin + "AM";
			}
			if (startMin > 9) {
				startTime1 += startMin + "AM";
			}
		}
		// If the start hour is at midday
		if (startHour == 12) {
			startTime1 = startTime1 + startHour + ":";
			if (startMin <= 9) {
				startTime1 += "0" + startMin + "PM";
			}
			if (startMin > 9) {
				startTime1 += startMin + "PM";
			}
		}
		// If the start hour is in the afternoon
		if (startHour > 12 && startHour < 24) {
			int tempStartHour = startHour - 12;
			startTime1 = startTime1 + tempStartHour + ":";
			if (startMin <= 9) {
				startTime1 += "0" + startMin + "PM";
			}
			if (startMin > 9) {
				startTime1 += startMin + "PM";
			}
		}
		// If the start hour is at midnight
		if (startHour == 24) {
			startTime1 = startTime1 + startHour + ":";
			if (startMin <= 9) {
				startTime1 += "0" + startMin + "AM";
			}
			if (startMin > 9) {
				startTime1 += startMin + "AM";
			}
		}

		// If the end hour is in the morning
		if (endHour < 12) {
			endTime1 = endTime1 + endHour + ":";
			if (endMin <= 9) {
				endTime1 += "0" + endMin + "AM";
			}
			if (endMin > 9) {
				endTime1 += endMin + "AM";
			}
		}
		// If the hour is at midday
		if (endHour == 12) {
			endTime1 = endTime1 + endHour + ":";
			if (endMin <= 9) {
				endTime1 += "0" + endMin + "PM";
			}
			if (endMin > 9) {
				endTime1 += endMin + "PM";
			}
		}
		// If the hour is in the afternoon
		if (endHour > 12 && endHour < 24) {
			int tempEndHour = endHour - 12;
			endTime1 = endTime1 + tempEndHour + ":";
			if (endMin <= 9) {
				endTime1 += "0" + endMin + "PM";
			}
			if (endMin > 9) {
				endTime1 += endMin + "PM";
			}
		}
		// If the end hour is at midnight
		if (endHour == 24) {
			int tempEndHour = endHour - 12;
			endTime1 = endTime1 + tempEndHour + ":";
			if (endMin <= 9) {
				endTime1 += "0" + endMin + "AM";
			}
			if (endMin > 9) {
				endTime1 += endMin + "AM";
			}
		}
		return getMeetingDays() + " " + startTime1 + "-" + endTime1;
	}

	/**
	 * This method checks for conflicting times between two activities. The way this
	 * method checks is by comparing the time in minutes of the two activities
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// Checking if the two activities have the same meeting day
		boolean flag = false;
		for(int i = 0; i < this.meetingDays.length(); i++) {
			for(int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				if(this.meetingDays.charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)) {
					flag = true;
				}
			}
		}
		
		// Checking if meeting times overlap 
		if(flag) {
			if(this.startTime <= possibleConflictingActivity.startTime && this.endTime >= possibleConflictingActivity.startTime) {
				throw new ConflictException("Schedule conflict.");
			} else if (this.startTime <= possibleConflictingActivity.endTime && this.endTime >= possibleConflictingActivity.startTime) {
				throw new ConflictException("Schedule conflict.");
			}
		}
		
	}

	/**
	 * Method for creating hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Method that compares two objects
	 * 
	 * @param obj an object to be compared
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Activity)) {
			return false;
		}
		Activity other = (Activity) obj;
		if (endTime != other.endTime) {
			return false;
		}
		if (meetingDays == null) {
			if (other.meetingDays != null) {
				return false;
			}
		} else if (!meetingDays.equals(other.meetingDays)) {
			return false;
		}
		if (startTime != other.startTime) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}