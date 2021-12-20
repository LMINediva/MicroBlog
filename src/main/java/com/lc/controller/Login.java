package com.lc.controller;

import com.lc.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DELL
 * @date 2021/12/13 12:19
 */
@WebServlet(
        urlPatterns = {"/login.do"},
        initParams = {
                @WebInitParam(name = "SUCCESS_VIEW", value = "message.do"),
                @WebInitParam(name = "ERROR_VIEW", value = "index.html")
        }
)
public class Login extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String page = ERROR_VIEW;

        UserService userService = (UserService) getServletContext().getAttribute("userService");
        if (userService.checkLogin(username, password)) {
            request.getSession().setAttribute("login", username);
            page = SUCCESS_VIEW;
        }
        response.sendRedirect(page);
    }
}
