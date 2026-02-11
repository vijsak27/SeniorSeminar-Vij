import java.util.*;
public class Schedule{
	private int numSlots;
	private int sessPerSlot;
	private int[][] schedule;
	private ArrayList<Integer> stuData;
	
	public Schedule(){
		ReadFile r1 = new ReadFile();
		r1.loadData();
		stuData = r1.getData();
	}
	public void scheduleDetails(){
		Scanner s1 = new Scanner(System.in);
		System.out.println("How many time slots are you offering?");
		numSlots = s1.nextInt();
		s1.nextLine();
		System.out.println("How many sessions per time slot?");
		sessPerSlot = s1.nextInt();
		s1.close();
		schedule = new int[numSlots][sessPerSlot];
	}
	
	public void Sort(){
		
		
		//for(int i = 0; i<stuChoices.size(); i++){
			
			//get populatiry list
			
			
			
			
		//}
	}
	public ArrayList rankedPopularity(){
		ArrayList popAllSlots = new ArrayList<ArrayList>();
		
		
		for(int i = 0; i < numSlots; i++){
			ArrayList rankedPerSlot = new ArrayList<Integer>();
			int len = stuData.size();
			for(int n = 0 ; n < len; n++){
				Student s = stuData.get(n)//need to fix the error here
				ArrayList<Integer> stuChoices = s.getChoices();
				int choice = stuChoices.get(i);
				rankedPerSlot.add(choice);
				
			}
			rankedPerSlot.sort(null);
			popAllSlots.add(rankedPerSlot);
			System.out.println(popAllSlots);
		}	
	}
		
		}
}
