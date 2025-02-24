package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable. Students have a first name, last
 * name, id number, number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Jack Farrell
 *
 */
public class Student implements Comparable<Student>, Identifiable {

	/**
	 * String field for first name
	 */
	private String first;

	/**
	 * String field for last name
	 */
	private String last;

	/**
	 * int field for id
	 */
	private int id;

	/**
	 * int field for credit hours
	 */
	private int creditHours;

	/**
	 * double field for gpa
	 */
	private double gpa;

	/**
	 * String field for unity id
	 */
	private String unityID;

	/**
	 * Constructor for the student object
	 * 
	 * @param first       String first name of student
	 * @param last        String last name of student
	 * @param id          int id of the student
	 * @param creditHours int credit hours of the student
	 * @param gpa         double gpa of the student
	 * @param unityID     String unity id of the student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		super();
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}

	/**
	 * Get method for first name
	 * 
	 * @return first name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Set method for first name
	 * 
	 * @param first sets the first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Get method for last name
	 * 
	 * @return last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Set method for last name
	 * 
	 * @param last sets the last name
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Get method for id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set method for id
	 * 
	 * @param id sets the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get method for credit hours
	 * 
	 * @return credit hours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Set method for credit hours
	 * 
	 * @param creditHours the credit hours
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Get method for gpa
	 * 
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Set method for gpa
	 * 
	 * @param gpa sets the gpa
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Get method for unity id
	 * 
	 * @return the unity id
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Set method for unity id
	 * 
	 * @param unityID sets the unity id
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		Student other = (Student) obj;
		return other.first.equals(this.first) && other.last.equals(this.last) && other.id == this.id;
	}

	@Override
	public int compareTo(Student o) {
		if (this.getLast().compareTo(o.getLast()) > 0) {
			return 1;
		}

		if (this.getLast().compareTo(o.getLast()) < 0) {
			return -1;
		}

		if (this.getFirst().compareTo(o.getFirst()) > 0) {
			return 1;
		}

		if (this.getFirst().compareTo(o.getFirst()) < 0) {
			return -1;
		}

		if (this.getUnityID().compareTo(o.getUnityID()) > 0) {
			return 1;
		}
		if (this.getUnityID().compareTo(o.getUnityID()) < 0) {
			return -1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

}
