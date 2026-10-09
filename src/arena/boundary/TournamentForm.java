/*
 * TournamentForm.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.control.AnnounceTournamentControl;
import arena.entity.Arena;
import arena.entity.League;
import arena.entity.Tournament;

/**
 * Biểu mẫu để chủ giải khởi tạo use case và nhập thông tin giải đấu
 * (Hình 5-26).
 *
 * <p>Trình tự: {@link #newTournament(League)} → {@link #setName(String)}
 * → {@link #setMaxPlayers(int)} → {@link #commit()}.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class TournamentForm {

    /** Thể hiện ARENA hiện tại. */
    private final Arena arena;

    /** Đối tượng điều khiển được tạo khi bắt đầu use case. */
    private AnnounceTournamentControl control;

    /** Tên giải đấu do chủ giải nhập. */
    private String name;

    /** Số người chơi tối đa do chủ giải nhập. */
    private int maxPlayers;

    /**
     * Khởi tạo biểu mẫu.
     *
     * @param arena thể hiện ARENA
     */
    public TournamentForm(Arena arena) {
        this.arena = arena;
    }

    /**
     * Bắt đầu tạo giải đấu mới: tạo đối tượng điều khiển và kiểm tra
     * giới hạn số giải đấu.
     *
     * @param league league chứa giải đấu
     * @return đối tượng điều khiển của use case
     * @throws IllegalStateException nếu ARENA đã hết tài nguyên
     */
    public AnnounceTournamentControl newTournament(League league) {
        control = new AnnounceTournamentControl(arena, league);
        if (!control.checkMaxTournament()) {
            throw new IllegalStateException(
                    "Không thể tạo thêm giải đấu: đã đạt giới hạn.");
        }
        System.out.println("[TournamentForm] Bắt đầu tạo giải đấu cho "
                + "league " + league.getName());
        return control;
    }

    /**
     * Nhập tên giải đấu.
     *
     * @param name tên giải đấu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Nhập số người chơi tối đa.
     *
     * @param maxPlayers số người chơi tối đa
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Xác nhận thông tin và yêu cầu đối tượng điều khiển tạo giải đấu.
     *
     * @return giải đấu vừa tạo
     * @throws IllegalStateException nếu chưa gọi
     *         {@link #newTournament(League)}
     */
    public Tournament commit() {
        if (control == null) {
            throw new IllegalStateException("Chưa gọi newTournament().");
        }
        Tournament tournament = control.createTournament(name, maxPlayers);
        System.out.println("[TournamentForm] Đã tạo: " + tournament);
        return tournament;
    }
}
