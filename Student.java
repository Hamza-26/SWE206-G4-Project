import java.util.ArrayList;

public class Student{
    private String name;
    private String id;
    private ArrayList<Team> allTeams = new ArrayList<>();
    public Student(String name,String id){
        this.name = name;
        this.id = id;
    }

    // getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
    // returns true if the student is enrolled in t
    public boolean participateIn(Tournament tournament){
        for(Team t : allTeams)
            if(t.getTournament().equals(tournament)) return true;
        return false;
    }

    public void addTeam(Team t){
        this.allTeams.add(t);
    }    

}