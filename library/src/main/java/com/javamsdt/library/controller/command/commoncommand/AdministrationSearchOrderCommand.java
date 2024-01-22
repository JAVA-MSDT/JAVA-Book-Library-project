package com.javamsdt.library.controller.command.commoncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.model.dto.orderservice.AdministrationOrderDisplay;
import com.javamsdt.library.model.service.OrderService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.EnumService;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdministrationSearchOrderCommand implements Command {
    private final static String SEARCH_VALUE = "query";
    private final static String SEARCH_CRITERIA = "type";
    private final static String BOOK_NAME = "bookName";
    private final static String USER_NAME = "userName";
    private final static String USER_EMAIL = "email";
    private static final String ORDER_DATE = "orderDate";
    private final static String RETURNING_DATE = "returningDate";
    private final static String READING_PLACE = "readingPlace";

    private OrderService orderService;

    public AdministrationSearchOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String searchValue = request.getParameter(SEARCH_VALUE);
        String searchCriteria = request.getParameter(SEARCH_CRITERIA);

        List<AdministrationOrderDisplay> orders = orderService.administrationAllOrder();
        List<AdministrationOrderDisplay> searchResult = findOrder(searchCriteria, searchValue, orders);
        if (searchResult != null) {
            request.setAttribute(OrderConstant.ORDER_LIST, searchResult);
        } else {
            request.setAttribute(OrderConstant.ORDER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
        }

        return new CommandResult(PageLocation.ADMINISTRATION_ORDER_LIST);
    }

    /**
     * @param criteria         to be checked if it is matched the AdministrationOrderDisplay variable.
     * @param query            to check if it is in the list or not
     * @param orderDisplayList to search inside it for the given query by the given criteria
     * @return list of AdministrationOrderDisplay depends on the search criteria and query
     */
    private List<AdministrationOrderDisplay> findOrder(String criteria, String query, List<AdministrationOrderDisplay> orderDisplayList) {

        List<AdministrationOrderDisplay> result;
        switch (criteria) {
            case BOOK_NAME:
                result = orderDisplayList.stream()
                        .filter(administrationOrderDisplay -> administrationOrderDisplay.getBookName().toLowerCase().contains(query.toLowerCase()))
                        .collect(Collectors.toList());

                break;
            case USER_NAME:
                result = orderDisplayList.stream().
                        filter(administrationOrderDisplay -> administrationOrderDisplay.getUserName().toLowerCase().equalsIgnoreCase(query.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case USER_EMAIL:
                result = orderDisplayList.stream()
                        .filter(administrationOrderDisplay -> administrationOrderDisplay.getUserEmail().toLowerCase().contains(query.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case ORDER_DATE:
                Date orderDate = Date.valueOf(query);
                result = orderDisplayList.stream()
                        .filter(administrationOrderDisplay -> administrationOrderDisplay.getOrderDate().equals(orderDate))
                        .collect(Collectors.toList());

                break;
            case RETURNING_DATE:
                Date returningDate = Date.valueOf(query);
                result = orderDisplayList.stream()
                        .filter(administrationOrderDisplay -> administrationOrderDisplay.getReturningDate().equals(returningDate))
                        .collect(Collectors.toList());
                break;
            case READING_PLACE:
                ReadingPlace readingPlace = EnumService.getReadingPlace(query);
                result = orderDisplayList.stream()
                        .filter(administrationOrderDisplay -> administrationOrderDisplay.getReadingPlace().equals(readingPlace))
                        .collect(Collectors.toList());
                break;
            default:
                result = orderDisplayList;
        }
        return result;
    }

}
