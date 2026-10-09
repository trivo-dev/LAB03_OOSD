/*
 * TicTacToe.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

/**
 * Trò chơi cờ ca-rô 3x3 ("tic tac toe").
 *
 * @author Tri
 * @version 1.0
 */
public class TicTacToe extends Game {

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "TicTacToe";
    }

    /** {@inheritDoc} */
    @Override
    public String describeRules() {
        return "Hai người lần lượt đánh X/O trên bàn 3x3; "
                + "ai có 3 ô thẳng hàng trước sẽ thắng.";
    }
}
