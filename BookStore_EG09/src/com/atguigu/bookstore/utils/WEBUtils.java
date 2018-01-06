package com.atguigu.bookstore.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Cart;

/**
 * WEB相关操作的工具类
 * @author lilichao
 *
 */
public class WEBUtils {

	/**
	 * 将请参数封装为一个对象，并返回
	 * @param request
	 * @param t
	 * @return
	 */
	public static <T>T param2Bean(HttpServletRequest request, T t) {
		
		//获取请求参数的map
		Map map = request.getParameterMap();
		
		try {
			//将map转换为一个对象
			BeanUtils.populate(t, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	/**
	 * 获取请求的路径
	 * @param request
	 * @return
	 */
	public static String getPath(HttpServletRequest request) {
		//动态获取请求的地址
		///BookStore_EG05/manager/BookManagerServlet
		String uri = request.getRequestURI();
		
		//获取查询字符串
		//method=findBook&pageNumber=2
		String queryString = request.getQueryString();
		//拼接一个完整的请求地址
		String path = uri+"?"+queryString;
		
		//判断path中是否包含&pageNumber字符串
		///BookStore_EG05/manager/BookManagerServlet?method=findBook&pageNumber=2
		if(path.contains("&pageNumber")){
			path = path.substring(0, path.indexOf("&pageNumber"));
		}
		return path;
	}

	/**
	 * 获取购物车对象
	 * @param request
	 * @return
	 */
	public static Cart getCart(HttpServletRequest request) {
		
		//获取Session
		HttpSession session = request.getSession();
		
		//从session中获取购物车对象
		Cart cart = (Cart) session.getAttribute("cart");
		
		//判断cart是否为空
		if(cart == null){
			//创建一个新的cart对象
			cart = new Cart();
			//将cart放入进session中
			session.setAttribute("cart", cart);
		}	
		
		return cart;
}

	
}
