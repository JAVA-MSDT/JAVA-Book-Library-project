package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.builder.BookBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        if (bookId != null && !bookId.isEmpty()) {
            Book book = builderFromRequest.buildBookToUpdate(request);
            bookService.update(book);
            page = PageLocation.ADMINISTRATION_EDIT_BOOK;
        } else {
            Book book = builderFromRequest.buildBookToAdd(request);
            bookService.save(book);
            page = PageLocation.ADMINISTRATION_EDIT_BOOK;
        }
        return page;
    }

}
