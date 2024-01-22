package com.javamsdt.library.controller.command.commoncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdministrationSearchUserCommand implements Command {

    private final static String SEARCH_VALUE = "query";
    private final static String SEARCH_CRITERIA = "type";
    private UserService userService;

    public AdministrationSearchUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String searchValue = request.getParameter(SEARCH_VALUE);
        String searchCriteria = request.getParameter(SEARCH_CRITERIA);

        Optional<User> optionalUser = findUser(searchCriteria, searchValue);

        List<User> userList = new ArrayList<>();
        if (optionalUser.isPresent()) {
            userList.add(optionalUser.get());
            request.setAttribute(UserConstant.USER_LIST, userList);
        } else {
            request.setAttribute(UserConstant.USER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
        }
        return new CommandResult(PageLocation.ADMINISTRATION_USER_LIST);
    }

    /**
     * @param searchCriteria to be checked if it is matched the user variable.
     * @param searchValue    to be checked if it is stored in that user variable
     * @return user depends on the given criteria and value
     * @throws ServiceException if something wrong during communicating with the database
     */
    private Optional<User> findUser(String searchCriteria, String searchValue) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        if (searchCriteria.equalsIgnoreCase(UserConstant.LOGIN)) {
            optionalUser = userService.findByLogin(searchValue);
        } else if (searchCriteria.equalsIgnoreCase(UserConstant.EMAIL)) {
            optionalUser = userService.findByEmail(searchValue);
        }
        return optionalUser;
    }
}
