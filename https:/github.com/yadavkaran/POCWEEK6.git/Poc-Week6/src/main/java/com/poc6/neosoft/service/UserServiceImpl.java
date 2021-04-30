package com.poc6.neosoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc6.neosoft.model.User;
import com.poc6.neosoft.repository.UserRepository1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository1 userRepository;

	@Override
	public void saveUser(User user) {
		this.userRepository.save(user);
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		return user;
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers(String keyword) {
		log.info("Gettin all users from database");
		if (keyword == null) {
			return this.userRepository.findAll();
			
		}
		return this.userRepository.search(keyword);
	}



}
