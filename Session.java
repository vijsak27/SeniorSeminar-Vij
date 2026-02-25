/*
The Session.java class sets up the structure of a session object
The Session object will contain 3 pieces of info (as of now)
1. sessionID
2. sessionName
3. presenterName
*/

public class Session{
    //variables contained in session
    private int sessionID;
    private String sessionName;
    private String presenterName;
    
    //constructor
    public Session(int ID, String name, String presenter){
        sessionID = ID;
        sessionName = name;
        presenterName = presenter;
    }
    
    //returns the ID of the session object getID() is called on
    public int getID(){
        return sessionID;
    }
    
}