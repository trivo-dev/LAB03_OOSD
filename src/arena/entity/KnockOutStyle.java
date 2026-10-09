/*
 * KnockOutStyle.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Thể thức loại trực tiếp: người chơi phải thắng để ở lại giải.
 *
 * <p>Cài đặt giả lập chỉ xếp cặp cho vòng đầu tiên theo thứ tự đăng ký;
 * người chơi lẻ cuối danh sách được miễn thi đấu (bye).</p>
 *
 * @author Tri
 * @version 1.0
 */
public class KnockOutStyle extends TournamentStyle {

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "KnockOut";
    }

    /** {@inheritDoc} */
    @Override
    public List<Match> planMatches(Tournament tournament,
            List<Player> players) {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i + 1 < players.size(); i += 2) {
            matches.add(new Match(tournament, players.get(i),
                    players.get(i + 1)));
        }
        return matches;
    }
}
