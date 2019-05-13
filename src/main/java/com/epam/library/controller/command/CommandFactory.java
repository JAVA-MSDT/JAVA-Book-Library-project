package com.epam.library.controller.command;


import com.epam.library.controller.command.admin.AdminChangeRoleCommand;
import com.epam.library.controller.command.admin.AdminRemoveBookCommand;
import com.epam.library.controller.command.admin.AdminRemoveUserCommand;
import com.epam.library.controller.command.admin.AdminUpdateRoleCommand;
import com.epam.library.controller.command.administration.book.LibrarianBookStoreCommand;
import com.epam.library.controller.command.administration.book.LibrarianEditBookCommand;
import com.epam.library.controller.command.administration.book.LibrarianUpdateBookCommand;
import com.epam.library.controller.command.administration.order.LibrarianEditOrderCommand;
import com.epam.library.controller.command.administration.order.LibrarianOrderListCommand;
import com.epam.library.controller.command.administration.order.LibrarianUpdateOrderCommand;
import com.epam.library.controller.command.administration.user.LibrarianEditUserCommand;
import com.epam.library.controller.command.administration.user.LibrarianUpdateUserCommand;
import com.epam.library.controller.command.administration.user.LibrarianUserListCommand;
import com.epam.library.controller.command.book.BookStoreCommand;
import com.epam.library.controller.command.book.SearchBookCommand;
import com.epam.library.controller.command.book.SortBookCommand;
import com.epam.library.controller.command.book.ViewBookCommand;
import com.epam.library.controller.command.commoncommand.*;
import com.epam.library.controller.command.user.UserOrderCommand;
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
            case "PROFILE":
                return new ProfileCommand();

            // ADMINISTRATION CLASSES

            case "ADMINISTRATION_BOOK_STORE":
                return new LibrarianBookStoreCommand(serviceFactory.getBookService());
            case "ADMINISTRATION_EDIT_BOOK":
                return new LibrarianEditBookCommand(serviceFactory.getBookService());
            case "ADMINISTRATION_UPDATE_BOOK":
                return new LibrarianUpdateBookCommand(serviceFactory.getBookService());

            case "ADMINISTRATION_ORDER_LIST":
                return new LibrarianOrderListCommand(serviceFactory.getOrderService());
            case "ADMINISTRATION_EDIT_ORDER":
                return new LibrarianEditOrderCommand(serviceFactory.getOrderService());
            case "ADMINISTRATION_UPDATE_ORDER":
                return new LibrarianUpdateOrderCommand(serviceFactory.getOrderService(), serviceFactory.getBookService(), transactionManager);
            case "ADMINISTRATION_SORT_ORDER":
                return new AdministrationSortOrderCommand(serviceFactory.getOrderService());
            case "ADMINISTRATION_SEARCH_ORDER":
                return new AdministrationSearchOrderCommand(serviceFactory.getOrderService());

            case "ADMINISTRATION_DISPLAY_USER":
                return new LibrarianUserListCommand(serviceFactory.getUserService());
            case "ADMINISTRATION_EDIT_USER":
                return new LibrarianEditUserCommand(serviceFactory.getUserService());
            case "ADMINISTRATION_UPDATE_USER":
                return new LibrarianUpdateUserCommand(serviceFactory.getUserService());
            case "ADMINISTRATION_SEARCH_USER":
                return new AdministrationSearchUserCommand(serviceFactory.getUserService());
            case "ADMINISTRATION_SORT_USER":
                return new AdministrationSortUserCommand(serviceFactory.getUserService());

                // Admin only
            case "ADMIN_REMOVE_BOOK":
                return new AdminRemoveBookCommand(serviceFactory.getBookService());
            case "ADMIN_REMOVE_USER":
                return new AdminRemoveUserCommand(serviceFactory.getUserService());
            case "ADMIN_CHANGE_ROLE":
                return new AdminChangeRoleCommand(serviceFactory.getUserService());
            case "ADMIN_UPDATE_ROLE":
                return new AdminUpdateRoleCommand(serviceFactory.getUserService());

            //Reader

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
            case "SEARCH_BOOK":
                return new SearchBookCommand(serviceFactory.getBookService());
            case "SORT_BOOK":
                return new SortBookCommand(serviceFactory.getBookService());
            default:

                throw new IllegalArgumentException("Illegal Command");
        }
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
