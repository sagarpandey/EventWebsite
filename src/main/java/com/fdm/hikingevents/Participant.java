package com.fdm.hikingevents;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="participant")

public class Participant implements Persistable<Participant>  {
	
	@Id
	private int pId;
	private String firstName;
	private String lastName;
	
	@OneToOne
	@JoinColumn (name="pInfoid")
	private ParticipantInfo participantInfo;
	
	
	@ManyToMany
	@JoinTable(
	        name = "EventParticipant", 
	        joinColumns = { @JoinColumn(name = "pId") }, 
	        inverseJoinColumns = { @JoinColumn(name = "eId") }
	    )
    Set<Event> eventsToParticipate;
	
	
	public Participant() {};

	public Participant(int pId, String firstName, String lastName, Set<Event> eventsToParticipate) {
		this.pId = pId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eventsToParticipate = eventsToParticipate;
	}

	public String getFirstName() {
		return firstName;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public void setPid(int id) {
		this.pId = id;
	}
	
	public Set<Event> getEventsToParticipate() {
		return eventsToParticipate;
	}

	public void setEventsToParticipate(Set<Event> eventsToParticipate) {
		this.eventsToParticipate = eventsToParticipate;
	}
	@Override
	public int getId() {
		return pId;
	}

	@Override
	public void update(Participant persistable) {
		this.firstName = persistable.firstName;
		this.lastName = persistable.lastName;
		
	
		
	}

	
	

}
