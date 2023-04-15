import java.util.ArrayList;

public class Team implements Comparable<Team>{
    private String name;
    private ArrayList<Student> members; 
    private Tournament tournament;
    private int goalsScored;
    private int goalsReceived;
    private int points;            // this varibale represent the current round if eliminatinon
    
    // for individual tournaments
    public Team(Tournament t,Student s){
        this.tournament = t;
        if (this.tournament.getIsIndividual()) this.name = s.getName();
        // fail message
    }
    // another constructor (can be used for both tournaments)
    public Team(Tournament tournament,String name){
        this.name = name;
        this.tournament = tournament;
    }

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






    public void addStudent(Student stu){
        if(stu.participateIn(tournament)){
            // show message 
            return;
        }
        members.add(stu);
    }


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
