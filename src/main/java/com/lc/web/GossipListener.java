package com.lc.web;

import com.lc.model.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author DELL
 * @date 2021/12/19 11:29
 */
@WebListener
public class GossipListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String USERS = sce.getServletContext().getInitParameter("USERS");
        context.setAttribute("userService", new UserService(USERS));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
