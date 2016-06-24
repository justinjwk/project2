
public class Student implements Comparable<Object>
{
	String id;

	public Student() { this.id = ""; }
	
	public Student(String id) { this.id = id; }
	
	public Student(Student s) { this.id = s.getId(); }
	
	protected String getId() { return id; }

	protected void setId(String id) { this.id = id;	}
	
	@Override
	public int compareTo(Object o)
	{
		Student s = (Student)o;
		return this.id.compareTo(s.getId());
	}

	@Override
	public String toString() { return "Student [id=" + id + "]"; }

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
