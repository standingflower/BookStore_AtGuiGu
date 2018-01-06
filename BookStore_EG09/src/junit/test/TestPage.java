package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public class TestPage {

	@Test
	public void test() {
		
		Page<Book> page = new Page<Book>();
		
		page.setPageNumber(3);
		page.setPageSize(2);
		page.setTotalRecord(8);
		
		//输出总页数
		System.out.println("总页数："+page.getTotalPage());
		//获取索引值
		System.out.println("当前索引: "+page.getIndex());
		
	}

}
