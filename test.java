import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

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
        Elimination t = new Elimination("Elimination", true, "Tennis", d);
        //add teams
        for(int i = 0; i < 7; i++){
            t.addTeam(new Team(t, "Team" + i));
        }
        //generate matches

        t.generateMatches(2);
       
        
        //print matches
        ArrayList<Match> matches = t.getMatches();
        printBinaryTree(matches);

    }
    
}
