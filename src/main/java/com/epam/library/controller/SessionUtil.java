package com.epam.library.controller;

import com.epam.library.entity.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void storeReaderSession(HttpSession session, User user){
        session.setAttribute("logged", user);
    }

    public static User getReaderFromSession(HttpSession session){
        return (User) session.getAttribute("logged");
    }
}
