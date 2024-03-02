package stu.common.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.util.*;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return filter(value);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = super.getParameterMap();
        Map<String, String[]> filteredParamMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            String[] values = paramMap.get(key);
            String[] filteredValues = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                filteredValues[i] = filter(values[i]);
            }
            filteredParamMap.put(key, filteredValues);
        }
        return filteredParamMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        String[] filteredValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            filteredValues[i] = filter(values[i]);
        }
        return filteredValues;
    }

    private String filter(String input) {
        if (input != null) {
            if (isSensitiveInput(input)) {
                // 민감한 입력에 대한 엄격한 필터링
                return strictFilter(input);
            } else {
                // 일반적인 입력에 대한 완화된 필터링
                return relaxedFilter(input);
            }
        }
        return input;
    }

    private boolean isSensitiveInput(String input) {
        return input.contains("keyword");
    }
    
    private String strictFilter(String value) {

        value = value.replaceAll("\0", "");
        return Jsoup.clean(value, Safelist.none());
    }
    
    private String relaxedFilter(String value) {
        value = value.replaceAll("\0", "");
        return Jsoup.clean(value, Safelist.basic());
    }
}
