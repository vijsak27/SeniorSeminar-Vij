/*
The Session.java class sets up the structure of a session object
The Session object will contain 4 pieces of info (as of now)
1. sessionID
2. sessionName
3. presenterName
4. numStudents
*/

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
    
    //returns the ID of the session object getID() is called on
    public int getID(){
        return sessionID;
    }
    
    public ArrayList<Student> getStudents(){
		return students;
	}
    
    
    public int getNumStudents(){
		return numStudents;
	}
	
	public void addStudent(Student s){
		students.add(s);
		numStudents++;
	}
	
	public String getName(){
		return sessionName;
	}
	
	public String getPresenter(){
		return presenterName;
	}
    
}
