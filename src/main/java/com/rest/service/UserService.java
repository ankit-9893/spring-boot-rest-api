package com.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.bean.User;
import com.rest.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public MappingJacksonValue findAllUsersName() {
		
		List<User> users = findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("name");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("filter user", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User findOne(int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isEmpty()) {
			return user.get();
		}
		return null;
	}
	
	public User deleteById(int id) {
		User user = findOne(id);
		if(user != null) {
			userRepository.delete(user);
			return user;
		}
		return null;
	}

}
