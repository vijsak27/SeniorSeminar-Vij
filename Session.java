public class Session{
    private int sessionID;
    private String sessionName;
    private String presenterName;
    
    public Session(int ID, String name, String presenter){
        sessionID = ID;
        sessionName = name;
        presenterName = presenter;
    }

    public int getID(){
        return sessionID;
    }
    
}