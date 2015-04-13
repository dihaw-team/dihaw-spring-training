package com.dihaw.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dihaw.controller.exception.UserNotFoundException;
import com.dihaw.dto.ResponseDTO;
import com.dihaw.entity.User;

public interface UserService {
	
	public Page<User> users(Pageable pageable);
	
	public User getUserById(int id) throws UserNotFoundException;
	
	public void updateUser(User user) throws UserNotFoundException;
	
	public void deleteUser(int id) throws UserNotFoundException;
	
	public ResponseDTO registerUser(User user);

	public User getUserByUsername(String username);


}
