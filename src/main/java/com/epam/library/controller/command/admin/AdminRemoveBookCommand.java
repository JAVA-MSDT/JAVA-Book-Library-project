package com.epam.library.controller.command.admin;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.Operation;
import com.epam.library.util.constant.RedirectTo;
import com.epam.library.util.constant.entityconstant.BookConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class AdminRemoveBookCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    private BookService bookService;


    public AdminRemoveBookCommand(BookService bookService) {
        this.bookService = bookService;

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CommandResult commandResult = new CommandResult();
        String operation;
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        List<Book> books = bookService.getAll();
        try {
            bookService.removeById(Long.valueOf(bookId));
            operation = Operation.REMOVED;
        } catch (ServiceException e) {
            operation = Operation.REMOVE_FAIL;
            logger.error(e);
        }
        request.setAttribute(BookConstant.BOOK_LIST, books);
        commandResult.redirect(RedirectTo.ADMINISTRATION_BOOK_STORE_PAGE + Operation.OPERATION_STATUS + operation);
        return commandResult;
    }
}
