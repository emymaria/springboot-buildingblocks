package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@Min(1) @PathVariable("id") Long id){
		try {
			Optional<User> userOptional= userService.userById(id);
			User user=userOptional.get();
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapper.setFilters(filterProvider);
			return mapper;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
	}
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@Min(1) @PathVariable("id") Long id,@RequestParam Set<String> fields){
		try {
			Optional<User> userOptional= userService.userById(id);
			User user=userOptional.get();
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapper.setFilters(filterProvider);
			return mapper;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
	}
}
