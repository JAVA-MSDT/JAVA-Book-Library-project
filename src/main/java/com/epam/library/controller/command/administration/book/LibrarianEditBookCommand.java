package com.epam.library.controller.command.administration.book;

import com.epam.library.controller.command.Command;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.entityconstant.BookConstant;
import com.epam.library.util.constant.DiffConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * EditBook 1.
 * 1 - This class will be triggered when you press the Edit button in the book table,
 */
public class LibrarianEditBookCommand implements Command {

    private BookService bookService;

    public LibrarianEditBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds a form that has the information about a specific book to be edited
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        session.removeAttribute(DiffConstant.ITEM_INSERTED); // to remove the lock after double submit attempt
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        if(bookId != null){
            Optional<Book> optionalBook = bookService.getById(Long.valueOf(bookId));
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                request.setAttribute(BookConstant.EDIT_BOOK, book);
                page = PageLocation.ADMINISTRATION_EDIT_BOOK;

            } else {
                request.setAttribute(BookConstant.BOOK_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_BOOK_STORE;
            }
        }else {
            page = PageLocation.ADMINISTRATION_EDIT_BOOK;
        }

        return page;
    }

}
