package com.epam.library.controller.command.librarian;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.Book;
import com.epam.library.model.dao.BookDao;
import com.epam.library.model.dao.DaoException;
import com.epam.library.model.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianBookStore implements Command {
    private BookDao bookDao = DaoFactory.getInstance().getBookDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Book> books = bookDao.getAll();
            request.setAttribute("bookList", books);

        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }


}
