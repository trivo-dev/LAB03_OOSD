/*
 * League.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Một league: nhóm các {@link Tournament} cùng trò chơi và cùng thể
 * thức, do một {@link LeagueOwner} quản lý.
 *
 * <p>Trong Hình 5-26, {@code League} nhận thông điệp
 * {@code createTournament(name, maxp)} từ đối tượng điều khiển và tạo
 * mới thực thể {@link Tournament}.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class League {

    /** Tên league. */
    private final String name;

    /** Chủ sở hữu league. */
    private final LeagueOwner owner;

    /** Trò chơi của league. */
    private final Game game;

    /** Thể thức thi đấu của league. */
    private final TournamentStyle style;

    /** Các giải đấu thuộc league. */
    private final List<Tournament> tournaments;

    /**
     * Khởi tạo một league và gắn nó vào chủ sở hữu.
     *
     * @param name  tên league
     * @param owner chủ sở hữu
     * @param game  trò chơi
     * @param style thể thức thi đấu
     */
    public League(String name, LeagueOwner owner, Game game,
            TournamentStyle style) {
        this.name = name;
        this.owner = owner;
        this.game = game;
        this.style = style;
        this.tournaments = new ArrayList<>();
        owner.addLeague(this);
    }

    /**
     * Tạo một giải đấu mới trong league.
     *
     * @param tournamentName tên giải đấu
     * @param maxPlayers     số người chơi tối đa
     * @return giải đấu vừa tạo
     */
    public Tournament createTournament(String tournamentName,
            int maxPlayers) {
        Tournament tournament =
                new Tournament(this, tournamentName, maxPlayers);
        tournaments.add(tournament);
        return tournament;
    }

    /**
     * Trả về tên league.
     *
     * @return tên league
     */
    public String getName() {
        return name;
    }

    /**
     * Trả về chủ sở hữu league.
     *
     * @return chủ sở hữu
     */
    public LeagueOwner getOwner() {
        return owner;
    }

    /**
     * Trả về trò chơi của league.
     *
     * @return trò chơi
     */
    public Game getGame() {
        return game;
    }

    /**
     * Trả về thể thức thi đấu.
     *
     * @return thể thức
     */
    public TournamentStyle getStyle() {
        return style;
    }

    /**
     * Trả về danh sách giải đấu (chỉ đọc).
     *
     * @return danh sách giải đấu
     */
    public List<Tournament> getTournaments() {
        return Collections.unmodifiableList(tournaments);
    }
}
