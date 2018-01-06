package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

/**
 * BookService的实现类
 * @author lilichao
 *
 */
public class BookServiceImpl implements BookService {
	
	//创建一个BookDao实例
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public int saveBook(Book book) {
		return bookDao.saveBook(book);
	}

	@Override
	public int delBook(String bookId) {
		return bookDao.delBook(bookId);
	}

	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public List<Book> getBookList() {
		return bookDao.getBookList();
	}

	@Override
	public Book getBookById(String bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public Page<Book> findBook(String pageNumber, int pageSize) {
		//在findBook方法，主要需要创建一个Page对象，并将pageNumber 和 pageSize封装进对象
		
		//为pn设置一个默认值
		int pn = 1;
		
		try {
			//一旦出现类型转换异常，则不会复制，pn为1
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
		}
		
		//创建一个Page对象
		Page<Book> page = new Page<Book>();
		page.setPageNumber(pn);
		page.setPageSize(pageSize);
		
		//调用DAO查询分页数据
		return bookDao.findBook(page);
		
	}

	@Override
	public Page<Book> findBookByPrice(String pageNumber, int pageSize,
			String min, String max) {
		
		//为pn设置一个默认值
		int pn = 1;
		
		try {
			//一旦出现类型转换异常，则不会复制，pn为1
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
		}
		
		//创建一个Page对象
		Page<Book> page = new Page<Book>();
		page.setPageNumber(pn);
		page.setPageSize(pageSize);
		
		//设置一个默认最大价格和最小价格
		double minPrice = 0;
		double maxPrice = Double.MAX_VALUE;
		
		//转型
		try {
			minPrice = Double.parseDouble(min);
		} catch (Exception e) {
		}
		
		try {
			maxPrice = Double.parseDouble(max);
		} catch (Exception e) {
		}
		
		return bookDao.findBookByPrice(page, minPrice, maxPrice);
	}

}
