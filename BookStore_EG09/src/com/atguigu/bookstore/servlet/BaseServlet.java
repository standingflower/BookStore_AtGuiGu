package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.servlet.client.UserServlet;

/**
 * 专门用来被其他Servlet继承
 * @author lilichao
 *
 */
public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//处理post请求乱码，只需要在getParamter方法第一次调用前，设置request的编码
		request.setCharacterEncoding("utf-8");
		
		//获取用户传递的请求参数
		String methodName = request.getParameter("method");
		//通过方法名获取到方法的对象
		//获取当前类的Class对象
		Class cla = this.getClass();
		//获取cla的的方法（Method对象）
		//getDeclaredMethod需要两个参数，方法名和参数名
		//因为在java需要通过方法名和参数列表来确定一个方法
		try {
			//获取方法对象
			Method method = cla.getDeclaredMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			//设置方法的访问权限
			method.setAccessible(true);
			//调用方法
			//invoke用于调用一个方法，第一个参数时要调用方法的对象，剩下是调用方法需要的参数
			method.invoke(this, request , response);
		} catch (Exception e) {
			//转换成一个运行时异常向上抛出
			throw new RuntimeException(e);
		}
		
	}

}
