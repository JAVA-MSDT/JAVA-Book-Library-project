package com.javamsdt.library.controller.command.commoncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.entity.enumeration.Role;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.List;

public class AdministrationSortUserCommand implements Command {
    private static final String SORT_CRITERIA = "type";
    private UserService userService;

    public AdministrationSortUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        String sortCriteria = request.getParameter(SORT_CRITERIA);

        List<User> userList = sortResult(sortCriteria, user);

        request.setAttribute(UserConstant.USER_LIST, userList);

        return new CommandResult(PageLocation.ADMINISTRATION_USER_LIST);
    }

    /**
     *
     * @param sortCriteria depends on it the user list will be sorted
     * @param user to check if it is role admin or librarian, each one will return different
     *             user list, because of librarian is not allowed to see admins or other librarian
     * @return return user list depends on the user role, because of librarian is not allowed to
     * see admins or other librarian
     * @throws ServiceException is something wrong happens during the query executing
     */
    private List<User> sortResult(String sortCriteria, User user) throws ServiceException {
        List<User> userList = null;

        if (sortCriteria.equalsIgnoreCase(UserConstant.NAME)) {
            if(user.getRole().equals(Role.ADMIN)){
                userList = userService.adminSortUsersByName();
            }else {
                userList = userService.sortUsersByName();
            }
        } else if (sortCriteria.equalsIgnoreCase(UserConstant.EMAIL)) {
            if(user.getRole().equals(Role.ADMIN)){
                userList = userService.adminSortUsersByEmail();
            }else {
                userList = userService.sortUsersByEmail();
            }
        }

        return userList;
    }
}
