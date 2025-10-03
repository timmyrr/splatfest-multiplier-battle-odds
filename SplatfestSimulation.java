import java.util.ArrayList;
import java.util.Random;

public class SplatfestSimulation {
    public static void main(String[] args) {
        // Define lists
        ArrayList<Integer> games_per_100x = new ArrayList<>();
        ArrayList<Double> team_a_shell_average = new ArrayList<>();
        ArrayList<Double> team_b_shell_average = new ArrayList<>();

        // Define variables

        // Customize simulation
        double shell_value = 0.0025; 
        int desired_333x_matches = 50;

        // Trigger counts
        int trigger_count = 0;
        int _100x_matches = 0;
        int _333x_matches = 0;
        float _333x_avg_per_100x = 0;
        float _100x_percent = 0;
        float _333x_percent = 0;
        float average_matches_per_100x = 0;
        float average_team_shells_per_100x = 0;
        float random_a_check;
        float random_b_check;

        // Team A
        // int team_a_players = 4;
        int team_a_triggers = 0;
        double team_a_shells = 0;

        // Team B
        // int team_b_players = 4;
        int team_b_triggers = 0;
        double team_b_shells = 0;

        // Match count variables
        int match_number = 1;
        int total_matches = 0;

        Random rand = new Random();

        // Simulation
        while (_333x_matches < desired_333x_matches) {
            // Prepare next game generation
            random_a_check = (float) rand.nextDouble();
            random_b_check = (float) rand.nextDouble();

            // These values are adding the number of players per team (4) to the average number of shells gained per match
            // Because a 10x battle is a 1/12 chance, and we assume we will win 50% of those
            // Thus an average of 1 full shell gained per person, per 24 games played
            team_a_shells += (4.0 / 24.0);
            team_b_shells += (4.0 / 24.0);

            // Ensure shell count doesn't exceed maximum of 28
            if (team_a_shells > 28) {
                team_a_shells = 28;
            }
            if (team_b_shells > 28) {
                team_b_shells = 28;
            }

            // Check if Team A triggered 100x
            if (random_a_check < (team_a_shells * shell_value)) {
                team_a_triggers += 1;
                // Prepare Team B for a 333x check by setting their shells to maximum
                team_b_shells = 28;
                // Check if Team B also triggered a 100x, causing a 333x
                if (random_b_check < (team_b_shells * shell_value)) {
                    team_b_triggers += 1;
                    _333x_matches += 1;
                }

                // Save key trigger data
                games_per_100x.add(match_number);
                team_a_shell_average.add(team_a_shells);
                team_b_shell_average.add(team_b_shells);

                // Reset shells and match counts
                team_a_shells = 0;
                team_b_shells = 0;
                match_number = 0;
            } else {
                if (random_b_check < (team_b_shells * shell_value)) {
                    team_b_triggers += 1;
                    // Prepare Team A for a 333x check by setting their shells to maximum
                    team_a_shells = 28;
                    // Check if Team A also triggered a 100x, causing a 333x
                    if (random_a_check < (team_a_shells * shell_value)) {
                        team_a_triggers += 1;
                        _333x_matches += 1;
                    }

                    // Save key trigger data
                    games_per_100x.add(match_number);
                    team_a_shell_average.add(team_a_shells);
                    team_b_shell_average.add(team_b_shells);

                    // Reset shells and match counts
                    team_a_shells = 0;
                    team_b_shells = 0;
                    match_number = 1;
                } else {
                    // This point is reached when neither team triggered a 100x
                    match_number += 1;
                }
            }
            total_matches += 1;
        }

        // After our desired number of triggered 333x battles is hit, we come here
        // We now calculate the averages
        for (int i = 0; i < games_per_100x.size(); i++) {
            trigger_count += 1;
            average_matches_per_100x += games_per_100x.get(i);
        }

        trigger_count = 0;

        for (int i = 0; i < team_a_shell_average.size(); i++) {
            trigger_count += 1;
            average_team_shells_per_100x += team_a_shell_average.get(i);
        }

        // Turn them into percents
        average_matches_per_100x = average_matches_per_100x / (games_per_100x.size() - _333x_matches);
        average_team_shells_per_100x = average_team_shells_per_100x / team_a_shell_average.size();
        _333x_avg_per_100x = ((float) trigger_count - (float) _333x_matches) / (float) _333x_matches;
        _100x_matches = trigger_count - _333x_matches;
        _100x_percent = (_100x_matches / (float) total_matches) * 100;
        _333x_percent = (_333x_matches / (float) total_matches) * 100;

        // Print results in a table-like format
        System.out.println("===== Splatfest Simulation Results =====");
        System.out.printf("%-25s %10s%n", "Metric", "Value");
        System.out.println("-----------------------------------------");
        System.out.printf("%-25s %10d%n", "Total Matches", total_matches);
        System.out.printf("%-25s %10d%n", "Team A Triggers 100x", team_a_triggers);
        System.out.printf("%-25s %10d%n", "Team B Triggers 100x", team_b_triggers);
        System.out.printf("%-25s %10d%n", "100x Matches", _100x_matches);
        System.out.printf("%-25s %10d%n", "333x Matches", _333x_matches);
        System.out.printf("%-25s %10.3f%n", "100x per 333x", _333x_avg_per_100x);
        System.out.printf("%-25s %10.3f%%%n", "100x %", _100x_percent);
        System.out.printf("%-25s %10.3f%%%n", "333x %", _333x_percent);
        System.out.printf("%-25s %10.3f%n", "Avg Matches/100x", average_matches_per_100x);
        System.out.printf("%-25s %10.3f%n", "Avg Shells Team A", average_team_shells_per_100x);
        System.out.println("========================================");
    }

}