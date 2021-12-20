package com.lc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * @author DELL
 * @date 2021/12/17 22:47
 */
public class EncodingWrapper extends HttpServletRequestWrapper {
    private String ENCODING;

    public EncodingWrapper(HttpServletRequest request, String ENCODING) {
        super(request);
        this.ENCODING = ENCODING;
    }

    @Override
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);
        if (value != null) {
            try {
                byte[] b = value.getBytes("ISO-8859-1");
                value = new String(b, ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }
}
