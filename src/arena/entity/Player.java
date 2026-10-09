/*
 * Player.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

/**
 * Người chơi tham gia các {@link Match} trong một {@link Tournament}.
 *
 * <p>Người chơi có thể là thành viên của các {@link InterestGroup} để
 * nhận thông báo về các giải đấu mới.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class Player extends User {

    /**
     * Khởi tạo một người chơi.
     *
     * @param name    tên người chơi
     * @param contact thông tin liên lạc
     */
    public Player(String name, String contact) {
        super(name, contact);
    }
}
