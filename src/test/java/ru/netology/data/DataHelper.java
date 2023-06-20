package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String cardId;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
}