package com.atguigu.bookstore.servlet.client;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理前端图书显示的请求
 * @author lilichao
 *
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建一个BookService对象
	private BookService bookService = new BookServiceImpl();
	
	/**
	 * 根据价格查找图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBookByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取页码
		String pageNumber = request.getParameter("pageNumber");
		//设置一个pageSize
		int pageSize = 4;
		//获取最大和最小价格
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		
		//调用Service查询page对象
		Page<Book> page = bookService.findBookByPrice(pageNumber, pageSize, min, max);
		
		//设置path属性
		page.setPath(WEBUtils.getPath(request));
		
		//将page对象设置进request域中
		request.setAttribute("page", page);
		
		//转发到index.jsp
		request.getRequestDispatcher("/pages/book/book-list.jsp").forward(request, response);
		
	}
	
	//前端分页查找图书的方法
	protected void findBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页码
		String pageNumber = request.getParameter("pageNumber");
		//设置一个pageSize
		int pageSize = 4;
		//调用service查询page对象
		Page<Book> page = bookService.findBook(pageNumber, pageSize);
		
		//设置path属性
		page.setPath(WEBUtils.getPath(request));
		
		//将page对象放入进域中
		request.setAttribute("page", page);
		
		//转发到index.jsp
		request.getRequestDispatcher("/pages/book/book-list.jsp").forward(request, response);
		
		
	}

}
