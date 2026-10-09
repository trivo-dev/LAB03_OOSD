/*
 * AnnounceTournamentControl.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.control;

import arena.boundary.InterestGroupNotice;
import arena.boundary.SponsorNotice;
import arena.boundary.SponsorshipReply;
import arena.boundary.SponsorshipRequest;
import arena.entity.Advertiser;
import arena.entity.Arena;
import arena.entity.InterestGroup;
import arena.entity.League;
import arena.entity.LeagueOwner;
import arena.entity.Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Đối tượng điều khiển của use case AnnounceTournament.
 *
 * <p>Đóng vai trò "keo dán" giữa đối tượng biên và đối tượng thực thể:
 * điều phối trình tự tạo giải đấu (Hình 5-26), quy trình tài trợ
 * (Hình 5-27) và thông báo nhóm quan tâm (Hình 5-28). Đối tượng này
 * cũng tạo ra các đối tượng biên {@link SponsorshipRequest},
 * {@link SponsorNotice} và {@link InterestGroupNotice}.</p>
 *
 * <p>Một thể hiện được tạo bởi {@code TournamentForm} khi chủ giải bắt
 * đầu use case và tồn tại trong suốt use case đó.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class AnnounceTournamentControl {

    /** Thể hiện ARENA hiện tại. */
    private final Arena arena;

    /** League chứa giải đấu được công bố. */
    private final League league;

    /** Giải đấu được tạo trong use case này. */
    private Tournament tournament;

    /** Các lời mời tài trợ đã gửi. */
    private final List<SponsorshipRequest> sponsorshipRequests;

    /** Các phản hồi tài trợ đã nhận. */
    private final List<SponsorshipReply> sponsorshipReplies;

    /**
     * Khởi tạo đối tượng điều khiển cho một league.
     *
     * @param arena  thể hiện ARENA
     * @param league league chứa giải đấu sắp được tạo
     */
    public AnnounceTournamentControl(Arena arena, League league) {
        this.arena = arena;
        this.league = league;
        this.sponsorshipRequests = new ArrayList<>();
        this.sponsorshipReplies = new ArrayList<>();
    }

    // ----------------------------------------------------------------
    // Hình 5-26: luồng tạo giải đấu
    // ----------------------------------------------------------------

    /**
     * Kiểm tra ARENA còn cho phép tạo thêm giải đấu không.
     *
     * @return {@code true} nếu còn tài nguyên
     */
    public boolean checkMaxTournament() {
        return arena.checkMaxTournament();
    }

    /**
     * Tạo giải đấu mới trong league.
     *
     * @param name       tên giải đấu
     * @param maxPlayers số người chơi tối đa
     * @return giải đấu vừa tạo
     * @throws IllegalStateException nếu đã tạo giải đấu trong use case
     *         này hoặc ARENA đã đạt giới hạn số giải đấu
     */
    public Tournament createTournament(String name, int maxPlayers) {
        if (tournament != null) {
            throw new IllegalStateException("Giải đấu đã được tạo.");
        }
        if (!arena.checkMaxTournament()) {
            throw new IllegalStateException(
                    "ARENA đã đạt số giải đấu tối đa.");
        }
        tournament = league.createTournament(name, maxPlayers);
        return tournament;
    }

    // ----------------------------------------------------------------
    // Hình 5-27: luồng tài trợ
    // ----------------------------------------------------------------

    /**
     * Gửi lời mời tài trợ độc quyền tới mọi nhà quảng cáo quan tâm.
     *
     * @return số lời mời đã gửi
     * @throws IllegalStateException nếu chưa tạo giải đấu
     */
    public int requestExclusiveSponsor() {
        requireTournament();
        List<Advertiser> interested =
                arena.findInterestedExclusiveSponsors();
        for (Advertiser advertiser : interested) {
            confirmSponsorInterest(advertiser);
        }
        return interested.size();
    }

    /**
     * Tạo và gửi một lời mời tài trợ tới một nhà quảng cáo
     * (thông điệp {@code confirmSponsorInterest()} trong Hình 5-27).
     *
     * @param advertiser nhà quảng cáo được mời
     */
    private void confirmSponsorInterest(Advertiser advertiser) {
        SponsorshipRequest request =
                new SponsorshipRequest(this, tournament, advertiser);
        sponsorshipRequests.add(request);
        request.notifySponsor();
    }

    /**
     * Ghi nhận phản hồi của nhà quảng cáo và báo cho chủ giải. Được gọi
     * bởi {@link SponsorshipRequest} sau khi nhà quảng cáo trả lời.
     *
     * @param reply phản hồi tài trợ
     */
    public void receiveSponsorshipReply(SponsorshipReply reply) {
        sponsorshipReplies.add(reply);
        reply.notifyLeagueOwner();
    }

    /**
     * Trả về danh sách nhà quảng cáo đã đồng ý tài trợ.
     *
     * @return danh sách nhà tài trợ tiềm năng
     */
    public List<Advertiser> getAcceptingSponsors() {
        List<Advertiser> result = new ArrayList<>();
        for (SponsorshipReply reply : sponsorshipReplies) {
            if (reply.isAccepted()) {
                result.add(reply.getAdvertiser());
            }
        }
        return result;
    }

    /**
     * Gán nhà tài trợ độc quyền cho giải đấu và trừ phí tài trợ vào tài
     * khoản của nhà quảng cáo.
     *
     * @param sponsor nhà tài trợ được chọn
     * @throws IllegalStateException    nếu chưa tạo giải đấu
     * @throws IllegalArgumentException nếu nhà quảng cáo chưa đồng ý
     *         tài trợ
     */
    public void setSponsorship(Advertiser sponsor) {
        requireTournament();
        if (!getAcceptingSponsors().contains(sponsor)) {
            throw new IllegalArgumentException(sponsor.getName()
                    + " chưa đồng ý tài trợ giải đấu này.");
        }
        tournament.setSponsorship(sponsor);
        sponsor.getAccount().charge(arena.getSponsorshipFee(),
                "Tài trợ độc quyền " + tournament.getName());
    }

    // ----------------------------------------------------------------
    // Hình 5-28: luồng thông báo
    // ----------------------------------------------------------------

    /**
     * Thông báo quyết định tài trợ tới mọi nhà quảng cáo đã phản hồi.
     * Bước này được bổ sung vào use case khi phân tích Hình 5-28.
     *
     * @throws IllegalStateException nếu chưa tạo giải đấu
     */
    public void notifySponsorsOfDecision() {
        requireTournament();
        Advertiser selected = tournament.getSponsor();
        for (SponsorshipReply reply : sponsorshipReplies) {
            Advertiser advertiser = reply.getAdvertiser();
            SponsorNotice notice = new SponsorNotice(advertiser,
                    tournament, advertiser.equals(selected));
            notice.notifyAdvertiser();
        }
    }

    /**
     * Thông báo giải đấu mới tới các nhóm quan tâm liên quan.
     *
     * @return số nhóm đã được thông báo
     * @throws IllegalStateException nếu chưa tạo giải đấu
     */
    public int notifyInterestGroups() {
        requireTournament();
        int count = 0;
        for (InterestGroup group : arena.getInterestGroups()) {
            if (group.isInterestedIn(tournament)) {
                new InterestGroupNotice(group, tournament).notifyPlayers();
                count++;
            }
        }
        return count;
    }

    // ----------------------------------------------------------------
    // Truy vấn
    // ----------------------------------------------------------------

    /**
     * Trả về chủ giải đấu khởi tạo use case.
     *
     * @return chủ giải đấu
     */
    public LeagueOwner getLeagueOwner() {
        return league.getOwner();
    }

    /**
     * Trả về giải đấu được tạo.
     *
     * @return giải đấu, hoặc {@code null} nếu chưa tạo
     */
    public Tournament getTournament() {
        return tournament;
    }

    /**
     * Trả về các lời mời tài trợ đã gửi (chỉ đọc).
     *
     * @return danh sách lời mời
     */
    public List<SponsorshipRequest> getSponsorshipRequests() {
        return Collections.unmodifiableList(sponsorshipRequests);
    }

    /**
     * Trả về các phản hồi tài trợ đã nhận (chỉ đọc).
     *
     * @return danh sách phản hồi
     */
    public List<SponsorshipReply> getSponsorshipReplies() {
        return Collections.unmodifiableList(sponsorshipReplies);
    }

    /**
     * Bảo đảm giải đấu đã được tạo trước khi thực hiện các bước sau.
     *
     * @throws IllegalStateException nếu chưa tạo giải đấu
     */
    private void requireTournament() {
        if (tournament == null) {
            throw new IllegalStateException("Chưa tạo giải đấu.");
        }
    }
}
