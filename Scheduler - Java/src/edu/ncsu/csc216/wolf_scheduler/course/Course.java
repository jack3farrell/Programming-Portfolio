package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This class creates the Course which is a child class of an activity and holds
 * data for what class will be taken
 * 
 * @author Jack Farrell
 */
public class Course extends Activity {

	/** Minimum name length. */
	private static final int MIN_NAME_LENGTH = 5;
	/** Maximum name length. */
	private static final int MAX_NAME_LENGTH = 8;
	/** Minimum letter count. */
	private static final int MIN_LETTER_COUNT = 1;
	/** Maximum letter count. */
	private static final int MAX_LETTER_COUNT = 4;
	/** Digit count. */
	private static final int DIGIT_COUNT = 3;
	/** Section length. */
	private static final int SECTION_LENGTH = 3;
	/** Max credits. */
	private static final int MAX_CREDITS = 5;
	/** Minimum credits. */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	/**
	 * Constructs a Course object with values for all fields
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
//		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
//		setMeetingDays(meetingDays);
//		setStartTime(startTime);
//		setEndTime(endTime);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if the name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check for pattern of L[LLL] NNN
		int counterLetters = 0;
		int counterDigits = 0;
		boolean spaceFlag = false;
		for (int i = 0; i < name.length(); i++) {
			if (!spaceFlag) {
				if (Character.isLetter(name.charAt(i))) {
					counterLetters++;
				} else if (name.charAt(i) == ' ') {
					spaceFlag = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (spaceFlag) {
				if (Character.isDigit(name.charAt(i))) {
					counterDigits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		// Check that the number of letters is correct
		if (counterLetters < MIN_LETTER_COUNT || counterLetters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check that the number of digits is correct
		if (counterDigits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section and checks that the parameter is a 3 digit number
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the Course's number of credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's number of credits between the numbers 1 and 5
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits not in between 1 and 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if ID is null or an empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Method for creating hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		if (credits != other.credits) {
			return false;
		}
		if (instructorId == null) {
			if (other.instructorId != null) {
				return false;
			}
		} else if (!instructorId.equals(other.instructorId)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (section == null) {
			if (other.section != null) {
				return false;
			}
		} else if (!section.equals(other.section)) {
			return false;
		}
		return true;
	}

	/**
	 * Method that uses short display for schedule
	 * 
	 * @return returns the array for GUI
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] returnArray = new String[4];
		returnArray[0] = getName();
		returnArray[1] = getSection();
		returnArray[2] = getTitle();
		returnArray[3] = getMeetingString();

		return returnArray;
	}

	/**
	 * Method that uses long display for catalog
	 * 
	 * @return returns the array for GUI
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] returnArray = new String[7];
		returnArray[0] = getName();
		returnArray[1] = getSection();
		returnArray[2] = getTitle();
		returnArray[3] = Integer.toString(getCredits());
		returnArray[4] = getInstructorId();
		returnArray[5] = getMeetingString();
		returnArray[6] = "";

		return returnArray;
	}

	/**
	 * Sets a courses meeting days and time. Checks all the requirements for
	 * military time and valid week dates.
	 * 
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime   start time for Course
	 * @param endTime     end time for Course
	 * 
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		} else {
			int countM = 0;
			int countT = 0;
			int countW = 0;
			int countH = 0;
			int countF = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					countM++;
				} else if (meetingDays.charAt(i) == 'T') {
					countT++;
				} else if (meetingDays.charAt(i) == 'W') {
					countW++;
				} else if (meetingDays.charAt(i) == 'H') {
					countH++;
				} else if (meetingDays.charAt(i) == 'F') {
					countF++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}

			if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks if activity is a duplicate
	 * 
	 * @param activity the object being passed
	 * @return returns true if it is a duplicate or returns false if it isn't
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course checkActivityName = (Course) activity;
			Course checkCourseName = this;
			if (checkActivityName.getName().equals(checkCourseName.getName())) {
				return true;
			}
		}
		return false;
	}

}
