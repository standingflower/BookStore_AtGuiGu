package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 定义User表基本操作的Dao
 * @author lilichao
 *
 */
public interface UserDao {

	/**
	 * 根据用户名和密码查询用户
	 * @param user
	 * @return
	 */
	User getUserByUsernameAndPassword(User user);
	
	/**
	 * 向数据库中插入一个用户对象
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	
	/**
	 * 根据用户名查找用户对象
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
}
