package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接的工具类
 * @author lilichao
 *
 */
public class JDBCUtils {
	
	private static DataSource dataSource = new ComboPooledDataSource("webDataSource");
	
	//创建一个Connection的属性
	//private static Connection conn;
	
	//private static Map<Thread , Connection> map = new ConcurrentHashMap<Thread, Connection>();
	
	//ThreadLocal对象在内部维护着一个map，这个map中的key就是当前线程，而值我们可以指定为一个任意类型。
	//ThreadLocal中用来在当前线程中共享对象
	//一个ThreadLocal在当前线程中只能存一个对象。
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	
	/**
	 * 获取数据连接
	 * @return
	 */
	public static Connection getConnection(){
		
		//从ThreadLocal中获取当前线程的Connection对象
		Connection conn = threadLocal.get();
		
		//判断conn是否为null
		if(conn==null){
			try {
				//给conn赋值
				conn = dataSource.getConnection();
				
				//将conn放入ThreadLocal
				threadLocal.set(conn);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//返回conn
		return conn;
		
		
		/*//从map中获取当前线程的Connection对象
		Connection conn = map.get(Thread.currentThread());
		
		//判断conn是否为null
		if(conn==null){
			try {
				//创建一个新的对象
				conn = dataSource.getConnection();
				//将conn放入map
				map.put(Thread.currentThread(), conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;*/
		
		
		//判断conn是否为null
		/*if(conn == null){
			try {
				//如果为null则附一个新值
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;*/
	}
	
	/**
	 * 释放conn属性
	 */
	public static void releaseConnection(){
		
		//从ThreadLocal中获取到Conn对象
		Connection conn = threadLocal.get();
		
		//关闭conn
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//从ThreadLocal中移除conn
		threadLocal.remove();
		
		
		/*//获取到当前线程的conn对象
		Connection conn = map.get(Thread.currentThread());
		
		//关闭Connection
		if(conn!=null){
			try {
				conn.close();
				
				//将conn从map中移除
				map.remove(Thread.currentThread());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		
		/*if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//将conn设置为null
		conn = null;*/
		
	}
	
	/**
	 * 释放数据库连接的方法
	 * @param conn
	 */
	public static void releaseConnection(Connection conn){
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
