import java.util.Date;
import java.time.Clock;

/**
 * This class represents a generic scoreboard.
 * It is the foundaScoreboard.javation for scoreboards used for specific sports.
 *
 * @author Michelle Gallant
 * @version 1.0.1
 */
public abstract class Scoreboard {
    // **************************************************
    // Fields
    // **************************************************
    private String sport;
    protected int[] score = new int[2];
    protected long startTime;
    protected long endTime;
    protected String[] teams;
    protected boolean GameIsActive = false;


    
    // **************************************************
    // Constructors
    // **************************************************
    /**
    * Constructor.
    *
    * Initialize with name of the sport, set score to 0-0
    * @param sport The sport that scoreboard is being used for.
    * @param team1 String representation of team 1 name
    * @param team1 String representation of team 2 name
    */
    public Scoreboard(String sport, String team1, String team2) {
        this.sport = sport; 
        this.teams = new String[] {team1, team2};
    }

    
    
    // **************************************************
    // Constructors
    // **************************************************
    /**
    * Constructor.
    *
    * Initialize with name of the sport, set score to 0-0
    * Default names for teams
    * @param sport The sport that scoreboard is being used for.
    */
    public Scoreboard(String sport) {
        this(sport, "Team 1", "Team 2");
    }

    
    // **************************************************
    // Getters and settings
    // **************************************************

    public boolean GameIsActive() {
        return this.GameIsActive;
    }

    public String[] GetTeams() {
        return this.teams;
    }

        
    // **************************************************
    // Public methods
    // **************************************************

    public void setTeams(String team1, String team2) {
        if ((team1 != null) && (team1.length() > 0)) {
            this.teams[0] = team1;
        } else {
            this.teams[0] = "Team 1";
        }
        if ((team2 != null)  && (team2.length() > 0)) {
            this.teams[1] = team2;
        } else {
            this.teams[1] = "Team 2";
        }
    }

    /**
     * Start a new game, sets gameisactive to true and initializes score
     * 
     */
    public void StartGame() {
        this.GameIsActive = true;
        this.score[0] = 0;
        this.score[1] = 0;  
        this.startTime = System.currentTimeMillis();
    }

    
    /**
     * Returns elapsed time since start of the game
     * 
     * @return int[] with two values - first value for minutes, second value for seconds
     */
    public int[] CurrentTime() {
        int[] currentTime = new int[2];

        if (this.GameIsActive) {
            long elapsedTime = (System.currentTimeMillis() - this.startTime)/1000;
            
            currentTime[0] = (int) ((elapsedTime) / 60); // minutes
            currentTime[1] = (int) (elapsedTime % 60); // seconds
        }

        return currentTime;
    }

    
    /**
     * Returns string representation of the current time
     * 
     * @param currentTime[] two-value integer array, first value is minutes, second value is seconds
     * @return String representation of current time
     */
    public static String CurrentTimeString(int[] currentTime) {
        String minutes = Integer.toString(currentTime[0]);
        String seconds = Integer.toString(currentTime[1]);
        
        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }

        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }

        return minutes + ":" + seconds;
    }


    /**
     * End the current game, sets gameisactive to false
     * 
     */
    public void EndGame() {        
        this.GameIsActive = false;
    }

    /**
     * Returns score of current game
     * 
     * @return String representation of current score
     */
    public String GetScore() {
        if (this.score[1] > this.score[0]) {
            return String.format("%s %d to %d %s", this.teams[1], this.score[1], this.score[0], this.teams[0]);
        } else {
            return String.format("%s %d / %d %s", this.teams[0], this.score[0], this.score[1], this.teams[1]);
        }
    }
    

    /**
     * Returns score of current game
     * 
     * @return String representation of current score
     */
    public void AddPoint(String forTeam) {
        // TODO: Add ability to track stats on goals - who scored, what time
        if (this.teams[0].equals(forTeam)) {
            score[0]++;
        } else if (this.teams[1].equals(forTeam)) {
            score[1]++;
        } else {
            // TODO: Throw error
            System.out.println(forTeam + " isn't playing, but yay for them for scoring.");
        }
    }
    
}
