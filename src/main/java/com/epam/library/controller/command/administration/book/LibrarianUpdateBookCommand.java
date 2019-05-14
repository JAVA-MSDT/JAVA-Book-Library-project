package com.epam.library.controller.command.administration.book;

import com.epam.library.controller.builder.BookBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.DiffConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LibrarianUpdateBookCommand implements Command {
    private BookService bookService;
    private BookBuilderFromRequest builderFromRequest = new BookBuilderFromRequest();

    public LibrarianUpdateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page depends on the bookId, if it is null that is mean the request coming from a
     * Add Book page so we will save the new book in the database.
     * if the bookId is not null that is mean the request coming from edit book page so we will
     * update the existing book.
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        if (bookId != null && !bookId.isEmpty()) {

            Book book = builderFromRequest.buildBookToUpdate(request);
            bookService.update(book);
            request.setAttribute(DiffConstant.SUCCESS_INFO_UPDATE, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.ADMINISTRATION_EDIT_BOOK;

        } else {

            if (session.getAttribute(DiffConstant.ITEM_INSERTED) == null) { // to prevent double submit
                Book book = builderFromRequest.buildBookToAdd(request);
                bookService.save(book);
                request.setAttribute(DiffConstant.INSERT_SUCCESS, DiffConstant.READ_FROM_PROPERTIES);
                session.setAttribute(DiffConstant.ITEM_INSERTED, DiffConstant.INSERTED);
                page = PageLocation.ADMINISTRATION_EDIT_BOOK;
            } else {
                request.setAttribute(DiffConstant.DOUBLE_SUBMIT_ATTEMPT, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_EDIT_BOOK;
            }
        }
        return page;
    }

}
