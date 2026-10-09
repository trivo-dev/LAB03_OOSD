/*
 * SponsorshipReply.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.boundary;

import arena.entity.Advertiser;
import arena.entity.LeagueOwner;
import arena.entity.Tournament;

/**
 * Phản hồi của nhà quảng cáo đối với lời mời tài trợ, được chuyển tới
 * {@link LeagueOwner} (Hình 5-27).
 *
 * @author Tri
 * @version 1.0
 */
public class SponsorshipReply {

    /** Nhà quảng cáo phản hồi. */
    private final Advertiser advertiser;

    /** Chủ giải nhận phản hồi. */
    private final LeagueOwner leagueOwner;

    /** Giải đấu liên quan. */
    private final Tournament tournament;

    /** Nhà quảng cáo có đồng ý tài trợ hay không. */
    private final boolean accepted;

    /**
     * Khởi tạo phản hồi tài trợ.
     *
     * @param advertiser  nhà quảng cáo phản hồi
     * @param leagueOwner chủ giải nhận phản hồi
     * @param tournament  giải đấu liên quan
     * @param accepted    {@code true} nếu đồng ý
     */
    public SponsorshipReply(Advertiser advertiser, LeagueOwner leagueOwner,
            Tournament tournament, boolean accepted) {
        this.advertiser = advertiser;
        this.leagueOwner = leagueOwner;
        this.tournament = tournament;
        this.accepted = accepted;
    }

    /**
     * Thông báo phản hồi tới chủ giải.
     */
    public void notifyLeagueOwner() {
        String message = advertiser.getName()
                + (accepted ? " ĐỒNG Ý" : " TỪ CHỐI")
                + " tài trợ giải '" + tournament.getName() + "'.";
        leagueOwner.receiveMessage(message);
        System.out.println("[SponsorshipReply] -> " + leagueOwner
                + ": " + message);
    }

    /**
     * Trả về nhà quảng cáo phản hồi.
     *
     * @return nhà quảng cáo
     */
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    /**
     * Cho biết nhà quảng cáo có đồng ý tài trợ không.
     *
     * @return {@code true} nếu đồng ý
     */
    public boolean isAccepted() {
        return accepted;
    }
}
