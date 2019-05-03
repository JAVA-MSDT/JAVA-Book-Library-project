package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.Book;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.util.constant.BookConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edit Book 2.
 * 2 - After triggering the edit book class you will be inside the jsp page which holds a form with the book info
 * to be edited after editing the book and pressing save update this class will be triggered to save the
 * update in the database.
 */
public class LibrarianUpdateBookCommand implements Command {

    private BookService bookService;
    private BookBuilder bookBuilder = new BookBuilder();

    public LibrarianUpdateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String bookId = request.getParameter(BookConstant.BOOK_ID);

        return updateOrSave(bookId, request);
    }


    /**
     * @param bookId  to be checked for null value
     * @param request to use for book building
     * @return page depends on if the book is it for editing or for saving
     * @throws ServiceException is something  wrong happens during book update or save
     */
    private String updateOrSave(String bookId, HttpServletRequest request) throws ServiceException {
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
