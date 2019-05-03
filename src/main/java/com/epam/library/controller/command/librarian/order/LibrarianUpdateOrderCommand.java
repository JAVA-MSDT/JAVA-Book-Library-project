package com.epam.library.controller.command.librarian.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianUpdateOrderCommand implements Command {
    private OrderService orderService;
    private BookService bookService;
    private OrderBuilder orderBuilder = new OrderBuilder();

    public LibrarianUpdateOrderCommand(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
            String orderId = request.getParameter(OrderConstant.ORDER_ID);
            String returned = request.getParameter(OrderConstant.BOOK_RETURNED);
            String bookId = request.getParameter(OrderConstant.BOOK_ID);
           page = updateOrSave(orderId, returned, bookId, request);
        }else {
            page = PageLocation.LOGIN_PAGE;
        }
        return page;
    }


    /**
     *
     * @param orderId to be checked for null value
     * @param request to use for order building
     * @return page depends on if the order is it for editing or for saving
     * @throws ServiceException is something  wrong happens during order update or save
     */
    private String updateOrSave(String orderId, String returned, String bookId, HttpServletRequest request) throws ServiceException {
        String page;

        if(orderId != null && returned.equalsIgnoreCase("false")){
            Order order = orderBuilder.buildToUpdate(request);
            orderService.update(order);
            page = PageLocation.LIBRARIAN_EDIT_ORDER;
        }else if(orderId != null && returned.equalsIgnoreCase("true")){
            updateBookQuantity(bookId);
            orderService.removeById(Long.valueOf(orderId));
            page = PageLocation.LIBRARIAN_EDIT_ORDER;
        } else {
            Order order = orderBuilder.buildToAdd(request);
            orderService.save(order);
            page = PageLocation.LIBRARIAN_ADD_ORDER;
        }
        return page;
    }


    private void updateBookQuantity(String bookId) throws ServiceException {
        long id = Long.valueOf(bookId);
        Optional<Book> optionalBook = bookService.findById(id);
        Book book = optionalBook.get();
        int updatedQuantity = book.getQuantity() + 1;
        bookService.updateQuantity(id, updatedQuantity);
    }
}
