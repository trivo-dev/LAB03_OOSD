/*
 * ArenaDemo.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena;

import arena.boundary.NotifyInterestGroupsForm;
import arena.boundary.RequestSponsorshipForm;
import arena.boundary.SelectExclusiveSponsorForm;
import arena.boundary.TournamentForm;
import arena.control.AnnounceTournamentControl;
import arena.entity.Advertiser;
import arena.entity.Arena;
import arena.entity.Chess;
import arena.entity.Game;
import arena.entity.InterestGroup;
import arena.entity.KnockOutStyle;
import arena.entity.League;
import arena.entity.LeagueOwner;
import arena.entity.Match;
import arena.entity.Player;
import arena.entity.TicTacToe;
import arena.entity.Tournament;
import arena.entity.TournamentStyle;

import java.util.List;

/**
 * Chương trình chạy thử kịch bản AnnounceTournament theo các biểu đồ
 * tuần tự 5-26, 5-27 và 5-28.
 *
 * @author Tri
 * @version 1.0
 */
public final class ArenaDemo {

    /** Số giải đấu tối đa của thể hiện ARENA dùng thử. */
    private static final int MAX_TOURNAMENTS = 5;

    /** Phí tài trợ độc quyền. */
    private static final double SPONSORSHIP_FEE = 1000.0;

    /** Không cho phép khởi tạo lớp tiện ích. */
    private ArenaDemo() {
    }

    /**
     * Điểm vào chương trình.
     *
     * @param args tham số dòng lệnh (không sử dụng)
     */
    public static void main(String[] args) {
        // Chuẩn bị dữ liệu thực thể
        Arena arena = new Arena(MAX_TOURNAMENTS, SPONSORSHIP_FEE);
        Game chess = new Chess();
        Game ticTacToe = new TicTacToe();
        TournamentStyle knockOut = new KnockOutStyle();
        arena.addGame(chess);
        arena.addGame(ticTacToe);
        arena.addTournamentStyle(knockOut);

        LeagueOwner owner = new LeagueOwner("Alice", "alice@arena.vn");
        arena.addLeagueOwner(owner);
        League league = new League("Chess Masters", owner, chess,
                knockOut);

        Advertiser a1 = new Advertiser("Vinamilk", "ads@vnm.vn",
                true, true);
        Advertiser a2 = new Advertiser("Viettel", "ads@viettel.vn",
                true, true);
        Advertiser a3 = new Advertiser("FPT", "ads@fpt.vn", true, false);
        Advertiser a4 = new Advertiser("Bitis", "ads@bitis.vn",
                false, true);
        arena.addAdvertiser(a1);
        arena.addAdvertiser(a2);
        arena.addAdvertiser(a3);
        arena.addAdvertiser(a4);

        Player p1 = new Player("Bình", "binh@mail.vn");
        Player p2 = new Player("Chi", "chi@mail.vn");
        Player p3 = new Player("Dũng", "dung@mail.vn");
        Player p4 = new Player("Em", "em@mail.vn");
        InterestGroup chessFans = new InterestGroup("Chess Fans", chess);
        chessFans.addMember(p1);
        chessFans.addMember(p2);
        InterestGroup tttFans = new InterestGroup("TTT Fans", ticTacToe);
        tttFans.addMember(p3);
        arena.addInterestGroup(chessFans);
        arena.addInterestGroup(tttFans);

        // Hình 5-26: tạo giải đấu
        System.out.println("=== Hình 5-26: Tạo giải đấu ===");
        TournamentForm tournamentForm = new TournamentForm(arena);
        AnnounceTournamentControl control =
                tournamentForm.newTournament(league);
        tournamentForm.setName("Autumn Open 2026");
        tournamentForm.setMaxPlayers(8);
        Tournament tournament = tournamentForm.commit();

        // Hình 5-27: tìm và chọn nhà tài trợ độc quyền
        System.out.println("\n=== Hình 5-27: Quy trình tài trợ ===");
        new RequestSponsorshipForm(control).requestExclusiveSponsor();
        SelectExclusiveSponsorForm selectForm =
                new SelectExclusiveSponsorForm(control);
        List<Advertiser> candidates = selectForm.displayCandidates();
        if (!candidates.isEmpty()) {
            selectForm.selectSponsor(candidates.get(0));
        }

        // Hình 5-28: thông báo nhóm quan tâm
        System.out.println("\n=== Hình 5-28: Thông báo nhóm quan tâm ===");
        new NotifyInterestGroupsForm(control).notifyInterestGroups();

        // Kiểm tra kết quả
        System.out.println("\n=== Kết quả ===");
        System.out.println("Giải đấu: " + tournament);
        System.out.println("Nhà tài trợ: "
                + tournament.getSponsor().getName()
                + ", số dư tài khoản: "
                + tournament.getSponsor().getAccount().getBalance());
        System.out.println("Hộp thư " + owner.getName() + ": "
                + owner.getMailbox().size() + " thông báo");

        tournament.addPlayer(p1);
        tournament.addPlayer(p2);
        tournament.addPlayer(p3);
        tournament.addPlayer(p4);
        System.out.print("Lịch thi đấu vòng 1 (" + knockOut + "):");
        for (Match match : tournament.planMatches()) {
            System.out.print(" [" + match + "]");
        }
        System.out.println();
    }
}
