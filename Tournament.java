import java.util.Date;
import java.util.ArrayList;

abstract public class Tournament {
    // attributes
    private String name;
    private boolean isIndividual;
    private String sport;
    private ArrayList<Team> teams;
    private Date startDate;
    private Date endDate;
    private boolean hasFinished;
    private Team winner;

    // constructor
    public Tournament(String name, boolean isIndividual, String sport, Date startDate, Date endDate) {
        this.name = name;
        this.isIndividual = isIndividual;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = new ArrayList<Team>();
        this.hasFinished = false;
    }
    // another constructor without endDate; atomaticlly set by the system
    public Tournament(String name, boolean isIndividual, String sport, Date startDate) {
        this.name = name;
        this.isIndividual = isIndividual;
        this.sport = sport;
        this.startDate = startDate;
        this.teams = new ArrayList<Team>();
        this.hasFinished = false;
    }

    // Getters 
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
    public int getNumberOfTeams() {
        return teams.size();
    }
    public boolean getHasFinished() {
        return hasFinished;
    }
    public Team getWinner() {
        return winner;
    }

    // Setters
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }
    public void setWinner(Team winner) {
        this.winner = winner;
    }

    // abstract methods
    public abstract void generateMatches(int restDays) throws Exception;
    public abstract void viewStanding();

    // non-abstract methods
    public void addTeam(Team t){
        teams.add(t);
    }

}
