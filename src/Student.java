// Author: Justin Kim
// Source file:  Student.java
// This Student class contains student's id

public class Student implements Comparable<Object>
{
	String id;

	/*
	 * Default constructor
	 */
	public Student() { this.id = ""; }
	
	/*
	 * Constructor with id
	 */
	public Student(String id) { this.id = id; }
	
	/*
	 * Constructor with Student object
	 */
	public Student(Student s) { this.id = s.getId(); }
	
	protected String getId() { return id; }

	protected void setId(String id) { this.id = id;	}
	
	@Override
	public int compareTo(Object o)
	{
		Student s = (Student)o;
		return this.id.compareTo(s.getId());
	}

	public String toString() 
	{ 
		return "Student [id=" + id + "]\n"; 
	}
}
