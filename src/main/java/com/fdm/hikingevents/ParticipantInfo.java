package com.fdm.hikingevents;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParticipantInfo implements Persistable<ParticipantInfo> {
	
	@Id
	private int pInfoid;
	private String address;
	private String designation;
	@OneToOne(mappedBy = "participantInfo")
	private Participant participant;
	 
	 
	public ParticipantInfo() {};
	
	public ParticipantInfo(int pInfoid, String address, String designation) {
		this.pInfoid = pInfoid;
		this.address = address;
		this.designation = designation;
	}
	public void setpInfoid(int pInfoid) {
		this.pInfoid = pInfoid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public int getId() {
		return pInfoid;
	}
	@Override
	public void update(ParticipantInfo persistable) {
		this.address=persistable.address;
		this.designation=persistable.designation;
		
	}

}
