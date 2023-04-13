# SWE206-G4-Project

## how we imagine the project:


so you have three main sceens the default one (for not logged in user), the one for admin, and the one for student
for the default one you can see the upcoming, current, and prev tournaments.
for the student and admin main sceen you get all the actions available to you in buttons each one indicating some use case
how should the system behave is clear in the use case description. 

## what each class do:

Admin class:  clear

Student class: store student data such as the teams he participated in

Team class: can represent a participant of an individual or a team and a team is created for a single tournament
Note: the variable points in elimination tournaments represent the round as mentioned in the comment of the code

Tournament class: abstract class for tournaments. Have basic methods and variables

RoundRobin class: rounRobin tournaments functinalities

Elimination class: represent the elimination tournaments 

Match class: represent a match of two teams also clear

if u didnt get something you may check the implementaion of the class or check the class diagram for more understanding

## Task List: 

- [x] create classes
- [x] add attributes, getters
- [ ] create adders methods (team.add(stu), tournament.add(team),...)
- [ ] add setters to variables that might need e.g. 'setTournamentRegistration(open = true)'
- [ ] create checkers methods including equals methods (student.isparticipant(tournament), tournament.equals(),...)
- [ ] create team comparetor i.e. complete compareTo() method
- [ ] create generate matches for eleminiation
- [ ] create generate matches for roundRobin
- [ ] create modifyScore and recordScore in Match class
- [ ] create Main class that extends Application from javaFx
- [ ] create GUI stu profiles
- [ ] create GUI calender for dates
- [ ] create the DB or whatever to store our data in
- [ ] implement the authenticatorfunction/method
- [ ] create rounRobin GUI tournament
- [ ] create elimination GUI bracket 
- [ ] creat Match scene GUI for Admin
- [ ] create match scene GUI for stu
- [ ] create tournament scene for admin
- [ ] create tournament scene for stu
- [ ] create admin main scene
- [ ] create stu main scene
- [ ] creat the default scene 'previous,current,upcoming competitions'
- [ ] creat login scene
- [ ] create GUI buttons
- [ ] add buttons actions 
- [ ] set action for the system
- [ ] IDK might add more tasks

## Important Notes:

- dont forget to add comments
- make you code clear and state clearly the variable names even if they were long (can be ignored for getters, setters,..)
- remember to check the implementaion is correct before you mark the task as done
