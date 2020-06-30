package com.epam.library.controller.command.commoncommand;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;
import com.epam.library.model.dto.orderservice.adminstration.sort.*;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdministrationSortOrderCommand implements Command {
    private static final String SORT_CRITERIA = "type";
    private final static String BOOK_NAME = "bookName";
    private final static String USER_NAME = "userName";
    private final static String USER_EMAIL = "email";
    private static final String ORDER_DATE = "orderDate";
    private final static String RETURNING_DATE = "returningDate";
    private final static String READING_PLACE = "readingPlace";

    private OrderService orderService;

    public AdministrationSortOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String sortCriteria = request.getParameter(SORT_CRITERIA);
        List<AdministrationOrderDisplay> orders = orderService.administrationAllOrder();
        sort(sortCriteria, orders);
        request.setAttribute(OrderConstant.ORDER_LIST, orders);
        return new CommandResult(PageLocation.ADMINISTRATION_ORDER_LIST);
    }

    private void sort(String criteria, List<AdministrationOrderDisplay> orderDisplayList) {
        switch (criteria) {
            case BOOK_NAME:
                orderDisplayList.sort(new SortOrderByBookName());
                break;
            case USER_NAME:
                orderDisplayList.sort(new SortOrderByUserName());
                break;
            case USER_EMAIL:
                orderDisplayList.sort(new SortOrderByUserEmail());
                break;
            case ORDER_DATE:
                orderDisplayList.sort(new SortOrderByOrderDate());
                break;
            case RETURNING_DATE:
                orderDisplayList.sort(new SortOrderByReturningDate());
                break;
            case READING_PLACE:
                orderDisplayList.sort(new SortOrderByReadingPlace());
                break;
        }
    }
}
