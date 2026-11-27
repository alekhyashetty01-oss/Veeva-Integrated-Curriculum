package leaderboard;

import java.util.Comparator;
import java.util.List;

public class LeaderboardRanker {

    private static final Comparator<Participant> CONTEST_RULES = (a, b) -> {
        if (a.getProblemsSolved() != b.getProblemsSolved()) {
            return Integer.compare(b.getProblemsSolved(), a.getProblemsSolved());
        }
        if (a.getPenaltyTime() != b.getPenaltyTime()) {
            return Integer.compare(a.getPenaltyTime(), b.getPenaltyTime());
        }
        if (a.getRating() != b.getRating()) {
            return Integer.compare(b.getRating(), a.getRating());
        }
        return a.getUsername().compareTo(b.getUsername());
    };

    public static void rank(List<Participant> participants) {
        participants.sort(CONTEST_RULES);
    }
}
