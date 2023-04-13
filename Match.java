import java.util.Date;

public class Match {
    private Tournament tournament;
    private Team team1;
    private Team team2;
    private int goals1;
    private int goals2;
    private Date date;
    public Match(Tournament t, Team a, Team b, Date d){
        tournament  = t;
        team1 = a;
        team2 = b;
        date = d;
    }


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




    public void recordScore(int a,int b){
        goals1 = a; goals2 = b;
    }

}
