// Author: Justin Kim
// Source file:  Staff.java
// This Staff class contains staff's information 
// includes their name, role, and student list


import java.util.ArrayList;
import java.util.Iterator;

public class Staff implements Comparable<Object>
{
	String fName, lName, role;
	ArrayList<Student> studentsList;
	
	/*
	 * Default Constructor
	 */
	public Staff()
	{
		this.fName = "";
		this.lName = "";
		this.role = "";
		this.studentsList = new ArrayList<Student>();
	}
	
	/*
	 * Constructor with staff's name and role
	 */
	public Staff(String fName, String lName, String role) 
	{
		this.fName = fName;
		this.lName = lName;
		this.role = role;
		this.studentsList = new ArrayList<Student>();
	}
	
	/*
	 * Constructor with staff's name, role, and student list
	 */
	public Staff(String fName, String lName, String role, ArrayList<Student> studentsList) 
	{
		this.fName = fName;
		this.lName = lName;
		this.role = role;
		this.studentsList = studentsList;
	}
	
	/*
	 * This method add a student to student list
	 */
	public void addStudent(Student s)
	{
		studentsList.add(s);
	}

	/*
	 * This method returns staff's information
	 */
	public String getStaffInfo() 
	{ 
		return this.getfName() + " " + this.getlName() + " - " + this.getRole(); 
	}
	
	@Override
	public int compareTo(Object o) {
		Staff s = (Staff)o;
		
		if(this.fName.equals(s.getfName()))
		{
			return this.lName.compareTo(s.getlName());
		}
		else
		{
			return this.fName.compareTo(s.getfName());
		}
	}

	public String getfName() { return fName; }

	public String getlName() { return lName; }

	public String getRole() { return role; }
	
	public ArrayList<Student> getStudentsList() { return studentsList; }

	public void setfName(String fName) { this.fName = fName; }

	public void setlName(String lName) { this.lName = lName; }

	public void setRole(String role) { this.role = role; }

	public void setStudentsList(ArrayList<Student> studentsList) { this.studentsList = studentsList; }

	public String toString() {
		String slist = "";
		Iterator<Student> istudent = studentsList.iterator();
		while(istudent.hasNext())
		{
			Student s = istudent.next();
			slist += s.getId() + "\n";
		}
		return fName + " " + lName + " - " + role + "\n----------------\n" + slist + "\n";
	}
}
