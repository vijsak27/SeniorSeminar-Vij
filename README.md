# SeniorSeminar
A student project for Managing Scheduling and Optimizing a Solution that reduces conflicts

## Develop a Java Based Solution to Minimize Scheduling Conflicts

The attached CSV/Spreadsheet file is the result of sending out a google form and having students enter their top 5 ranked choices, using the mini table in the spreadsheet that contains the sessions, IDs and speakers.

**Feel free to clean up the spreadsheets, and even create new ones if needed, HOWEVER - you many not alter student preferences in the fiel or spreadsheet.

- 5 Time Slots 
- 5 Classrooms (16 students max/ class)
- Sessions Cannot Run more than Twice
- Notice that some sessions are taught by same person
- Student May Not Repeat a Session
- Schedule a Student for 5 Session

**Goal is to design and code an algorithm that generates a schedule for the students, given structure above, with the fewest number of conflicts

- Generate a schedule for the sessions and speakers, 
- Create lists for each student so they know what sessions to attend, rooms and times.

Journal Entry 1 (2/19):
Today, I got the rankings to print out; however, the logic is not correctly working. I added to the rankedPopularity() method, which will be called in the Sort() method later on. 
The logic is somewhat working (for instance in time slot one session 15 [the most popular one in time slot one] is ranked at number 1 and time slot 2 is ranked correctly at 2). however
time slot 16 (which only has one appearance in the first time slot choices) appears incorrectly at rank 3. 


Journal Entry 2 (2/23):
Today, I worked on debugging the ranking code. I have made progress with some of the rankings and have identified where the error is arising. I now need to spend some time tracing
the code to see why the popularity max is not being correctly identified. The program does still compile, but the logic is not fully correct yet. Furthermore, I checked my the list that gets popularity
per time slot (non-ranked) and they seem to be accurate. After fixing the popularity error, I will use the populairty to create the schedule. I believe that the error is arising in the logic of the for loop I have implemented to find the 
maximum in the array that contains all of the popularities of all of the session for the time slot being analyzed. I believe that next steps will include
continuing to add print statements to trace the values of all of the variables I am using in order to identifiy where the program is going wrong and how I can alter the code to avoid the logical errors in my looping and ranking logic currently.


Journal Entry 3 (2/27):
Today, I spent time working on resolving an error that came up with regards to the file that I was uploading for the numerial IDs of the sessions that will be included in the senior seminar event. The error arising was that splitting the Scanner.nextLine() function stored in the variable line. The 
fix that I implemented today involved editing the original file to mitigate any potential errors in the line splitting. The error was resolved by the end of the day
and the file is now correctly uploading all data.
