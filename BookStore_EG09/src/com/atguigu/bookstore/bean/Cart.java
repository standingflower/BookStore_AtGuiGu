package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装购物车信息
 * @author lilichao
 *
 */
public class Cart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  在map中保存购物项的信息
	 *  key 是 String型的 bookId
	 *  value 是 CartItem
	 */
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	/**
	 * 商品的总数量
	 */
	//private int totalCount;
	
	/**
	 * 商品的总金额
	 */
	//private double totalAmount;
	
	
	/**
	 * 修改指定购物项的数量
	 * @param bookId
	 * @param countStr
	 */
	public void updateCount(String bookId , String countStr){
	
		//指定一个默认值
		int count = 1;
		
		try {
			//将countStr转型为int
			count = Integer.parseInt(countStr);
		} catch (NumberFormatException e) {
		}
		
		//获取到要修改的购物项
		CartItem cartItem = map.get(bookId);
		
		//设置一个新的数量
		cartItem.setCount(count);
		
		
		
	}
	
	/**
	 * 向购物车里添加一本图书
	 * @param book
	 */
	public void addBook2Cart(Book book){
		
		//从购物车获取指定的购物项
		CartItem cartItem = map.get(book.getId()+"");
		
		//判断cartItem是否为null
		if(cartItem == null){
			//创建一个新的CartItem
			cartItem = new CartItem();
			//设置图书的信息
			cartItem.setBook(book);
			//设置图书的数量
			cartItem.setCount(1);
			//将新的CartItem放入进Map里
			map.put(book.getId()+"", cartItem);
		}else{
			//cartItem不为，购物中已经有了该购物项，需要在购物项的数量+1
			int count = cartItem.getCount();
			cartItem.setCount(count+1);
		}
		
		
	}
	
	/**
	 * 删除一个购物项
	 * @param bookId
	 */
	public void delCartItem(String bookId){
		//从map移除指定的购物项
		map.remove(bookId);
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		
		//清空map
		map.clear();
	}
	
	
	/**
	 * 获取所有的购物项
	 * @return
	 */
	public List<CartItem> getCartItems(){
		
		//获取所有map中value
		Collection<CartItem> values = map.values();
		
		//将Collection转成一个ArrayList
		List<CartItem> cartItems = new ArrayList<CartItem>(values);
		
		//返回cartItems
		return cartItems;
	}
	

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public int getTotalCount() {
		
		//计算商品的总数
		//定义一个总数量
		int totalCount = 0;
		
		List<CartItem> cartItems = getCartItems();
		//遍历商品
		for (CartItem cartItem : cartItems) {
			//计算数量
			totalCount += cartItem.getCount();
		}
		
		return totalCount;
	}


	public double getTotalAmount() {
		
		//计算总金额
		//定义一个变量
		//double totalAmount = 0;
		BigDecimal totalAmount = new BigDecimal("0");
		
		//获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			//计算总金额
			//totalAmount += cartItem.getAmount();
			//将cartItem.getAmount()包装为一个BigDecimal对象
			BigDecimal amount = new BigDecimal(cartItem.getAmount()+"");
			
			//计算金额
			totalAmount = totalAmount.add(amount);
		}
		
		return totalAmount.doubleValue();
	}


}
