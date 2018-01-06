package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

/**
 * 处理订单相关业务的接口
 * @author lilichao
 *
 */
public interface OrderService {
	
	/**
	 * 根据购物车信息和用户信息生成一个订单，并返回订单号
	 * @param cart
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart , User user);
	
	/**
	 * 查询所有订单，有管理员调用
	 * @return
	 */
	List<Order> getOrderList();
	
	/**
	 * 根据用户的id返回订单信息
	 * @param userId
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);
	
	/**
	 * 发货
	 * @param orderId
	 */
	void sendBook(String orderId);
	
	/**
	 * 确认收货
	 * @param orderId
	 */
	void takeBook(String orderId);
	
	/**
	 * 根据订单号获取订单详情
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderInfo(String orderId); 

}
