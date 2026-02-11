import java.util.*;

public class Student{
	private int ID;
	private String name;
	private ArrayList<Integer> choices = new ArrayList<Integer>();
	private ArrayList<Integer> schedule = new ArrayList<Integer>();//this wil contain the room numbers in time slots
	public Student(int id, String n, ArrayList c){
		ID = id;
		name = n;
		choices = c;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return ID;
	}
	public ArrayList getChoices(){
		return choices;
	}
	
}
