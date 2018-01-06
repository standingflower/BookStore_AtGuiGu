package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 用于统一管理事务的Filter
 * @author lilichao
 *
 */
public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//获取数据库连接
		Connection connection = JDBCUtils.getConnection();
		try {
			//设置手动提交事务
			connection.setAutoCommit(false);
			//放行请求
			chain.doFilter(request, response);
			
			//正常处理，提交事务
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			//如果有异常，则回滚事务
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//设置一个错误消息
			request.setAttribute("error", "系统出现异常，请联系管理员！");
			//转发到错误页面
			request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
			
		} finally{
			//关闭数据库连接
			JDBCUtils.releaseConnection();
		}
		
		
	}

}
