package junit.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class TestOrderItemDao {

	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	
	@Test
	public void testSaveOrderItem() {
		
		OrderItem orderItem = new OrderItem(null, 2, 10, "三国演义", "老罗", 20, "hello", "14458264482703");
		
		int count = orderItemDao.saveOrderItem(orderItem);
		
		System.out.println(count);
		
	}

	@Test
	public void testGetOrderItemListByOrderId() {
		
		List<OrderItem> list = orderItemDao.getOrderItemListByOrderId("14458264482703");
		
		System.out.println(list);
	}

}
