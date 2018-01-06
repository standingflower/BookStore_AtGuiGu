package junit.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;

public class TestCart {

	@Test
	public void test() {
		
		//创建一个购物车对象
		Cart cart = new Cart();
		
		//创建Book对象
		Book book = new Book(1, "abc", "bcd", 10, 10, 10, "");
		Book book2 = new Book(2, "123", "bcd", 0.05, 10, 10, "");
		Book book3 = new Book(3, "456", "bcd", 0.01, 10, 10, "");
		
		//向购物车中添加一本图书
		/*cart.addBook2Cart(book);
		cart.addBook2Cart(book);*/
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);
		
		//移除一个购物项
		//cart.delCartItem("1");
		
		//修改数量
		//cart.updateCount("2", "10");
		
		//清空购物车
		//cart.clear();
		
		//输出购物车中的信息
		System.out.println("商品总数量: "+cart.getTotalCount());
		System.out.println("商品总金额: "+cart.getTotalAmount());
		
		//遍历所有的购物项
		List<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {
			System.out.println(cartItem.getCount());
		}
		
	}

}
