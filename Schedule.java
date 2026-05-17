/*
 * Schedule.java
 * Author: Sakshum Vij
 * Date: 5/17/26
 * Program Name: Senior Seminar
 * Purpose:
 * The schedule class performs to organization of the senior seminar event. It include several
 * instance variables that the user inputs and calls upon the ReadFile.java class to load
 * the student information from the inputted data files. This class has several methods that
 * form the functionality of this class, cinluding the ranking of session popularity, forming
 * the session schedule, and organizing the students.
 * */

//imports
import java.util.*;
import java.io.*

public class Schedule{
	
	//instance variables
	private int numSlots; //number of time slots
	private int sessPerSlot; //number of sessions offered per time slot
	private Session[][] schedule; //the schedule of sessions
	private ArrayList<Student> stuData; //used to load in data files
	private ArrayList<Session> sessions; //loaded in from data file
	private int numSessions; //input by user (number of sessinos offered)
	private int maxCapacity; //maximum number of students in a session
	
	//constructor
	public Schedule(){
		ReadFile r1 = new ReadFile();
		r1.loadStudents();
		stuData = r1.getStudents();
		r1.loadSessions();
		sessions=r1.getSessions();
	}
	
	/*
	The scheduleDetails() method is a void method (does not return any value) that taken in the
	inputs necessary to run senior seminar from the user. This information includes, the number of
	time slots (must match the data file), the number of sessions offered per time slot, total
	number of session (must match the data file), and the maximum capacity of one session. These
	inputs are then assigned to the instance variables of the schedule object.
	*/
	public void scheduleDetails(){
		Scanner s1 = new Scanner(System.in);
		
		System.out.println("How many time slots are you offering?");
		numSlots = s1.nextInt();
		s1.nextLine();//clear buffer
		
		System.out.println("How many sessions per time slot?");
		sessPerSlot = s1.nextInt();
		s1.nextLine();//clear buffer
		
		System.out.println("How many sessions will you offer in total?");
		numSessions = s1.nextInt();
		s1.nextLine();//clear buffer
		
		System.out.println("How many students will be allowed in one session?");
		maxCapacity = s1.nextInt();
		
		schedule = new Session[numSlots][sessPerSlot]; //initialize the schedule 2D array with inputted values
		
		s1.close();
		
	}
	
	/*
	The customSchedule() method was primarily used to compare different versions of the master schedule
	. Currently, it contains the schedule that was created by Mr. Twyford. In the schedule() method
	below, a boolean value can be passed into the method as a parameter than determines whether
	the program will use the custom schedule included in this method or if the program will
	create a master schedule using the popularity of the sessions
	*/
	public void customSchedule(){
		
		//custom schedule provided by Mr. Twyford
		int[][] customSchedule = 
					{{1,9,14,5,15},
					{2,6,10,12,16},
					{15,3,11,4,7},
					{16,18,13,1,9},
					{7,8,17,2,6}};
					
		//update the schedule[][] with the custom schedule			
		for (int row = 0; row<numSlots; row++){
			for(int col = 0; col<sessPerSlot; col++){
				for(int session = 0; session<numSessions; session++){
					if(customSchedule[row][col]==sessions.get(session).getID()){
						
						/*
						Need to make a copy of the original session so that if a sessino occurs
						more than once, each session in schedule[][] will be distinct
						*/
						Session originial = sessions.get(session);
						schedule[row][col] = new Session (originial.getID(),originial.getName(), originial.getPresenter());
					}
				}
			}
		}
	}
	
	
	/*
	The sort() method sorts the master schedule by taking in the ranked popularity from the
	rankedPopularityAllSlots method and identifying the most popular sessions to add into
	the master schedule. The only constraint on this is that there cannot be more than 2 occurences
	of the same session in the master schedule
	*/

	public void sort(){
		
		//take in output of rankedrankedPopularityAllSlots() method
		ArrayList<ArrayList<ArrayList<Integer>>> rankedPopularity = rankedPopularityAllSlots();
		int[] sessionCounts = new int[numSessions];
		
		for (int slot = 0; slot<numSlots; slot++){
			
			//Extract the popularity for the current slot from the rankedPopularity ArrayList
			ArrayList<Integer> popularityThisSlot = rankedPopularity.get(1).get(slot);
		
			int sessionsFilled = 0;
			int rankIndex = 0; //starts at the first most popular session (included this to account for sessions already booked twice);
			
			//while all sessions for the slot have not been filled, continue looping
			while(sessionsFilled<sessPerSlot){
				
				//get the rankIndex ranked session for this slot
				int sessionID = popularityThisSlot.get(rankIndex);
				
				if(sessionCounts[sessionID-1]<2){// a session can run max twice
					
					for(int i = 0 ; i<numSessions; i++){
						if(sessionID == sessions.get(i).getID()){
							
							//add a copy of the session the proper location the schedule
							Session originial = sessions.get(i);
							schedule[slot][sessionsFilled] = new Session (originial.getID(),originial.getName(), originial.getPresenter());
							
							//update the number of times the session has been schedule and the number of session filled for the slot
							sessionCounts[sessionID-1]++;
							sessionsFilled++;
						}
					}
				}
				//go to the next most popular session for scheduling
				rankIndex++;
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

		
		ArrayList<ArrayList<ArrayList<Integer>>> returnList = new ArrayList<>();
		returnList.add(rankedPopAllSlots);//first add all the actual popularity value
		returnList.add(sessPopAllSlots);//sessions ranked by popularity
		
		return returnList;	
	}
	
	
	public void prioritySortStudents() {
		int demand[] = new int[numSessions+1]; //+1 to account for the fact that index 18 wouldn't exist in len = 18
		
		for(Student s : stuData) {
			ArrayList<Integer> choices = s.getChoices();
			if(choices.get(0) != 0){ //0 accounts for empty choices
				demand[choices.get(0)]++;//increment demand at the index of the session
			}
		}
		
		int len = stuData.size();
		for(int i = 0; i<len-1; i++){
			int nicheChoicesStudentIndex = i;
			for(int n = i+1; n< len; n++){
				if(demand[stuData.get(n).getChoices().get(0)]<demand[stuData.get(nicheChoicesStudentIndex).getChoices().get(0)]){
					nicheChoicesStudentIndex = n;
				}
			}
			
			//swap the students and move the stundet with more niche choices forward in the stuData ArrayList
			Student temp = stuData.get(nicheChoicesStudentIndex);
			stuData.set(nicheChoicesStudentIndex, stuData.get(i));
			stuData.set(i,temp);
			
		}
	}
	
	/*
	I am creating a second version of assigning students for go student by student rather than
	slot by slot
	 */
	public int assignStudentsV2(){
		int totalConflicts = 0;
		
		for(Student currStudent : stuData){
			ArrayList<Integer> stuChoices = currStudent.getChoices();
			boolean[] slotsFilled = new boolean[numSlots];//create an array for each students trakcing if they have been assigned sessions in each slot
			int numAssigned = 0;
			
			for (int choice : stuChoices){
				if(choice == 0){
					continue; //skip the empty choices for the studetns at the end
				}
				
				boolean alreadyHasSession = false;
				for(int i = 0; i<currStudent.getSchedule().size(); i++){
						if(currStudent.getSchedule().get(i) == choice){
							alreadyHasSession = true;
						}
				}
				if(alreadyHasSession){
					continue;//if already has the sessino then skip it
				}
				
				boolean gotChoice = false;
				for(int slot = 0; slot < numSlots; slot++){
					if(slotsFilled[slot]==true){
							continue; //if already assigned for slot then skip
					}
					for(int session = 0; session < sessPerSlot; session++){
						Session sessAtLocation = schedule[slot][session];
						
						
						if(sessAtLocation.getID() == choice && sessAtLocation.getNumStudents()<maxCapacity){
							sessAtLocation.addStudent(currStudent);
							currStudent.addToSchedule(choice);
							slotsFilled[slot]=true;
							gotChoice = true;
							numAssigned++;
							break;//stop searching after assigned for the slot
						}
					}
					if(gotChoice){
						break;//move on to next choice after this one is assigned
					}
				}
			}
			
			
			//account for conflicts
			if(numAssigned < numSlots){
				totalConflicts += (numSlots-numAssigned);
				
				for(int slot = 0; slot <numSlots; slot++){
					if(!slotsFilled[slot]){
						for(int session = 0 ; session < sessPerSlot; session++){
							Session fillInSession = schedule[slot][session];
							
							boolean alreadyTaken = false;
							for(int i = 0; i<currStudent.getSchedule().size(); i++){
								
								if((currStudent.getSchedule().get(i))==fillInSession.getID()){
									alreadyTaken=true;
								}
							}
							
							if(!alreadyTaken && fillInSession.getNumStudents()<maxCapacity){
								fillInSession.addStudent(currStudent);
								currStudent.addToSchedule(fillInSession.getID());
								slotsFilled[slot]=true;
								break; // once assigned stop searching
							}
						}
					}
				}
			}
		}
		
		for(Student student: stuData){
			System.out.println(student);
		}
		System.out.println("Total Conflicts: "+totalConflicts);
		
		return totalConflicts;
		
	}
	
	
	public int assignStudents(){
		int totalConflicts = 0;
		
		
		for(int slot = 0; slot<numSlots; slot++){
			for (Student currentStudent : stuData){
				boolean assignedThisSlot = false;
				ArrayList<Integer> stuChoices = currentStudent.getChoices();
				
				for(int choice : stuChoices){
				
					boolean alreadyTaken = false;
					for(int session = 0; session<currentStudent.getSchedule().size(); session++){
						
						if(currentStudent.getSchedule().get(session)==choice){
							alreadyTaken=true;
						}
					}
					
					if(alreadyTaken){
						continue; //the student already has the session so dont assing it again
					}
						
				
				
					for(int room = 0; room<sessPerSlot; room++){//loops throuhg all 5 "rooms" used per slot
						Session sessionAtCurrLocation = schedule[slot][room];
						if(sessionAtCurrLocation.getID()==choice && sessionAtCurrLocation.getNumStudents()<maxCapacity){
							sessionAtCurrLocation.addStudent(currentStudent);
							currentStudent.addToSchedule(choice);
							assignedThisSlot=true;
							break; //stop looking for assignments in this slot (since already assigned)
						}
					}
					
					if(assignedThisSlot){
						break; //becuase already assigned and got high preferences 
					}

			}
				boolean noChoices = true;
				for(int i = 0; i<stuChoices.size();i++){
					if(stuChoices.get(i)!=0){
						noChoices = false;
					}
				}
				if(assignedThisSlot==false && !noChoices){
					totalConflicts++;
					//fill in slot with another session
					for(int room = 0; room< sessPerSlot; room ++){
						Session fillInSession = schedule[slot][room];
						boolean fillInSessionAlreadyTaken =false;
																
							for(int session = 0; session<currentStudent.getSchedule().size(); session++){
								
								if((currentStudent.getSchedule().get(session))==fillInSession.getID()){
									fillInSessionAlreadyTaken=true;
								}
							}
														
							if(!fillInSessionAlreadyTaken && fillInSession.getNumStudents()<maxCapacity){
								fillInSession.addStudent(currentStudent);
								currentStudent.addToSchedule(fillInSession.getID());
								assignedThisSlot=true;
								break; //stop looking for assignments in this slot (since already assigned)
							}
														
				}	
								
			}							
		}	
		}				
		
		
		
		for(Student student:stuData){
			if(student.getSchedule().size()<numSlots){
				while(student.getSchedule().size()<numSlots){
					for(int slot = 0; slot<numSlots; slot++){
						for(int session = 0; session<sessPerSlot; session++){
							if(schedule[slot][session].getNumStudents()<maxCapacity){
								student.addToSchedule(schedule[slot][session].getID());
								schedule[slot][session].addStudent(student);
								break; //don't add to more sessions in same time slot
							}
						}
					}
				}
			}
		
		}
		
		for(Student student: stuData){
			System.out.println(student);
		}
		System.out.println("Total Conflicts: "+totalConflicts);
		
		return totalConflicts;
		
		
		
	}
	
	
	
	
	public void showSessionRosters(){
		for(int slot = 0; slot<numSlots; slot++){
			for(int session = 0; session<sessPerSlot; session++){
				System.out.println("Slot: "+slot+", Session: "+session + "\n\nRosterIDs: ");
				for(Student student: schedule[slot][session].getStudents()){
					System.out.print(student.getID()+" ");
				}
				System.out.println("Session size: "+schedule[slot][session].getNumStudents());
				System.out.print("\n\n");
			}
		}
		
	
	}
		
	public void schedule(boolean custom){
		scheduleDetails();
		prioritySortStudents();
		rankedPopularityAllSlots();
		if(custom){
			customSchedule();
		}
		else{
			sort();
		}
		int conflicts = assignStudents();
		showSessionRosters();
		System.out.println(toString());
		System.out.println("Total Conflicts: "+ conflicts);
		System.out.println("Average conflicts per student: "+((double)conflicts/stuData.size()));
	}
	
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

