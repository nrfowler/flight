package com.cooksys.pojo;

import java.io.Serializable;

import com.cooksys.entity.Location;

public class Layover implements Serializable{
	private String city;
	private long time;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public Layover(String city, long layover) {
		super();
		this.city = city;
		this.time = layover;
		System.out.println("Layover created with " + city +" city and "+ layover +" time");
	}
	public Layover(){}
	
	
}
