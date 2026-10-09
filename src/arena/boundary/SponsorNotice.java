/*
 * SponsorNotice.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.entity.Advertiser;
import arena.entity.Tournament;

/**
 * Thông báo quyết định tài trợ gửi tới một nhà quảng cáo đã phản hồi.
 *
 * <p>Đối tượng biên này được phát hiện khi vẽ biểu đồ tuần tự Hình
 * 5-28, do use case ban đầu chưa nêu cách báo cho nhà tài trợ.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class SponsorNotice {

    /** Nhà quảng cáo nhận thông báo. */
    private final Advertiser advertiser;

    /** Giải đấu liên quan. */
    private final Tournament tournament;

    /** Nhà quảng cáo có được chọn hay không. */
    private final boolean selected;

    /**
     * Khởi tạo thông báo.
     *
     * @param advertiser nhà quảng cáo nhận thông báo
     * @param tournament giải đấu liên quan
     * @param selected   {@code true} nếu nhà quảng cáo được chọn
     */
    public SponsorNotice(Advertiser advertiser, Tournament tournament,
            boolean selected) {
        this.advertiser = advertiser;
        this.tournament = tournament;
        this.selected = selected;
    }

    /**
     * Gửi thông báo tới nhà quảng cáo.
     */
    public void notifyAdvertiser() {
        String message = selected
                ? "Bạn được chọn làm nhà tài trợ độc quyền giải '"
                        + tournament.getName() + "'."
                : "Rất tiếc, bạn không được chọn tài trợ giải '"
                        + tournament.getName() + "'.";
        advertiser.receiveMessage(message);
        System.out.println("[SponsorNotice] -> " + advertiser + ": "
                + message);
    }
}
