package com.fdm.hikingevents;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;

public class EventTest {
	
	private final EntityTransaction transaction= mock(EntityTransaction.class);
	private final EntityManager manager= mock(EntityManager.class);
	private final EntityManagerFactory emf= mock(EntityManagerFactory.class);
	private final GenericDao<Event> dao= new GenericDao<>(emf,Event.class);
	
	private Event event;
	
	@Before
	public void setUp() {
		event= new Event();
	}
	
	
	@Test
	public void when_id_is_set_to_be_1_it_should_return_1() {
		event.setEid(1);
		assertEquals(1,event.getId());
		
	}
	
	@Test
	public void when_event_name_is_set_it_should_return_the_eventname() {
		event.setEventName("Running");
		assertEquals("Running", event.getEventName());
	}
	
	@Test
	public void when_start_date_is_set_should_return_the_startdate() {
		event.setStartDate("1/2/2019");
		assertEquals("1/2/2019", event.getStartDate());
	}
	
	@Test
	public void when_end_date_is_set_should_return_the_startdate() {
		event.setEndDate("2/2/2019");
		assertEquals("2/2/2019", event.getEndDate());
	}
	
	@Test
	public void when_cost_is_set_should_return_the_cost() {
		event.setCost(20.0);
		assertEquals(20.0,event.getCost(),0.00);
		
		
	}
	
	@Test
	public void on_update_gets_managed_entity_and_sets_new_id() {
		
		Event event1= new Event();
		Event event2= new Event();
		event1.setEventName("Pizza eating");
		event1.setCost(20.0);
		event1.setEid(5);
		event1.setStartDate("1/1/2010");
		assertEquals("Pizza eating",event1.getEventName());
		
		event2.setEventName("swimming");
		event2.setCost(50.0);
		event2.setStartDate("2/2/2020");

		event1.update(event2);
		
		assertEquals("swimming",event1.getEventName());
	}
		
		


}
