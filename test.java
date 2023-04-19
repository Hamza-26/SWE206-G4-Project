import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class test {
    public static void printBinaryTree(ArrayList<Match> tree) {
        int n = tree.size();
        int maxLevel = (int) (Math.log(n) / Math.log(2)) + 1;
    
        int index = 0;
        for (int level = 1; level <= maxLevel; level++) {
            int levelNodes = (int) Math.pow(2, level - 1);
            int padding = (int) Math.pow(2, maxLevel - level) - 1;
    
            for (int i = 0; i < levelNodes; i++) {
                if (index >= n) {
                    break;
                }
    
                // Add padding spaces before the node value
                for (int j = 0; j < padding; j++) {
                    System.out.print(" ");
                }
    
                System.out.print(tree.get(index).getTeam1() + " vs " + tree.get(index).getTeam2() + " ");
    
                // Add padding spaces after the node value
                for (int j = 0; j < padding; j++) {
                    System.out.print(" ");
                }
    
                index++;
            }
    
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        //test elimination tournament
        System.out.println("Test elimination tournament");
        //create elimation tournament
        Date d = new GregorianCalendar(2020, 0, 1).getTime();
        Date d2 = new GregorianCalendar(2020, 0, 2).getTime();
        Elimination t = new Elimination("Elimination", true, "Tennis", d);
        //add teams
      for(int i = 0; i < 5; i++){
            t.addTeam(new Team(t, "Team" + i));
        }
        //generate matches

        t.generateMatches(2);
       
        
        //print matches
        ArrayList<Match> matches = t.getMatches();
        printBinaryTree(matches);
        //TESTING
        //take input from user, for recording the scores, show the binary tree after each record
        while(!t.getHasFinished() && t.getWinner() == null){
            //take input
            System.out.println("Enter the match number and the score of the first team, second team, each separated by a space");
            Scanner sc = new Scanner(System.in);
            int matchNumber = sc.nextInt();
            int score1 = sc.nextInt();
            int score2 = sc.nextInt();
            //record the score
            matches.get(matchNumber).recordScore(score1, score2);
            //print the tree
            printBinaryTree(matches);
        }
        System.out.println("The winner is " + t.getWinner().getName());

        //print points and goals
        System.out.println("Points:");
        for(Team team : t.getTeams()){
            System.out.println(team.getName() + ": " + team.getPoints());
        }
        System.out.println("Goals:");
        //for match in matches
        for(Match match : matches){
            System.out.println(match.getTeam1().getName() + " vs " + match.getTeam2().getName() + ": " + match.getGoals1() + " - " + match.getGoals2());
        }


    }
    
    
}
