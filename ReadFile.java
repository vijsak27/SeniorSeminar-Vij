import java.io.*;
import java.util.*;

public class ReadFile{
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public ReadFile(){
		
	}
	public String toString(){
		String output="";
		for(int i=0; i<students.size(); i++){
			output = students.get(i)+"\n";
		}
		return output;
	}
	public void loadData(){
		String fileName = "numericalChoices.csv"; //enter name of file here
		File myFile = new File(fileName);
		
		try (Scanner reader = new Scanner(myFile)){
			reader.nextLine();
			while (reader.hasNextLine()){
				String name = reader.nextLine();
				String[] arr = name.split(",");
				ArrayList<String> studentChoices = new ArrayList<String>();
				studentChoices.add(arr[3]);
				studentChoices.add(arr[4]);
				studentChoices.add(arr[5]);
				studentChoices.add(arr[6]);
				studentChoices.add(arr[7]);
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

		
	}
	

