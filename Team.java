import java.util.ArrayList;

public class Team implements Comparable<Team>{
    private String name;
    private ArrayList<Student> members = new ArrayList<>(); 
    private Tournament tournament;
    private int goalsScored;
    private int goalsReceived;
    private int points;            // this varibale represent the current round if eliminatinon
    
    // for individual tournaments
    public Team(Tournament t,Student s){
        this(t,s.getName());
        
        // fail message
    }
    // another constructor (can be used for both tournaments)
    public Team(Tournament tournament,String name){
        this.name = name;
        this.tournament = tournament;
    }
    // this method checkes if a student is in a team.
    public boolean isInTeam(Student student) {
        if (members == null)
            return false;
        for (Student s : members) 
            if(student.getId().contains(s.getId())) return true;
        return false;
    }
    //another version using id
    public boolean isInTeam(String id) {
        if (members == null)
            return false;
        for (Student s : members) 
            if(id.contains(s.getId())) return true;
        return false;
    }

    // getters
    public int getGoalsReceived() {
        return goalsReceived;
    }
    public int getGoalsScored() {
        return goalsScored;
    }
    public int getPoints() {
        return points;
    }
    public String getName() {
        return name;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public ArrayList<Student> getMembers() {
        return members;
    }






    public void addStudent(Student stu) throws Exception{
        if(stu.participateIn(tournament)){
            throw new Exception("the student is already participated in the tournament "+tournament.toString());
            
        }
        members.add(stu);
        stu.addTeam(this);
    }
    public void deleteStudent(Student stu) throws Exception{
        if (this.members.contains(stu)){
            this.members.remove(stu);
            stu.removeTeam(this);
        }else throw new Exception("the student is not in the team");
        
    }


    // team setters
    public void addGoalsReceived(int goalsReceived) {
        this.goalsReceived += goalsReceived;
    }
    public void addGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }
    public void addPoints(int points) {
        this.points += points;
    }





    

    public int compareTo(Team o) {
        return this.points - o.points;
    }

    @Override
    public String toString(){
        return name;
    }
}
