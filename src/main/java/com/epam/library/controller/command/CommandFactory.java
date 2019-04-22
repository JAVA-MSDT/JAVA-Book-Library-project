package com.epam.library.controller.command;


import com.epam.library.controller.command.librarian.LibrarianBookStore;
import com.epam.library.controller.command.librarian.LibrarianEditUser;
import com.epam.library.controller.command.librarian.LibrarianUpdateUser;

public class CommandFactory {
    public static Command create(String command){
        switch (command.toUpperCase()){
            case "LOGIN":
                return new LoginCommand();
            case "LOGOUT":
                return new LogoutCommand();
            case "LIBBOOKSTORE":
                return new LibrarianBookStore();
            case "LIBDISPLAYREADERS":
                return new DisplayReaders();
            case "LIBEDITREADER":
                return new LibrarianEditUser();
            case "LIBUPDATEREADER":
                return new LibrarianUpdateUser();
            default:
                throw new IllegalArgumentException("Illegal Command");
        }
    }
}
