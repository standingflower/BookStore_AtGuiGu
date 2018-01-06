package junit.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class TestBookService {
	
	BookService bookService = new BookServiceImpl();

	@Test
	public void test() {
		
		Page<Book> page = bookService.findBookByPrice("1", 4, null, null);
		
		List<Book> list = page.getData();
		
		for (Book book : list) {
			System.out.println(book);
		}
	}

}
