package com.yohnk.web.supplement;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ryanyohnk on 3/7/17.
 */
public class SupplementHeaderTest {

    @Test
    public void testConfigToMap() throws ServletException {
        FilterConfig config = new MockFilterConfig(testMap());
        SupplementHeaderFilter filter = new SupplementHeaderFilter();
        Map<String, String> out = filter.mapFromConfig(config);

        assertEquals(out.get("Alpha"), "1");
        assertEquals(out.get("Beta"), "2");
        assertNull(out.get("Gamma"));
        assertNull(out.get("Theta"));
    }

    @Test
    public void testMapToValues() {
        SupplementHeaderFilter filter = new SupplementHeaderFilter();
        List<SupplementValue> values = filter.valuesFromMap(testMap());

        assertEquals(values.size(), 2);
        assertEquals(values.get(0), new SupplementValue("Alpha", "1"));
        assertEquals(values.get(1), new SupplementValue("Beta", "2"));
    }

    @Test
    public void testFilter() throws ServletException, IOException {
        HttpServletResponse response = new DumbHeadersHttpServletResponse(mock(HttpServletResponse.class));

        doFilter(mock(HttpServletRequest.class), response, mock(FilterChain.class), testMap());

        assertEquals(response.getHeader("Alpha"), "1");
        assertEquals(response.getHeader("Beta"), "2");
        assertNull(response.getHeader("Gamma"));
        assertNull(response.getHeader("Theta"));
    }

    @Test
    public void testPrevExisting() throws ServletException, IOException {
        HttpServletResponse response = new DumbHeadersHttpServletResponse(mock(HttpServletResponse.class));
        response.setHeader("Beta", "3");

        doFilter(mock(HttpServletRequest.class), response, mock(FilterChain.class), testMap());

        assertEquals(response.getHeader("Beta"), "3");
    }

    private SupplementHeaderFilter doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Map<String, String> map) throws IOException, ServletException {
        SupplementHeaderFilter filter = new SupplementHeaderFilter();
        filter.init(new MockFilterConfig(map));
        filter.doFilter(request, response, chain);
        return filter;
    }

    private static Map<String, String> testMap() {
        HashMap<String, String> in = new HashMap<String, String>();
        in.put("Alpha", "1");
        in.put("Beta", "2");
        in.put("Gamma", "");
        return in;
    }

    private static Enumeration<String> testConfigToMapEnum() {
        return new Vector<String>(Arrays.asList("Alpha", "Beta", "Gamma")).elements();
    }

}
