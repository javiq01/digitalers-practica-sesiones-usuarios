package com.digitalers.jdbc.implementations;

import java.util.List;

import com.digitalers.entities.User;
import com.digitalers.jdbc.DAO;

public class UserImplementation implements DAO<User, Long> {

	public User findById(Long id) {
		return null;
	}

	public boolean save(User e) {
		return false;
	}

	public boolean delete(User e) {
		return false;
	}

	public List<User> findAll() {
		return null;
	}

}
