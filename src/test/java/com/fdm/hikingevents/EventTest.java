package com.fdm.hikingevents;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		Event event= mock(Event.class);
		Event found= mock(Event.class);
		when(event.getId()).thenReturn(5);
		when(manager.find(Event.class,5)).thenReturn(found);
		
		dao.update(event);
	
		InOrder order= inOrder(manager,transaction,emf,found);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(transaction).begin();
		order.verify(manager).find(Event.class, 5);
		order.verify(found).update(event);
		order.verify(manager).close();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
