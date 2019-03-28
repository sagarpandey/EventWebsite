package com.fdm.hikingevents;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location implements Persistable<Location> {
	
	@Id
	private int lId;
	private String startLocation;
	private String endLocation;
	private String description;
	
	
	public Location() {};
	
	public Location(int lId, String startLocation, String endLocation, String description, Set<Event> event) {
		this.lId = lId;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.description = description;
		this.event = event;
	}


	
	@OneToMany(mappedBy="location")
	@Column(name="Events")
	private Set<Event> event;
	

	
	public Set<Event> getEvents() {
		return event;
	}

	public void setEvents(Set<Event> event) {
		this.event = event;
	}

	public void setlId(int lId) {
		this.lId = lId;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getId() {
		return lId;
	}

	@Override
	public void update(Location persistable) {
		this.startLocation=persistable.startLocation;
		this.endLocation=persistable.endLocation;
		this.description=persistable.description;
		
	}
	

}
