package com.epam.library.controller.command.administration.book;

import com.epam.library.controller.builder.BookBuilderFromRequest;
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

public class AdministrationUpdateBookCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    private BookService bookService;
    private BookBuilderFromRequest builderFromRequest = new BookBuilderFromRequest();

    public AdministrationUpdateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page depends on the bookId, if it is null that is mean the request coming from a
     * Add Book page so we will save the new book in the database.
     * if the bookId is not null that is mean the request coming from edit book page so we will
     * update the existing book.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String operation;
        CommandResult commandResult = new CommandResult();
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        if (bookId != null && !bookId.isEmpty()) {

            Book book = builderFromRequest.buildBookToUpdate(request);
            try {
                bookService.update(book);
                operation = Operation.UPDATED;
            } catch (ServiceException e) {
                operation = Operation.UPDATE_FAIL;
                logger.error(e);
            }

        } else {

            Book book = builderFromRequest.buildBookToAdd(request);
            try {
                bookService.save(book);
                operation = Operation.INSERTED;
            } catch (ServiceException e) {
                operation = Operation.INSERT_FAIL;
                logger.error(e);
            }

        }
        commandResult.redirect(RedirectTo.ADMINISTRATION_EDIT_BOOK_PAGE + Operation.OPERATION_STATUS + operation);
        return commandResult;
    }

}
