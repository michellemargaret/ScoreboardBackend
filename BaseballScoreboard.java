
/**
 * This class represents a baseball scoreboard.
 *
 * @author Michelle Gallant
 * @version 1.0.1
 */
public class BaseballScoreboard extends Scoreboard {
    // **************************************************
    // Private variables 
    // **************************************************
    private int numInnings;
    private int currentBalls;
    private int currentStrikes;
    private int currentOuts;
    private int currentInning;
    private boolean topOfInning;
    private String currentBattingTeam;

    // **************************************************
    // Constructors
    // **************************************************
    /**
    * Constructor.
    *
    * Initialize with number of innings
    * @param numInnings The number of innings in a game
    * @param team1 String representation of team 1 name
    * @param team2 String representation of team 2 name
    */
    public BaseballScoreboard(int numInnings, String team1, String team2) {
        super("Baseball", team1, team2);
        this.numInnings = numInnings;
    }  

    /**
    * Constructor.
    *
    * Initialize with default innings
    */
    public BaseballScoreboard(String team1, String team2) {
        this(9, team1, team2);
    }  

    /**
    * Constructor.
    *
    * Initialize with default teams
    */
    public BaseballScoreboard(int numInnings) {
        this(numInnings, "Team 1", "Team 2");
    }

    /**
    * Constructor.
    *
    * Initialize with default innings and team names
    */
    public BaseballScoreboard() {
        this(9, "Team 1", "Team 2");
    }

    
    public void SetCurrentBattingTeam(String currentBattingTeam) {
        if ((teams[0].equals(currentBattingTeam)) || (teams[1].equals(currentBattingTeam))) {
            this.currentBattingTeam = currentBattingTeam;
        } else {
            // Throw error?
        }
    }

    public String GetCurrentBattingTeam() {
        return this.currentBattingTeam;
    }
        
    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Start a new game, sets gameisactive to true and starts clock
     * 
     */    
    public void StartGame() {
        super.StartGame();
        
        // Initialize new game
        this.ResetBatCount();
        this.currentOuts = 0;
        this.currentInning = 1;
        this.topOfInning = true;
    }

    public void ResetBatCount() {
        this.currentBalls = 0;
        this.currentStrikes = 0;
    }

    public void NewBatter() {
        ResetBatCount();
    }

    /**
     * 
     * Updates counts based on the call on a pitch
     * 
     * @param inCall The call on the pitch - BALL, STRIKE, FOUL, HIT
     * 
     */
    public void Pitch(String inCall) {
        inCall = inCall.toUpperCase();

        switch (inCall) {
            case "BALL":
                this.currentBalls++;
                if (this.currentBalls == 4) {
                    this.NewBatter();
                }
                break;
            case "STRIKE":
                this.currentStrikes++;
                if (this.currentStrikes == 3) {
                    this.AddOut();
                    this.NewBatter();
                }
                break;
            case "FOUL":
                if (this.currentStrikes < 2) {
                    this.currentStrikes++;
                }                
                break;
            case "HIT":
                this.NewBatter();
                break;
            default:
        }
    }

    public void AddPoint() {
        super.AddPoint(this.currentBattingTeam);
    }

    public void AddOut() {
        this.currentOuts++;

        if (this.currentOuts >= 3) {
            this.SwitchBattingTeam();
        }         
    }

    public void SwitchBattingTeam() {
        if (this.currentBattingTeam.equals(teams[0])) {
            this.currentBattingTeam = teams[1];
        } else {
            this.currentBattingTeam = teams[0];
        }

        if (this.topOfInning) {
            // Move to bottom of current inning
            this.topOfInning = false;
        } else {
            // Currently in bottom of inning, move to top of next inning
            this.topOfInning = true;
            this.currentInning++;

            if (this.currentInning > this.numInnings) {
                this.EndGame();
            }
        }

        this.currentOuts = 0;
        this.NewBatter();
    }

    /**
     * Returns string representation of scoreboard data
     * 
     * @return String representation of scoreboard data
     */
    public String DisplayScoreboard() {
        String score;

        if (this.score[1] > this.score[0]) {
            score = String.format("----      %s: %d \n----      %s: %d", this.teams[1], this.score[1], this.teams[0], this.score[0]);
        } else {
            score = String.format("----      %s: %d \n----      %s: %d", this.teams[0], this.score[0], this.teams[1], this.score[1]);
        }

        return "-----------------------------------\n" +
        "----\n----   Score\n" + score + "\n----\n" +
        "----    Inning: " + this.currentInning + "\n" +
        "----    Strikes: " + this.currentStrikes + 
        " Balls: " + this.currentBalls + 
        " Outs: " + this.currentOuts + "\n----\n" +
        "----   Now batting: " + this.currentBattingTeam + "\n----" +
        "-----------------------------------";
    }
}