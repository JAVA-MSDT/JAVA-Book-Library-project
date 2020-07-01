package com.javamsdt.library.controller.command.administration.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.model.dto.orderservice.AdministrationOrderDisplay;
import com.javamsdt.library.model.service.OrderService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;

import java.util.List;

public class AdministrationOrderListCommand implements Command {
    private OrderService orderService;

    public AdministrationOrderListCommand(OrderService orderService) {
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
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<AdministrationOrderDisplay> orders = orderService.administrationAllOrder();
        request.setAttribute(OrderConstant.ORDER_LIST, orders);
        return new CommandResult(PageLocation.ADMINISTRATION_ORDER_LIST);

    }
}
