// Author: Justin Kim
// Source file:  project2.java

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class project2
{
	// create a Scanner for user input
	public static Scanner sc = new Scanner(System.in);

	private final static boolean DEBUG = true;
	private final static String STUDENT_FILE = "usernames1.txt";
	private final static String STAFF_FILE = "staff1.txt";
	private final static String OUTPUT_FILE_NAME = "result.txt";
	private final static int MAX_STUDENTS_FOR_TA = 25;

	// create a student list and staff list
	private static ArrayList<Student> allStudentList = new ArrayList<Student>();
	private static ArrayList<Staff> staffList = new ArrayList<Staff>();

	private static int numStudents, numStaffs, numTA, numGrader;

	public static void main(String[] args) {

		allStudentList = generateStudentList(STUDENT_FILE);
		staffList = generateStaffList(STAFF_FILE);

		numStudents = getNumOfStudents(allStudentList);
		numStaffs = getNumOfStaffs(staffList);
		numTA = countTA(staffList);
		numGrader = countGrader(staffList);

		// this userInput for containing user's input
		char userInput;

		//greeting();

		assignStudents(allStudentList, staffList);

		do
		{
			// call displayMenu() method and display menu for a user
			displayMenu();

			// call askUserInput() method and assign it to userInput value
			userInput = askUserInput();

			// If user inputs choice of "D"
			if(userInput == 'D')
			{
				Iterator<Staff> iStaff = staffList.iterator();
				// loop through staff list
				for (int i = 0; i < numStaffs; i++)
				{
					Staff s = iStaff.next();
					System.out.println("\n" + s.getfName() + " " + s.getlName() + " - " + s.getRole());
					System.out.println("-------------");
					System.out.println(s.getStudentsList().toString());
					
					// ask user input unless it prints the last staff
					if(i < numStaffs - 1)
					{
						askUserInput();
					}
				}

			}
			
			// if user inputs 'c' or 'C' then display the number of students
			if(userInput == 'C')
			{
				System.out.println("The number of all students = " + numStudents);
			}
			if(userInput == 'S')
			{
				System.out.println(staffList);
			}


		} while(userInput != 'Q'); // this loop will be stopped when user inputs 'x' character or counter is 75 

		// call finsihed() method to display a game over message
		finished();
	}
	private static void displayAssignedStudentList(Staff s)
	{
		
	}
	private static void assignStudents(ArrayList<Student> allStudentList, ArrayList<Staff> staffList)
	{

		int totalStudentsForTAs = numTA * MAX_STUDENTS_FOR_TA;
		int totalStudentsForGraders = numStudents - totalStudentsForTAs;
		int studentsForEachGrader = totalStudentsForGraders / numGrader;
		int remainStudentsForGrader = totalStudentsForGraders % numGrader; 

		int[] numStudentsForGraders = new int[numGrader];
		for(int i = 0; i < numGrader; i++)
		{
			numStudentsForGraders[i] = studentsForEachGrader;
		}
		for(int i = 0; i < remainStudentsForGrader; i++)
		{
			numStudentsForGraders[i] += 1;
		}

		if(DEBUG)
		{
			System.out.println("---- Assigning students to staffs ----");
			System.out.println("Students for TAs = " + totalStudentsForTAs);
			System.out.println("Students for Graders = " + totalStudentsForGraders);
			System.out.println("Students for Each Grader = " + studentsForEachGrader);
			System.out.println("Remain students for Graders = " + remainStudentsForGrader);
			for(int i = 0; i < numStudentsForGraders.length; i++)
			{
				System.out.println("Number of student for Grader " + (i + 1) + " = " + numStudentsForGraders[i]);
			}
		}

		Iterator<Student> iStudent = allStudentList.iterator();
		Iterator<Staff> iStaff = staffList.iterator();
		
		int indexOfStaff = 0;
		int graderCounter = 0;
		
		while(iStaff.hasNext())
		{
			Staff s = iStaff.next();
			if(s.getRole().equals("TA"))
			{
				for(int i = 0; i < MAX_STUDENTS_FOR_TA; i++)
				{
					Student student = iStudent.next();
					staffList.get(indexOfStaff).getStudentsList().add(student);
				}
			}
			else
			{
				for(int i = 0; i < numStudentsForGraders[graderCounter]; i++)
				{
					Student student = iStudent.next();
					staffList.get(indexOfStaff).getStudentsList().add(student);
				}
				graderCounter++;
			}
			indexOfStaff++;
		}
		
		if(DEBUG)
		{
			System.out.println("----- Staff List after assigning students ----");
			System.out.println(staffList);
		}
		
		//writeFile(OUTPUT_FILE_NAME);
		
	}
	
	/*private static void writeFile(String fileName) 
	{
		PrintWriter outfile = null;
		try
		{
			outfile = new PrintWriter(fileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File writing is failed");
			e.printStackTrace();
			System.exit(0);
		}
		
		Iterator<Staff> iStaff = staffList.iterator();
		
		while(iStaff.hasNext())
		{
			Staff s = iStaff.next();
			System.out.println(s.getfName() + " " + s.getlName() + " - " + s.getRole());
			System.out.println("-------------------------------");
//			outfile.println(s.getfName() + " " + s.getlName() + " - " + s.getRole());
//			outfile.println("-------------------------------");
			
			Iterator iStudent = s.getStudentsList().iterator();
			int counter = 0;
			while(iStudent.hasNext())
			{
				outfile.println(s.getStudentsList().get(counter).getId());
				System.out.println(s.getStudentsList().get(counter).getId());
				counter++;
			}
			
		}
		
		outfile.close();
		
	}*/

	private static int countTA(ArrayList<Staff> staffList)
	{
		int count = 0;
		Iterator<Staff> i = staffList.iterator();
		while(i.hasNext())
		{
			Staff s = i.next();
			if(s.getRole().equals("TA")) { count++; }
		}

		if(DEBUG)
		{
			System.out.println("---- Number of TAs ----");
			System.out.println("Num of TAs = " + count);
		}

		return count;
	}

	private static int countGrader(ArrayList<Staff> staffList)
	{
		int count = 0;
		Iterator<Staff> i = staffList.iterator();
		while(i.hasNext())
		{
			Staff s = i.next();
			if(s.getRole().equals("Grader")) { count++; }
		}

		if(DEBUG)
		{
			System.out.println("---- Number of Graders ----");
			System.out.println("Num of Graders = " + count);
		}

		return count;
	}

	private static ArrayList<Student> generateStudentList(String filePath)
	{
		// read a username files and assign it to studentlist
		allStudentList = readStudentData(filePath);

		// sort student list
		Collections.sort(allStudentList);

		// Debug statments
		if(DEBUG) 
		{
			System.out.println("---- All students list ----");
			System.out.println(allStudentList);
		}

		return allStudentList;
	}

	private static int getNumOfStudents(ArrayList<Student> allStudentList)
	{
		if(DEBUG)
		{
			System.out.println("---- Num of all students ----");
			System.out.println(allStudentList.size());
		}
		return allStudentList.size();
	}
	
	private static int getNumOfStaffs(ArrayList<Staff> staffList)
	{
		if(DEBUG)
		{
			System.out.println("---- Num of staffs ----");
			System.out.println(staffList.size());
		}

		return staffList.size();
	}

	private static ArrayList<Staff> generateStaffList(String filePath)
	{
		staffList = readSaffData(filePath);

		Collections.sort(staffList);

		if(DEBUG) 
		{
			System.out.println("---- All staffs list ----");
			System.out.println(staffList);
		}

		return staffList;
	}

	public static ArrayList<Student>readStudentData(String filePath)
	{
		ArrayList<Student> studentsList = new ArrayList<Student>();

		Scanner infile = null;
		try
		{
			infile = new Scanner(new FileReader(filePath));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}

		while(infile.hasNextLine())
		{
			String id = infile.nextLine();
			Student s = new Student(id);
			studentsList.add(s);
		}
		return studentsList;
	}

	public static ArrayList<Staff>readSaffData(String filePath)
	{
		ArrayList<Staff> staffsList = new ArrayList<Staff>();

		Scanner infile = null;
		try
		{
			infile = new Scanner(new FileReader(filePath));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}

		while(infile.hasNextLine())
		{
			String line = infile.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while(tokenizer.hasMoreTokens())
			{
				String fName = tokenizer.nextToken();
				String lName = tokenizer.nextToken();
				String role = tokenizer.nextToken();

				Staff staff = new Staff(fName, lName, role);
				staffsList.add(staff);
			}
		}
		return staffsList;
	}

	/*
	 * This method displays greeting message to user
	 */
	private static void greeting()
	{
		System.out.println("***************************************************************");
		System.out.println("*                Welcome to Grading Distributor!              *");
		System.out.println("*                     Author : Justin Kim                     *");
		System.out.println("*                         410-430-5656                        *");
		System.out.println("*                    justinjaekim@gmail.com                   *");
	}

	private static void displayMenu()
	{
		System.out.println("***************************************************************");
		System.out.println("*                      == User Menu ==                        *");
		System.out.println("*   D - Display single Staff member and students assigned     *");
		System.out.println("*   C - Display the number of students                        *");
		System.out.println("*   S - Display all staff members                             *");
		System.out.println("*   Q - Quit the program                                      *");
		System.out.println("***************************************************************");
	}

	/*
	 * This method asks user to input his/her choice
	 */
	private static char askUserInput() 
	{
		// Display a message to user to input his/her choice 
		System.out.print("Enter Your Choice: ");
		// return with first letter of user's input as a upper case letter
		return sc.next().toUpperCase().charAt(0);
	}

	/* 
	 * This method displays a message to user the program is finished.
	 */
	private static void finished() 
	{
		// Display a game over message
		System.out.println("===============================================================");
		System.out.println("=                   Program is finsihed!                      =");
		System.out.println("===============================================================");
	}
}
