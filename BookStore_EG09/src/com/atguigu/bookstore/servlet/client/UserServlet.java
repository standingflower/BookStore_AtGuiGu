package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

/**
 * 处理用户相关请求的Servlet
 * @author lilichao
 *
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个UserService对象
	private UserService userService = new UserServiceImpl();
	
	
	/**
	 * 检查用户是否可用
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户填写的用户名
		String username = request.getParameter("username");
		
		//调用service检查用户名
		boolean flag = userService.checkUsername(username);
		
		if(flag){
			//如果为true，代表用户名可用，响应一个 1
			response.getWriter().print("1");
		}else{
			//用户名已存在 不可用，响应一个0
			response.getWriter().print("0");
		}
		
	}
       
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取session对象
		HttpSession session = request.getSession();
		
		//强制Session失效
		session.invalidate();
		
		//重定向到首页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取session对象
		HttpSession session = request.getSession();
		
		//调用WEBUtils将请求参数封装为一个User对象
		User user = WEBUtils.param2Bean(request , new User());
		
		
		//调用Service验证用户名和密码
		User loginUser = userService.login(user);
		
		//如果loginUser为null，用户名或密码错误，转发到login.html
		if(loginUser == null){
			
			//登录失败，设置一个错误消息 用户名或密码错误
			request.setAttribute("msg", "用户名或密码错误！");
			
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			
			//将用户对象放入进session中
			session.setAttribute("loginUser", loginUser);
			
			//用户名和密码正确登录成功,重定向到login-success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
		
	}

	
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取Session
		HttpSession session = request.getSession();
		
		//获取用户填写的验证码
		String reqCode = request.getParameter("code");
		
		//获取session中的验证码
		String sessCode = (String) session.getAttribute("code");
		
		//移除session中的验证码
		session.removeAttribute("code");
		
		//检查验证码是否正确
		if(reqCode != null && reqCode.equals(sessCode)){
			//验证码正确
			User user = WEBUtils.param2Bean(request, new User());
			
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
		
		}else{
			//验证码错误
			//设置一个错误消息
			request.setAttribute("msg", "验证码填写错误！");
			
			//转发到regist.jsp
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}


}
