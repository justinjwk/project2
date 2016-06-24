import java.util.ArrayList;

public class Staff implements Comparable<Object>
{
	String fName, lName, role;
	ArrayList<Student> studentsList;
	
	public Staff()
	{
		this.fName = "";
		this.lName = "";
		this.role = "";
		this.studentsList = null;
	}
	
	public Staff(String fName, String lName, String role) 
	{
		this.fName = fName;
		this.lName = lName;
		this.role = role;
		this.studentsList = null;
	}
	
	public Staff(String fName, String lName, String role, ArrayList<Student> studentsList) 
	{
		this.fName = fName;
		this.lName = lName;
		this.role = role;
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

	public String getRole() { return role; }

	public ArrayList<Student> getStudentsList() { return studentsList; }

	public void setfName(String fName) { this.fName = fName; }

	public void setlName(String lName) { this.lName = lName; }

	public void setRole(String role) { this.role = role; }

	public void setStudentsList(ArrayList<Student> studentsList) { this.studentsList = studentsList; }

	@Override
	public String toString() {
		return "Name= " + fName + " " + lName + ", role= " + role + ", studentsList= " + studentsList + "\n";
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (studentsList == null) {
			if (other.studentsList != null)
				return false;
		} else if (!studentsList.equals(other.studentsList))
			return false;
		return true;
	}
	
	
	
	
	
}
