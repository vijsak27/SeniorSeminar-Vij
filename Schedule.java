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
import java.io.*;

public class Schedule{
	
	//instance variables
	private int numSlots; //number of time slots
	private int sessPerSlot; //number of sessions offered per time slot
	private Session[][] schedule; //the schedule of sessions
	private ArrayList<Student> stuData; //used to load in data files
	private ArrayList<Session> sessions; //loaded in from data file
	private int numSessions; //input by user (number of sessinos offered)
	private int maxCapacity; //maximum number of students in a session
	private int maxRepeats = 2; //maximum number of times a session can repeat
	
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
		
		System.out.println("How many time slots are you offering? (MUST match data file)");
		numSlots = s1.nextInt();
		s1.nextLine();//clear buffer
		
		System.out.println("How many sessions per time slot?");
		sessPerSlot = s1.nextInt();
		s1.nextLine();//clear buffer
		
		System.out.println("How many sessions will you offer in total? (MUST match data file)");
		numSessions = s1.nextInt();
		s1.nextLine();//clear buffer
		
		System.out.println("How many students will be allowed in one session?");
		maxCapacity = s1.nextInt();
		
		schedule = new Session[numSlots][sessPerSlot]; //initialize the schedule 2D array with inputted values
		
		
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
				
				if(rankIndex>=popularityThisSlot.size()){
					break;
				}
				
				//get the rankIndex ranked session for this slot
				int sessionID = popularityThisSlot.get(rankIndex);
				
				//get the session object from the ID
				Session currSession = null;
				for(int s = 0; s<numSessions; s++){
					if(sessions.get(s).getID()==sessionID){
						currSession = sessions.get(s);
						break;
					}
				}
				
				//see if the presenter if busy during this slot
				boolean presenterBusy = false;
				if(currSession != null){
					for(int n = 0; n<sessPerSlot; n++){
						if(schedule[slot][n]!=null&&schedule[slot][n].getPresenter().equals(currSession.getPresenter())){
							presenterBusy=true;
						}
					}
				}
				if(!presenterBusy && sessionCounts[sessionID-1]<maxRepeats){// a session can run max twice
					
					for(int i = 0 ; i<numSessions; i++){
						if(sessionID == sessions.get(i).getID()){
							
							//add a copy of the session the proper location the schedule
							Session originial = sessions.get(i);
							schedule[slot][sessionsFilled] = new Session (originial.getID(),originial.getName(), originial.getPresenter());
							
							//update the number of times the session has been schedule and the number of session filled for the slot
							sessionCounts[sessionID-1]++;
							sessionsFilled++;
							
							break;
						}
					}
				}
				//go to the next most popular session for scheduling
				rankIndex++;
			}
			
		
		}
	}
	
	
	/*
	The rankedPopularityAllSlots() method return the ArrayList containing the ranked popularity
	of all of the sessino across all of the slots. This method does so by first calculating the
	popularity of all of the sessions and then ranking the sessions based on the popularity
	*/
	
	public ArrayList<ArrayList<ArrayList<Integer>>> rankedPopularityAllSlots(){
		
		ArrayList<ArrayList> choicesAllSlots = new ArrayList<ArrayList>(); // all choices; all slots
		ArrayList<int[]> popularitiesAcrossSlots = new ArrayList<int[]>();//numerical pop values all slots
	
		ArrayList<ArrayList<Integer>> rankedPopAllSlots = new ArrayList<>();//numerical values of popularity stored in 5 different arraylists
		ArrayList<ArrayList<Integer>> sessPopAllSlots = new ArrayList<>();//session IDs of popularity all slots (1-18 in this case)

		for(int i = 0; i < numSlots; i++){
			
			ArrayList<Integer> choicesPerSlot = new ArrayList<Integer>();
			
			int len = stuData.size();
			for(int n = 0 ; n < len; n++){

				Student s = stuData.get(n);
				ArrayList<Integer> stuChoices = s.getChoices();
				
				//the students choice to the list for each slot
				int choiceThisSlot = stuChoices.get(i);
				choicesPerSlot.add(choiceThisSlot);
				
			}
		
			//add the choices for this slot to the larger choicesAllSlots ArrayList
			choicesAllSlots.add(choicesPerSlot);
			
			int length = choicesPerSlot.size();
			
			//get popularity for each session
			int[] popularityPerSession = new int[numSessions];
			
			//loop through each session
			for (int a = 0 ; a<numSessions; a++){
				
				//count popularity (resets to 0 for every session)
				int popularityCounter = 0;
				
				for(int b = 0 ; b<length; b++){
					
					if(((int)(choicesPerSlot.get(b))) == a+1){//add one since the indexes and sessionIDs are off by 1
						popularityCounter++;
					}
				}
				
				//update popularityPerSession array
				//important note: popularityPerSession sarts at 0, so session 1 is at index 0
				popularityPerSession[a]=popularityCounter;	
			}
			
			//the above below contain popularity across all slots for all sessions (non-ranked)
			popularitiesAcrossSlots.add(popularityPerSession);
			
		}
		// at this point all of the choices should have been loaded and the popularities have been loaded
	
		
		//loop through each slot and rank the popularity
		for(int c = 0 ; c<numSlots; c++){
			ArrayList<Integer> rankedPopThisSlot = new ArrayList<Integer>();//stores the numerical value of popularity (number of students)
			ArrayList<Integer> sessionsPopularityRankedThisSlot = new ArrayList<Integer>();//stores the sessionIDs (1-18) in ranked pop order

			//use output of first half of this method()
			int[] currTimeSlotPop = popularitiesAcrossSlots.get(c);
			
			int arrayLength = currTimeSlotPop.length;
			boolean[] used = new boolean[arrayLength]; //keep track of which indices have been "used" as a popular session in the popularity rankings
			
			//variables used to find most popular session
			int max;
			int popularSessionIndex;
			
			for(int q = 0 ; q<numSessions;q++){
				
					max = Integer.MIN_VALUE;//default Integer.MIN_VALUE for max and popular session index
					popularSessionIndex=-1;//default starting value will be updated by code below
					
					//find the most popular session for the slot
					for(int d = 0; d<arrayLength; d++){
						if(!used[d] && currTimeSlotPop[d]>max){
							// set max equal to number of students in the most popular session 
							max = currTimeSlotPop[d];
							
							//set popularSessionIndex to the index of the most popular session for this slot
							popularSessionIndex = d;
						}
					}
				//add the sessino in ranked order
				rankedPopThisSlot.add(max);	
				sessionsPopularityRankedThisSlot.add((popularSessionIndex+1));
				
				//make sure same session isn't max again
				used[popularSessionIndex] = true;
	
				
				
			}
			//add the rankedPopThisSlot and sessionsPopularityRankedThisSlot to the cumulative ArrayLists
			rankedPopAllSlots.add(rankedPopThisSlot);
			sessPopAllSlots.add(sessionsPopularityRankedThisSlot);	
		}

		//create a returnList to return the output of this method in one variable
		ArrayList<ArrayList<ArrayList<Integer>>> returnList = new ArrayList<>();
		returnList.add(rankedPopAllSlots);//first add all the actual popularity value; index 0
		returnList.add(sessPopAllSlots);//sessions ranked by popularity; index 1
		
		return returnList;	
	}
	
	/*
	The prioritySortStudents() organizes the student from most niche to least niche selections
	to maximize the number of choices each student can get becuase it is important to not fill up
	the niche sessions with overflows from the expectionally popular sessions, but rather, to give 
	the student with less popular choice their desired session prior to filling in the more popular
	sessions and the overflows from those sessions. This methodology decreased the number of conflict
	I have.
	*/
	public void prioritySortStudents() {
		int demand[] = new int[numSessions+1]; //+1 to account for the fact that index 18 wouldn't exist in len = 18
		
		//loop through students
		for(Student s : stuData) {
			
			//get each students choices
			ArrayList<Integer> choices = s.getChoices();
			
			//get the highest priority session
			if(choices.get(0) != 0){ //0 accounts for empty choices
				demand[choices.get(0)]++;//increment demand at the index of the session
			}
		}
		
		int len = stuData.size();
		for(int i = 0; i<len-1; i++){
			int nicheChoicesStudentIndex = i;
			for(int n = i+1; n< len; n++){
				
				//compare the demand of neighboring students
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
	The assignStudents() method handles the process of placing each student in a session
	for all slots. It does so be looping throuhg all of the slots and then assigning each
	student a session in that slot. If the student does not have choice that matches with any
	of the availble sessino during a slot, that student in randomly assigned to an available session
	and the assignment is counted as a conflict. At the end, the assignStudents() method will
	return the total number of conflicts
	*/
	public int assignStudents(){
		
		int totalConflicts = 0;
		
		//tracks if a student has been assigned all slots
		boolean[][] studentSlotTracker = new boolean[stuData.size()][numSlots];
		
		//loop through each slot
		for(int slot = 0; slot<numSlots; slot++){
			for (int studentIndex = 0; studentIndex < stuData.size(); studentIndex++){
				Student currentStudent = stuData.get(studentIndex);
				
				//create a variable to see if the student is assigned for this slot
				boolean assignedThisSlot = false;
				
				//get the currentStudent's choices
				ArrayList<Integer> stuChoices = currentStudent.getChoices();
				
				//loop through the student's choice and see if it is in the slot
				for(int choice : stuChoices){
					
					//don't keep on searching if already assigned
					if(assignedThisSlot){
						break;
					}
				
					//see if student has alreadyTaken the session
					boolean alreadyTaken = false;
					for(int session = 0; session<currentStudent.getSchedule().size(); session++){
						
						if(currentStudent.getSchedule().get(session)==choice){
							alreadyTaken=true;
						}
					}
					
					if(alreadyTaken){
						continue; //the student already has the session so dont assing it again
					}
						
				
					//loops throuhg all 5 "rooms" used per slot
					for(int room = 0; room<sessPerSlot; room++){
						Session sessionAtCurrLocation = schedule[slot][room];
						
						if(sessionAtCurrLocation!= null &&sessionAtCurrLocation.getID()==choice && sessionAtCurrLocation.getNumStudents()<maxCapacity){
							
							//keep track of the students added to the session
							sessionAtCurrLocation.addStudent(currentStudent);
							
							//keep track of the student's schedule
							currentStudent.addToSchedule(choice);
							
							//this variable will tell the program to move onto the next slot
							assignedThisSlot=true;
							
							//update the 2D array tracking assignments
							studentSlotTracker[studentIndex][slot] = true;
							
							break; //stop looking for assignments in this slot (since already assigned)
						}
					}
					
					if(assignedThisSlot){
						break; //becuase already assigned and got a preference
					}

			}
			
			/*
			 This code snipped accounts for the several student who did not enter any choices.
			 It ensures that the students are not mistakenly counted as conflicts
			*/
			boolean noChoices = true;
			for(int i = 0; i<stuChoices.size();i++){
				if(stuChoices.get(i)!=0){
					noChoices = false;
				}
			}
			
			
			if(assignedThisSlot==false && !noChoices){
				
				//if the student did not receive a preference in the time slot add a conflict
				totalConflicts++;
				
				//fill in slot with another session
				for(int room = 0; room< sessPerSlot; room ++){
					
					//potential filler session
					Session fillInSession = schedule[slot][room];
					
					//see if the student has already taken the potential filler session
					boolean fillInSessionAlreadyTaken =false;
						
					//loop through the student's current schedule and add a filler session											
					for(int session = 0; session<currentStudent.getSchedule().size(); session++){
							
						if(fillInSession!=null&&((currentStudent.getSchedule().get(session))==fillInSession.getID())){
							fillInSessionAlreadyTaken=true;
						}
					}
						
					//if not already taken and there is space add the student								
					if(fillInSession!=null&&!fillInSessionAlreadyTaken && fillInSession.getNumStudents()<maxCapacity){
						
						//update the session by adding the student
						fillInSession.addStudent(currentStudent);
						
						//update the student's schedule
						currentStudent.addToSchedule(fillInSession.getID());
						
						//mark as assigned
						assignedThisSlot=true;
						
						//update student assignment tracker
						studentSlotTracker[studentIndex][slot] = true;
						
						//stop looking for assignments in this slot (since already assigned)
						break;
					}
													
				}	
								
			}							
		}	
		}				
		
		/*
		The code below ensures that all students have assigned spots for all slots
		to ensure that no student is unaccounted for. This code assigns the schedules
		for the students who entered no preferences
		*/
		
		for(int studentIndex = 0; studentIndex<stuData.size(); studentIndex++){
			Student currentStudent = stuData.get(studentIndex);
				
			if (currentStudent.getSchedule().size()<numSlots){
				for(int slot = 0; slot<numSlots; slot++){
					
					if(!studentSlotTracker[studentIndex][slot]){
							
					for(int session = 0; session<sessPerSlot; session++){
						if(schedule[slot][session]!= null && schedule[slot][session].getNumStudents()<maxCapacity){
									
							//ensure that the student hasn't already aattended the session
							boolean alreadyTaken = false;
									
							for(int i = 0; i<currentStudent.getSchedule().size(); i++){
								if(currentStudent.getSchedule().get(i)==schedule[slot][session].getID()){
									alreadyTaken=true;
									break;
								}
							}
									
							if(!alreadyTaken){
								currentStudent.addToSchedule(schedule[slot][session].getID());
								schedule[slot][session].addStudent(currentStudent);
								studentSlotTracker[studentIndex][slot] = true;
									
								break; //don't add to more sessions in same time slot
							}
						}							
					}
				
					
				}
				}
			}		

		
		}
		
		//print statements for debugging
		/*
		for(Student student: stuData){
			System.out.println(student);
		}
		
		System.out.println("Total Conflicts: "+totalConflicts+"\n\n");
		
		*/
		return totalConflicts;
		
		
		
	}
	
	
	
	/*
	The showSessionRosters() method enables the program to shows all of the rosters of all of the 
	sessions during the Senior Seminar event 
	*/
	public void showSessionRosters(){
		for(int slot = 0; slot<numSlots; slot++){
			for(int session = 0; session<sessPerSlot; session++){
				if(schedule[slot][session] != null){
					//print out the Slot, Session, and IDs of student
					//the +1's in the next line are because in the code the slots begin at 0
					System.out.println("Slot: "+(slot+1)+", Session: "+(session+1) + "\n\nRoster by Student IDs: ");
					for(Student student: schedule[slot][session].getStudents()){
						System.out.print(student.getID()+" ");
					}
					//also print out the number of the students assigned to the session
					System.out.println("Session size: "+schedule[slot][session].getNumStudents());
					System.out.print("\n\n");
				}	
			}
		}
		
	
	}
		
		
	/*
	The schedule() method acitivates when a user asks for the Senior Seminar program to run
	and organize the sessions and students. This organizes the functionality of the methods in 
	this class
    */	
	public void schedule(boolean custom){
		//ask for user input
		scheduleDetails();
		
		//rank the students
		prioritySortStudents();
		
		//calculate the popularity for each session
		rankedPopularityAllSlots();
		
		//see if a custom schedule should be used
		if(custom){
			customSchedule();
		}
		else{
			sort();
		}
		
		//assign students and get conflicts
		int conflicts = assignStudents();
		
		//showSessionRosters();
		//print out master schedule
		//System.out.println(toString());
		
		//print out how optimal the schedule is
		System.out.println("Total Conflicts: "+ conflicts);
		System.out.println("Average conflicts per student: "+((double)conflicts/stuData.size()));
	}
	
	/*
	The toString() method defines what is printed out when the schedule object is
	printed. The schedule object printed will produce the master schedule
	*/
	public String toString(){
		String output = "";
		
		for(int r = 0 ; r<numSlots;r++){
			for(int c = 0 ; c<sessPerSlot; c++){
				if(schedule[r][c]==null){
					output += " _ ";
				}
				else{
					output+=(schedule[r][c].getID()+" ");
				}
			}
			output+=("\n");
		}
		
		return output;
	}
	
	
	/*
	The menu() method runs the user interfacce for my program. It allows users with three options
	These include seeing the master schedule, printing sessino rosters, and searching for a
	particular student by name. This is the method that is called in the main class to run 
	Senior Seminar
	*/
	
	public void menu(boolean custom){
		System.out.println("Welcome to Senior Seminar\n__________________________________");
		System.out.println("Running Senior Seminar based on inputted file");
		schedule(custom);
		Scanner s1 = new Scanner(System.in);
		
		String response = "-1";
		
		while(!response.equals("q")){
			System.out.println("1. See Master Schedule");
			System.out.println("2. Print Session Rosters");
			System.out.println("3. Student Schedule By Name");
			System.out.println("4. Presenter Schedule By Name");
			System.out.println("Enter 1, 2, 3, or q to quit");
			response = s1.nextLine();
			if(response.equals("1")){
				System.out.println("Master Scheulde");
				System.out.println("Rows represent time slots; columns represent sessions in those slots");
				System.out.println(this);
			}
			else if(response.equals("2")){
				showSessionRosters();
			}
			else if(response.equals("3")){
				System.out.println("Enter Student Name (must match data file): ");
				String name = s1.nextLine();
				
				for(Student s: stuData){
					if(s.getName().toLowerCase().equals(name.toLowerCase())){
						System.out.println("\n\nStudent: "+name+ " ID: "+s.getID());
						System.out.println("Schedule (Session IDs): "+s.getSchedule()+"\n\n");
					}
				}
			}
			else if (response.equals("4")){
				System.out.println("Enter Presenter Name (must match data file): ");
				String name = s1.nextLine();
				
				System.out.println("\n\n"+name+ "'s Schedule: ");
				for(int r = 0; r<numSlots; r++){
					for (int c = 0; c<sessPerSlot; c++){
						if(schedule[r][c]!=null&&schedule[r][c].getPresenter().equals(name)){
							System.out.println("Time Slot: "+(r+1)+ ", Session: "+schedule[r][c].getName());
						}
					}
				}
				System.out.println("\n\n");
			}
			else if(response.equals("q")){
				response = "q";
			}
			else{
				System.out.println("Invalid entry, try again");
			}
		}
		
		
	}
		
}

