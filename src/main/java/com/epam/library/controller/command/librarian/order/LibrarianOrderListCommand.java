package com.epam.library.controller.command.librarian.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianOrderListCommand implements Command {
    private OrderService orderService;

    public LibrarianOrderListCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
                List<Order> orders = orderService.getAll();
                request.setAttribute(OrderConstant.ORDER_LIST,orders);
                page = PageLocation.LIBRARIAN_ORDER_LIST;

        }else{
            page = PageLocation.LIBRARIAN_PROFILE;
        }
        return page;
    }
}
