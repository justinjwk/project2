// Author: Justin Kim
// Source file:  project2.java
// This program read data from files and assign students to staffs
// Staff has 2 different types, such as TA, and Grader
// TA can grade only 25 students and Grader will grade rest of them.
// Distribute student to Graders evenly
// This program interact with users. It displays menu and run it base on user's choice

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

	// final values for debug, files, max number of students assign to TAs
	private final static boolean DEBUG = false;
	private final static String STUDENT_FILE = "usernames1.txt";
	private final static String STAFF_FILE = "staff1.txt";
	private final static String OUTPUT_FILE_NAME = "result1.txt";
	private final static int MAX_STUDENTS_FOR_TA = 25;

	// create a student list and staff list
	private static ArrayList<Student> allStudentList = new ArrayList<Student>();
	private static ArrayList<Staff> staffList = new ArrayList<Staff>();

	// create a variables will be contains total number of students, staffs, TAs, and Grader
	private static int totalNumStudents, totalNumStaffs, totalNumTA, totalNumGrader;

	// Main
	public static void main(String[] args) {

		// call each method and store its result
		allStudentList = generateStudentList(STUDENT_FILE);
		staffList = generateStaffList(STAFF_FILE);

		totalNumStudents = getNumOfStudents(allStudentList);
		totalNumStaffs = getNumOfStaffs(staffList);
		totalNumTA = getNumOfTA(staffList);
		totalNumGrader = getNumOfGrader(staffList);

		// this userInput for containing user's input
		char userInput;

		// call to greeting method to display greeting message
		greeting();

		// call to assignStudent method to assign students to each staffs
		assignStudents(allStudentList, staffList);

		// call to writeFile method and write result to a file
		writeFile(OUTPUT_FILE_NAME);

		// this loop will handle menu
		do
		{
			// call displayMenu() method and display menu for a user
			displayMenu();

			// call askUserInput() method and assign it to userInput value
			userInput = askUserInput();

			// If user inputs choice of "d" or "D",
			// thren display single Staff member and students assigned
			if(userInput == 'D')
			{
				// Iterator for looping staff list
				Iterator<Staff> iStaff = staffList.iterator();

				// loop through staff list
				for (int i = 0; i < totalNumStaffs; i++)
				{
					Staff s = iStaff.next();
					
					// display a staff and list of assigned students to screen
					System.out.println();
					System.out.println("===============================");
					System.out.println(s.getStaffInfo());
					System.out.println("===============================");
					System.out.println(getAssignedStudentList(s));

					// ask user input unless it prints the last staff
					if(i < totalNumStaffs - 1)
					{
						System.out.print("Enter any key to continue: ");
						sc.next();
					}
				}
			}

			// If user inputs 'c' or 'C',
			// then display the number of students
			if(userInput == 'C')
			{
				System.out.println();
				System.out.println("The number of all students = " + totalNumStudents);
				System.out.println();
			}
			
			// If user inputs 's' or 'S',
			// then display aal staff list
			if(userInput == 'S')
			{
				System.out.println();
				System.out.println(displayStaffList(staffList));
			}


		} while(userInput != 'Q'); // this loop will be stopped when user inputs 'x' character or counter is 75 

		// call finsihed() method to display a game over message
		finished();
	}

	/*
	 *  This method reads students data from the file 
	 */
	private static ArrayList<Student>readStudentData(String filePath)
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

		// loop through file and store it to studentList
		while(infile.hasNextLine())
		{
			String id = infile.nextLine();
			Student s = new Student(id);
			studentsList.add(s);
		}
		return studentsList;
	}

	/*
	 *  This method reads staffs data from the file
	 */
	private static ArrayList<Staff>readSaffData(String filePath)
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

		// loop through file and store it to staffList
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
	 *  This method sorts StudentList
	 */
	private static ArrayList<Student> generateStudentList(String filePath)
	{
		// read a username files and assign it to studentlist
		allStudentList = readStudentData(filePath);

		// sort student list
		Collections.sort(allStudentList);

		// Debug statements
		if(DEBUG) 
		{
			System.out.println("---- All students list ----");
			System.out.println(allStudentList);
		}

		return allStudentList;
	}

	/*
	 *  This method sort StaffList
	 */
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

	/*
	 *  This method assigns students to each staff based on their role (TA, Grader)
	 */
	private static void assignStudents(ArrayList<Student> allStudentList, ArrayList<Staff> staffList)
	{
		
		// This part is calculation part for being used to assign students to TAs and Graders
		
		// Iterators for Students and Staffs
		Iterator<Student> iStudent = allStudentList.iterator();
		Iterator<Staff> iStaff = staffList.iterator();

		// Calculate a total number how many students will be assigned to TAs
		int totalStudentsForTAs = totalNumTA * MAX_STUDENTS_FOR_TA;

		// Cacualte a total number how many students will be assigned to Graders
		int totalStudentsForGraders = totalNumStudents - totalStudentsForTAs;

		// Caclualte how many students will be assigned to each Grader
		int studentsForEachGrader = totalStudentsForGraders / totalNumGrader;

		// Caculate how many students are remained for Graders
		int remainStudentsForGrader = totalStudentsForGraders % totalNumGrader; 

		// This array contains how many students are assigned to each graders
		int[] numStudentsForGraders = new int[totalNumGrader];

		// Assign a number of students to each graders
		for(int i = 0; i < totalNumGrader; i++)
		{
			numStudentsForGraders[i] = studentsForEachGrader;
		}

		// assign remaining students to graders
		for(int i = 0; i < remainStudentsForGrader; i++)
		{
			numStudentsForGraders[i] += 1;
		}

		// Debug statements
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

	
		// This part is assigning students based on calculation above
	
		// index and counters for loop
		int indexOfStaff = 0;
		int graderCounter = 0;

		// loop through all staffs
		while(iStaff.hasNext())
		{
			Staff s = iStaff.next();

			// If staff is a "TA"
			if(s.getRole().equals("TA"))
			{
				// Loop MAX_STUDENTS_FOR_TA times(25 times) 
				for(int i = 0; i < MAX_STUDENTS_FOR_TA; i++)
				{
					Student student = iStudent.next();
					// add Student to Staff's student list
					staffList.get(indexOfStaff).getStudentsList().add(student);
				}
			}
			// If staff is a "Grader"
			else
			{
				// Loop x times based on the numStudentsForGraders array
				for(int i = 0; i < numStudentsForGraders[graderCounter]; i++)
				{
					Student student = iStudent.next();
					// add Student to Staff's student list
					staffList.get(indexOfStaff).getStudentsList().add(student);
				}
				// increase counter by 1
				graderCounter++;
			}
			// increase index by 1
			indexOfStaff++;
		}

		if(DEBUG)
		{
			System.out.println("----- Staff List after assigning students ----");
			System.out.println(staffList);
		}
	}

	/*
	 *  This method write results to a file
	 */
	private static void writeFile(String fileName) 
	{
		PrintWriter outfile = null;

		Iterator<Staff> iStaff = staffList.iterator();

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

		outfile.println("*******************************************************");
		outfile.println("*             Student list for each staff             *");
		outfile.println("*******************************************************");
		outfile.println();

		// loop through all staffs and write staff's information and assigned students
		while(iStaff.hasNext())
		{
			Staff s = iStaff.next();
			outfile.println("===========================");
			outfile.println(s.getStaffInfo());
			outfile.println("===========================");
			outfile.println(getAssignedStudentList(s));
		}

		outfile.close();

	}

	/*
	 *  This method returns assigned student list to display
	 */
	private static String getAssignedStudentList(Staff s)
	{
		Iterator<Student> i = s.getStudentsList().iterator();
		String list = "";
		while (i.hasNext())
		{
			Student student = i.next();
			list += student.getId() + "\n";
		}

		return list;
	}

	/*
	 *  This method returns staff list to display
	 */
	private static String displayStaffList(ArrayList<Staff> s)
	{
		String list = "=====================================\n"
				+"=             Staff List            =\n"
				+"=====================================\n";

		Iterator<Staff> i = s.iterator();

		while(i.hasNext())
		{
			Staff staff = i.next();

			list += staff.getStaffInfo() + "\n";
		}
		return list;

	}

	/*
	 *  This method returns total number of students
	 */
	private static int getNumOfStudents(ArrayList<Student> allStudentList)
	{
		if(DEBUG)
		{
			System.out.println("---- Num of all students ----");
			System.out.println(allStudentList.size());
		}

		return allStudentList.size();
	}

	/*
	 * This method returns total number of staffs
	 */
	private static int getNumOfStaffs(ArrayList<Staff> staffList)
	{
		if(DEBUG)
		{
			System.out.println("---- Num of staffs ----");
			System.out.println(staffList.size());
		}

		return staffList.size();
	}

	/*
	 * This method returns total number of TAs
	 */
	private static int getNumOfTA(ArrayList<Staff> staffList)
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

	/*
	 * This method returns total number of Graders
	 */
	private static int getNumOfGrader(ArrayList<Staff> staffList)
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

	/*
	 * This method display a menu
	 */
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
