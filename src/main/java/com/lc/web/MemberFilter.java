package com.lc.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DELL
 * @date 2021/12/19 11:10
 */
@WebFilter(
        urlPatterns = {"/delete.do", "/logout.do",
                "/message.do", "/member.view"},
        initParams = {
                @WebInitParam(name = "LOGIN_VIEW", value = "index.jsp")
        }
)
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.LOGIN_VIEW = config.getInitParameter("LOGIN_VIEW");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute("login") != null) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(LOGIN_VIEW);
        }
    }

    @Override
    public void destroy() {
    }
}
