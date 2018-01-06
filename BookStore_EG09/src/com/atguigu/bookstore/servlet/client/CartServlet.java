package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;
import com.google.gson.Gson;

/**
 * 处理购物车相关请求的Servlet
 * @author lilichao
 *
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个BookService的对象
	private BookService bookService = new BookServiceImpl();
	
	/**
	 * 修改指定的图书的数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取要修改数量的图书的id
		String bookId = request.getParameter("bookId");
		
		//获取要修改成的数量
		String countStr = request.getParameter("count");
		
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		
		//修改图书的数量
		cart.updateCount(bookId, countStr);
		
		//将修改的信息作为响应发送totalCount totalAmount amount
		int totalCount = cart.getTotalCount();
		double totalAmount = cart.getTotalAmount();
		
		//获取所有的cartItem的map
		Map<String, CartItem> map = cart.getMap();
		//根据ID获取CartItem对象
		CartItem cartItem = map.get(bookId);
		//获取amount
		double amount = cartItem.getAmount();
		
		//创建一个Map对象
		Map<String , Object> jsonMap = new HashMap<String, Object>();
		
		//向jsonMap中添加数据
		jsonMap.put("totalCount", totalCount+"");
		jsonMap.put("totalAmount", totalAmount+"");
		jsonMap.put("amount", amount+"");
		
		//将jsonMap转换为一个json字符串
		String json = new Gson().toJson(jsonMap);
		
		//将字符串作为响应发送
		response.getWriter().print(json);
		
	}
	
	/**
	 * 删除一个指定的购物项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delCartItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户的bookId
		String bookId = request.getParameter("bookId");
		
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		
		//移除购物项
		cart.delCartItem(bookId);
		
		//重定向到购物车页面
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		
	}
	
	/**
	 * 清空购物车
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取购物车对象
		//HttpSession session = request.getSession();
		
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		
		//清空购物车
		cart.clear();
		
		//重定向到cart.jsp
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		
	}
       
	/**
	 * 向购物车中添加一本图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//处理响应乱码问题
		response.setContentType("text/html;charset=utf-8");
		
		//获取图书的id
		String bookId = request.getParameter("bookId");
		
		//查询图书对象
		Book book = bookService.getBookById(bookId);
		
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		
		//向购物车中添加一本图书
		cart.addBook2Cart(book);
		
		//获取购物车中商品的总数
		int totalCount = cart.getTotalCount();
		//获取刚刚添加进购物车的图书的名字
		String title = book.getTitle();
		
		//将totalCount和title放入进一个map中
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("title", title);
		
		//将map转换为JSON字符串
		String json = new Gson().toJson(map);
		
		//将json作为响应发送
		response.getWriter().print(json);
		
	}


}
