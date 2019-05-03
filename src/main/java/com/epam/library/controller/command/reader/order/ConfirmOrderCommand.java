package com.epam.library.controller.command.reader.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmOrderCommand implements Command {
    private BookService bookService;
    private OrderService orderService;
    private OrderBuilder orderBuilder = new OrderBuilder();

    public ConfirmOrderCommand(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        if(user != null){
            Optional<Book> optionalBook = bookService.findById(Long.valueOf(bookId));
            Order order = orderBuilder.readerOrder(request, optionalBook.get(), user);
            orderService.save(order);
            updateBookQuantity(optionalBook.get());
        }
        return PageLocation.VIEW_BOOK;
    }

    /**
     * To update the book quantity in the data base
     * @param book that the user want to order it
     * @throws ServiceException if something wrong with updating method
     */
    private void updateBookQuantity(Book book) throws ServiceException {
        Long id = book.getId();
        int quantity = book.getQuantity();
        int updatedQuantity = quantity - 1;
        bookService.updateQuantity(id, updatedQuantity);
    }
}
