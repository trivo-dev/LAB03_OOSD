/*
 * RequestSponsorshipForm.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.control.AnnounceTournamentControl;

/**
 * Biểu mẫu để chủ giải yêu cầu tìm nhà tài trợ độc quyền (Hình 5-27).
 *
 * @author Tri
 * @version 1.0
 */
public class RequestSponsorshipForm {

    /** Đối tượng điều khiển của use case. */
    private final AnnounceTournamentControl control;

    /**
     * Khởi tạo biểu mẫu.
     *
     * @param control đối tượng điều khiển
     */
    public RequestSponsorshipForm(AnnounceTournamentControl control) {
        this.control = control;
    }

    /**
     * Yêu cầu gửi lời mời tài trợ độc quyền tới các nhà quảng cáo quan
     * tâm.
     *
     * @return số lời mời đã gửi
     */
    public int requestExclusiveSponsor() {
        System.out.println("[RequestSponsorshipForm] Yêu cầu tìm nhà "
                + "tài trợ độc quyền.");
        return control.requestExclusiveSponsor();
    }
}
