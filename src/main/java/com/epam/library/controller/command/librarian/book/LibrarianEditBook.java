package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * EditBook 1.
 * 1 - This class will be triggered when you press the Edit button in the book table,
 */
public class LibrarianEditBook implements Command {

    private BookService bookService;

    public LibrarianEditBook(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

        if(user != null){
                String bookId = request.getParameter(BookConstant.BOOK_ID);
                Optional<Book> optionalBook = bookService.findById(Long.valueOf(bookId));

                optionalBook.ifPresent(value -> request.setAttribute(BookConstant.EDIT_BOOK, value));
                page = PageLocation.LIBRARIAN_EDIT_BOOK;

        }else {
            page = PageLocation.LIBRARIAN_PROFILE;
        }
        return page;
    }
}
