/*
 * Tournament.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Một giải đấu thuộc một {@link League}.
 *
 * <p>Thuộc tính {@code sponsor} và thao tác {@link #setSponsorship}
 * được phát hiện khi phân tích luồng tài trợ (Hình 5-27).</p>
 *
 * @author Tri
 * @version 1.0
 */
public class Tournament {

    /** League chứa giải đấu. */
    private final League league;

    /** Tên giải đấu. */
    private String name;

    /** Số người chơi tối đa. */
    private int maxPlayers;

    /** Nhà tài trợ độc quyền ({@code null} nếu chưa có). */
    private Advertiser sponsor;

    /** Người chơi đã đăng ký. */
    private final List<Player> players;

    /** Các trận đấu đã lập. */
    private final List<Match> matches;

    /**
     * Khởi tạo một giải đấu. Chỉ {@link League} được phép tạo giải đấu.
     *
     * @param league     league chứa giải đấu
     * @param name       tên giải đấu
     * @param maxPlayers số người chơi tối đa (lớn hơn 1)
     * @throws IllegalArgumentException nếu tham số không hợp lệ
     */
    Tournament(League league, String name, int maxPlayers) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tên giải đấu rỗng.");
        }
        if (maxPlayers < 2) {
            throw new IllegalArgumentException(
                    "Số người chơi tối đa phải >= 2.");
        }
        this.league = league;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    /**
     * Gán nhà tài trợ độc quyền cho giải đấu.
     *
     * @param advertiser nhà tài trợ được chọn
     */
    public void setSponsorship(Advertiser advertiser) {
        this.sponsor = advertiser;
    }

    /**
     * Đăng ký một người chơi vào giải đấu.
     *
     * @param player người chơi
     * @return {@code true} nếu đăng ký thành công, {@code false} nếu
     *         giải đã đủ người hoặc người chơi đã đăng ký
     */
    public boolean addPlayer(Player player) {
        if (players.size() >= maxPlayers || players.contains(player)) {
            return false;
        }
        players.add(player);
        return true;
    }

    /**
     * Lập lịch thi đấu theo thể thức của league.
     *
     * @return danh sách trận đấu (chỉ đọc)
     */
    public List<Match> planMatches() {
        matches.clear();
        matches.addAll(league.getStyle().planMatches(this, players));
        return getMatches();
    }

    /**
     * Trả về league chứa giải đấu.
     *
     * @return league
     */
    public League getLeague() {
        return league;
    }

    /**
     * Trả về tên giải đấu.
     *
     * @return tên giải đấu
     */
    public String getName() {
        return name;
    }

    /**
     * Đổi tên giải đấu.
     *
     * @param name tên mới
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Trả về số người chơi tối đa.
     *
     * @return số người chơi tối đa
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Cập nhật số người chơi tối đa.
     *
     * @param maxPlayers giá trị mới
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Trả về nhà tài trợ độc quyền.
     *
     * @return nhà tài trợ, hoặc {@code null} nếu chưa có
     */
    public Advertiser getSponsor() {
        return sponsor;
    }

    /**
     * Trả về danh sách người chơi (chỉ đọc).
     *
     * @return danh sách người chơi
     */
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Trả về danh sách trận đấu (chỉ đọc).
     *
     * @return danh sách trận đấu
     */
    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    /**
     * Trả về chuỗi mô tả giải đấu.
     *
     * @return chuỗi mô tả
     */
    @Override
    public String toString() {
        return name + " [" + league.getGame() + ", "
                + league.getStyle() + ", tối đa " + maxPlayers
                + " người chơi]";
    }
}
