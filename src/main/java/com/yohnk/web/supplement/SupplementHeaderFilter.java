package com.yohnk.web.supplement;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by ryanyohnk on 3/7/17.
 */
public class SupplementHeaderFilter implements Filter {
    private List<SupplementValue> values;

    public void init(FilterConfig fc) throws ServletException {
        values = valuesFromMap(mapFromConfig(fc));
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {

        HttpServletResponse httpRes = (HttpServletResponse) res;

        for(SupplementValue supp : values) {
            if(blank(httpRes.getHeader(supp.getHeader()))) {
                httpRes.setHeader(supp.getHeader(), supp.getValue());
            }
        }

        fc.doFilter(req, res);
    }

    public void destroy() {}

    Map<String, String> mapFromConfig(FilterConfig fc){
        Enumeration paramNames = fc.getInitParameterNames();
        Map<String, String> output = new HashMap<String, String>();

        while(paramNames.hasMoreElements()){
            String hName = (String)paramNames.nextElement();
            String hValue = fc.getInitParameter(hName);

            if(!blank(hName) && !blank(hValue)){
                output.put(hName, hValue);
            }
        }

        return output;
    }

    List<SupplementValue> valuesFromMap(Map<String, String> params){
        List<SupplementValue> output = new ArrayList<SupplementValue>();

        for(String key : params.keySet()){
            if(!blank(key) && !blank(params.get(key))) {
                output.add(new SupplementValue(key, params.get(key)));
            }
        }

        return output;
    }

    private static boolean blank(String s){
        return s == null || "".equals(s);
    }

}


