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
	
	public ArrayList<ArrayList<Flight>> getToFromFlightList(String origin, String dest)
	{
		ArrayList<Flight> layoverFlights = new ArrayList<Flight>(flightList);
		ArrayList<Flight> layoverFlights2 = new ArrayList<Flight>(flightList);
		ArrayList<Flight> endFlights = new ArrayList<Flight>();
		ArrayList<Flight> startFlights = new ArrayList<Flight>();
		ArrayList<ArrayList<Flight>> possibleRoutes = new ArrayList<ArrayList<Flight>>();
		for(Flight f : layoverFlights2){
			if(Objects.equals(f.getOrigin(),origin)){
				startFlights.add(f);
			}
			if(Objects.equals(f.getDestination(),dest)){
				endFlights.add(f);
			}
			if(Objects.equals(f.getOrigin(),origin)&&Objects.equals(f.getDestination(),dest)){
				ArrayList<Flight> temp = new ArrayList<Flight>();
				temp.add(f);
				possibleRoutes.add(temp);
				layoverFlights.remove(f);
			}
			//if the plane is going the opposite direction (loops), remove these from the possible search
			if(Objects.equals(f.getOrigin(),dest)||Objects.equals(f.getDestination(),origin)){
				layoverFlights.remove(f);
			}
			
			//if offset and layover is such that no time to board another plane, then remove
			
			
		}
		for(Flight f : layoverFlights){
			//possflights=layoverflights-f
			//for possflight in possflights
				//if possflight starts after f, 
					//add possflight to possroute
					//posflights = possflights - possflight
					//for possflight2 in possflights
						//possflight2.time > possflight
						//possflights = possflights - possflight2
						//for possflight3 in possflights
					
		}
		
		for(Flight f : startFlights){
			for(Flight e: endFlights){
				if(Objects.equals(e.getOrigin(),f.getDestination())&&((f.getOffset()+f.getFlightTime()+1)<=e.getOffset())){
					ArrayList<Flight> temp = new ArrayList<Flight>();
					temp.add(f);
					temp.add(e);
					possibleRoutes.add(temp);
				}
			}
		}
		//combine possibleroutes and selFlights
		return possibleRoutes;
	}
	
	//layovers
	//routes
	//getRoute(flight)
	//if (time> mytime && dest != mydest)
		//route.add(flight)
		// getRoute(flight, possflights);
	//if ( dest==mydest,
		//routes.add(route)
	
	//an arraylist arraylist which is passed recursively to a function, which replaces each route arraylist with new route arraylists (if more valid flights) or deletes it (if no more valid flights to destination)
	//if lands on dest, then saves arraylist to a arraylist, or just doesn't delete...
//it will need a ref. to list of all layover flights, either passed in or global. it will run through all of them on each route.
	//a global save routes would be necessary, to avoid repeat evaluation of routes
	//My plan: a class which contains "global" data, and a function which calls itself, passing in the data that I need to modify
	
	//	ArrayList<ArrayList<Flight>> possRoutes(ArrayList<Flight> flights){
//		ArrayList<ArrayList<Flight>> possibleRoutes = new ArrayList<ArrayList<Flight>>();
//		for(Flight f : flights){
//			flights.remove(f);
//			
//		}
//		
//	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=1000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
	}
	
}
