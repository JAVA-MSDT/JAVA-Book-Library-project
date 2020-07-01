package com.javamsdt.library.controller.command.administration.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.controller.builder.BookBuilderFromRequest;
import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.Operation;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.RedirectTo;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;
import com.javamsdt.library.util.validate.entityvalidate.BookValidator;

public class AdministrationUpdateBookCommand implements Command {
	private final static Logger logger = LogManager.getLogger();
	private BookService bookService;
	private BookBuilderFromRequest builderFromRequest = new BookBuilderFromRequest();

	public AdministrationUpdateBookCommand(final BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * @param request  from the jsp
	 * @param response to the jsp
	 * @return page depends on the bookId, if it is null that is mean the request coming from a
	 * Add Book page so we will save the new book in the database.
	 * if the bookId is not null that is mean the request coming from edit book page so we will
	 * update the existing book.
	 */
	@Override
	public CommandResult execute(final HttpServletRequest request, final HttpServletResponse response) {

		CommandResult commandResult;
		String bookId = request.getParameter(BookConstant.BOOK_ID);
		if ((bookId != null) && !bookId.isEmpty()) {
			commandResult = updateBook(request);

		} else {
			commandResult = insertBook(request);
		}
		return commandResult;
	}

	/**
	 * In case the book parameter validation fail we will forward the request with a message to the edit
	 * book page,
	 * In case the parameter validation pass we will update the specified book then send redirect to
	 * edit book page.
	 * @param request extract the book parameter for validation then building the book object to update it
	 * @return commandResult
	 */
	private CommandResult updateBook(final HttpServletRequest request){
		String operation = null;
		CommandResult commandResult = new CommandResult();
		List<String> bookValidation = BookValidator.validateBookParameter(request);
		if(bookValidation.size() == 0){
			Book book = builderFromRequest.buildBookToUpdate(request);
			try {
				bookService.update(book);
				operation = Operation.UPDATED;
			} catch (ServiceException e) {
				operation = Operation.UPDATE_FAIL;
				logger.error(e);
			}finally {
				commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_BOOK_PAGE + Operation.OPERATION_STATUS + operation);
			}
		}else {
			request.setAttribute(Operation.VALIDATION_LIST, bookValidation);
			commandResult.forward(request.getContextPath() + PageLocation.ADMINISTRATION_EDIT_BOOK);
		}

		return commandResult;
	}

	/**
	 * In case the book parameter validation fail we will forward the request with a message to the edit
	 * book page,
	 * In case the parameter validation pass we will insert the new book into the database then send redirect to
	 * edit book page.
	 * @param request extract the book parameter for validation then building the book object to update it
	 * @return commandResult
	 */
	private CommandResult insertBook(final HttpServletRequest request){
		String operation = null;
		CommandResult commandResult = new CommandResult();
		List<String> bookValidation = BookValidator.validateBookParameter(request);
		if(bookValidation.size() == 0){
			Book book = builderFromRequest.buildBookToAdd(request);
			try {
				bookService.save(book);
				operation = Operation.INSERTED;
			} catch (ServiceException e) {
				operation = Operation.INSERT_FAIL;
				logger.error(e);
			}finally {
				commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_BOOK_PAGE + Operation.OPERATION_STATUS + operation);
			}
		}else {
			request.setAttribute(Operation.VALIDATION_LIST, bookValidation);
			commandResult.forward(request.getContextPath() + PageLocation.ADMINISTRATION_EDIT_BOOK);
		}

		return commandResult;
	}
}
