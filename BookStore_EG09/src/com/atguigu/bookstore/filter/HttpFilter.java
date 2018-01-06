package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门用来被其他Filter继承
 * @author lilichao
 *
 */
public abstract class HttpFilter implements Filter {
	
	//定义一个FilterConfig对象
	private FilterConfig config;

	//getConfig方法用来获取FilterConfig对象
	public FilterConfig getConfig() {
		return config;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		init();
	}
	
	/**
	 * 重载的init()，用来初始化Filter
	 * @throws ServletException
	 */
	public void init() throws ServletException {
	}
	
	/**
	 * 重载的doFilter，通过重写该方法来过滤请求
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException ;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//将ServletRequest和ServletResponse强转为HttpServletRequest和HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//调用重载的doFilter
		doFilter(req, resp, chain);
	}

	@Override
	public void destroy() {
	}

}
