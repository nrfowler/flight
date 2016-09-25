package com.cooksys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.User;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping
	public List<User> get() {
		return userService.getAll();
	}

	@RequestMapping("/{id}")
	public User get(@PathVariable("id") long id) {
		return userService.get(id);
	}
	@RequestMapping("/register")
	public void register(@RequestBody User body){
		userService.register(body);
	}
	@RequestMapping("/login")
	public void login(@RequestBody User body){
		userService.login(body);
	}
	
}
