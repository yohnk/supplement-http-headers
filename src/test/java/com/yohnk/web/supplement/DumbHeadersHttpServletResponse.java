package com.yohnk.web.supplement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by ryanyohnk on 3/7/17.
 */
public class DumbHeadersHttpServletResponse extends HttpServletResponseWrapper {
    private HashMap<String, String> headers;

    public DumbHeadersHttpServletResponse(HttpServletResponse response){
        super(response);
        headers = new HashMap<String, String>();
    }

    @Override
    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        setHeader(name, value);
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return Collections.singleton(getHeader(name));
    }

    public Collection<String> getHeaderNames() {
        return headers.keySet();
    }
}
