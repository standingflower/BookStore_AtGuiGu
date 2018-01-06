package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

/**
 * 定义订单的基本的操作方法
 * @author lilichao
 *
 */
public interface OrderDao {
	
	/**
	 * 向数据库中插入一个订单
	 * @param order
	 * @return
	 */
	int saveOrder(Order order);
	
	/**
	 * 更新一个订单的状态
	 * @param orderId
	 * @param state
	 * @return
	 */
	int updateState(String orderId , int state);
	
	/**
	 * 获取所有的订单信息，管理员调用
	 * @return
	 */
	List<Order> getOrderList();
	
	/**
	 * 根据用户的id获取订单信息，用户调用
	 * @param userId
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);

}
