package com.javamsdt.library.controller.command.user.order;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.builder.OrderBuilderFromRequest;
import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.entity.Order;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.OrderService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.TransactionManager;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.Operation;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.RedirectTo;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;
import com.javamsdt.library.util.validate.entityvalidate.OrderValidator;

public class ConfirmOrderCommand implements Command {

	private BookService bookService;
	private OrderService orderService;
	private TransactionManager transactionManager;
	private OrderBuilderFromRequest builderFromRequest = new OrderBuilderFromRequest();

	public ConfirmOrderCommand(final BookService bookService, final OrderService orderService, final TransactionManager transactionManager) {
		this.bookService = bookService;
		this.orderService = orderService;
		this.transactionManager = transactionManager;
	}

	/**
	 * @param request  from the jsp
	 * @param response to the jsp
	 * @return page which confirms that order is done depends on the userSession in the library
	 * using the book and user info to build order from the request.
	 * @throws ServiceException if something wrong during the connection with database
	 */
	@Override
	public CommandResult execute(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {

		CommandResult commandResult = new CommandResult();
		User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

		if (user != null) {
			List<String> orderValidation = OrderValidator.validateOrderParameter(request);
			if (orderValidation.size() == 0) {

				commandResult = confirmOrder(request, user.getId());
			}else {

				request.setAttribute(Operation.VALIDATION_LIST, orderValidation);
				commandResult.forward(request.getContextPath() + PageLocation.VIEW_BOOK);
			}
		}
		return commandResult;
	}


	private CommandResult confirmOrder(final HttpServletRequest request, final Long userId) throws ServiceException {
		String operation;
		CommandResult commandResult = new CommandResult();
		String bookId = request.getParameter(BookConstant.BOOK_ID);
		Optional<Book> optionalBook = bookService.getById(Long.valueOf(bookId));

		if (optionalBook.isPresent()) {

			Book book = optionalBook.get();
			Order order = builderFromRequest.userOrder(request, book.getId(), userId);
			orderService.confirmUserOrder(order, book, bookService, transactionManager);
			operation = Operation.ORDER_CONFIRMED;
			commandResult.redirect(request.getContextPath() + RedirectTo.BOOK_LIST_PAGE + Operation.OPERATION_STATUS + operation);
		} else {

			request.setAttribute(Operation.CONFIRM_FAIL, DiffConstant.READ_FROM_PROPERTIES);
			commandResult.forward(request.getContextPath() + PageLocation.VIEW_BOOK);
		}
		return commandResult;
	}
}
