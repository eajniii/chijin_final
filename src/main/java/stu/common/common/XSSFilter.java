package stu.common.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class XSSFilter implements Filter{
	
	private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		XSSRequestWrapper wrappedRequest = new XSSRequestWrapper((HttpServletRequest) request);
		chain.doFilter(wrappedRequest, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
