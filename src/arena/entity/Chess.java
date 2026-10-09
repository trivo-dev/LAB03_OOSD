/*
 * Chess.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

/**
 * Trò chơi cờ vua.
 *
 * @author Tri
 * @version 1.0
 */
public class Chess extends Game {

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Chess";
    }

    /** {@inheritDoc} */
    @Override
    public String describeRules() {
        return "Hai người chơi trên bàn 8x8; "
                + "chiếu hết vua đối phương để thắng.";
    }
}
