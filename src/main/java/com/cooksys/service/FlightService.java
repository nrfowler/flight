package com.cooksys.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.pojo.Flight;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	public ArrayList<Flight> getToFromFlightList(String origin, String dest)
	{
		ArrayList<Flight> selFlights = new ArrayList<Flight>();
		for(Flight f : flightList){
			if(Objects.equals(f.getOrigin(),origin)&&Objects.equals(f.getDestination(),dest)){
				selFlights.add(f);
			}
		}
		return selFlights;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=5000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
	}
	
}
