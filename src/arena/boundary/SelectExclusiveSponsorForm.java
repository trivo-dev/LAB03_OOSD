/*
 * SelectExclusiveSponsorForm.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.control.AnnounceTournamentControl;
import arena.entity.Advertiser;

import java.util.List;

/**
 * Biểu mẫu để chủ giải chọn nhà tài trợ độc quyền trong số các nhà
 * quảng cáo đã đồng ý (Hình 5-27, 5-31).
 *
 * <p>Sau khi chọn, biểu mẫu yêu cầu đối tượng điều khiển gửi
 * {@link SponsorNotice} tới mọi nhà quảng cáo đã phản hồi.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class SelectExclusiveSponsorForm {

    /** Đối tượng điều khiển của use case. */
    private final AnnounceTournamentControl control;

    /**
     * Khởi tạo biểu mẫu.
     *
     * @param control đối tượng điều khiển
     */
    public SelectExclusiveSponsorForm(AnnounceTournamentControl control) {
        this.control = control;
    }

    /**
     * Hiển thị danh sách nhà quảng cáo đã đồng ý tài trợ.
     *
     * @return danh sách nhà tài trợ có thể chọn
     */
    public List<Advertiser> displayCandidates() {
        List<Advertiser> candidates = control.getAcceptingSponsors();
        System.out.println("[SelectExclusiveSponsorForm] Ứng viên: "
                + candidates);
        return candidates;
    }

    /**
     * Chọn nhà tài trợ độc quyền và thông báo quyết định.
     *
     * @param sponsor nhà tài trợ được chọn
     */
    public void selectSponsor(Advertiser sponsor) {
        System.out.println("[SelectExclusiveSponsorForm] Chọn "
                + sponsor.getName());
        control.setSponsorship(sponsor);
        control.notifySponsorsOfDecision();
    }
}
