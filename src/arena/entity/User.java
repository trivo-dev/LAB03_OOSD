/*
 * User.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lớp trừu tượng biểu diễn một người dùng của hệ thống ARENA.
 *
 * <p>Lớp này được xác định qua tổng quát hóa (Hình 5-30): gom các thuộc
 * tính chung (tên, thông tin liên lạc) của {@link LeagueOwner},
 * {@link Advertiser} và {@link Player}. Mỗi người dùng có một hộp thư
 * ({@code mailbox}) để nhận thông báo từ ARENA.</p>
 *
 * @author Tri
 * @version 1.0
 */
public abstract class User {

    /** Tên hiển thị của người dùng. */
    private final String name;

    /** Thông tin liên lạc (ví dụ: địa chỉ e-mail). */
    private final String contact;

    /** Hộp thư chứa các thông báo người dùng nhận được. */
    private final List<String> mailbox;

    /**
     * Khởi tạo một người dùng.
     *
     * @param name    tên người dùng, không được rỗng
     * @param contact thông tin liên lạc, không được rỗng
     * @throws IllegalArgumentException nếu {@code name} hoặc
     *         {@code contact} rỗng
     */
    protected User(String name, String contact) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tên người dùng rỗng.");
        }
        if (contact == null || contact.isBlank()) {
            throw new IllegalArgumentException("Thông tin liên lạc rỗng.");
        }
        this.name = name;
        this.contact = contact;
        this.mailbox = new ArrayList<>();
    }

    /**
     * Trả về tên người dùng.
     *
     * @return tên người dùng
     */
    public String getName() {
        return name;
    }

    /**
     * Trả về thông tin liên lạc của người dùng.
     *
     * @return thông tin liên lạc
     */
    public String getContact() {
        return contact;
    }

    /**
     * Lưu một thông báo vào hộp thư của người dùng.
     *
     * @param message nội dung thông báo
     */
    public void receiveMessage(String message) {
        mailbox.add(message);
    }

    /**
     * Trả về danh sách thông báo (chỉ đọc) trong hộp thư.
     *
     * @return danh sách thông báo không thể sửa đổi
     */
    public List<String> getMailbox() {
        return Collections.unmodifiableList(mailbox);
    }

    /**
     * Trả về chuỗi mô tả người dùng.
     *
     * @return chuỗi dạng {@code Tên <liên lạc>}
     */
    @Override
    public String toString() {
        return name + " <" + contact + ">";
    }
}
