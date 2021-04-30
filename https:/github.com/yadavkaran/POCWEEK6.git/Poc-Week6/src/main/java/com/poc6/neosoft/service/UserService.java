package com.poc6.neosoft.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poc6.neosoft.model.User;

public interface UserService {
	List<User> getAllUsers(String keyword);
	void saveUser(User user);
	User getUserById(long id);
	void deleteUserById(long id);
	
}

