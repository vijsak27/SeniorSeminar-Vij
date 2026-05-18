/*
 * Main.java
 * Author: Sakshum Vij
 * Date: 5/17/26
 * Program Name: Senior Seminar
 * Purpose: The Main class runs the program by creating a schedule object
 * and then running the schedule() method to launch the program. Currently "false" is passed into
 * the schedule method which toggles between using a customized schedule (when "true" is passed in)
 * or calculating the schedule for the senior seminar event
 * */

import java.util.*;

/*
The Main class will load in the files and run the program to 
organize the students and sessions for senior seminar
*/

public class Main{
	public static void main (String[] args){
		Schedule s1 = new Schedule();//create a schedule object
		s1.menu(false);// currently false so that program will make schedule
	}

}

