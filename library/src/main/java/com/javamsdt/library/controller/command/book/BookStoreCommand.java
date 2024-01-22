package com.javamsdt.library.controller.command.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;

import java.util.List;

public class BookStoreCommand implements Command {

    private BookService bookService;

    public BookStoreCommand(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds the information about the books to display them on the page for users
     * or visitors
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Book> books = bookService.getAll();
        request.setAttribute(BookConstant.BOOK_LIST, books);

        return new CommandResult(PageLocation.BOOK_STORE);
    }
}
