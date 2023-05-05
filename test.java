import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

//test
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
        // test api
        System.out.println("Test api");
        signInApi();

        // test elimination tournament
        System.out.println("Test elimination tournament");
        // create elimation tournament
        Date d = new GregorianCalendar(2020, 0, 1).getTime();
        Date d2 = new GregorianCalendar(2020, 0, 2).getTime();
        Elimination t = new Elimination("Elimination", true, "Tennis", d);
        // add teams
        for (int i = 0; i < 5; i++) {
            t.addTeam(new Team(t, "Team" + i));
        }
        // generate matches

        t.generateMatches(2);

        // print matches
        ArrayList<Match> matches = t.getMatches();
        printBinaryTree(matches);
        // TESTING
        // take input from user, for recording the scores, show the binary tree after
        // each record
        while (!t.getHasFinished() && t.getWinner() == null) {
            // take input
            System.out.println(
                    "Enter the match number and the score of the first team, second team, each separated by a space");
            Scanner sc = new Scanner(System.in);
            int matchNumber = sc.nextInt();
            int score1 = sc.nextInt();
            int score2 = sc.nextInt();
            // record the score
            matches.get(matchNumber).recordScore(score1, score2);
            // print the tree
            printBinaryTree(matches);
        }
        System.out.println("The winner is " + t.getWinner().getName());

        // print points and goals
        System.out.println("Points:");
        for (Team team : t.getTeams()) {
            System.out.println(team.getName() + ": " + team.getPoints());
        }
        System.out.println("Goals:");
        // for match in matches
        for (Match match : matches) {
            System.out.println(match.getTeam1().getName() + " vs " + match.getTeam2().getName() + ": "
                    + match.getGoals1() + " - " + match.getGoals2());
        }

    }

    public static void signInApi() throws Exception {
        // end point: https://us-central1-swe206-221.cloudfunctions.net/app
        // path: /UserSignIn
        // method: GET
        // params: username, password
        // return: 200 if success, 400 if missing params, 403 if wrong username or
        // password

        // take input from user
        System.out.println("Enter username and password, each separated by a space");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        String password = sc.next();

        // api call
        URL url = new URL("https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + username
                + "&password=" + password);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        // get response code
        int status = con.getResponseCode();

        // if success, print "success" and show the user info
        if (status == 200) {
            System.out.println("success");
            // get response body
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // print response body
            System.out.println(content.toString());
            in.close();
        }
        // if missing params, print "missing params"
        else if (status == 400) {
            System.out.println("missing params");
        }
        // if wrong username or password, print "wrong username or password"
        else if (status == 403) {
            System.out.println("wrong username or password");
        }
        // if other error, print "error"
        else {
            System.out.println("something went wrong");
        }
    }

}
