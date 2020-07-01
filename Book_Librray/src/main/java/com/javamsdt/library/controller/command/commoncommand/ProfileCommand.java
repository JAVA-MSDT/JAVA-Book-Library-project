package com.javamsdt.library.controller.command.commoncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;

public class ProfileCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(PageLocation.PROFILE);
    }
}
