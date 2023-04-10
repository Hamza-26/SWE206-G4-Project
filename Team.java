import java.util.ArrayList;

public class Team implements Comparable<Team>{
    private String name;
    private ArrayList<Student> members; 
    private Tournament tournament;
    private int goalsScored;
    private int goalsReceived;
    private int points;            // this varibale represent the current round if eliminatinon
    

    public Team(Tournament tournament,String name){
        this.tournament = tournament;
        this.name = name;
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
}
