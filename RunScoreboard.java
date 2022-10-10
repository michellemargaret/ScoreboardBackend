import java.util.Scanner;
public class RunScoreboard {

    public static String[] teams;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String currentSport;
        teams = new String[2];

        System.out.println("What sport are you watching? (B - baseball or S - soccer)");
        currentSport = input.next();


        System.out.println("What teams are playing? (Enter one team per line)");
        // TODO: Allow teams with spaces in their names
        teams[0] = input.next();
        teams[1] = input.next();

        if (currentSport.equals("B")) {
            playBaseball();
        } else if (currentSport.equals("S")) {
            playSoccer();
        } else {
            // TODO: Throw error
            System.out.println(String.format("%s not supported. Good bye.", currentSport));
        }
    }    

    private static void playBaseball() {

        Scanner input = new Scanner(System.in);
        BaseballScoreboard sb;
        int numInnings = 9;

        System.out.print("Enter number of innings (default 9): ");
        numInnings = input.nextInt();

        if (teams.length > 0) {
            sb = new BaseballScoreboard(numInnings, teams[0], teams[1]);
        } else {
            sb = new BaseballScoreboard(numInnings);
        }

        System.out.println();

        System.out.println("Game Setup Complete");
        System.out.println("Enter S when time to start.");

        // Wait for enter, then start game
        input.next();
        sb.StartGame();

        String nextOption;
        String[] teams = sb.GetTeams();

        System.out.println("******* GAME ON *********");

        System.out.println("");
        System.out.println("Who's batting first? 1: " + teams[0] + ", 2: " + teams[1]);
        sb.SetCurrentBattingTeam(teams[input.nextInt()-1]);

        while (sb.GameIsActive()) {

            System.out.println();
            System.out.println("     ----------");
            System.out.println("       Options:");
            System.out.println("          S: Strike, B: Ball, F: Foul, O: Out, H: Hit, R: Run, T: Switch Batting Team,");
            System.out.println("          D: Display Scoreboard, E: End Game");
            nextOption = input.next().toUpperCase();

            System.out.println("     Selected: " + nextOption);
            System.out.println();

            switch (nextOption) {
                case "S":
                    sb.Pitch("STRIKE");
                    break;
                case "B":
                    sb.Pitch("BALL");
                    break;
                case "F":
                    sb.Pitch("FOUL");
                    break;
                case "O": 
                    sb.AddOut();
                    break;
                case "H":
                    sb.Pitch("HIT");
                    break;
                case "R":
                    sb.AddPoint();
                    break;
                case "T":
                    sb.SwitchBattingTeam();
                    break;
                case "D":
                    // Scoreboard displayed everytime, no need to make another call to it here
                    break;
                case "E":
                    sb.EndGame();
                    System.out.println("Game over");
                    break;
            }

            System.out.println(sb.DisplayScoreboard());
        }
        System.out.println("------  GAME OVER   ------");
    }

    private static void playSoccer() {
        Scanner input = new Scanner(System.in);
        SoccerScoreboard sb;
        int gameLength = 90;
        int halfLength = 15;

        System.out.print("Enter number of minutes for game play (default 90): ");
        gameLength = input.nextInt();
        System.out.print("Enter number of minutes for half time (default 15): ");
        halfLength = input.nextInt();

        if (teams.length > 0) {
            sb = new SoccerScoreboard(gameLength, halfLength, teams[0], teams[1]);
        } else {
            sb = new SoccerScoreboard(gameLength, halfLength);
        }

        System.out.println();

        System.out.println("Game Setup Complete");
        System.out.println("Enter S when time to start.");

        // Wait for enter, then start game
        input.next();
        sb.StartGame();

        String nextOption;
        String[] teams = sb.GetTeams();

        System.out.println("******* GAME ON *********");

        while (sb.GameIsActive()) {
            System.out.println("");
            System.out.println("     ----------");
            System.out.println("       Options:");
            System.out.println("          G: Goal, T: Display Time, S: Display Score, E: End Game");
            System.out.println("     ----------");
            nextOption = input.next().toUpperCase();

            switch (nextOption) {
                case "G":
                    System.out.println("Who scored? 1: " + teams[0] + ", 2: " + teams[1]);
                    int goalBy = input.nextInt();
                    
                    if ((goalBy == 1) || (goalBy == 2)) {
                        sb.AddPoint(teams[goalBy-1]);
                        System.out.println("Point added for " + teams[goalBy-1]);
                        System.out.println("Current score: " + sb.GetScore());
                    } else {
                        System.out.println("Invalid entry. No update made.");
                    }
                    break;
                    case "T":
                        System.out.println("Current Time: " + sb.CurrentTimeString(sb.CurrentTime()));
                        break;
                    case "S":
                        System.out.println("Current Score: " + sb.GetScore());
                        break;
                case "E":
                    sb.EndGame();
                    System.out.println("Game over");
                    System.out.println("Final score: " + sb.GetScore());
                    break;
            }

        }
    }

}
