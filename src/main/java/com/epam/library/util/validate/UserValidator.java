package com.epam.library.util.validate;

public class UserValidator {


    public static boolean isValidLogin(String login){
        return DataMatcher.isValid(DataRegex.LOGIN, login);
    }

    public static boolean isValidPassword(String password){
        return DataMatcher.isValid(DataRegex.PASSWORD, password);
    }
}
