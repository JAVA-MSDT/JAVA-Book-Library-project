package com.epam.library.controller.command.librarian;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianProfileCommand implements Command {

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return librarian profile page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageLocation.LIBRARIAN_PROFILE;
    }
}
