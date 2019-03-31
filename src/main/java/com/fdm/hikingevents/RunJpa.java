package com.fdm.hikingevents;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class RunJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("oracleDBconnect");
		GenericDao<Location> dao4= new GenericDao<>(emf, Location.class);
		Location location1= new Location(1, "Melbourne", "Sydney", "This location is the most preferred", new HashSet<Event>() );
		Location location2= new Location(2, "Wynyard", "OperaHouse", "This location is for all the young people", new HashSet<Event>());
		Location location3= new Location(3, "Hurstville", "Rockdale", "This location has some hudles and needs fitness", new HashSet<Event>());
		Location location4= new Location(4, "Wollongong", "OperaHouse", "This location is better for running", new HashSet<Event>());
		dao4.add(location1);
		dao4.add(location2);
		dao4.add(location3);
		dao4.add(location4);
		
		GenericDao<Event> dao= new GenericDao<>(emf,Event.class);
		Event event1= new Event(1,"Running","1/01/2019" ,"2/1/2019", 10.0, new HashSet<Participant>(), location1);
		Event event2= new Event(2,"100m Race","5/01/2019" ,"5/1/2019", 15.0,  new HashSet<Participant>(), location1);
		Event event3= new Event(3,"Marathon","6/01/2019" ,"7/1/2019", 20.0,  new HashSet<Participant>(), location2);
		Event event4= new Event(4,"100m Race","7/01/2019" ,"8/1/2019", 15.0,  new HashSet<Participant>(), location3);
		Event event5= new Event(5,"Friendly catch up","10/01/2019" ,"10/1/2019", 20.0,  new HashSet<Participant>(), location4);
		Event event6= new Event(6,"Party","15/01/2019" ,"15/1/2019", 15.0,  new HashSet<Participant>(), location3);
		
		dao.add(event1);
		dao.add(event2);
		dao.add(event3);
		dao.add(event4);
		dao.add(event5);
		dao.add(event6);
		
	System.out.println(dao4.getAll());
		


	}

}
