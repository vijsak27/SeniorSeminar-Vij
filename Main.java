import java.util.*;

public class Main{
	public static void main (String[] args){
		ReadFile rf1 = new ReadFile();
		rf1.loadStudents();
		rf1.loadSessions();
		Schedule s1 = new Schedule();
		s1.scheduleDetails();
		//s1.rankedPopularityAllSlots();
		//I am currently working on this aspect (ranking the popularity)
	
	}

}

