package com.cooksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.User;
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

	public Boolean register(User body) {
		User temp=repo.findByNameAndPw(body.getName(), body.getPw());
		if(temp==null){
			repo.save(body);
			return true;
		}
		else{
			return false;
		}
		

	}

	public void login(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		u.setIsLoggedIn(true);
		repo.save(u);
	}
	public void logout(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		u.setIsLoggedIn(false);
		repo.save(u);
	}

	public void book(User body) {
		User u = repo.findByNameAndPw(body.getName(), body.getPw());
		u.moveBookedToPast();
		u.setBookedRoute(body.getBookedRoute());
		repo.save(u);
	}

	public void deleteAll() {
		repo.deleteAll();
	}	

}
