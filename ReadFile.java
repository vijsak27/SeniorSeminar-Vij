import java.io.*;
import java.util.*;

public class ReadFile{
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Session> sessions = new ArrayList<Session>();
	
	public ReadFile(){
		
	}
	public String toString(){
		String output="";
		for(int i=0; i<students.size(); i++){
			output = students.get(i)+"\n";
		}
		return output;
	}
	public void loadStudents(){
		String fileName = "numericalChoices.csv"; //enter name of file here
		File myFile = new File(fileName);
		
		try (Scanner reader = new Scanner(myFile)){
			reader.nextLine();
			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] arr = line.split(",");
				ArrayList<Integer> studentChoices = new ArrayList<Integer>();
				studentChoices.add(Integer.parseInt(arr[3]));
				studentChoices.add(Integer.parseInt(arr[4]));
				studentChoices.add(Integer.parseInt(arr[5]));
				studentChoices.add(Integer.parseInt(arr[6]));
				studentChoices.add(Integer.parseInt(arr[7]));
				Student s1 = new Student(Integer.parseInt(arr[0]),arr[1], studentChoices);
				students.add(s1);
				System.out.println("Added: "+s1.getName()+"  ID: "+s1.getID() +" Student's choices: "+ studentChoices);
			}
		} catch (FileNotFoundException e){
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}
	public ArrayList getData(){
		return students;
	}


	public void loadSessions(){
		String fileName = "SrSeminar_RawData (2) - Speaker List.csv"; //enter name of file here
		File myFile = new File(fileName);
		
		try (Scanner reader = new Scanner(myFile)){
			reader.nextLine();
			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] arr = line.split(",");
				Session s1 = new Session(Integer.parseInt(arr[1]),arr[0],arr[2]);
				sessions.add(s1);
				System.out.println("Added session with ID: "+s1.getID());
			}
		} catch (FileNotFoundException e){
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}

		
	}
	

