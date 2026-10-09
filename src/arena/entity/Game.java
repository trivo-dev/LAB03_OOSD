/*
 * Game.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

/**
 * Lớp trừu tượng biểu diễn một trò chơi được hỗ trợ trên ARENA.
 *
 * <p>Được xác định qua chuyên biệt hóa (Hình 5-30); các lớp con cụ thể
 * như {@link TicTacToe} và {@link Chess} chứa luật chơi tương ứng.</p>
 *
 * @author Tri
 * @version 1.0
 */
public abstract class Game {

    /**
     * Trả về tên trò chơi.
     *
     * @return tên trò chơi
     */
    public abstract String getName();

    /**
     * Trả về mô tả ngắn gọn luật chơi.
     *
     * @return mô tả luật chơi
     */
    public abstract String describeRules();

    /**
     * Trả về tên trò chơi.
     *
     * @return tên trò chơi
     */
    @Override
    public String toString() {
        return getName();
    }
}
