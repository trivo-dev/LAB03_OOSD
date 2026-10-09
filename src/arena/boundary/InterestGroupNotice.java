/*
 * InterestGroupNotice.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.entity.InterestGroup;
import arena.entity.Tournament;
import arena.entity.User;

/**
 * Thông báo giải đấu mới gửi tới các thành viên của một
 * {@link InterestGroup} (Hình 5-28).
 *
 * @author Tri
 * @version 1.0
 */
public class InterestGroupNotice {

    /** Nhóm quan tâm nhận thông báo. */
    private final InterestGroup interestGroup;

    /** Giải đấu được công bố. */
    private final Tournament tournament;

    /**
     * Khởi tạo thông báo.
     *
     * @param interestGroup nhóm quan tâm
     * @param tournament    giải đấu được công bố
     */
    public InterestGroupNotice(InterestGroup interestGroup,
            Tournament tournament) {
        this.interestGroup = interestGroup;
        this.tournament = tournament;
    }

    /**
     * Gửi thông báo tới từng thành viên của nhóm (thông điệp
     * {@code notifyPlayer()} trong Hình 5-28).
     *
     * @return số thành viên đã được thông báo
     */
    public int notifyPlayers() {
        String sponsorText = tournament.getSponsor() == null
                ? "" : " - tài trợ: " + tournament.getSponsor().getName();
        String message = "Giải đấu mới: " + tournament + sponsorText;
        for (User member : interestGroup.getMembers()) {
            member.receiveMessage(message);
            System.out.println("[InterestGroupNotice] ("
                    + interestGroup.getName() + ") -> " + member
                    + ": " + message);
        }
        return interestGroup.getMembers().size();
    }
}
