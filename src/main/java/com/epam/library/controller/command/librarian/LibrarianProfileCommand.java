package com.epam.library.controller.command.librarian;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
         return PageLocation.LIBRARIAN_PROFILE;
    }
}
