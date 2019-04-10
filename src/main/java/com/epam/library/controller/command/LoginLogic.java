package com.epam.library.controller.command;

public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASSWORD = "admin";

    public static boolean checkUserData(String login, String password){
        return login.equalsIgnoreCase(ADMIN_LOGIN) && password.equalsIgnoreCase(ADMIN_PASSWORD);
    }
}
