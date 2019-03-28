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

public class LocationTest {
	private final EntityTransaction transaction= mock(EntityTransaction.class);
	private final EntityManager manager= mock(EntityManager.class);
	private final EntityManagerFactory emf= mock(EntityManagerFactory.class);
	private final GenericDao<Location> dao= new GenericDao<>(emf,Location.class);

private Location location;
	
	@Before
	public void setUp() {
		location=new Location();
	}
	
	@Test
	public void when_setting_id_as_1_it_should_return_1() {
		location.setlId(1);
		assertEquals(1,location.getId());
	}
	
	@Test
	public void when_setting_start_location_as_sydney_should_return_sydney() {
		location.setStartLocation("Sydney");
		assertEquals("Sydney",location.getStartLocation());
	}
	
	

	@Test
	public void when_setting_end_location_as_melbourne_should_return_melbourne() {
		location.setStartLocation("Melbourne");
		assertEquals("Melbourne",location.getStartLocation());
	}
	
	@Test
	public void when_setting_description_should_return_melbourne() {
		location.setDescription("This location is good");
		assertEquals("This location is good",location.getDescription());
	}
	
	@Test
	public void on_update_gets_managed_entity_and_sets_new_id() {
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		Location location= mock(Location.class);
		Location found= mock(Location.class);
		when(location.getId()).thenReturn(5);
		when(manager.find(Location.class,5)).thenReturn(found);
		
		dao.update(location);
	
		InOrder order= inOrder(manager,transaction,emf,found);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(transaction).begin();
		order.verify(manager).find(Location.class, 5);
		order.verify(found).update(location);
		order.verify(manager).close();
	}
	
}
