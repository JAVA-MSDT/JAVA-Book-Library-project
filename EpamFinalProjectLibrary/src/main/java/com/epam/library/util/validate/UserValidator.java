package com.epam.library.util.validate;

public class UserValidator {


    public static boolean isValidLogin(String login) {
        return DataMatcher.isValid(DataRegex.LOGIN, login);
    }

    public static boolean isValidPassword(String password) {
        return DataMatcher.isValid(DataRegex.PASSWORD, password);
    }

    public static boolean isValidEmail(String email) {
        return DataMatcher.isValid(DataRegex.E_MAIL, email);
    }

    public static boolean isValidName(String name) {
        return DataMatcher.isValid(DataRegex.NAME, name);
    }
}
