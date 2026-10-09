/*
 * TournamentStyle.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.List;

/**
 * Lớp trừu tượng biểu diễn thể thức tổ chức giải đấu.
 *
 * <p>Các lớp con cụ thể ({@link KnockOutStyle}, {@link RoundRobinStyle})
 * cung cấp thuật toán xếp {@link Player} vào các {@link Match}
 * (Hình 5-30).</p>
 *
 * @author Tri
 * @version 1.0
 */
public abstract class TournamentStyle {

    /**
     * Trả về tên thể thức.
     *
     * @return tên thể thức
     */
    public abstract String getName();

    /**
     * Lập danh sách trận đấu (vòng đầu) cho các người chơi đã đăng ký.
     *
     * @param tournament giải đấu chứa các trận
     * @param players    danh sách người chơi
     * @return danh sách trận đấu được lập
     */
    public abstract List<Match> planMatches(Tournament tournament,
            List<Player> players);

    /**
     * Trả về tên thể thức.
     *
     * @return tên thể thức
     */
    @Override
    public String toString() {
        return getName();
    }
}
