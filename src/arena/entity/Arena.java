/*
 * Arena.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Đối tượng gốc biểu diễn một thể hiện (instantiation) của hệ thống
 * ARENA (Hình 5-29).
 *
 * <p>Thông qua {@code Arena} có thể truy vấn danh sách
 * {@link LeagueOwner}, {@link Advertiser}, {@link InterestGroup},
 * {@link Game} và {@link TournamentStyle}. Các đối tượng không được chia
 * sẻ giữa các thể hiện ARENA khác nhau.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class Arena {

    /** Số giải đấu tối đa được phép tồn tại đồng thời. */
    private final int maxTournaments;

    /** Phí tài trợ độc quyền cho một giải đấu. */
    private final double sponsorshipFee;

    /** Các chủ giải đấu. */
    private final List<LeagueOwner> leagueOwners;

    /** Các nhà quảng cáo. */
    private final List<Advertiser> advertisers;

    /** Các nhóm quan tâm. */
    private final List<InterestGroup> interestGroups;

    /** Các trò chơi được hỗ trợ. */
    private final List<Game> games;

    /** Các thể thức thi đấu được hỗ trợ. */
    private final List<TournamentStyle> tournamentStyles;

    /**
     * Khởi tạo một thể hiện ARENA.
     *
     * @param maxTournaments số giải đấu tối đa
     * @param sponsorshipFee phí tài trợ độc quyền
     */
    public Arena(int maxTournaments, double sponsorshipFee) {
        this.maxTournaments = maxTournaments;
        this.sponsorshipFee = sponsorshipFee;
        this.leagueOwners = new ArrayList<>();
        this.advertisers = new ArrayList<>();
        this.interestGroups = new ArrayList<>();
        this.games = new ArrayList<>();
        this.tournamentStyles = new ArrayList<>();
    }

    /**
     * Kiểm tra tài nguyên còn cho phép tạo thêm giải đấu hay không
     * (thông điệp {@code checkMaxTournament()} trong Hình 5-26).
     *
     * @return {@code true} nếu còn có thể tạo giải đấu mới
     */
    public boolean checkMaxTournament() {
        return countTournaments() < maxTournaments;
    }

    /**
     * Tìm các nhà quảng cáo quan tâm tài trợ độc quyền (thông điệp
     * {@code findInterestedExclusiveSponsors()} trong Hình 5-27).
     *
     * @return danh sách nhà quảng cáo quan tâm
     */
    public List<Advertiser> findInterestedExclusiveSponsors() {
        List<Advertiser> result = new ArrayList<>();
        for (Advertiser advertiser : advertisers) {
            if (advertiser.isInterestedInExclusiveSponsorship()) {
                result.add(advertiser);
            }
        }
        return result;
    }

    /**
     * Đếm tổng số giải đấu hiện có trong thể hiện ARENA.
     *
     * @return tổng số giải đấu
     */
    public int countTournaments() {
        int count = 0;
        for (LeagueOwner owner : leagueOwners) {
            for (League league : owner.getLeagues()) {
                count += league.getTournaments().size();
            }
        }
        return count;
    }

    /**
     * Đăng ký một chủ giải đấu.
     *
     * @param owner chủ giải đấu
     */
    public void addLeagueOwner(LeagueOwner owner) {
        leagueOwners.add(owner);
    }

    /**
     * Đăng ký một nhà quảng cáo.
     *
     * @param advertiser nhà quảng cáo
     */
    public void addAdvertiser(Advertiser advertiser) {
        advertisers.add(advertiser);
    }

    /**
     * Đăng ký một nhóm quan tâm.
     *
     * @param group nhóm quan tâm
     */
    public void addInterestGroup(InterestGroup group) {
        interestGroups.add(group);
    }

    /**
     * Thêm một trò chơi được hỗ trợ.
     *
     * @param game trò chơi
     */
    public void addGame(Game game) {
        games.add(game);
    }

    /**
     * Thêm một thể thức thi đấu được hỗ trợ.
     *
     * @param style thể thức
     */
    public void addTournamentStyle(TournamentStyle style) {
        tournamentStyles.add(style);
    }

    /**
     * Trả về số giải đấu tối đa.
     *
     * @return số giải đấu tối đa
     */
    public int getMaxTournaments() {
        return maxTournaments;
    }

    /**
     * Trả về phí tài trợ độc quyền.
     *
     * @return phí tài trợ
     */
    public double getSponsorshipFee() {
        return sponsorshipFee;
    }

    /**
     * Trả về danh sách chủ giải đấu (chỉ đọc).
     *
     * @return danh sách chủ giải đấu
     */
    public List<LeagueOwner> getLeagueOwners() {
        return Collections.unmodifiableList(leagueOwners);
    }

    /**
     * Trả về danh sách nhà quảng cáo (chỉ đọc).
     *
     * @return danh sách nhà quảng cáo
     */
    public List<Advertiser> getAdvertisers() {
        return Collections.unmodifiableList(advertisers);
    }

    /**
     * Trả về danh sách nhóm quan tâm (chỉ đọc).
     *
     * @return danh sách nhóm quan tâm
     */
    public List<InterestGroup> getInterestGroups() {
        return Collections.unmodifiableList(interestGroups);
    }

    /**
     * Trả về danh sách trò chơi (chỉ đọc).
     *
     * @return danh sách trò chơi
     */
    public List<Game> getGames() {
        return Collections.unmodifiableList(games);
    }

    /**
     * Trả về danh sách thể thức (chỉ đọc).
     *
     * @return danh sách thể thức
     */
    public List<TournamentStyle> getTournamentStyles() {
        return Collections.unmodifiableList(tournamentStyles);
    }
}
