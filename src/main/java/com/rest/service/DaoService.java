package com.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.bean.User;

@Component
public class DaoService {
	
	private static List<User> users = new ArrayList<User>();
	private int userCount = 5;
	
	static {
		users.add(new User(1, "Ankit", new Date()));
		users.add(new User(2, "Stark", new Date()));
		users.add(new User(3, "Steve", new Date()));
		users.add(new User(4, "Tony", new Date()));
		users.add(new User(5, "Amit", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public MappingJacksonValue findAllUsersName() {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("name");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("filter user", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
