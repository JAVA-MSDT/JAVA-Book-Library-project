package com.javamsdt.library.util.validate.entityvalidate;

import javax.servlet.http.HttpServletRequest;

import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;
import com.javamsdt.library.util.validate.DataMatcher;
import com.javamsdt.library.util.validate.DataRegex;

import java.util.ArrayList;
import java.util.List;

public class OrderValidator {

    public static List<String> validateOrderParameter(HttpServletRequest request){
        String orderDate = request.getParameter(OrderConstant.ORDER_DATE);
        String returningDate = request.getParameter(OrderConstant.RETURNING_DATE);
        List<String> validationList = new ArrayList<>();
        if(orderDate.compareToIgnoreCase(returningDate) > 0){
            validationList.add(DiffConstant.RETURNING_DATE_OLDER);
        }

        return validationList;
    }
}
