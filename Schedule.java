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
	public ArrayList rankedPopularityAllSlots(){
		ArrayList<ArrayList> choicesAllSlots = new ArrayList<ArrayList>(); // all choices; all slots
		ArrayList<int[]> popularitiesAcrossSlots = new ArrayList<int[]>();//numerical pop values all slots
		ArrayList<Integer> rankedPopThisSlot = new ArrayList<Integer>();//stores the numerical value of popularity (number of students)
		ArrayList<Integer> sessionsPopularityRankedThisSlot = new ArrayList<Integer>();//stores the sessionIDs (1-18) in ranked pop order
		
		ArrayList<ArrayList> rankedPopAllSlots = new ArrayList<ArrayList>();//numerical values of pop stored in 5 diff arraylists
		ArrayList<ArrayList> sessPopAllSlots = new ArrayList<ArrayList>();//session IDs of popularity all slots (1-18 in this case)
		
		//ArrayList<ArrayList> po
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
					
					if(((int)(choicesPerSlot.get(b))) == a+1){
						popularityCounter++;
					}
					
				}
					popularityPerSession[a]=popularityCounter;//important note: popularityPerSession sarts at 0, so session 1 is at index 0
					popularityCounter = 0;//reset to 0 for next timeslot calculations
					
			}
			
			popularitiesAcrossSlots.add(popularityPerSession);
			//System.out.println("popularitiesAcrossSlots: \n"+popularitiesAcrossSlots);//contains the popularity for each timeslot
			//the above line contain popularity across all sessions (non-ranked)
			
			
			
		}
		// at this point all of the choices should have been loaded and the popularities have been loaded
	
		
		for(int c = 0 ; c<sessPerSlot; c++){
		

			int[] currTimeSlotPop = popularitiesAcrossSlots.get(c);
			int arrayLength = currTimeSlotPop.length;
			for(int i = 0; i<arrayLength; i++){
				System.out.println(currTimeSlotPop[i]+" ");
			}
			System.out.println("Array Length: "+arrayLength);
			
			int max = currTimeSlotPop[0];
			
			
			
			System.out.println("Session being checked: "+(c+1));
			System.out.println("Preloop max: "+max);
			int popularSessionIndex=0;
				
			
			for(int e = 0; e<numSessions; e++){

			
				for(int d = 0; d<arrayLength; d++){
					if(currTimeSlotPop[d]>max){
						max = currTimeSlotPop[d];
						popularSessionIndex = d;
						System.out.println("Current Index: "+d);
						System.out.println("Current Max: "+max);
						
						
					}


				}
				System.out.println(currTimeSlotPop[max]);
				rankedPopThisSlot.add(currTimeSlotPop[max]);
				sessionsPopularityRankedThisSlot.add(max);
				//make sure same session isn't max again
			}
			currTimeSlotPop[max] = -1;
			rankedPopAllSlots.add(rankedPopThisSlot);
			sessPopAllSlots.add(sessionsPopularityRankedThisSlot);
			//System.out.println("Added Session ID: "+ (popularSessionIndex+1) +" to time slot "+ (c+1));
			//System.out.println("Popularity: "+max);
			//rankedPopThisSlot.add((popularSessionIndex+1));//adds session ID to the most popular sessions list
				
		}
		//System.out.println(choicesAllSlots+"\n\n\n\n");
		//System.out.println(popularitiesAcrossSlots.size());
		System.out.println(rankedPopAllSlots+"\n\n\n\n");
		ArrayList<ArrayList> returnList = new ArrayList<ArrayList>();
		returnList.add(rankedPopAllSlots);
		returnList.add(sessPopAllSlots);
		return returnList;	//need to work on logic of ranking popularity
	}
		
}

