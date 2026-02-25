import java.io.*;
import java.util.*;

/*
the readFile calss will contain functionally for the 2 csv files used in this project to be 
loaded in and stored in two array lists called students and sessions

The first file contains all the students' choices, names, and IDs
The second file contains the sessions, speaker, and session name

the methods for these classes will be called in Main
to load the data in from the csv files
*/

public class ReadFile{
	private ArrayList<Student> students = new ArrayList<Student>();// array list including data for students
	private ArrayList<Session> sessions = new ArrayList<Session>();// array list including data for sessions
	

	//constructor (empty)
	public ReadFile(){
		
	}

	//print the students data out
	public String toString(){
		String output="";
		for(int i=0; i<students.size(); i++){
			output = students.get(i)+"\n";
		}
		return output;
	}

	/*
	the loadStudents() method will add all of the students from the
	csv file to the students ArrayList as student objects (each object
	contains information about name, ID, choices, and schedule)
	As of now no schedule will be loaded since that will be done by the Sort()
	method currently underdevelopment in the Schedule class
	*/
	public void loadStudents(){
		String fileName = "numericalChoices.csv"; //enter name of file here
		File myFile = new File(fileName); //file object from filename
		
		try (Scanner reader = new Scanner(myFile)){//try catch set up
			reader.nextLine();//skip the first line (contains the headers)
			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] arr = line.split(",");//split the line into an array
				ArrayList<Integer> studentChoices = new ArrayList<Integer>();//create an ArrayList of the current students choices
				
				//find each choice (1st, 2nd, 3rd, 4th, 5th) and add is to the studentChoices ArrayList
				studentChoices.add(Integer.parseInt(arr[3]));
				studentChoices.add(Integer.parseInt(arr[4]));
				studentChoices.add(Integer.parseInt(arr[5]));
				studentChoices.add(Integer.parseInt(arr[6]));
				studentChoices.add(Integer.parseInt(arr[7]));

				//create a new student object with the studentChoices from the ArrayList and include the name and ID
				Student s1 = new Student(Integer.parseInt(arr[0]),arr[1], studentChoices);
				students.add(s1); // add the student to the general ArrayList containing the students
				System.out.println("Added: "+s1.getName()+"  ID: "+s1.getID() +" Student's choices: "+ studentChoices);//pirnt out that student has been added

			}
		} catch (FileNotFoundException e){//print out if error occurs
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}
	//getter function for the student data (in the students ArrayList)
	public ArrayList getData(){
		return students;
	}

	/*
	the loadSessions() function will go through the csv file containing
	the speaker sessions and information about those sesssion and add them to an ArrayList called
	sessions which will contain Session objects (see Session.java)
	*/
	public void loadSessions(){
		String fileName = "SrSeminar_RawData (2) - Speaker List.csv"; //enter name of file here
		File myFile = new File(fileName);//file object from csv file
		
		try (Scanner reader = new Scanner(myFile)){//try catch structure
			reader.nextLine(); // skip the first line of the file that contains the headers
			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] arr = line.split(",");//split the line along the commas
				Session s1 = new Session(Integer.parseInt(arr[1]),arr[0],arr[2]);//create a new session object containing ID, session name, and presenter name
				sessions.add(s1);//add the session object to the sessions ArrayList
				System.out.println("Added session with ID: "+s1.getID());//print that session has been added
			}
		} catch (FileNotFoundException e){
			System.out.println("Error!!!");//print if error occurs
			e.printStackTrace();
		}
	}

	public ArrayList getSessions(){
		return sessions;//return the arraylist containing all the of the session objects
	}

		
	}
	

