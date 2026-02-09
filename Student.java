public class Student{
	private int ID;
	private String name;
	private ArrayList choices;
	private ArrayList schedule;
	public Student(int id; String n; ArrayList c){
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
	
}
