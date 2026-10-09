/*
 * SponsorshipRequest.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.control.AnnounceTournamentControl;
import arena.entity.Advertiser;
import arena.entity.Tournament;

/**
 * Lời mời tài trợ gửi tới một {@link Advertiser} (Hình 5-27).
 *
 * <p>Trong cài đặt giả lập, {@link #notifySponsor()} gửi thông báo vào
 * hộp thư nhà quảng cáo rồi lấy quyết định giả lập của họ và gọi
 * {@link #reply(boolean)}.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class SponsorshipRequest {

    /** Đối tượng điều khiển đã tạo lời mời. */
    private final AnnounceTournamentControl control;

    /** Giải đấu cần tài trợ. */
    private final Tournament tournament;

    /** Nhà quảng cáo được mời. */
    private final Advertiser advertiser;

    /** Phản hồi nhận được ({@code null} nếu chưa phản hồi). */
    private SponsorshipReply reply;

    /**
     * Khởi tạo lời mời tài trợ.
     *
     * @param control    đối tượng điều khiển
     * @param tournament giải đấu cần tài trợ
     * @param advertiser nhà quảng cáo được mời
     */
    public SponsorshipRequest(AnnounceTournamentControl control,
            Tournament tournament, Advertiser advertiser) {
        this.control = control;
        this.tournament = tournament;
        this.advertiser = advertiser;
    }

    /**
     * Gửi lời mời tới nhà quảng cáo và mô phỏng việc họ trả lời.
     */
    public void notifySponsor() {
        String message = "Mời tài trợ độc quyền giải đấu '"
                + tournament.getName() + "'.";
        advertiser.receiveMessage(message);
        System.out.println("[SponsorshipRequest] -> " + advertiser
                + ": " + message);
        reply(advertiser.decideSponsorship(tournament));
    }

    /**
     * Ghi nhận câu trả lời của nhà quảng cáo, tạo
     * {@link SponsorshipReply} và chuyển cho đối tượng điều khiển.
     *
     * @param accepted {@code true} nếu nhà quảng cáo đồng ý
     * @return phản hồi vừa tạo
     * @throws IllegalStateException nếu lời mời đã được trả lời
     */
    public SponsorshipReply reply(boolean accepted) {
        if (reply != null) {
            throw new IllegalStateException("Lời mời đã được trả lời.");
        }
        reply = new SponsorshipReply(advertiser,
                tournament.getLeague().getOwner(), tournament, accepted);
        control.receiveSponsorshipReply(reply);
        return reply;
    }

    /**
     * Trả về nhà quảng cáo được mời.
     *
     * @return nhà quảng cáo
     */
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    /**
     * Trả về phản hồi đã nhận.
     *
     * @return phản hồi, hoặc {@code null} nếu chưa có
     */
    public SponsorshipReply getReply() {
        return reply;
    }
}
