package com.epam.library.controller.command;


import com.epam.library.controller.command.reader.order.*;
import com.epam.library.controller.command.book.ViewBookCommand;
import com.epam.library.controller.command.librarian.*;
import com.epam.library.controller.command.librarian.book.*;
import com.epam.library.controller.command.librarian.order.*;
import com.epam.library.controller.command.librarian.user.*;
import com.epam.library.controller.command.book.BookStoreCommand;
import com.epam.library.controller.command.reader.*;
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
                return new LibrarianProfileCommand();

            case "LIBRARIAN_BOOK_STORE":
                return new LibrarianBookStoreCommand(serviceFactory.getBookService());
            case "LIBRARIAN_EDIT_BOOK":
                return new LibrarianEditBookCommand(serviceFactory.getBookService());
            case "LIBRARIAN_UPDATE_BOOK":
                return new LibrarianUpdateBookCommand(serviceFactory.getBookService());
            case "LIBRARIAN_ADD_BOOK":
                return new LibrarianAddBookCommand();

            case "LIBRARIAN_ORDER_LIST":
                return new LibrarianOrderListCommand(serviceFactory.getOrderService());
            case "LIBRARIAN_EDIT_ORDER":
                return new LibrarianEditOrderCommand(serviceFactory.getOrderService());
            case "LIBRARIAN_ADD_ORDER":
                return new LibrarianAddOrderCommand();
            case "LIBRARIAN_UPDATE_ORDER":
                return new LibrarianUpdateOrderCommand(serviceFactory.getOrderService(), serviceFactory.getBookService());

            case "LIBRARIAN_DISPLAY_READERS":
                return new LibrarianDisplayUserCommand(serviceFactory.getUserService());
            case "LIBRARIAN_EDIT_READER":
                return new LibrarianEditUserCommand(serviceFactory.getUserService());
            case "LIBRARIAN_UPDATE_READER":
                return new LibrarianUpdateUserCommand(serviceFactory.getUserService());
            case "LIBRARIAN_ADD_READER":
                return new LibrarianAddUserCommand();

                //Reader
            case"READER_PROFILE":
                return new ReaderProfileCommand();
            case "DISPLAY_BOOK":
                return new BookStoreCommand(serviceFactory.getBookService());
            case "CONFIRM_ORDER":
                return new ConfirmOrderCommand(serviceFactory.getBookService(), serviceFactory.getOrderService());
            case "READER_ORDER":
                return new ReaderOrderCommand(serviceFactory.getOrderService());
                //book
            case "VIEW_BOOK":
                return new ViewBookCommand(serviceFactory.getBookService());
            case "ORDER_BOOK":
                return new OrderBookCommand(serviceFactory.getBookService());
            default:

                throw new IllegalArgumentException("Illegal Command");
        }
    }

    @Override
    public void close()  {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
