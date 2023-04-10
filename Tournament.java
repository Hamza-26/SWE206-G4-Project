import java.sql.Date;
import java.util.ArrayList;

abstract public class Tournament {
    private String name;
    private boolean isIndividual;
    private String sport;
    private ArrayList<Team> teams;
    private Date startDate;
    private Date endDate;


    public abstract void generateMatches();

}
