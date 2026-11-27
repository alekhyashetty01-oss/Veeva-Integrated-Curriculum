package leaderboard;

public class Participant {
    private final String username;
    private final int problemsSolved;
    private final int penaltyTime;
    private final int rating;

    public Participant(String username, int problemsSolved, int penaltyTime, int rating) {
        this.username = username;
        this.problemsSolved = problemsSolved;
        this.penaltyTime = penaltyTime;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public int getProblemsSolved() {
        return problemsSolved;
    }

    public int getPenaltyTime() {
        return penaltyTime;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return username + " " + problemsSolved + " " + penaltyTime + " " + rating;
    }
}