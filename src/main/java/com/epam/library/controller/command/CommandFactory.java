package com.epam.library.controller.command;


public class CommandFactory {
    public static Command create(String command){
        switch (command.toUpperCase()){
            case "LOGIN":
                return new LoginCommand();

            case "LOGOUT":
                return new LogoutCommand();

            default:
                throw new IllegalArgumentException("Illegal Command");
        }
    }
}
