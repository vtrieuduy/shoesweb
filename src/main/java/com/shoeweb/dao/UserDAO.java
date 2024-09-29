package com.shoeweb.dao;

import java.util.List;

import com.shoeweb.entity.User;

public interface UserDAO {
	User findById(String id);

	List<User> findAll();

	User create(User entity);

	void update(User entity);

	User delete(String id);

	long getPageCount(int pageSize);

	List<User> getPage(int pageNo, int pageSize);

}
