package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

/**
 * OrderService的实现类
 * @author lilichao
 *
 */
public class OrderServiceImpl implements OrderService {
	
	//分别创建三个DAO
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public String createOrder(Cart cart, User user) {
		
		//生成一个订单号:时间戳+用户id
		String orderId = System.currentTimeMillis()+""+user.getId();
		
		//读取cart中的信息
		//获取商品的总金额
		double totalAmount = cart.getTotalAmount();
		//获取商品的总数量
		int totalCount = cart.getTotalCount();
		//创建Order对象
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, user.getId());
		//将Order对象插入数据库
		orderDao.saveOrder(order);
		
		//获取所有的购物项
		List<CartItem> cartItems = cart.getCartItems();
		
		//创建两个二维数组，用来保存Book和orderItem的参数
		Object[][] itemParams = new Object[cartItems.size()][];
		Object[][] bookParams = new Object[cartItems.size()][];
		
		
		//遍历购物项
		for(int i=0 ; i<cartItems.size() ; i++ ){
			//获取cartItem
			CartItem cartItem = cartItems.get(i);
			//获取图书对象
			Book book = cartItem.getBook();
			//读取CartItem中的信息
			//图书的小计金额
			double amount = cartItem.getAmount();
			//获取图书的属性
			int count = cartItem.getCount();
			
			//获取图书的信息
			String title = book.getTitle();
			String author = book.getAuthor();
			double price = book.getPrice();
			String imgPath = book.getImgPath();
			
			//获取图书的销量及库存
			int sales = book.getSales();
			int stock = book.getStock();
			
			//创建OrderItem
			//OrderItem orderItem = new OrderItem(null, count, amount, title, author, price, imgPath, orderId);
			//将orderItem插入进数据库
			//orderItemDao.saveOrderItem(orderItem);
			
			//向数组中添加属性
			itemParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId};
			
			//UPDATE bs_book SET sales=? , stock=? WHERE id=?
			bookParams[i] = new Object[]{sales+count , stock-count , book.getId()};
			
			//判断库存是否充足
			if(stock-count < 0){
				//库存不足
				throw new RuntimeException("库存不足");
			}
			
			
			
			//修改图书的销量及库存
			/*book.setSales(sales+count);
			book.setStock(stock-count);*/
			
			//更新数据库
			//bookDao.updateBook(book);
		}
		
		//批量插入OrderItem
		orderItemDao.batchSave(itemParams);
		//批量修改销量和库存
		bookDao.batchUpdateSalesAndStock(bookParams);

		//清空购物车
		cart.clear();
		
		
		//返回订单号
		return orderId;
	}

	@Override
	public List<Order> getOrderList() {
		return orderDao.getOrderList();
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		return orderDao.getOrderListByUserId(userId);
	}

	@Override
	public void sendBook(String orderId) {
		
		orderDao.updateState(orderId, 1);
	}

	@Override
	public void takeBook(String orderId) {
		
		orderDao.updateState(orderId, 2);
		
	}

	@Override
	public List<OrderItem> getOrderInfo(String orderId) {
		return orderItemDao.getOrderItemListByOrderId(orderId);
	}

}
