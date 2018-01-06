package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理用户登录的Servlet
 * @author lilichao
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个UserService
	private UserService userService = new UserServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户填写用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//将用户名和密码封装为User对象
		User user = new User(null, username, password, null);
		
		//调用Service验证用户名和密码
		User loginUser = userService.login(user);
		
		//如果loginUser为null，用户名或密码错误，转发到login.html
		if(loginUser == null){
			
			//登录失败，设置一个错误消息 用户名或密码错误
			request.setAttribute("msg", "用户名或密码错误！");
			
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//用户名和密码正确登录成功,重定向到login-success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
		
	}

}
