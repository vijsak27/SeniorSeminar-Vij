import java.util.*;

/*
The Main class will load in the files and run the program to 
organize the students and sessions for senior seminar
*/

public class Main{
	public static void main (String[] args){
		ReadFile rf1 = new ReadFile();//create ReadFile object to load in information from CSV files
		rf1.loadStudents();//load the students
		rf1.loadSessions();//load the sessions
		Schedule s1 = new Schedule();//create a schedule object
		s1.scheduleDetails();//prompt user for details about the event they are planning for (number of time slots, sessions, etc.)
		s1.rankedPopularityAllSlots();//I am currently working on this aspect (ranking the popularity) to fix logic errors
		
	
	}

}

