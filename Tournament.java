import java.util.Date;
import java.util.ArrayList;

abstract public class Tournament {
    private String name;
    private boolean isIndividual;
    private String sport;
    private ArrayList<Team> teams;
    private Date startDate;
    private Date endDate;

    public boolean getIsIndividual(){
        return isIndividual;
    }
    public String getName() {
        return name;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public String getSport() {
        return sport;
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }

    public abstract void generateMatches(int restDays);
    public abstract void viewStanding();
    public void addTeam(Team t){
        // not done yet
    }


}
