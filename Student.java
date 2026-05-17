/* Student.java
 * Author: Sakshum Vij
 * Date: 5/17/26
 * Program: Senior Seminar
 * Purpose: The student class forms the basis of the student objects that are organized
 * across the different session and time slots. Each student has several defining characteristic
 * including a name, an ID, choices, and a scheudle that are taken in an altered by this program
 * and the methods in this class
 */

//imports
import java.util.*;

public class Student{
	
	//instance variables
	private int ID;
	private String name;
	private ArrayList<Integer> choices = new ArrayList<Integer>();
	private ArrayList<Integer> schedule = new ArrayList<Integer>();//this wil contain the room numbers in time slots
	
	//constructor that takes in paramtere to define a student
	public Student(int id, String n, ArrayList c){
		ID = id;
		name = n;
		choices = c;
	}
	
	/*
	the getName() method return the name of the student object the method is called upon
	*/
	public String getName(){
		return name;
	}
	
	/* 
	the getID() method return the numerical value of the ID of the student object it is called
	upon
	*/
	public int getID(){
		return ID;
	}
	
	/*
	the getChoices() method return the ArrayList containg the choices the each students filled out
	in the form in the data file
	*/ 
	public ArrayList<Integer> getChoices(){
		return choices;
	}
	
	/*
	the getSchedule() method returns the ArrayList containg the schedule of the student object it is 
	called upon. This ArrayList will start empty and be updated as the students schedule is made
	by the assignStudents() method in the Conference.java class 
	*/
	public ArrayList<Integer> getSchedule(){
		return schedule;
	}
	
	/*
	the addToSchedule() method takes in a sessionID parameter to add the sessino to the student's
	schedule ArrayList
	*/
	public void addToSchedule(int sessionID){
		schedule.add(sessionID);
	}
	
	/*
	the toString() method provides functionality to print out a student in the format below:
	
	name, ID
	Schedule: {-,-,-,-,-}
	
	*/
	public String toString() {
		String output = "";
		output = name+", "+ID+"\nSchedule: "+schedule;
		return output;
	}

	
}
