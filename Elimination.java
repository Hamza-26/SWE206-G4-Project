import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Elimination extends Tournament{
    // attributes
    private ArrayList<Match> matches;

    // constructor
    public Elimination(String name, boolean isIndividual, String sport, Date startDate, Date endDate) {
        super(name, isIndividual, sport, startDate, endDate);
        matches = new ArrayList<Match>();
    }
    // another constructor without endDate; atomaticlly set by the system
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
        ArrayList<Team> teamsCopy = new ArrayList<Team>(getTeams());

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

        //set the end date if it is not set
        if (getEndDate() == null) {
            setEndDate(new Date(getStartDate().getTime() + restDays * (height + 1) * 24 * 60 * 60 * 1000));
        }
        //check if the final date is before or on the same date as the end date or raise an error
        if (matches.get(0).getDate().compareTo(getEndDate()) > 0) {
            throw new Exception("The tournament cannot be finished in the given time, please reduce the number of rest days");
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

        //for the leafs, if there is a match with only one team, then the team will go to the next round
        for (int i = stop; i < Maxi + stop; i++) {
            if (matches.get(i).getTeam2() == null) {
                //move the team to the next round, team 1 in the next match if the current match is even, team 2 if the current match is odd
                if ((i - stop) % 2 == 0) {
                    matches.get((i - 1) / 2).setTeam1(matches.get(i).getTeam1());
                } else {
                    matches.get((i - 1 ) / 2).setTeam2(matches.get(i).getTeam1());
                }
            }
        }
    }

    // a method to update the matches, in case of a winnner result, the winner will go to the next round
    public void updateBracket(Match match){
        //get the index of the match
        int index = matches.indexOf(match);
        //if the match is the final match, the winner will be the winner of the tournament
        if (index == 0) {
            setWinner(match.getWinner());
            return;
        }
        //if the match is even, the winner will go to the next match, team 1
        if ((index - matches.size())% 2 == 0) 
            matches.get((index - 1) / 2).setTeam1(match.getWinner());
        //if the match is odd, the winner will go to the next match, team 2
         else 
            matches.get((index - 1) / 2).setTeam2(match.getWinner());

        System.out.println("next round" + matches.get((index - 1) / 2).getTeam1() + " " + matches.get((index - 1) / 2).getTeam2());
        
        }


    // a method to remove the affected team from the matches above the given match, in case of a editied match
    public void editBracket(Match match){
        //get the index of the match
        int index = matches.indexOf(match);
        //if the match is even, the team 1 will be removed
        if ((matches.size() - index) % 2 == 0) {
            matches.get((index-1) / 2).setTeam1(null);
        //if the match is odd, the team 2 will be removed
        } else {
            matches.get((index-1) / 2).setTeam2(null);
        }
        //if the match is just under the root, no need to call the helper method
        if ((index-1)/2 != 0){
        helper((((index-1) / 2) - 1) / 2);
        }

    }
    // a helper method to remove the affected team from the matches above the given match, in case of a editied match
    private void helper(int i) {
        //make a new match, keep the date and tournament only
        Match match = new Match(this, matches.get(i).getDate());
        //replace the match with the new match
        matches.set(i, match);
        //call the helper method again if the index is greater than 0
        if (i > 0){
            helper((i-1) / 2);
        }
        
    }

    public void viewStanding() {   
    }
    
}