package com.javamsdt.library.controller.command.administration.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.controller.builder.OrderBuilderFromRequest;
import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Order;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.OrderService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.TransactionManager;
import com.javamsdt.library.util.constant.Operation;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.RedirectTo;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;
import com.javamsdt.library.util.validate.entityvalidate.OrderValidator;

public class AdministrationUpdateOrderCommand implements Command {
	private final static Logger logger = LogManager.getLogger();

	private final static String TRUE = "true";
	private final static String FALSE = "false";

	private OrderService orderService;
	private BookService bookService;
	private TransactionManager transactionManager;
	private OrderBuilderFromRequest builderFromRequest = new OrderBuilderFromRequest();

	public AdministrationUpdateOrderCommand(final OrderService orderService, final BookService bookService,
			final TransactionManager transactionManager) {
		this.orderService = orderService;
		this.bookService = bookService;
		this.transactionManager = transactionManager;
	}

	/**
	 * @param response to jsp
	 * @param request  from jsp
	 * @return page depends on if the order is it for editing or for saving,
	 *         checking if the bookReturned is false we will update the existing
	 *         order, if the bookReturned is true we will update the quantity of the
	 *         book which returned by 1 then removing the order from the list.
	 */
	@Override
	public CommandResult execute(final HttpServletRequest request, final HttpServletResponse response) {
		CommandResult commandResult;
		String orderId = request.getParameter(OrderConstant.ORDER_ID);
		String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);
		String bookId = request.getParameter(OrderConstant.BOOK_ID);

		if ((orderId != null) && bookReturned.trim().equalsIgnoreCase(FALSE) && !orderId.isEmpty()) {
			commandResult = updateOrder(request);
		} else if ((orderId != null) && bookReturned.trim().equalsIgnoreCase(TRUE) && !orderId.isEmpty()) {
			commandResult = removeOrder(request, orderId, bookId);
		} else {
			commandResult = insertOrder(request);
		}
		return commandResult;
	}

	/**
	 * In case the order parameter validation fail we will forward the request with
	 * a message to the edit order page, In case of the parameter validation pass we
	 * will update the specified order then send redirect to edit order page.
	 * 
	 * @param request extract the order parameter for validation then building the
	 *                order object to update it
	 * @return commandResult
	 */
	private CommandResult updateOrder(final HttpServletRequest request) {

		String operation = null;
		CommandResult commandResult = new CommandResult();
		List<String> orderValidation = OrderValidator.validateOrderParameter(request);
		if (orderValidation.size() == 0) {
			Order order = builderFromRequest.buildToUpdate(request);
			try {
				orderService.update(order);
				operation = Operation.UPDATED;
			} catch (ServiceException e) {
				operation = Operation.UPDATE_FAIL;
				logger.error(e);
			} finally {
				commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_ORDER_PAGE
						+ Operation.OPERATION_STATUS + operation);
			}
		} else {
			request.setAttribute(Operation.VALIDATION_LIST, orderValidation);
			commandResult.forward(request.getContextPath() + PageLocation.ADMINISTRATION_EDIT_ORDER);
		}
		return commandResult;
	}

	/**
	 * In case the order parameter validation fail we will forward the request with
	 * a message to the edit order page, In case of the parameter validation pass we
	 * will insert the new order into the database then send redirect to edit order
	 * page.
	 * 
	 * @param request extract the book parameter for validation then building the
	 *                book object to update it
	 * @return commandResult
	 */
	private CommandResult insertOrder(final HttpServletRequest request) {

		String operation = null;
		CommandResult commandResult = new CommandResult();
		List<String> orderValidation = OrderValidator.validateOrderParameter(request);
		if (orderValidation.size() == 0) {
			Order order = builderFromRequest.buildToAdd(request);
			try {
				orderService.save(order);
				operation = Operation.INSERTED;
			} catch (ServiceException e) {
				operation = Operation.INSERT_FAIL;
				logger.error(e);
			} finally {
				commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_ORDER_PAGE
						+ Operation.OPERATION_STATUS + operation);
			}
		} else {
			request.setAttribute(Operation.VALIDATION_LIST, orderValidation);
			commandResult.forward(request.getContextPath() + PageLocation.ADMINISTRATION_EDIT_ORDER);
		}
		return commandResult;
	}

	/**
	 *
	 * @param orderId to be removed
	 * @param bookId  to get the returning book and updated it by 1 after removing
	 *                the order
	 * @return commandResult
	 */
	private CommandResult removeOrder(final HttpServletRequest request, final String orderId, final String bookId) {
		String operation = null;
		CommandResult commandResult = new CommandResult();
		try {
			orderService.administrationOrderRemoval(orderId, bookId, bookService, transactionManager);
			operation = Operation.REMOVED;
		} catch (ServiceException e) {
			operation = Operation.REMOVE_FAIL;
			logger.error(e);
		} finally {
			commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_ORDER_PAGE
					+ Operation.OPERATION_STATUS + operation);
		}
		return commandResult;
	}

}
