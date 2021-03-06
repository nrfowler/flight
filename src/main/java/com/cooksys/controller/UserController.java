package com.cooksys.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.User;
import com.cooksys.pojo.Flight;
import com.cooksys.pojo.Trip;
import com.cooksys.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete() {
		userService.deleteAll();
	}

	@RequestMapping("/{id}")
	public User get(@PathVariable("id") long id) {
		return userService.get(id);
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public long register(@RequestBody User body){
		 return userService.register(body);
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public long login(@RequestBody User body){
		return userService.login(body);
	}
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public void logout(@RequestBody User body){
		userService.logout(body);
	}
	@RequestMapping(value="/book", method = RequestMethod.POST)
	public Trip book(@RequestBody User u) {

		 return userService.book(u);
	}
	@RequestMapping(value="/booked", method = RequestMethod.POST)
	public ArrayList<Flight> booked(@RequestBody User u) {
		 return userService.getBookedRoute(u);
	}

}
