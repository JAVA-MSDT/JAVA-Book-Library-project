package com.epam.library.controller.command.book;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.BookConstant;
import com.epam.library.util.constant.entityconstant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortBookCommand implements Command {
    private static final String SORT_CRITERIA = "type";

    private BookService bookService;

    public SortBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        String sortCriteria = request.getParameter(SORT_CRITERIA);
        List<Book> bookList = sortedBookList(sortCriteria);
        request.setAttribute(BookConstant.BOOK_LIST, bookList);
        return commandResult(user);
    }

    /**
     * @param sortCriteria to be checked if it is matched the book variable.
     * @return Sorted Book List depends on the sort criteria
     * @throws ServiceException if something wrong during communicating with the database
     */
    private List<Book> sortedBookList(String sortCriteria) throws ServiceException {

        List<Book> bookList = null;
        if (sortCriteria.equalsIgnoreCase(BookConstant.BOOK_NAME)) {
            bookList = bookService.sortBooksByName();
        } else if (sortCriteria.equalsIgnoreCase(BookConstant.BOOK_QUANTITY)) {
            bookList = bookService.sortBookByQuantity();
        }
        return bookList;
    }

    /**
     * @param user to check it is role and if it is null
     * @return page depends on the user role or if it is null
     */
    private CommandResult commandResult(User user) {
        String page;
        if (user == null) {
            page = PageLocation.BOOK_STORE;
        } else if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.LIBRARIAN)) {
            page = PageLocation.ADMINISTRATION_BOOK_STORE;
        } else {
            page = PageLocation.BOOK_STORE;
        }
        return new CommandResult(page);
    }
}
