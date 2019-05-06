package com.epam.library.controller.command;


import com.epam.library.controller.command.book.BookStoreCommand;
import com.epam.library.controller.command.book.ViewBookCommand;
import com.epam.library.controller.command.commoncommand.LanguageCommand;
import com.epam.library.controller.command.librarian.LibrarianProfileCommand;
import com.epam.library.controller.command.librarian.book.LibrarianAddBookCommand;
import com.epam.library.controller.command.librarian.book.LibrarianBookStoreCommand;
import com.epam.library.controller.command.librarian.book.LibrarianEditBookCommand;
import com.epam.library.controller.command.librarian.book.LibrarianUpdateBookCommand;
import com.epam.library.controller.command.librarian.order.LibrarianAddOrderCommand;
import com.epam.library.controller.command.librarian.order.LibrarianEditOrderCommand;
import com.epam.library.controller.command.librarian.order.LibrarianOrderListCommand;
import com.epam.library.controller.command.librarian.order.LibrarianUpdateOrderCommand;
import com.epam.library.controller.command.librarian.user.LibrarianAddUserCommand;
import com.epam.library.controller.command.librarian.user.LibrarianEditUserCommand;
import com.epam.library.controller.command.librarian.user.LibrarianUpdateUserCommand;
import com.epam.library.controller.command.librarian.user.LibrarianUserListCommand;
import com.epam.library.controller.command.user.UserOrderCommand;
import com.epam.library.controller.command.user.UserProfileCommand;
import com.epam.library.controller.command.user.order.ConfirmOrderCommand;
import com.epam.library.controller.command.user.order.OrderBookCommand;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.TransactionManager;

import java.sql.Connection;

public class CommandFactory implements AutoCloseable {

    private ServiceFactory serviceFactory;
    private TransactionManager transactionManager;
    private Connection connection;

    public CommandFactory() {
        connection = ConnectionPool.getInstance().getConnection();
        serviceFactory = new ServiceFactory(connection);
        transactionManager = new TransactionManager(connection);
    }

    public Command create(String command) {
        String formatCommand = command.replace('-', '_');
        switch (formatCommand.toUpperCase()) {
            case "LOGIN":
                return new LoginCommand(serviceFactory.getUserService());
            case "LOGOUT":
                return new LogoutCommand();
            case "CHANGE_LANGUAGE":
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
                return new LibrarianUpdateOrderCommand(serviceFactory.getOrderService(), serviceFactory.getBookService(), transactionManager);

            case "LIBRARIAN_DISPLAY_USER":
                return new LibrarianUserListCommand(serviceFactory.getUserService());
            case "LIBRARIAN_EDIT_USER":
                return new LibrarianEditUserCommand(serviceFactory.getUserService());
            case "LIBRARIAN_UPDATE_USER":
                return new LibrarianUpdateUserCommand(serviceFactory.getUserService());
            case "LIBRARIAN_ADD_USER":
                return new LibrarianAddUserCommand();

            //Reader
            case "USER_PROFILE":
                return new UserProfileCommand();
            case "DISPLAY_BOOK":
                return new BookStoreCommand(serviceFactory.getBookService());
            case "CONFIRM_ORDER":
                return new ConfirmOrderCommand(serviceFactory.getBookService(), serviceFactory.getOrderService(), transactionManager);
            case "USER_ORDER":
                return new UserOrderCommand(serviceFactory.getOrderService());
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
    public void close() {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
