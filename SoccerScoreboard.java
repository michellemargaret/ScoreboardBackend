
/**
 * This class represents a soccer scoreboard.
 *
 * @author Michelle Gallant
 * @version 1.0.1
 */
public class SoccerScoreboard extends Scoreboard {
    // **************************************************
    // Private variables 
    // **************************************************
    private int gameLength;
    private int halfLength;
    private int minutesStartHalf;
    private int minutesStartSecondHalf;
    private int minutesEndGame;

    // **************************************************
    // Constructors
    // **************************************************
    /**
    * Constructor.
    *
    * Initialize with length of game, length of half time,
    * @param gameLength The length of a game in minutes.
    * @param halfLength The length of halftime in minutes.
    * @param team1 String representation of team 1 name
    * @param team2 String representation of team 2 name
    */
    public SoccerScoreboard(int gameLength, int halfLength, String team1, String team2) {
        super("Soccer", team1, team2);
        this.gameLength = gameLength;
        this.halfLength = halfLength;
    }  

    /**
    * Constructor.
    *
    * Initialize with default times
    */
    public SoccerScoreboard(String team1, String team2) {
        this(90, 15, team1, team2);
    }  

    /**
    * Constructor.
    *
    * Initialize with default teams
    */
    public SoccerScoreboard(int gameLength, int halfLength) {
        this(gameLength, halfLength, "Team 1", "Team 2");
    }

    /**
    * Constructor.
    *
    * Initialize with default times and team names
    */
    public SoccerScoreboard() {
        this(90, 15, "Team 1", "Team 2");
    }

    
        
    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Start a new game, sets gameisactive to true and initializes score
     * 
     */    
    public void StartGame() {
        super.StartGame();
        
        // Determine when half time will start,
        // when second half will start, and when game will end
        // so these don't need to be calculated each time current
        // time is updated
        this.minutesStartHalf = this.gameLength / 2;
        this.minutesStartSecondHalf = this.minutesStartHalf + this.halfLength;
        this.minutesEndGame = this.gameLength + this.halfLength;
    }

    /**
     * Returns elapsed time since start of the game
     * 
     * @return int[] with two values - first value for minutes, second value for seconds
     */
    public int[] CurrentTime() {
        int[] currentTime = super.CurrentTime();
        int currentMinute = currentTime[0];

        // Determine what point the game is at, and calculate
        // clock time accordingly
        // TODO: Accomodate extra time
        if (currentMinute >= this.minutesEndGame) {
            // Stoppage time or game over
            currentTime[0] = this.gameLength;                
        } else if (currentMinute >= this.minutesStartSecondHalf) {
            // Currently in second half, remove half time length from elapsed time
            currentTime[0] = currentMinute - this.halfLength;
        } else if (currentMinute >= minutesStartHalf) {
            // Currently in half time, clock should display half of game length
            currentTime[0] = this.gameLength / 2;
        } else {
            // Currently in first half, elapsed time is correct time to display,
            // no adjustment needed
        }
        
        return currentTime;
    }


}