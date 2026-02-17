import java.util.*;
public class Schedule{
	private int numSlots;
	private int sessPerSlot;
	private int[][] schedule;
	private ArrayList<Student> stuData;
	private int numSessions;
	
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
		s1.nextLine();
		System.out.println("How many sessions will you offer in total?");
		numSessions = s1.nextInt();
		s1.close();
		schedule = new int[numSlots][sessPerSlot];
	}
	
	public void Sort(){
		
		
		//for(int i = 0; i<stuChoices.size(); i++){
			
			//get populatiry list
			
			
			
			
		//}
	}
	public ArrayList rankedPopularity(){
		ArrayList<ArrayList> choicesAllSlots = new ArrayList<ArrayList>();
		ArrayList<int[]> popularitiesAcrossSlots = new ArrayList<int[]>();
		
		for(int i = 0; i < numSlots; i++){
			ArrayList choicesPerSlot = new ArrayList<Integer>();
			int len = stuData.size();
			for(int n = 0 ; n < len; n++){
				//System.out.println("Print: "+stuData.get(n));
				Student s = stuData.get(n);//need to fix the error here
				ArrayList<Integer> stuChoices = s.getChoices();
				int choiceThisSlot = stuChoices.get(i);
				choicesPerSlot.add(choiceThisSlot);
				
			}
			//rankedPerSlot.sort(null);
			choicesAllSlots.add(choicesPerSlot);
			//int sessionNum = choicesPerSlot.get(0);//first element in the list of choices for one time slot
			int popularityCounter = 0;
			int length = choicesPerSlot.size();
			int[] popularityPerSession = new int[numSessions];
			//ArrayList <Integer> popPerSessionList= new Arraylist<Integer>();
			for (int a = 0 ; a<numSessions; a++){
				for(int b = 0 ; b<length; b++){
					
					if(((int)(choicesPerSlot.get(b))) == a){
						popularityCounter++;
					}
				}
					popularityPerSession[a]=popularityCounter;//important note: popularityPerSession sarts at 0, so session 1 is at index 0
					//choicesAllSlots.add(popularityPerSession);
					popularitiesAcrossSlots.add(popularityPerSession);//why is this 90 rn?
			}
			
			
			
		}
		System.out.println(choicesAllSlots+"\n\n\n\n");
		System.out.println(popularitiesAcrossSlots.size());
		return choicesAllSlots;	
	}
		
		}

