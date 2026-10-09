/*
 * NotifyInterestGroupsForm.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.control.AnnounceTournamentControl;

/**
 * Biểu mẫu để chủ giải yêu cầu thông báo giải đấu mới tới các nhóm quan
 * tâm (Hình 5-28).
 *
 * @author Tri
 * @version 1.0
 */
public class NotifyInterestGroupsForm {

    /** Đối tượng điều khiển của use case. */
    private final AnnounceTournamentControl control;

    /**
     * Khởi tạo biểu mẫu.
     *
     * @param control đối tượng điều khiển
     */
    public NotifyInterestGroupsForm(AnnounceTournamentControl control) {
        this.control = control;
    }

    /**
     * Yêu cầu thông báo giải đấu tới các nhóm quan tâm.
     *
     * @return số nhóm đã được thông báo
     */
    public int notifyInterestGroups() {
        System.out.println("[NotifyInterestGroupsForm] Thông báo tới "
                + "các nhóm quan tâm.");
        return control.notifyInterestGroups();
    }
}
