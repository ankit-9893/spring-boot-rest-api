package com.rest.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class HomeController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "get")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping(path = "getMessage")
	public String goodWillMessage(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null ,locale);
	}
	
	@GetMapping(path = {"message", "message/{name}"})
	public String message(@PathVariable Optional<String> name ) {
		if(name.isPresent()) {
			return "hello " + name.get();
		} else {
			return "Hello Sir";
		}	
	}
	
}
