package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * 对向发送到OrderClientServlet的请求进行统一的登录验证。
 * @author lilichao
 *
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//获取HttpSesiion对象
		HttpSession session = request.getSession();
		
		//从session中获取loginUser对象
		User user = (User) session.getAttribute("loginUser");
		
		//判断用户是否登录
		if(user == null){
			//没登录
			//设置一个错误消息
			request.setAttribute("msg", "该操作需要登录！");
			
			//转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else{
			//已登录，直接放行
			chain.doFilter(request, response);
		}
		
		
		
	}


}
