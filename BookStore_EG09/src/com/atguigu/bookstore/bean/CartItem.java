package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 封装购物项信息的类型
 * 
 * @author lilichao
 * 
 */
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 所购买的图书的信息
	 */
	private Book book;

	/**
	 * 所购买的图书的数量
	 */
	private int count;

	/**
	 * 所购买的的图书的金额
	 * 金额 = 单价 * 数量
	 */
	//private double amount;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		
		//计算商品的总金额
		//double amount = book.getPrice() * getCount();
		
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		BigDecimal count = new BigDecimal(getCount());
		
		
		return price.multiply(count).doubleValue();
	}


	public CartItem() {
	}

}
