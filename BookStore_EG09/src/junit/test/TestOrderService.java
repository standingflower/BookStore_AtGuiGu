package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

public class TestOrderService {

	OrderService orderService = new OrderServiceImpl();
	
	BookDao bookDao = new BookDaoImpl();
	
	@Test
	public void testCreateOrder() {
		
		//创建一个Cart
		Cart cart = new Cart();
		
		//从数据库中查询图书
		Book book1 = bookDao.getBookById("11");
		Book book2 = bookDao.getBookById("12");
		Book book3 = bookDao.getBookById("13");
		
		//购买图书
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);
		
		//创建一个User
		User user = new User(3, null, null, null);
		
		//生成一个订单
		String orderId = orderService.createOrder(cart, user);
		
		System.out.println(orderId);
		
	}

}
