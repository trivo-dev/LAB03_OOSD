/*
 * RoundRobinStyle.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Thể thức vòng tròn: mỗi người chơi gặp tất cả người chơi khác đúng
 * một lần.
 *
 * @author Tri
 * @version 1.0
 */
public class RoundRobinStyle extends TournamentStyle {

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "RoundRobin";
    }

    /** {@inheritDoc} */
    @Override
    public List<Match> planMatches(Tournament tournament,
            List<Player> players) {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                matches.add(new Match(tournament, players.get(i),
                        players.get(j)));
            }
        }
        return matches;
    }
}
