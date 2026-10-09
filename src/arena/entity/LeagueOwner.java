/*
 * LeagueOwner.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chủ giải đấu (actor khởi tạo use case AnnounceTournament).
 *
 * <p>Một {@code LeagueOwner} thuộc đúng một thể hiện {@link Arena} và
 * sở hữu một hoặc nhiều {@link League} (Hình 5-29).</p>
 *
 * @author Tri
 * @version 1.0
 */
public class LeagueOwner extends User {

    /** Các giải (league) do chủ giải này quản lý. */
    private final List<League> leagues;

    /**
     * Khởi tạo một chủ giải đấu.
     *
     * @param name    tên chủ giải
     * @param contact thông tin liên lạc
     */
    public LeagueOwner(String name, String contact) {
        super(name, contact);
        this.leagues = new ArrayList<>();
    }

    /**
     * Gắn một league vào chủ giải. Được gọi bởi constructor của
     * {@link League}, do đó chỉ có phạm vi package.
     *
     * @param league league cần gắn
     */
    void addLeague(League league) {
        leagues.add(league);
    }

    /**
     * Trả về danh sách league (chỉ đọc) của chủ giải.
     *
     * @return danh sách league
     */
    public List<League> getLeagues() {
        return Collections.unmodifiableList(leagues);
    }
}
