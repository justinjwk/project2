import java.util.ArrayList;

public class TA extends Staff implements Comparable<Object>
{
	ArrayList<Student> studentsList = new ArrayList<Student>();

	public TA()
	{
		super();
		this.studentsList = null;
	}
	
	public TA(String fName, String lName)
	{
		super(fName, lName);
		this.studentsList = null;
	}
	
	public TA(String fName, String lName, ArrayList<Student> studentsList) {
		super(fName, lName);
		this.studentsList = studentsList;
	}

	public ArrayList<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(ArrayList<Student> studentsList) {
		this.studentsList = studentsList;
	}

	@Override
	public String toString() {
		return "TA [Name=" + fName + " " + lName + ", studentsList=" + studentsList + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TA other = (TA) obj;
		if (studentsList == null) {
			if (other.studentsList != null)
				return false;
		} else if (!studentsList.equals(other.studentsList))
			return false;
		return true;
	}	
}
