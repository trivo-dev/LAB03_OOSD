/*
 * Advertiser.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nhà quảng cáo - đối tượng có thể trở thành nhà tài trợ độc quyền
 * (exclusive sponsor) cho một {@link Tournament}.
 *
 * <p>Thuộc tính {@code interestedInExclusiveSponsorship} được phát hiện
 * khi phân tích Hình 5-27: {@link Arena} cần biết nhà quảng cáo nào
 * quan tâm đến tài trợ độc quyền. Mỗi nhà quảng cáo có một
 * {@link Account} để bị trừ phí tài trợ.</p>
 *
 * <p>Vì đây là cài đặt giả lập, quyết định chấp nhận/từ chối lời mời
 * tài trợ được cấu hình sẵn qua {@code acceptsSponsorshipOffers}.</p>
 *
 * @author Tri
 * @version 1.0
 */
public class Advertiser extends User {

    /** Cho biết nhà quảng cáo có quan tâm tài trợ độc quyền hay không. */
    private boolean interestedInExclusiveSponsorship;

    /** Quyết định giả lập khi nhận lời mời tài trợ. */
    private boolean acceptsSponsorshipOffers;

    /** Tài khoản thanh toán của nhà quảng cáo. */
    private final Account account;

    /** Các mẫu quảng cáo của nhà quảng cáo. */
    private final List<Advertisement> advertisements;

    /**
     * Khởi tạo một nhà quảng cáo.
     *
     * @param name                     tên nhà quảng cáo
     * @param contact                  thông tin liên lạc
     * @param interestedInExclusive    có quan tâm tài trợ độc quyền không
     * @param acceptsSponsorshipOffers quyết định giả lập khi được mời
     */
    public Advertiser(String name, String contact,
            boolean interestedInExclusive,
            boolean acceptsSponsorshipOffers) {
        super(name, contact);
        this.interestedInExclusiveSponsorship = interestedInExclusive;
        this.acceptsSponsorshipOffers = acceptsSponsorshipOffers;
        this.account = new Account();
        this.advertisements = new ArrayList<>();
    }

    /**
     * Cho biết nhà quảng cáo có quan tâm tài trợ độc quyền hay không.
     *
     * @return {@code true} nếu có quan tâm
     */
    public boolean isInterestedInExclusiveSponsorship() {
        return interestedInExclusiveSponsorship;
    }

    /**
     * Cập nhật mức độ quan tâm tài trợ độc quyền.
     *
     * @param interested giá trị mới
     */
    public void setInterestedInExclusiveSponsorship(boolean interested) {
        this.interestedInExclusiveSponsorship = interested;
    }

    /**
     * Thiết lập quyết định giả lập khi nhận lời mời tài trợ.
     *
     * @param accepts {@code true} nếu sẽ chấp nhận
     */
    public void setAcceptsSponsorshipOffers(boolean accepts) {
        this.acceptsSponsorshipOffers = accepts;
    }

    /**
     * Mô phỏng việc nhà quảng cáo xem xét lời mời tài trợ một giải đấu.
     *
     * @param tournament giải đấu được mời tài trợ
     * @return {@code true} nếu chấp nhận tài trợ
     */
    public boolean decideSponsorship(Tournament tournament) {
        return acceptsSponsorshipOffers;
    }

    /**
     * Trả về tài khoản của nhà quảng cáo.
     *
     * @return tài khoản
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Thêm một mẫu quảng cáo.
     *
     * @param advertisement mẫu quảng cáo
     */
    public void addAdvertisement(Advertisement advertisement) {
        advertisements.add(advertisement);
    }

    /**
     * Trả về danh sách quảng cáo (chỉ đọc).
     *
     * @return danh sách quảng cáo
     */
    public List<Advertisement> getAdvertisements() {
        return Collections.unmodifiableList(advertisements);
    }
}
