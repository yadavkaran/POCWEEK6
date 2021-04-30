package com.poc6.neosoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc6.neosoft.model.User;

@Repository
public interface UserRepository1 extends JpaRepository<User, Long>{
	 @Query("SELECT u FROM User u WHERE u.email LIKE %?1%"
	            + " OR u.city LIKE %?1%")
	public 	List<User> search(String keyword);
}

