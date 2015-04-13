package com.dihaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dihaw.entity.City;
import com.dihaw.repository.CityRepository;
import com.dihaw.services.CityService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;
	
	public List<City> getAll() {
		return cityRepository.findAll();
	}


}
