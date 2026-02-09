import java.io.*;
import java.util.Scanner;

public class ReadFile{
	private ArrayList students;
	public ReadFile(){
	}
	public String toString(){
		String output;
		for(int i=0; i<students.size(); i++){
			output = students.get(i)+"\n";
		}
		return output;
	}
	public loadData(){
		String fileName = ""; //enter name of file here
		File myFile = new File(fileName);
		for (int i=1; i<len; i++){
		try (Scanner reader = new Scanner(myFile)){
			while (reader.hasNextLine()){
				String name = reader.nextLine();
				String[] arr = name.split(",");
				ArrayList<int> studentChoices = new ArrayList<int>();
				studentChoices.add(arr[3]);
				studentChoices.add(arr[4]);
				studentChoices.add(arr[5]);
				studentChoices.add(arr[6]);
				studentChoices.add(arr[7]);
				Student s1 = new Student(arr[0],arr[1], studentChoices);
				
				System.out.println("Added: "+s1.getName()+"  ID: "+s1.getID);
			}
		} catch (FileNotFoundException e){
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}

		
	}
	
}
