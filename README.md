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

Journal Entry 4 (3/13):
Today, I finished identifying the error in the ranking error. The error entailed the program mistankenly finding only the top 1 session in popularity across all time slots rathers than the top 5 in the current time slot
. To fix the error, I have included another for loop outside my ranking loop that will ensure that the code finds the top 5 rather than just the top 1 session in popularity. Furthermore, I spent time adding some debugging 
lines to fix another logic error that I believe was arising due to code structure and sequence. Currently, the code runs, but the logic of the ranking has not been fully fixed. I was able to get the program
to run properly once, but I am currently working on making the ranking method more compatible with the rest of my code by adding the ranked popularities to another ArrayList that other parts of the code can access 
when creating the schedule for the day.


Journal Entry 5 (3/27):
Today, I finally fixed the bug in the popularity ranking code. I was mistakenly adding the ranked popularity for each slot to the overall list to early (before the popularity had been ranked).
Furthermore, I had unnecessary loops that were no needed in the program and I went through an removed those to improve code efficiency.
I also made an update in my apporach to the project. Rather than only ranking the top 5 in each slot, I generated the ranked popularity of all the session in a slot. I did this
because I wanted to account for the case in which a session has already been used twice, so I can't use the same session again. If this happens, I can iterate to the next most popular session and include that instead.
I then went on to start to organize the schedule to generate a schedule (currently does not adhere to the 2 times a session can repeat rules, this will be done tomorrow). I also added a methodolgoy to calculate conflicts and currently have 115 conflicts based ont he scedhuel that does not follow all project rules.

Journal Entry 6 (3/28):
Today, I altered my assignStudents method and sort methods mainly to ensure they comply with the rules (max 16 per session and max 2 times a session can be repeated). 
The code does run now. I am also eable to generate individual student schedules and overall session rosters. I have one error that is arising with student 68 in particular.
This student seems to only have 4 assigned sessions (even though my code requires that the length of a student's schedule must be at least 5). Furthermore, this student 
appears in two places at the same time as per my output logs that contian the roster for each session. I am not yet completely sure as to why the error is arising.
I will continue to sort through my code and identify where this error is arising from. The code currently is able to create a schedule, assign the students correctly (with the exception of student 68) and print out
the rosters of each session. I also updated my session objects to make a function called getNumStudents() and a separte method called getStudents() which return the student objects rather than the number
of student in a session to allow for more informational printing of session rosters and more student accessbility from the session object.
