package com.epam.library.controller.command;


import com.epam.library.controller.command.librarian.*;
import com.epam.library.controller.command.librarian.book.LibrarianAddBook;
import com.epam.library.controller.command.librarian.book.LibrarianBookStore;
import com.epam.library.controller.command.librarian.book.LibrarianEditBook;
import com.epam.library.controller.command.librarian.book.LibrarianUpdateBook;
import com.epam.library.controller.command.librarian.order.LibrarianAddOrder;
import com.epam.library.controller.command.librarian.order.LibrarianEditOrder;
import com.epam.library.controller.command.librarian.order.LibrarianOrderList;
import com.epam.library.controller.command.librarian.order.LibrarianUpdateOrder;
import com.epam.library.controller.command.librarian.user.LibrarianAddUser;
import com.epam.library.controller.command.librarian.user.LibrarianDisplayUser;
import com.epam.library.controller.command.librarian.user.LibrarianEditUser;
import com.epam.library.controller.command.librarian.user.LibrarianUpdateUser;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.service.ServiceFactory;

import java.sql.Connection;

public class CommandFactory implements AutoCloseable {

    private ServiceFactory serviceFactory;
    private Connection connection;

    public CommandFactory() {
        connection = ConnectionPool.getInstance().getConnection();
        serviceFactory = new ServiceFactory(connection);
    }

    public Command create(String command){
        String formatCommand = command.replace('-','_');
        switch (formatCommand.toUpperCase()){
            case "LOGIN":
                return new LoginCommand(serviceFactory.getUserService());
            case "LOGOUT":
                return new LogoutCommand();
            case "LANGUAGE_COMMAND":
                return new LanguageCommand();

                // LIBRARIAN CLASSES
            case "LIBRARIAN_PROFILE":
                return new LibrarianProfilePage();

            case "LIBRARIAN_BOOK_STORE":
                return new LibrarianBookStore(serviceFactory.getBookService());
            case "LIBRARIAN_EDIT_BOOK":
                return new LibrarianEditBook(serviceFactory.getBookService());
            case "LIBRARIAN_UPDATE_BOOK":
                return new LibrarianUpdateBook(serviceFactory.getBookService());
            case "LIBRARIAN_ADD_BOOK":
                return new LibrarianAddBook();

            case "LIBRARIAN_ORDER_LIST":
                return new LibrarianOrderList(serviceFactory.getOrderService());
            case "LIBRARIAN_EDIT_ORDER":
                return new LibrarianEditOrder(serviceFactory.getOrderService());
            case "LIBRARIAN_ADD_ORDER":
                return new LibrarianAddOrder();
            case "LIBRARIAN_UPDATE_ORDER":
                return new LibrarianUpdateOrder(serviceFactory.getOrderService());

            case "LIBRARIAN_DISPLAY_READERS":
                return new LibrarianDisplayUser(serviceFactory.getUserService());
            case "LIBRARIAN_EDIT_READER":
                return new LibrarianEditUser(serviceFactory.getUserService());
            case "LIBRARIAN_UPDATE_READER":
                return new LibrarianUpdateUser(serviceFactory.getUserService());
            case "LIBRARIAN_ADD_READER":
                return new LibrarianAddUser();
            default:
                throw new IllegalArgumentException("Illegal Command");
        }
    }

    @Override
    public void close()  {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
