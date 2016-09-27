package com.cooksys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cooksys.pojo.Flight;

@Entity
@Table(name = "User")
public class User implements Serializable{
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String pw;
	private Boolean isLoggedIn;
	private ArrayList<Flight> bookedRoute;
	private ArrayList<ArrayList<Flight>> prevRoutes= new ArrayList<ArrayList<Flight>>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public ArrayList<Flight> getBookedRoute() {
		return bookedRoute;
	}
	public void setBookedRoute(ArrayList<Flight> bookedRoute) {
		this.bookedRoute = bookedRoute;
	}
	public void moveBookedToPast() {
prevRoutes.add(bookedRoute);
prevRoutes.removeAll(Collections.singleton(null));
	}
	public ArrayList<ArrayList<Flight>> getPrevRoutes() {
		return prevRoutes;
	}
	public void setPrevRoutes(ArrayList<ArrayList<Flight>> prevRoutes) {
		this.prevRoutes = prevRoutes;
	}
	
}
