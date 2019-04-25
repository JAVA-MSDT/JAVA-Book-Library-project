package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.util.PageLocation;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edit Book 2.
 * 2 - After triggering the edit book class you will be inside the jsp page which holds a form with the book info
 * to be edited after editing the book and pressing save update this class will be triggered to save the
 * update in the database.
 */
public class LibrarianUpdateBook implements Command {
    private BookService bookService = ServiceFactory.getInstance().getBookService();
    private BookBuilder bookBuilder = new BookBuilder();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if (user != null) {
            request.setAttribute(UserConstant.USER_ATTRIBUTE, user);
            try {
                String bookId = request.getParameter(BookConstant.BOOK_ID);
                page = findPage(bookId, request);
            } catch (ServiceException e) {
                page = PageLocation.LIBRARIAN_BOOK_STORE;
            }
        }
        return page;
    }


    private String findPage(String bookId, HttpServletRequest request) throws ServiceException {
        String page;
        if (bookId != null) {

            Book book = bookBuilder.buildBookToUpdate(request);
            bookService.updateBook(book);
            page = PageLocation.LIBRARIAN_EDIT_BOOK;
        } else {

            Book book = bookBuilder.buildBookToAdd(request);
            bookService.saveBook(book);
            page = PageLocation.LIBRARIAN_ADD_BOOK;
        }
        return page;
    }
}
