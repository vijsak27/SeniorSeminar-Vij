import java.util.*;

public class Schedule{
	private int numSlots;
	private int sessPerSlot;
	private Session[][] schedule;
	private ArrayList<Student> stuData;
	private ArrayList<Session> sessions;
	private int numSessions;
	
	public Schedule(){
		ReadFile r1 = new ReadFile();
		r1.loadStudents();
		stuData = r1.getStudents();
		r1.loadSessions();
		sessions=r1.getSessions();
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
		schedule = new Session[numSlots][sessPerSlot];
	}
	
	public void sort(){
		
		
		ArrayList<ArrayList<ArrayList<Integer>>> rankedPopularity = rankedPopularityAllSlots();
		
		for (int slot = 0; slot<numSlots; slot++){
			for (int session = 0; session<sessPerSlot; session++){
				for(int i = 0 ; i<numSessions; i++){
					if(rankedPopularity.get(1).get(slot).get(session)==sessions.get(i).getID()){
						schedule[slot][session] = sessions.get(i);
					}
				}
			}
		
		}
	}
	
	public ArrayList<ArrayList<ArrayList<Integer>>> rankedPopularityAllSlots(){
		ArrayList<ArrayList> choicesAllSlots = new ArrayList<ArrayList>(); // all choices; all slots
		ArrayList<int[]> popularitiesAcrossSlots = new ArrayList<int[]>();//numerical pop values all slots
	
		
		ArrayList<ArrayList<Integer>> rankedPopAllSlots = new ArrayList<>();//numerical values of pop stored in 5 diff arraylists
		ArrayList<ArrayList<Integer>> sessPopAllSlots = new ArrayList<>();//session IDs of popularity all slots (1-18 in this case)
		

		for(int i = 0; i < numSlots; i++){
			ArrayList<Integer> choicesPerSlot = new ArrayList<Integer>();
			
			int len = stuData.size();
			
			for(int n = 0 ; n < len; n++){

				Student s = stuData.get(n);
				ArrayList<Integer> stuChoices = s.getChoices();
				
				int choiceThisSlot = stuChoices.get(i);
				choicesPerSlot.add(choiceThisSlot);
				
			}
		
			choicesAllSlots.add(choicesPerSlot);
			//int sessionNum = choicesPerSlot.get(0);//first element in the list of choices for one time slot
			
			int length = choicesPerSlot.size();
			int[] popularityPerSession = new int[numSessions];
			//ArrayList <Integer> popPerSessionList= new Arraylist<Integer>();
			for (int a = 0 ; a<numSessions; a++){
				
				int popularityCounter = 0;
				
				for(int b = 0 ; b<length; b++){
					
					if(((int)(choicesPerSlot.get(b))) == a+1){
						popularityCounter++;
					}
				}
					popularityPerSession[a]=popularityCounter;//important note: popularityPerSession sarts at 0, so session 1 is at index 0
					popularityCounter = 0;//reset to 0 for next timeslot calculations	
			}
			
			popularitiesAcrossSlots.add(popularityPerSession);
			//the above line contain popularity across all sessions (non-ranked)
			
			
			
		}
		// at this point all of the choices should have been loaded and the popularities have been loaded
	
		
		for(int c = 0 ; c<numSlots; c++){
			ArrayList<Integer> rankedPopThisSlot = new ArrayList<Integer>();//stores the numerical value of popularity (number of students)
			ArrayList<Integer> sessionsPopularityRankedThisSlot = new ArrayList<Integer>();//stores the sessionIDs (1-18) in ranked pop order

			int[] currTimeSlotPop = popularitiesAcrossSlots.get(c);
			
			int arrayLength = currTimeSlotPop.length;
			boolean[] used = new boolean[arrayLength]; //keep track of which indices have been "used" as a popular session in the popularity rankings
			
			int max;
			int popularSessionIndex;
			
			for(int q = 0 ; q<numSessions;q++){
				
					max = -1;//default -1 for max and popular session index
					popularSessionIndex=-1;
					
					for(int d = 0; d<arrayLength; d++){
						if(!used[d] && currTimeSlotPop[d]>max){
							max = currTimeSlotPop[d];
							popularSessionIndex = d;
						}
					}
				rankedPopThisSlot.add(max);	
				sessionsPopularityRankedThisSlot.add((popularSessionIndex+1));
				//make sure same session isn't max again
				used[popularSessionIndex] = true;
	
				
				
			}
			rankedPopAllSlots.add(rankedPopThisSlot);
			sessPopAllSlots.add(sessionsPopularityRankedThisSlot);	
		}

		System.out.println(sessPopAllSlots+"\n\n\n\n");
		ArrayList<ArrayList<ArrayList<Integer>>> returnList = new ArrayList<>();
		returnList.add(rankedPopAllSlots);//first add all the actual popularity value
		returnList.add(sessPopAllSlots);//sessions ranked by popularity
		System.out.println(returnList);
		return returnList;	
	}
	/*
	public int assignStudents(){
		for(int slot = 0; slot<numSlots; slot++){
			for(int student = 0; student<stuData.size(); student++){
				if(stuData.get(student))
			}
		}
	}
	public int calculateConflicts(){
		
		}
		*/
	
	
	public String toString(){
		String output = "";
		
		for(int r = 0 ; r<numSlots;r++){
			for(int c = 0 ; c<sessPerSlot; c++){
				output+=(schedule[r][c].getID()+" ");
			}
			output+=("\n");
		}
		
		return output;
	}
		
}

