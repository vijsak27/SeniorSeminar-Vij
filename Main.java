import java.util.*;

/*
The Main class will load in the files and run the program to 
organize the students and sessions for senior seminar
*/

public class Main{
	public static void main (String[] args){
		Schedule s1 = new Schedule();//create a schedule object
		s1.scheduleDetails();//prompt user for details about the event they are planning for (number of time slots, sessions, etc.)
		s1.rankedPopularityAllSlots();//I am currently working on this aspect (ranking the popularity) to fix logic errors
		s1.sort();
		s1.assignStudents();
		System.out.println(s1);
		s1.showSessionRosters();
	
	}

}

