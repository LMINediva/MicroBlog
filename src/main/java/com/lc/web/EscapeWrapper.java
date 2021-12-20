package com.lc.web;

import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author DELL
 * @date 2021/12/17 20:14
 */
public class EscapeWrapper extends HttpServletRequestWrapper {

    public EscapeWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);
        return StringEscapeUtils.escapeHtml(value);
    }
}
