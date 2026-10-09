/*
 * Account.java
 *
 * Mô hình phân tích ARENA - Use case AnnounceTournament.
 */
package arena.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tài khoản của {@link Advertiser} gồm số dư, các khoản phí
 * ({@code charges}) và các khoản thanh toán ({@code payments}) như trong
 * Hình 5-29.
 *
 * @author Tri
 * @version 1.0
 */
public class Account {

    /** Số dư hiện tại (âm nghĩa là còn nợ). */
    private double balance;

    /** Lịch sử các khoản phí đã ghi nợ. */
    private final List<String> charges;

    /** Lịch sử các khoản thanh toán. */
    private final List<String> payments;

    /** Khởi tạo tài khoản với số dư bằng 0. */
    public Account() {
        this.balance = 0.0;
        this.charges = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    /**
     * Ghi nợ một khoản phí vào tài khoản.
     *
     * @param amount      số tiền (dương)
     * @param description mô tả khoản phí
     * @throws IllegalArgumentException nếu {@code amount} không dương
     */
    public void charge(double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền phải dương.");
        }
        balance -= amount;
        charges.add(description + ": " + amount);
    }

    /**
     * Ghi nhận một khoản thanh toán.
     *
     * @param amount số tiền (dương)
     * @throws IllegalArgumentException nếu {@code amount} không dương
     */
    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền phải dương.");
        }
        balance += amount;
        payments.add(String.valueOf(amount));
    }

    /**
     * Trả về số dư hiện tại.
     *
     * @return số dư
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Trả về lịch sử phí (chỉ đọc).
     *
     * @return danh sách phí
     */
    public List<String> getCharges() {
        return Collections.unmodifiableList(charges);
    }

    /**
     * Trả về lịch sử thanh toán (chỉ đọc).
     *
     * @return danh sách thanh toán
     */
    public List<String> getPayments() {
        return Collections.unmodifiableList(payments);
    }
}
