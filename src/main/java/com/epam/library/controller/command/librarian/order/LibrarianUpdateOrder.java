package com.epam.library.controller.command.librarian.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianUpdateOrder implements Command {
    private OrderService orderService;
    private OrderBuilder orderBuilder = new OrderBuilder();

    public LibrarianUpdateOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
            String orderId = request.getParameter(OrderConstant.ORDER_ID);
           page = updateOrSave(orderId, request);
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
    private String updateOrSave(String orderId, HttpServletRequest request) throws ServiceException {
        String page;

        if(orderId != null){
            Order order = orderBuilder.buildToUpdate(request);
            orderService.update(order);
            page = PageLocation.LIBRARIAN_EDIT_ORDER;
        }else {
            Order order = orderBuilder.buildToAdd(request);
            orderService.save(order);
            page = PageLocation.LIBRARIAN_ADD_ORDER;
        }
        return page;
    }
}
