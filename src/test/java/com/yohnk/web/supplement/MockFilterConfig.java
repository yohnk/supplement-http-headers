package com.yohnk.web.supplement;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ryanyohnk on 3/7/17.
 */
public class MockFilterConfig implements FilterConfig{
    private Map<String, String> params;

    public MockFilterConfig(Map<String, String> params){
        this.params = params;
    }

    public String getFilterName() {
        return "";
    }

    public ServletContext getServletContext() {
        return null;
    }

    public String getInitParameter(String s) {
        return params.get(s);
    }

    public Enumeration getInitParameterNames() {
        return new Vector<String>(params.keySet()).elements();
    }
}
