package com.epam.library.controller.command;


import com.epam.library.controller.command.admin.AdminRemoveBookCommand;
import com.epam.library.controller.command.admin.AdminRemoveUserCommand;
import com.epam.library.controller.command.administration.book.AdministrationBookStoreCommand;
import com.epam.library.controller.command.administration.book.AdministrationEditBookCommand;
import com.epam.library.controller.command.administration.book.AdministrationUpdateBookCommand;
import com.epam.library.controller.command.administration.order.AdministrationEditOrderCommand;
import com.epam.library.controller.command.administration.order.AdministrationOrderListCommand;
import com.epam.library.controller.command.administration.order.AdministrationUpdateOrderCommand;
import com.epam.library.controller.command.administration.user.AdministrationEditUserCommand;
import com.epam.library.controller.command.administration.user.AdministrationUpdateUserCommand;
import com.epam.library.controller.command.administration.user.AdministrationUserListCommand;
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
import com.epam.library.util.constant.CommandName;

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
        switch (command) {
            case CommandName.REGISTRATION:
                return new RegistrationCommand(serviceFactory.getUserService());
            case CommandName.LOGIN:
                return new LoginCommand(serviceFactory.getUserService());
            case CommandName.LOGOUT:
                return new LogoutCommand();
            case CommandName.CHANGE_LANGUAGE:
                return new LanguageCommand();
            case CommandName.PROFILE:
                return new ProfileCommand();

            // ADMINISTRATION

            case CommandName.ADMINISTRATION_BOOK_STORE:
                return new AdministrationBookStoreCommand(serviceFactory.getBookService());
            case CommandName.ADMINISTRATION_EDIT_BOOK:
                return new AdministrationEditBookCommand(serviceFactory.getBookService());
            case CommandName.ADMINISTRATION_UPDATE_BOOK:
                return new AdministrationUpdateBookCommand(serviceFactory.getBookService());

            case CommandName.ADMINISTRATION_ORDER_LIST:
                return new AdministrationOrderListCommand(serviceFactory.getOrderService());
            case CommandName.ADMINISTRATION_EDIT_ORDER:
                return new AdministrationEditOrderCommand(serviceFactory.getOrderService(), serviceFactory.getBookService(), serviceFactory.getUserService());
            case CommandName.ADMINISTRATION_UPDATE_ORDER:
                return new AdministrationUpdateOrderCommand(serviceFactory.getOrderService(), serviceFactory.getBookService(), transactionManager);
            case CommandName.ADMINISTRATION_SORT_ORDER:
                return new AdministrationSortOrderCommand(serviceFactory.getOrderService());
            case CommandName.ADMINISTRATION_SEARCH_ORDER:
                return new AdministrationSearchOrderCommand(serviceFactory.getOrderService());

            case CommandName.ADMINISTRATION_DISPLAY_USER:
                return new AdministrationUserListCommand(serviceFactory.getUserService());
            case CommandName.ADMINISTRATION_EDIT_USER:
                return new AdministrationEditUserCommand(serviceFactory.getUserService());
            case CommandName.ADMINISTRATION_UPDATE_USER:
                return new AdministrationUpdateUserCommand(serviceFactory.getUserService());
            case CommandName.ADMINISTRATION_SEARCH_USER:
                return new AdministrationSearchUserCommand(serviceFactory.getUserService());
            case CommandName.ADMINISTRATION_SORT_USER:
                return new AdministrationSortUserCommand(serviceFactory.getUserService());

                // Admin only
            case CommandName.ADMIN_REMOVE_BOOK:
                return new AdminRemoveBookCommand(serviceFactory.getBookService());
            case CommandName.ADMIN_REMOVE_USER:
                return new AdminRemoveUserCommand(serviceFactory.getUserService());

            //User
            case CommandName.DISPLAY_BOOK:
                return new BookStoreCommand(serviceFactory.getBookService());
            case CommandName.CONFIRM_ORDER:
                return new ConfirmOrderCommand(serviceFactory.getBookService(), serviceFactory.getOrderService(), transactionManager);
            case CommandName.USER_ORDER:
                return new UserOrderCommand(serviceFactory.getOrderService());

            //book
            case CommandName.VIEW_BOOK:
                return new ViewBookCommand(serviceFactory.getBookService());
            case CommandName.ORDER_BOOK:
                return new OrderBookCommand(serviceFactory.getBookService());
            case CommandName.SEARCH_BOOK:
                return new SearchBookCommand(serviceFactory.getBookService());
            case CommandName.SORT_BOOK:
                return new SortBookCommand(serviceFactory.getBookService());
            default:

                throw new IllegalArgumentException(CommandName.ILLEGAL_COMMAND);
        }
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().returnConnection(connection);
    }
}
