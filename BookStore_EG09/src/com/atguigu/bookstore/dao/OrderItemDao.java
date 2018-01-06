package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

/**
 * 定义订单项的数据库操作的接口
 * @author lilichao
 *
 */
public interface OrderItemDao {
	
	/**
	 * 向数据库中插入一个订单项
	 * @param orderItem
	 * @return
	 */
	int saveOrderItem(OrderItem orderItem);
	
	/**
	 * 根据订单号查找订单项
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderItemListByOrderId(String orderId);
	
	/**
	 * 批量向数据库中插入订单项
	 * @param params
	 */
	void batchSave(Object[][] params);

}
