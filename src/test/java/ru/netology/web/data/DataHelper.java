package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class ReplenishCardAmount {
        int amount;
    }

    public static int getReplenishCardAmount(int amount) {
        return amount;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559000000000001");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559000000000002");
    }

    public static int checkBalanceDebitCard(int balance, int sum) {
        int finalBalance = balance - sum;
        return finalBalance;
    }

    public static int checkBalanceRechargeCard(int balance, int sum) {
        int finalBalance = balance + sum;
        return finalBalance;
    }
}
