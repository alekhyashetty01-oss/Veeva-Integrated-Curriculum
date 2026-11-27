package leaderboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        if (firstLine == null || firstLine.trim().isEmpty()) {
            return;
        }

        int n = Integer.parseInt(firstLine.trim());
        List<Participant> participants = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new Strin
            gTokenizer(br.readLine());
            String username = st.nextToken();
            int problemsSolved = Integer.parseInt(st.nextToken());
            int penaltyTime = Integer.parseInt(st.nextToken());
            int rating = Integer.parseInt(st.nextToken());

            participants.add(new Participant(username, problemsSolved, penaltyTime, rating));
        }

        LeaderboardRanker.rank(participants);

        StringBuilder out = new StringBuilder();
        for (Participant p : participants) {
            out.append(p).append('\n');
        }
        System.out.print("output\n");
        System.out.print(out);
    }
}