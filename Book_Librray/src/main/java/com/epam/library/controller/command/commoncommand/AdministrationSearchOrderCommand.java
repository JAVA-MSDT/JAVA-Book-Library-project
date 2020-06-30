package com.epam.library.controller.command.commoncommand;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;
import com.epam.library.model.dto.orderservice.adminstration.search.*;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.EnumService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
                result = searchResult(orderDisplayList, new FindByBookName(query));
                break;
            case USER_NAME:
                result = searchResult(orderDisplayList, new FindByUserName(query));
                break;
            case USER_EMAIL:
                result = searchResult(orderDisplayList, new FindByEmail(query));
                break;
            case ORDER_DATE:
                Date orderDate = Date.valueOf(query);
                result = searchResult(orderDisplayList, new FindOrderByDate(orderDate));
                break;
            case RETURNING_DATE:
                Date returningDate = Date.valueOf(query);
                result = searchResult(orderDisplayList, new FindByReturningDate(returningDate));
                break;
            case READING_PLACE:
                ReadingPlace readingPlace = EnumService.getReadingPlace(query);
                result = searchResult(orderDisplayList, new FindByReadingPlace(readingPlace));
                break;
            default:
                result = null;
        }
        return result;
    }


    /**
     * Helper Method to not repeat the same code in the findOrder method
     *
     * @param orderDisplays  to search inside it for the given value
     * @param findCriteria object that will compare the value if it is in the list or not
     * @return list of AdministrationOrderDisplay depends on the search value
     */
    private List<AdministrationOrderDisplay> searchResult(List<AdministrationOrderDisplay> orderDisplays, FindCriteria<AdministrationOrderDisplay> findCriteria) {
        List<AdministrationOrderDisplay> result = new ArrayList<>();

        for (AdministrationOrderDisplay orderDisplay : orderDisplays) {
            if (findCriteria.isExist(orderDisplay)) {
                result.add(orderDisplay);
            }
        }

        return result;
    }

}
