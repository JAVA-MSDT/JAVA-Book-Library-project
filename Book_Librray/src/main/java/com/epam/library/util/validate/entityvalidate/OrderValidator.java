package com.epam.library.util.validate.entityvalidate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.entityconstant.OrderConstant;

public class OrderValidator {

	public static List<String> validateOrderParameter(final HttpServletRequest request){
		String orderDate = request.getParameter(OrderConstant.ORDER_DATE);
		String returningDate = request.getParameter(OrderConstant.RETURNING_DATE);
		List<String> validationList = new ArrayList<>();
		if(orderDate.compareToIgnoreCase(returningDate) > 0){
			validationList.add(DiffConstant.RETURNING_DATE_OLDER);
		}

		return validationList;
	}
}
