package com.atguigu.bookstore.servlet.client;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理用户相关请求的Servlet
 * @author lilichao
 *
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个OrderService的实例
	private OrderService orderService = new OrderServiceImpl();
	
	/**
	 * 获取当前订单详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void orderInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取订单号
		String orderId = request.getParameter("orderId");
		
		//调用service查询订单详情
		List<OrderItem> list = orderService.getOrderInfo(orderId);
		
		//将list放入request域中
		request.setAttribute("list", list);
		
		//转发到一个页面
		request.getRequestDispatcher("/pages/order/order-info.jsp").forward(request, response);
		
	}
	
	
	/**
	 * 客户端确认收货的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void takeBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取订单号
		String orderId = request.getParameter("orderId");
		
		//调用service进行收货
		orderService.takeBook(orderId);
		
		//重定向到客户端的订单列表页面
		response.sendRedirect(request.getContextPath()+"/client/OrderClientServlet?method=orderList");
	}
	
	/**
	 * 查询当前用户的所有订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void orderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取session对象
		HttpSession session = request.getSession();
		//获取user对象
		User user = (User) session.getAttribute("loginUser");
		
		//掉用service查询订单信息
		List<Order> list = orderService.getOrderListByUserId(user.getId()+"");
		//将list放入request域中
		request.setAttribute("list", list);
		//转发到订单的列表页面/pages/order/order.jsp
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		
		
	}

	/**
	 * 用来结账的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取Session对象
		HttpSession session = request.getSession();
		//获取当前登录的用户的对象
		User user = (User) session.getAttribute("loginUser");
		
		//已经登录
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		
		//调用service生成一个订单
		String orderId = orderService.createOrder(cart, user);
		
		//将订单号放入进域对象中
		request.setAttribute("orderId", orderId);
		
		//转发到/pages/cart/checkout.jsp
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
			
		
		
	}

}
