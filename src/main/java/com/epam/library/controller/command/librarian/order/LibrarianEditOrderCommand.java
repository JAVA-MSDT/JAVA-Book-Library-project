package com.epam.library.controller.command.librarian.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianEditOrderCommand implements Command {

    private OrderService orderService;

    public LibrarianEditOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User)request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

        if(user != null){
                String orderId = request.getParameter(OrderConstant.ORDER_ID);
                Optional<Order> optionalOrder = orderService.getById(Long.valueOf(orderId));

                optionalOrder.ifPresent(value -> request.setAttribute(OrderConstant.EDIT_ORDER, value));
                page = PageLocation.LIBRARIAN_EDIT_ORDER;
        }else {
            page = PageLocation.LIBRARIAN_ORDER_LIST;
        }
        return page;
    }


}
