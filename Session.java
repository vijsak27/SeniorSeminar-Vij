/*
 * Session.java
 * Author: Sakshum Vij
 * Date: 5/17/26
 * Program: Senior Seminar
 * Purpose:
 * The Session.java class sets up the structure of a session object
 * The Session object will contain 5 pieces of info (as of now)
 * 1. sessionID - id for the session as assigned in the data file
 * 2. sessionName - name of the session from data file
 * 3. presenterName - presenting speaker
 * 4. numStudents - number of studentd attending the session
 * 5. students - arraylist of student objects attending the session
 * 
 * 
 * 
 * 
 * 
 * */


//imports
import java.util.*;

public class Session{
	
    //variables contained in session
    private int sessionID;
    private String sessionName;
    private String presenterName;
    private int numStudents;
    private ArrayList<Student> students = new ArrayList<Student>();
    
    //constructor
    public Session(int ID, String name, String presenter){
        sessionID = ID;
        sessionName = name;
        presenterName = presenter;
        numStudents=0;//default - each session starts with 0 students assigned
    }
    
    /*
     The getID() returns the ID of the session object getID() is called on
   */
    public int getID(){
        return sessionID;
    }
    
    /*
     The getStudents() returns the ArrayList of students in the session 
     object getStudents() is called on
   */
    public ArrayList<Student> getStudents(){
		return students;
	}
    
    /*
     The getNumStudents() returns the number of students in the session
     object getNumStudents() is called on
   */
    public int getNumStudents(){
		return numStudents;
	}
	
	/*
     The addStudent() taken in a student object as an arguments and adds the student
     to the students ArrayList and increases numStudents by one
   */
	public void addStudent(Student s){
		students.add(s);
		numStudents++;
	}
	
	/*
     The getName() returns the name of the session getName() is called on
   */
	public String getName(){
		return sessionName;
	}
	
	/*
     The getPresenter() returns the name of the presenter of the session getPresenter()
     is called on
   */
	public String getPresenter(){
		return presenterName;
	}
    
}
