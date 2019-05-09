package com.epam.library.controller.command.admin;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminRemoveBookCommand implements Command {

    private BookService bookService;

    public AdminRemoveBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        bookService.removeById(Long.valueOf(bookId));

        List<Book> books = bookService.getAll();
        request.setAttribute(BookConstant.BOOK_LIST, books);
        return PageLocation.ADMINISTRATION_BOOK_STORE;
    }
}
