package com.javamsdt.library.controller.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.dto.orderservice.UserOrderDisplay;
import com.javamsdt.library.model.service.OrderService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.List;
import java.util.stream.Collectors;

public class UserOrderCommand implements Command {
    private OrderService orderService;

    public UserOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds the orders of that user
     * @throws ServiceException if something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        List<UserOrderDisplay> userOrderDisplays = orderService.userOrders();

        List<UserOrderDisplay> orderList = userOrderDisplays.stream()
                .filter(userOrderDisplay -> userOrderDisplay.getUserId() == user.getId())
                .collect(Collectors.toList());

        request.setAttribute(OrderConstant.ORDER_LIST, orderList);
        return new CommandResult(PageLocation.USER_ORDER);
    }

}
