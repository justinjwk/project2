import java.util.ArrayList;

public class Staff implements Comparable<Object>
{
	String fName, lName;
	ArrayList<Student> studentsList;
	
	public Staff() 
	{
		this.fName = "";
		this.lName = "";
		this.studentsList = null;
	}
	
	public Staff(String fName, String lName)
	{
		this.fName = fName;
		this.lName = lName;
		this.studentsList = null;
	}
	
	public Staff(String fName, String lName, ArrayList<Student> studentsList) 
	{
		this.fName = fName;
		this.lName = lName;
		this.studentsList = studentsList;
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
	
	public void setfName(String fName) { this.fName = fName; }

	public void setlName(String lName) { this.lName = lName; }

	public ArrayList<Student> getStudentsList() { return studentsList; }

	public void setStudentsList(ArrayList<Student> studentsList) { this.studentsList = studentsList; }

	@Override
	public String toString() {
		return "Staff [Name= " + fName + " " + lName + ", studentsList=" + studentsList + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (studentsList == null) {
			if (other.studentsList != null)
				return false;
		} else if (!studentsList.equals(other.studentsList))
			return false;
		return true;
	}
}
