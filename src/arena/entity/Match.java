/*
 * Match.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

/**
 * Một trận đấu giữa hai {@link Player} trong một {@link Tournament}.
 *
 * @author Tri
 * @version 1.0
 */
public class Match {

    /** Giải đấu chứa trận này. */
    private final Tournament tournament;

    /** Người chơi thứ nhất. */
    private final Player firstPlayer;

    /** Người chơi thứ hai. */
    private final Player secondPlayer;

    /**
     * Khởi tạo một trận đấu.
     *
     * @param tournament   giải đấu chứa trận
     * @param firstPlayer  người chơi thứ nhất
     * @param secondPlayer người chơi thứ hai
     */
    public Match(Tournament tournament, Player firstPlayer,
            Player secondPlayer) {
        this.tournament = tournament;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    /**
     * Trả về giải đấu chứa trận.
     *
     * @return giải đấu
     */
    public Tournament getTournament() {
        return tournament;
    }

    /**
     * Trả về người chơi thứ nhất.
     *
     * @return người chơi thứ nhất
     */
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    /**
     * Trả về người chơi thứ hai.
     *
     * @return người chơi thứ hai
     */
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    /**
     * Trả về chuỗi mô tả trận đấu.
     *
     * @return chuỗi dạng {@code A vs B}
     */
    @Override
    public String toString() {
        return firstPlayer.getName() + " vs " + secondPlayer.getName();
    }
}
