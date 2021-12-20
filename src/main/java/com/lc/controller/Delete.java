package com.lc.controller;

import com.lc.model.Blah;
import com.lc.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author DELL
 * @date 2021/12/16 18:40
 */
@WebServlet(
        urlPatterns = {"/delete.do"},
        initParams = {
                @WebInitParam(name = "SUCCESS_VIEW", value = "message.do")
        }
)
public class Delete extends HttpServlet {
    private String SUCCESS_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("login");
        String message = request.getParameter("message");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date(Long.parseLong(message)));
        userService.deleteBlah(blah);
        response.sendRedirect(SUCCESS_VIEW);
    }
}
