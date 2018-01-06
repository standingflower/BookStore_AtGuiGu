package com.atguigu.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 定义一些数据库的基本操作
 * 这个类是专门用来被其他Dao继承
 * @author lilichao
 *
 * @param <T>
 */
public class BaseDao<T> {
	
	private QueryRunner runner = new QueryRunner();
	
	private Class<T> type;
	
	public BaseDao() {
		//创建了一个无参的构造器，这个构造器是由子类调用
		//UserDao extends BaseDao<User>
		//获取当前子类的类型
		Class cla = this.getClass();
		
		//获取父类的类型
		ParameterizedType pt = (ParameterizedType) cla.getGenericSuperclass();
		
		//获取所有的泛型
		 Type[] types = pt.getActualTypeArguments();
		 
		 this.type = (Class<T>) types[0];
		
	}
	
	/**
	 * 批量操作数据库的方法
	 * @param sql
	 * @param params
	 * Object[][] params是一个二维数组
	 * 		二维数组的第一维，sql语句要执行的次数。
	 * 		二维数组的第二维，每次执行sql语句时使用的参数
	 */
	public void batchUpdate(String sql , Object[][] params){
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			//将异常转换一个运行时异常向上抛
			 throw new RuntimeException(e);
		} 
		/*finally{
			JDBCUtils.releaseConnection(conn);
		}*/
		
	}
	
	/**
	 * 从数据库中查询到一个单一的值
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleValue(String sql , Object ... params){
		
		Object obj = null;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			//ScalarHandler可以返回到查询的数据的第一行第一个单元格中数据
			obj = runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			//将异常转换一个运行时异常向上抛
			 throw new RuntimeException(e);
		} 
		/*finally{
			JDBCUtils.releaseConnection(conn);
		}*/
		
		return obj;
		
	}
	
	
	/**
	 * 查询一个对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql , Object ... params){
		T t = null;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();

		try {
			t = runner.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			//将异常转换一个运行时异常向上抛
			 throw new RuntimeException(e);
		} 
		/*finally{
			JDBCUtils.releaseConnection(conn);
		}*/
		
		return t;
	}
	
	/**
	 * 查询一组对象列表
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql , Object ... params){
		
		List<T> list = null;
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		try {
			list = runner.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			//将异常转换一个运行时异常向上抛
			 throw new RuntimeException(e);
		} 
		/*finally{
			JDBCUtils.releaseConnection(conn);
		}*/
		return list;
		
	}
	
	
	/**
	 * 更新数据库操作的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql , Object ... params){
		int count = 0;
		
		Connection conn = JDBCUtils.getConnection();
		try {
			count = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//将异常转换一个运行时异常向上抛
			throw new RuntimeException(e);
		} 
		/*finally{
			JDBCUtils.releaseConnection(conn);
		}*/
		
		return count;
		
	}

}
