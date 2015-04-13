package com.dihaw.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dihaw.controller.exception.UserNotFoundException;
import com.dihaw.dto.ResponseDTO;
import com.dihaw.dto.ResponseStatusType;
import com.dihaw.entity.City;
import com.dihaw.entity.Gender;
import com.dihaw.entity.User;
import com.dihaw.repository.CityRepository;
import com.dihaw.repository.UserRepository;
import com.dihaw.services.UserService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Page<User> users(Pageable pageable){
		
		return userRepository.findAll(pageable);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User getUserById(String id) throws UserNotFoundException{
		
		User user= userRepository.findOne(Integer.parseInt(id));
		
		if(user == null)
			throw new UserNotFoundException(String.format("No user found for id "+id));
		
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(User user) throws UserNotFoundException {
		
		User userSearch = userRepository.findOne(user.getId());
		
		if(userSearch == null)
			throw new UserNotFoundException(String.format("No user found for id "+user.getId()));
		
		City city = cityRepository.findByCityName(user.getCity().getCityName());
		
		userRepository.updateUser(user.getId(), user.getFirstName(), user.getLastName(), Gender.fromValue(user.getGender().value()), city);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO registerUser(User user){
		
		City city = cityRepository.findByCityName(user.getCity().getCityName());
		
		User u = new User(user.getFirstName(), user.getLastName(),
				Gender.fromValue(user.getGender().value()), city);
		
		User userToAdd = userRepository.save(u);
		
		ResponseDTO response = new ResponseDTO();
		
		if(userToAdd == null){
			
			response.setStatus(ResponseStatusType.WRONG_DATA);
		}else{
			
			response.setStatus(ResponseStatusType.SUCCESS);
		}
		
		return response;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String id){
		
		userRepository.delete(Integer.parseInt(id));
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}


}
