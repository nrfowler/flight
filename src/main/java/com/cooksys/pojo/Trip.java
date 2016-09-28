package com.cooksys.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Trip implements Serializable{
	private ArrayList<Layover> layovers;
	private ArrayList<Flight> route;
	private long totalLayoverTime;
	private long totalFlightTime;
	public void computeLayovers(){
		if(route.size()==1){
			return;
		}
		layovers = new ArrayList<Layover>();
		for(int i =1;i<route.size();i++){
			Layover layover = new Layover(route.get(i).getOrigin(), route.get(i).getOffset()-route.get(i-1).getOffset()-route.get(i-1).getFlightTime());
			layovers.add(layover);
			totalLayoverTime+=layover.getTime();
			System.out.println(layover.getCity());
		}
		
	}
	public ArrayList<Layover> getLayovers() {
		return layovers;
	}
	public void setLayovers(ArrayList<Layover> layovers) {
		this.layovers = layovers;
	}
	public ArrayList<Flight> getRoute() {
		return route;
	}
	public void setRoute(ArrayList<Flight> route) {
		this.route = route;
		for(Flight f : route){
			totalFlightTime+=f.getFlightTime();
		}
	}
	public Trip(ArrayList<Layover> layovers, ArrayList<Flight> route) {
		super();
		this.layovers = layovers;
		this.route = route;
		this.totalFlightTime=0;
				this.totalLayoverTime=0;
	}
	public Trip(){
		this.totalFlightTime=0;
		this.totalLayoverTime=0;
	}
	public long getTotalLayoverTime() {
		return totalLayoverTime;
	}
	public void setTotalLayoverTime(long totalLayoverTime) {
		this.totalLayoverTime = totalLayoverTime;
	}
	public long getTotalFlightTime() {
		return totalFlightTime;
	}
	public void setTotalFlightTime(long totalFlightTime) {
		this.totalFlightTime = totalFlightTime;
	}
	public Trip(ArrayList<Layover> layovers, ArrayList<Flight> route, long totalLayoverTime, long totalFlightTime) {
		super();
		this.layovers = layovers;
		this.route = route;
		this.totalLayoverTime = totalLayoverTime;
		this.totalFlightTime = totalFlightTime;
	};
	
}
