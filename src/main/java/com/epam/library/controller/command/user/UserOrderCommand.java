package com.epam.library.controller.command.user;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.User;
import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.user.UserOrderDisplay;
import com.epam.library.model.dto.orderservice.user.search.FindOrderByUserId;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.OrderConstant;
import com.epam.library.util.constant.entityconstant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
        List<UserOrderDisplay> orderList = searchResult(userOrderDisplays, new FindOrderByUserId(user.getId()));

        request.setAttribute(OrderConstant.ORDER_LIST, orderList);
        return new CommandResult(PageLocation.USER_ORDER);
    }


    private List<UserOrderDisplay> searchResult(List<UserOrderDisplay> orderDisplays, FindCriteria<UserOrderDisplay> findOrderIndex) {
        List<UserOrderDisplay> userOrderList = new ArrayList<>();

        for (UserOrderDisplay userOrder : orderDisplays) {
            if (findOrderIndex.isExist(userOrder)) {
                userOrderList.add(userOrder);
            }
        }
        return userOrderList;
    }
}
