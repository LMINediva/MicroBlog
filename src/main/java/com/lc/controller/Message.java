package com.lc.controller;

import com.lc.model.Blah;
import com.lc.model.UserService;
import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2021/12/16 17:38
 */
@WebServlet(
        urlPatterns = {"/message.do"},
        initParams = {
                @WebInitParam(name = "MEMBER_VIEW", value = "member.jsp")
        }
)
public class Message extends HttpServlet {
    private String MEMBER_VIEW;

    @Override
    public void init() throws ServletException {
        MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("login");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Blah blah = new Blah();
        blah.setUsername(username);

        String blabla = StringEscapeUtils.unescapeHtml(request.getParameter("blabla"));
        if (blabla != null && blabla.length() != 0) {
            if (blabla.length() < 140) {
                blah.setDate(new Date());
                blah.setTxt(blabla);
                userService.addBlah(blah);
            } else {
                request.setAttribute("blabla", blabla);
            }
        }
        List<Blah> blahs = userService.getBlahs(blah);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
