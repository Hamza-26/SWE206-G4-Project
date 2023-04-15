import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Elimination extends Tournament{
    // attributes
    private ArrayList<Match> matches;
    private ArrayList<Team> teamsCopy;

    public Elimination(String name, boolean isIndividual, String sport, Date startDate) {
        super(name, isIndividual, sport, startDate);
        matches = new ArrayList<Match>();
    }

    //getters
    public ArrayList<Match> getMatches() {
        return matches;
    }


    // a method to generate matches, 
    public void generateMatches(int restDays) throws Exception {
        //validate the input
        if (restDays < 0) {
            throw new Exception("Rest days cannot be negative");
        }
        if (matches.size() > 0) {
            throw new Exception("Matches have already been generated");
        }
        if (getTeams().size() < 2) {
            throw new Exception("Not enough teams to generate matches");
        }
        if (getHasFinished()) {
            throw new Exception("Tournament has finished");
        }

        //copy the teams, so that the original teams will not be changed
        teamsCopy = new ArrayList<Team>(getTeams());

        //shuffling the teams, to make the matches random
        Collections.shuffle(teamsCopy);

        // get necessary information about the tree
        Date date;
        int leafs = (getNumberOfTeams() +1 ) / 2; 
        int height = (int) Math.ceil(Math.log(leafs) / Math.log(2));
        
        //fill the tree, starting from the top, stop before the leafs
        //for the dates, match date = (start date) + (restdate) * (height - current height)
        int currentHeight = 0;
        int stop = (int) Math.pow(2, height) - 1;
        for (int i = 0; i < stop; i++) {
            
            date = new Date(getStartDate().getTime() + restDays * (height - currentHeight) * 24 * 60 * 60 * 1000);            
            matches.add(new Match(this, date));

            if (i == Math.pow(2, currentHeight + 1) - 2) {
                currentHeight++;
            }
        }

        //fill the leafs, starting from the left, each leaf add the start date and one team only
        int Maxi = (int) Math.pow(2, height);
        for(int i = 0; i < Maxi; i++) {
            matches.add(new Match(this, getStartDate(), teamsCopy.get(i)));
        }
        
        //fill the rest of the matches, starting from the first leaf, each match add one team only
        for (int i = 0; i < Maxi && i + Maxi < teamsCopy.size(); i++) {
            matches.get(i + stop).setTeam2(teamsCopy.get(i +  Maxi));
        }

    }


    public void viewStanding() {   
    }
    
}