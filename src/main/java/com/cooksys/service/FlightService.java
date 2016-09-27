package com.cooksys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		System.out.println("--------------");
		 printFlightList();
		ArrayList<Flight> layoverFlights = new ArrayList<Flight>(flightList);
		ArrayList<Flight> endFlights = new ArrayList<Flight>();
		ArrayList<Flight> startFlights = new ArrayList<Flight>();
		ArrayList<ArrayList<Flight>> possibleRoutes = new ArrayList<ArrayList<Flight>>();
		ArrayList<ArrayList<Flight>> startingRoutes = new ArrayList<ArrayList<Flight>>();

		for(Flight f : flightList){
			if(Objects.equals(f.getOrigin(),origin)){
				startFlights.add(f);
				ArrayList<Flight> temp = new ArrayList<Flight>();
				temp.add(f);
				startingRoutes.add(temp);
				layoverFlights.remove(f);
			}
			if(Objects.equals(f.getDestination(),dest)){
				endFlights.add(f);
			}
			//if flight is one way to our dest.
			if(Objects.equals(f.getOrigin(),origin)&&Objects.equals(f.getDestination(),dest)){
				ArrayList<Flight> temp = new ArrayList<Flight>();
				temp.add(f);
				possibleRoutes.add(temp);
				//layoverFlights.remove(f);
			}
			//if the plane is going the opposite direction (loops), remove these from the possible search
			if(Objects.equals(f.getOrigin(),dest)||Objects.equals(f.getDestination(),origin)){
				layoverFlights.remove(f);
			}
			
			//if offset and layover is such that no time to board another plane, then remove
			
			
		}
	
		System.out.println("New Call");
		System.out.println("Starting routes outside fn");
		printRoutes(startingRoutes);
		System.out.println("Layover flights (other than starting flight) outside fn");
		printflights(layoverFlights);
		return recursive(new ArrayList<ArrayList<Flight>>(), layoverFlights, startingRoutes, dest);
	}
	Boolean isValidNextFlight(Flight last, Flight next){
		return(Objects.equals(next.getOrigin(),last.getDestination())&&((last.getOffset()+last.getFlightTime()+1)<=next.getOffset()));
	}
	void printFlight (Flight f){
		System.out.println(f.getOrigin()+" "+f.getDestination());
	}
	
	void printFlightList(){
		System.out.println("flightList (all flights): ");
		for (Flight f : this.flightList){
			printFlight(f);
		}
	}
	
	void printRoutes(ArrayList<ArrayList<Flight>> routes){
		for ( ArrayList<Flight> route : routes){
			System.out.println("Route: "+route.size());
			printflights(route);
		}
	}
	
	void printflights(ArrayList<Flight> flights){
		for (Flight f : flights){
			printFlight(f);
		}
	}
	 ArrayList<ArrayList<Flight>> recursive(ArrayList<ArrayList<Flight>> validRoutes, ArrayList<Flight> possibleFlights, ArrayList<ArrayList<Flight>> possibleRoutes, String destination){
		 ArrayList<ArrayList<Flight>> newPossibleRoutes = new ArrayList<ArrayList<Flight>>();
		 printFlightList();
		 System.out.println("List of possible flights (other than starting flight)");
		 printflights(possibleFlights);
		 System.out.println("List of starting routes inside fn");
		 printRoutes(possibleRoutes);
		 for(ArrayList<Flight> route : possibleRoutes){
			 //check for one way flights in the possibleRoutes
			 //System.out.println("last flight of route: ");
			 //printFlight(route.get(route.size()-1));
			 if(Objects.equals(route.get(route.size()-1).getDestination(), destination) ){
				 System.out.println("valid route added");
				 validRoutes.add(route);
			 }
			for(Flight nextFlight : possibleFlights){
				//printFlight(nextFlight);
				if(isValidNextFlight(route.get(route.size()-1), nextFlight)){
				ArrayList<Flight> newRoute = new ArrayList<Flight>(route);
				newRoute.add(nextFlight);
				if(Objects.equals(nextFlight.getDestination(),destination)){
					validRoutes.add(newRoute);
				}
				else
					newPossibleRoutes.add(newRoute);
				}
				
			}

		}
		 System.out.println("Valid Routes: ");
		 printRoutes(validRoutes);
		if(newPossibleRoutes.size()==0)
			return validRoutes;
		else
			return recursive(validRoutes, possibleFlights, newPossibleRoutes, destination);
	}

	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=1000)
	private void refreshFlights()
	{
		flightList = new ArrayList<Flight>();//generator.generateNewFlightList();
		flightList.add(new Flight("CHATTANOOGA", "NASHVILLE", 2, 0));
		flightList.add(new Flight("NASHVILLE","CHATTANOOGA", 1, 0));
		flightList.add(new Flight("NASHVILLE","MEMPHIS", 1, 0));
		flightList.add(new Flight("MEMPHIS","KNOXVILLE", 1, 3));
		flightList.add(new Flight("KNOXVILLE","CHATTANOOGA", 1, 5));
		flightList.add(new Flight("KNOXVILLE","CHATTANOOGA", 1, 6));
		flightList.add(new Flight("KNOXVILLE","CHATTANOOGA", 1, 3));




	}
	
}
