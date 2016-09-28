package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.User;
import com.cooksys.pojo.Flight;
import com.cooksys.pojo.Trip;
import com.cooksys.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
		
	public List<User> getAll()
	{
		return repo.findAll();
	}

	public User get(long id) {
		return repo.findById(id);
	}

	public long register(User body) {
		User temp=repo.findByNameAndPw(body.getName(), body.getPw());
		if(temp==null){
			body.setIsLoggedIn(true);
			repo.save(body);
			temp=repo.findByNameAndPw(body.getName(), body.getPw());
			return temp.getId();
		}
		else{
			return 0;
		}
		

	}

	public long login(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		u.setIsLoggedIn(true);
		repo.save(u);
		return u.getId();
	}
	
	public void get(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		u.setIsLoggedIn(true);
		repo.save(u);
	}
	public void logout(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		u.setIsLoggedIn(false);
		repo.save(u);
	}

	public Trip book(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		System.out.println(u.getBookedRoute());
		u.moveBookedToPast();
		System.out.println(u.getPrevRoutes());
		
		u.setBookedRoute(body.getBookedRoute());
		repo.save(u);
		Trip trip = new Trip();
		trip.setRoute(body.getBookedRoute());
		trip.computeLayovers();

		return trip;
	}

	public void deleteAll() {
		repo.deleteAll();
	}

	public ArrayList<Flight> getBookedRoute(User u) {
		return repo.findByNameAndPw(u.getName(), u.getPw()).getBookedRoute();
		
	}	

}
