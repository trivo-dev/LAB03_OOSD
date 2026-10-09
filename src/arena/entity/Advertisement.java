/*
 * Advertisement.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

/**
 * Một mẫu quảng cáo thuộc về {@link Advertiser}, được hiển thị trong
 * các trận đấu của giải mà nhà quảng cáo tài trợ.
 *
 * @author Tri
 * @version 1.0
 */
public class Advertisement {

    /** Nội dung quảng cáo. */
    private final String content;

    /**
     * Khởi tạo một mẫu quảng cáo.
     *
     * @param content nội dung quảng cáo
     */
    public Advertisement(String content) {
        this.content = content;
    }

    /**
     * Trả về nội dung quảng cáo.
     *
     * @return nội dung
     */
    public String getContent() {
        return content;
    }
}
