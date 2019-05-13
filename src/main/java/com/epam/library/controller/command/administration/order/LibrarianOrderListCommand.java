package com.epam.library.controller.command.administration.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;
import com.epam.library.util.constant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianOrderListCommand implements Command {
    private OrderService orderService;

    public LibrarianOrderListCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds the information about the orders to display them on the page, for the
     * administration to control them, adding or editing
     * @throws ServiceException if something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<AdministrationOrderDisplay> orders = orderService.administrationAllOrder();
        request.setAttribute(OrderConstant.ORDER_LIST, orders);
        return PageLocation.ADMINISTRATION_ORDER_LIST;

    }
}
