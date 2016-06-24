// Author: Justin Kim
// Source file:  project2.java

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class project2
{
	private final static boolean DEBUG = true;
	private final static String STUDENT_FILE = "usernames1.txt";
	private final static String STAFF_FILE = "staff1.txt";
	
	// create a student list and staff list
	private static ArrayList<Student> allStudentList = new ArrayList<Student>();
	private static ArrayList<Staff> staffList = new ArrayList<Staff>();
	
	// create a Scanner for user input
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int numStudents, numStaff, numTA, numGrader;

		allStudentList = generateStudentList(STUDENT_FILE);

		// this userInput for containing user's input
		char userInput;

		greeting();

		do
		{
			// call askUserInput() method and assign it to userInput value
			userInput = askUserInput();

			// if user inputs 'n' then restart the game
			if(userInput == 'C')
			{

			}

		} while(userInput != 'Q'); // this loop will be stopped when user inputs 'x' character or counter is 75 

		// call finsihed() method to display a game over message
		finished();

	}
	
	private static ArrayList<Student> generateStudentList(String filePath)
	{
		// read a username files and assign it to studentlist
		allStudentList = readStudentData(filePath);

		//numStudents = allStudentList.size();
		//System.out.println("Student number : " + numStudents);
		// sort student list
		Collections.sort(allStudentList);
		
		
		if(DEBUG) 
		{
			System.out.println("---- All students list ----");
			System.out.println(allStudentList);

		}
		
		return allStudentList;
	}
	
	private static void generateStaffList()
	{
		staffList = readSaffData("staff1.txt");
		//numStaff = staffList.size();
		//System.out.println("Staff number : " + numStaff);
		Collections.sort(staffList);

		if(DEBUG) 
		{
			System.out.println("---- All staffs list ----");
			System.out.println(staffList);

		}
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
				// if staff's role is a TA, then create a TA object and add to staffslist
				if (role.equals("TA"))
				{
					TA ta = new TA(fName, lName);
					staffsList.add(ta);
				}
				else
				{
					Grader grader = new Grader(fName, lName);
					staffsList.add(grader);
				}
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
		System.out.println("*                      == User Menu ==                    s   *");
		System.out.println("*   D - Display single Staff member and students assigned     *");
		System.out.println("*   C - Display the number of students                        *");
		System.out.println("*   S - Display all staff members                             *");
		System.out.println("*   Q - Quite the program                                     *");
		System.out.println("***************************************************************");
		System.out.println();
	}

	/*
	 * This method asks user to input his/her choice
	 */
	private static char askUserInput() 
	{
		// Display a message to user to input his/her choice 
		System.out.print("User Input: ");
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
