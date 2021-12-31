package com.lc.web;

import com.lc.model.AccountDAOJdbcImpl;
import com.lc.model.BlahDAOJdbcImpl;
import com.lc.model.QQMailCarrier;
import com.lc.model.UserService;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author DELL
 * @date 2021/12/19 11:29
 */
@WebListener
public class GossipListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource =
                    (DataSource) envContext.lookup("jdbc/gossip");
            ServletContext context = sce.getServletContext();
            Properties props = new Properties();
            props.load(
                    context.getResourceAsStream("/WEB-INF/mail.properties"));
            UserService userService = new UserService(
                    new AccountDAOJdbcImpl(dataSource),
                    new BlahDAOJdbcImpl(dataSource),
                    new QQMailCarrier(props));
            userService.setTemplate(getHtmlTemplate(context, props));
            context.setAttribute("userService", userService);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHtmlTemplate(ServletContext context, Properties props) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getResourceAsStream(
                            props.getProperty("com.lc.template")), StandardCharsets.UTF_8));
            StringBuilder template = new StringBuilder();
            String text = null;
            while ((text = reader.readLine()) != null) {
                template.append(text);
            }
            return template.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Enumeration drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = (Driver) drivers.nextElement();
                DriverManager.deregisterDriver(driver);
                System.out.println("注销JDBC驱动：" + driver);
            }
            AbandonedConnectionCleanupThread.uncheckedShutdown();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("销毁工作异常");
        }
    }
}
