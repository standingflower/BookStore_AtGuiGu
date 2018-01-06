package junit.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;

public class TestOrderDao {
	
	OrderDao orderDao = new OrderDaoImpl();

	@Test
	public void testSaveOrder() {
		
	
		//生成一个订单号
		String id = System.currentTimeMillis()+""+3;
		//创建一个Order对象
		Order order = new Order(id, new Date(), 10, 100, 0, 3);
		
		//将order存入数据库
		int count = orderDao.saveOrder(order);
		
		System.out.println(count);
		
	}

	@Test
	public void testUpdateState() {
		
		int count = orderDao.updateState("14458264482703", 1);
		System.out.println(count);
	}

	@Test
	public void testGetOrderList() {
		
		List<Order> list = orderDao.getOrderList();
		
		System.out.println(list);
		
	}

	@Test
	public void testGetOrderListByUserId() {
		
		List<Order> list = orderDao.getOrderListByUserId("3");
		System.out.println(list);
		
	}

}
