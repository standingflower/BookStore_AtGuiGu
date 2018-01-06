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
 * 处理用注册功能的Servlet
 * @author lilichao
 *
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个UserService
	private UserService userService = new UserServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户填写用户名、密码、电子邮件
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		//封装User对象
		User user = new User(null, username, password, email);
		
		//调用Service将User插入进数据库
		boolean isRegist = userService.regist(user);
		
		//注册成功，重定向到regist_success.html
		if(isRegist){
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else{
			//设置一个错误消息
			request.setAttribute("msg", "用户名已存在！");
			
			//注册失败,转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			
		}
	}

}
