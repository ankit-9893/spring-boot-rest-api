package com.rest.controller;

import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.bean.User;
import com.rest.exception.UserNotFoundException;
import com.rest.service.DaoService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private DaoService daoService;
	
	@GetMapping("all")
	public List<User> getAllUsers() {
		return daoService.findAll();
	}
	
	@GetMapping("allNames")
	public MappingJacksonValue getAllUsersNames() {
		return daoService.findAllUsersName();
	}
	
	@GetMapping("find/{id}")
	public EntityModel<User> findUser(@PathVariable int id) {
		User user = daoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id:-" + id);
		}
		
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder link =linkTo(methodOn(this.getClass()).getAllUsers());
		
		model.add(link.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping("add")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = daoService.save(user);
		
//		URI location = ServletUriComponentsBuilder
//			.fromCurrentRequest()
//			.path("/{id}")
//			.buildAndExpand(savedUser.getId())
//			.toUri();
		
		return new ResponseEntity(savedUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = daoService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id:-" + id);
		}
		return user;
	}
	
}
