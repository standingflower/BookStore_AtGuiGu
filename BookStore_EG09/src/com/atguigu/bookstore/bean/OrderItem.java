package com.atguigu.bookstore.bean;

/**
 * 封装订单项的信息
 * 
 * @author lilichao
 * 
 */
public class OrderItem {

	private Integer id;
	/**
	 * 单本图书的数量
	 */
	private int count;

	/**
	 * 单本图书的小计金额
	 */
	private double amount;

	/**
	 * 图书的名称
	 */
	private String title;
	/**
	 * 图书的作者
	 */
	private String author;

	/**
	 * 图书的单价
	 */
	private double price;

	/**
	 * 图书的封面
	 */
	private String imgPath;

	/**
	 * 订单项所属的订单
	 */
	private String orderId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderItem(Integer id, int count, double amount, String title,
			String author, double price, String imgPath, String orderId) {
		super();
		this.id = id;
		this.count = count;
		this.amount = amount;
		this.title = title;
		this.author = author;
		this.price = price;
		this.imgPath = imgPath;
		this.orderId = orderId;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", count=" + count + ", amount="
				+ amount + ", title=" + title + ", author=" + author
				+ ", price=" + price + ", imgPath=" + imgPath + ", orderId="
				+ orderId + "]";
	}

}
