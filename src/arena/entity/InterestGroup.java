/*
 * InterestGroup.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nhóm quan tâm: tập hợp người dùng muốn được thông báo về các giải
 * đấu mới của một {@link Game}.
 *
 * @author Tri
 * @version 1.0
 */
public class InterestGroup {

    /** Tên nhóm. */
    private final String name;

    /** Trò chơi mà nhóm quan tâm. */
    private final Game game;

    /** Các thành viên của nhóm. */
    private final List<User> members;

    /**
     * Khởi tạo một nhóm quan tâm.
     *
     * @param name tên nhóm
     * @param game trò chơi nhóm quan tâm
     */
    public InterestGroup(String name, Game game) {
        this.name = name;
        this.game = game;
        this.members = new ArrayList<>();
    }

    /**
     * Thêm thành viên vào nhóm.
     *
     * @param user người dùng cần thêm
     */
    public void addMember(User user) {
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    /**
     * Cho biết nhóm có quan tâm tới giải đấu đã cho hay không, tức là
     * giải đấu có cùng trò chơi với nhóm.
     *
     * @param tournament giải đấu cần kiểm tra
     * @return {@code true} nếu nhóm quan tâm
     */
    public boolean isInterestedIn(Tournament tournament) {
        return game.getClass()
                .equals(tournament.getLeague().getGame().getClass());
    }

    /**
     * Trả về tên nhóm.
     *
     * @return tên nhóm
     */
    public String getName() {
        return name;
    }

    /**
     * Trả về trò chơi nhóm quan tâm.
     *
     * @return trò chơi
     */
    public Game getGame() {
        return game;
    }

    /**
     * Trả về danh sách thành viên (chỉ đọc).
     *
     * @return danh sách thành viên
     */
    public List<User> getMembers() {
        return Collections.unmodifiableList(members);
    }
}
