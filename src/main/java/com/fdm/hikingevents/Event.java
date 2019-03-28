package com.fdm.hikingevents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="event")
public class Event implements Persistable<Event> {
	@Id
	private int eId;
	private String eventName;
	private String startDate;
	private String endDate;
	private double cost;
	
	@ManyToMany(mappedBy = "eventsToParticipate")
    Set<Participant> eventParticipant;
	
	

//
//	public Event(int eId, String eventName, String startDate, String endDate, double cost, Location location) {
//		this.eId = eId;
//		this.eventName = eventName;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.cost = cost;
//		this.location = location;
//	}

	public Event() {};
	public Event(int eId, String eventName, String startDate, String endDate, double cost,
			Set<Participant> eventParticipant, Location location) {
		this.eId = eId;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.eventParticipant = eventParticipant;
		this.location = location;
	}


	@ManyToOne
	@JoinColumn(name = "lId")
	private Location location;
	
	
	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public void setEid(int id) {
		this.eId = id;
	}
	
	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public Set<Participant> getEventParticipant() {
		return eventParticipant;
	}


	public void setEventParticipant(Set<Participant> eventParticipant) {
		this.eventParticipant = eventParticipant;
	}
	
	@Override
	public int getId() {
		return eId;
	}

	
	@Override
	public void update(Event persistable) {
		this.startDate=persistable.startDate;
		this.endDate=persistable.endDate;
		this.cost=persistable.cost;
		
		
	}
	
	

}
