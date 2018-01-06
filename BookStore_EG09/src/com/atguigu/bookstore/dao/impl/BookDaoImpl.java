package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

/**
 * BookDao的实现类
 * 
 * @author lilichao
 * 
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public int saveBook(Book book) {

		String sql = "INSERT INTO bs_book(title, author , price , sales , stock , img_path) "
				+ "VALUES(?,?,?,?,?,?)";

		return this.update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath());
	}

	@Override
	public int delBook(String bookId) {

		String sql = "DELETE FROM bs_book WHERE id=? ";

		return this.update(sql, bookId);
	}

	@Override
	public int updateBook(Book book) {

		String sql = "UPDATE bs_book SET title=? , author=? , price=? , sales=? , stock=? , img_path=? "
				+ "WHERE id=?";

		return this.update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath(), book.getId());
	}

	@Override
	public List<Book> getBookList() {
		
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath FROM bs_book";
		
		return this.getBeanList(sql);
	}

	@Override
	public Book getBookById(String bookId) {
		
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath FROM bs_book "
				+ "WHERE id=?";

		return this.getBean(sql, bookId);
	}

	@Override
	public Page<Book> findBook(Page<Book> page) {
		/**
		 *  参数中的page由service传递过来，
		 *  page中已经有了两个属性 pageNumber 和 pageSize
		 *  在当前方法中，我们还需要封装两个参数totalRecord 和 data
		 */
		
		//获取图书的总记录数
		String sql  = "SELECT COUNT(*) FROM bs_book";
		long totalRecord = (long) this.getSingleValue(sql);
		
		//将总记录数设置进Page对象中
		page.setTotalRecord((int) totalRecord);
		
		//查询图书信息
		sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ "FROM bs_book LIMIT ?,?";
		
		List<Book> data = this.getBeanList(sql, page.getIndex() , page.getPageSize());
		
		//将图书数据设置进page对象中
		page.setData(data);
		
		
		return page;
	}

	@Override
	public Page<Book> findBookByPrice(Page<Book> page, double minPrice,
			double maxPrice) {
		
		//查找总记录数 totalRecord
		String sql = "SELECT COUNT(*) FROM bs_book WHERE price >= ? AND price <= ?";
		long totalRecord = (long) this.getSingleValue(sql, minPrice , maxPrice);
		
		//将总记录数设置进page对象
		page.setTotalRecord((int) totalRecord);
		
		//查询图书的信息
		sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ "FROM bs_book WHERE price >= ? AND price <= ? LIMIT ? ,?";
		List<Book> data = this.getBeanList(sql, minPrice , maxPrice , page.getIndex() , page.getPageSize());
		
		//将数据设置进page对象
		page.setData(data);
		
		//将page对象返回
		return page;
	}

	@Override
	public void batchUpdateSalesAndStock(Object[][] params) {
		
		String sql = "UPDATE bs_book SET sales=? , stock=? WHERE id=?";
		
		this.batchUpdate(sql, params);
		
	}

}
