/*
The Session.java class sets up the structure of a session object
The Session object will contain 4 pieces of info (as of now)
1. sessionID
2. sessionName
3. presenterName
4. numStudents
*/

public class Session{
    //variables contained in session
    private int sessionID;
    private String sessionName;
    private String presenterName;
    private int numStudents;
    
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
    
    
    public int getStudents(){
		return numStudents;
	}
    
}
