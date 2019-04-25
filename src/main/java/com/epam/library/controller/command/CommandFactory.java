package com.epam.library.controller.command;


import com.epam.library.controller.command.librarian.*;
import com.epam.library.controller.command.librarian.book.LibrarianAddBook;
import com.epam.library.controller.command.librarian.book.LibrarianBookStore;
import com.epam.library.controller.command.librarian.book.LibrarianEditBook;
import com.epam.library.controller.command.librarian.book.LibrarianUpdateBook;
import com.epam.library.controller.command.librarian.user.LibrarianAddUser;
import com.epam.library.controller.command.librarian.user.LibrarianDisplayUser;
import com.epam.library.controller.command.librarian.user.LibrarianEditUser;
import com.epam.library.controller.command.librarian.user.LibrarianUpdateUser;

public class CommandFactory {
    public static Command create(String command){
        String formatCommand = command.replace('-','_');
        switch (formatCommand.toUpperCase()){
            case "LOGIN":
                return new LoginCommand();
            case "LOGOUT":
                return new LogoutCommand();

                // LIBRARIAN CLASSES
            case "LIBRARIAN_PROFILE":
                return new LibrarianProfilePage();

            case "LIBRARIAN_BOOK_STORE":
                return new LibrarianBookStore();
            case "LIBRARIAN_EDIT_BOOK":
                return new LibrarianEditBook();
            case "LIBRARIAN_UPDATE_BOOK":
                return new LibrarianUpdateBook();
            case "LIBRARIAN_ADD_BOOK":
                return new LibrarianAddBook();

            case "LIBRARIAN_DISPLAY_READERS":
                return new LibrarianDisplayUser();
            case "LIBRARIAN_EDIT_READER":
                return new LibrarianEditUser();
            case "LIBRARIAN_UPDATE_READER":
                return new LibrarianUpdateUser();
            case "LIBRARIAN_ADD_READER":
                return new LibrarianAddUser();
            default:
                throw new IllegalArgumentException("Illegal Command");
        }
    }
}
