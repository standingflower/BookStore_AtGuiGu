package com.atguigu.bookstore.servlet.manager;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理图书相关请求的Servlet
 * @author lilichao
 *
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建BookService
	private BookService bookService = new BookServiceImpl();
	
	/**
	 * 根据分页查找图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取页码
		String pageNumber = request.getParameter("pageNumber");
		//指定一个pageSize
		int pageSize = 4;
		//调用service查询分页数据
		Page<Book> page = bookService.findBook(pageNumber, pageSize);
		
		//获取请求的路径
		String path = WEBUtils.getPath(request);
		
		//将路径设置进page对象
		page.setPath(path);
		
		//将page放入request域中
		request.setAttribute("page", page);
		
		//转发到图书列表页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
		
	}
	
	/**
	 * 修改或添加图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取要处理图书对象
		Book book = WEBUtils.param2Bean(request, new Book());
		
		if(book.getId() == null||book.getId() == 0){
			//如果book对象中没有id属性，则做添加操作
			bookService.saveBook(book);
		}else{
			//有id属性则做修改操作
			//修改图书
			bookService.updateBook(book);
		}
		
		String referer = request.getParameter("referer");
		
		//重定向到图书列表页面
		//能用重定向就绝对不用转发
		response.sendRedirect(referer);
		//request.getRequestDispatcher("/manager/BookManagerServlet?method=findBook").forward(request, response);
		
	}
	
	/**
	 * 去修改图书的页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取要修改的图书的id
		String bookId = request.getParameter("bookId");
		
		//调用service查询图书
		Book book = bookService.getBookById(bookId);
		
		//将book放入进request域中
		request.setAttribute("book", book);
		
		//转发到/pages/manager/book_edit.jsp
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
	/**
	 * 删除一本图书的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取要删除图书的id
		String bookId = request.getParameter("bookId");
		
		//调用service删除图书
		bookService.delBook(bookId);
		
		/**
		 * 这方法目前是删除完一个数据，默认会回到第一页
		 * 而我们希望他哪来的回哪去
		 * referer请求头
		 * Referer: http://localhost:8080/BookStore_EG05/manager/BookManagerServlet?method=findBook&pageNumber=9
		 * 我们只需要让请求回到referer头所指向的地址
		 */
		//获取referer头的信息
		String referer = request.getHeader("referer");
		
		//重定向到列表页面
		response.sendRedirect(referer);
		
	}
	
	/**
	 * 获取所有的图书列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void bookList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取图书的List
		List<Book> list = bookService.getBookList();
		
		//将list放入进request域中
		request.setAttribute("list", list);
		
		//转发到列表页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	/**
	 * 向数据库中添加图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//获取要添加的图书（获取页面传送请求参数，并封装为book对象）
		Book book = WEBUtils.param2Bean(request, new Book());
		
		//调用service将图书添加进数据库
		bookService.saveBook(book);
		
		//将请求重定向到一个页面
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=bookList");
		
	}

}
