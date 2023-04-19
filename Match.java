import java.util.Date;

public class Match {
    private Tournament tournament;
    private Team team1;
    private Team team2;
    private int goals1;
    private int goals2;
    private Date date;
    private boolean finshed = false;

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
    public boolean gethasFinshed() {
        return finshed;
    }
    public Team getWinner() {
        //if the match is not finished, return null
        if (!finshed) {
            return null;
        }
        //if the match is a draw, return null
        if (goals1 == goals2) {
            return null;
        }
        //if team1 wins, return team1
        if (goals1 > goals2) {
            return team1;
        }
        //if team2 wins, return team2
        return team2;
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
    
    // a method to record the score of the match (We can use this method to edit the score as well as record it)
    public void recordScore(int a, int b) throws Exception{
        //if there is no team1 or team2, throw an exception
        if (team1 == null || team2 == null) {
            throw new Exception("There must be two teams");
        }
        //if tournament has finished, throw an exception
        if (tournament.getHasFinished()) {
            throw new Exception("Tournament has finished");
        }
        //check if the score is valid
        if (a < 0 || b < 0) {
            throw new Exception("Goals cannot be negative");
        }

        //if the match has already been played, Go to editScore method
        if (finshed) {
            editScore(a, b);
        }
        //if the match has not been played, record the score, and update the teams' points if it is a round robin tournament or update table if it is an elimination tournament
        else {
            //record the score
            goals1 = a;
            goals2 = b;
            finshed = true;
            //check if the match is a round robin match
            if (tournament instanceof RoundRobin) {
                //check if the match is a draw
                if (a == b) {
                    team1.addPoints(1);
                    team2.addPoints(1);
                }
                //check if team1 wins
                else if (a > b) {
                    team1.addPoints(3);
                    
                }
                //check if team2 wins
                else {
                    team2.addPoints(3);
                }
            }
            //if the match is an elimination match, update the table
            else {
                //check if the match is a draw, return exception
                if (a == b) {
                    throw new Exception("There must be a winner");
                }
                //check if team1 wins
                else if (a > b) {
                    team1.addPoints(1);
                }
                //check if team2 wins
                else {
                    team2.addPoints(1);
                }
                
                //update the table
                //cast tournament to an elimination tournament
                ((Elimination) tournament).updateBracket(this);
            }
        }

    }
    // a method to edit the score of the match
    private void editScore(int a, int b) throws Exception {
        // if it is a round robin tournament, update the points of the teams
        //remove the old points
        if (tournament instanceof RoundRobin) {
            //check if the match is a draw
            if (goals1 == goals2) {
                team1.addPoints(-1);
                team2.addPoints(-1);
            }
            //check if team1 wins
            else if (goals1 > goals2) {
                team1.addPoints(-3);
                
            }
            //check if team2 wins
            else {
                team2.addPoints(-3);
            }
            //record the new result
            finshed = false;
            recordScore(a, b);
        }
        //if it is an elimination tournament, update the table
        else {
            //check if the match is a draw, return exception
            if (a == b) {
                throw new Exception("There must be a winner");
            }
            //check if team1 wins
            else if (a > b) {
                team1.addPoints(-1);
            }
            //check if team2 wins
            else {
                team2.addPoints(-1);
            }

            //edit the bracket first
            ((Elimination) tournament).editBracket(this);

            //record the new result
            finshed = false;
            recordScore(a, b);   
        }
    }

}
