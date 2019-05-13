package com.epam.library.controller.command.administration.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Order;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianEditOrderCommand implements Command {

    private OrderService orderService;

    public LibrarianEditOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds a form that has the information about a specific order to be edited
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        if(orderId != null){
            Optional<Order> optionalOrder = orderService.getById(Long.valueOf(orderId));
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                request.setAttribute(OrderConstant.EDIT_ORDER, order);
                page = PageLocation.ADMINISTRATION_EDIT_ORDER;
            } else {
                request.setAttribute(OrderConstant.ORDER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_ORDER_LIST;
            }
        } else {
            page = PageLocation.ADMINISTRATION_EDIT_ORDER;
        }

        return page;
    }


}
