package com.javamsdt.library.controller.command.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.entity.enumeration.Role;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.List;
import java.util.Objects;

public class SearchBookCommand implements Command {
    private final static String SEARCH_VALUE = "query";
    private final static String SEARCH_CRITERIA = "type";

    private BookService bookService;

    public SearchBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

        String searchValue = request.getParameter(SEARCH_VALUE);
        String searchCriteria = request.getParameter(SEARCH_CRITERIA);


        List<Book> bookList = findBook(searchCriteria, searchValue);

        if (Objects.requireNonNull(bookList).size() > 0) {
            request.setAttribute(BookConstant.BOOK_LIST, bookList);
        } else {
            request.setAttribute(BookConstant.BOOK_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
        }

        return commandResult(user);
    }


    /**
     * @param searchCriteria to be checked if it is matched the book variable.
     * @param searchValue    to be checked if it is stored in that book variable
     * @return user depends on the given criteria and value
     * @throws ServiceException if something wrong during communicating with the database
     */
    private List<Book> findBook(String searchCriteria, String searchValue) throws ServiceException {
        List<Book> bookList = null;

/*        switch (searchCriteria) { // for future use if i have more search criteria i will use the switch instead of if statement.
            case BookConstant.BOOK_NAME:
                bookList = bookService.findByName(searchValue);
        }*/
        if (searchCriteria.equalsIgnoreCase(BookConstant.BOOK_NAME)) {
            bookList = bookService.findByName(searchValue);
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
