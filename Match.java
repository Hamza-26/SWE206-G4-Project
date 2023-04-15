import java.util.Date;

public class Match {
    private Tournament tournament;
    private Team team1;
    private Team team2;
    private int goals1;
    private int goals2;
    private Date date;

    //Match constructor
    public Match(Tournament t, Team a, Team b, Date d){
        tournament  = t;
        team1 = a;
        team2 = b;
        date = d;
    }
    //another constructors with 1/without Teams
    public Match(Tournament t, Date d, Team a){
        tournament  = t;
        date = d;
        team1 = a;
    }
    public Match(Tournament t, Date d){
        tournament  = t;
        date = d;
    }


    //Getters
    public Date getDate() {
        return date;
    }
    public int getGoals1() {
        return goals1;
    }
    public int getGoals2() {
        return goals2;
    }
    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }public Tournament getTournament() {
        return tournament;
    }

    //Setters
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    

    public void recordScore(int a,int b){
        goals1 = a; goals2 = b;
    }

}
