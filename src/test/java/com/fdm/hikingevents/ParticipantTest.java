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

public class ParticipantTest {
	
	private final EntityTransaction transaction= mock(EntityTransaction.class);
	private final EntityManager manager= mock(EntityManager.class);
	private final EntityManagerFactory emf= mock(EntityManagerFactory.class);
	private final GenericDao<Participant> dao= new GenericDao<>(emf,Participant.class);
	
	private Participant participant;
	
	@Before
	public void setUp() {
		participant =new Participant();
	}
	
	@Test
	public void when_id_is_set_as_1_it_should_return_1() {
		participant.setPid(1);
		assertEquals(1,participant.getId());
		
	}
	
	@Test
	public void when_first_name_is_set_as_sagar_it_should_return_sagar() {
		participant.setFirstName("sagar");;
		assertEquals("sagar",participant.getFirstName());
		
	}
	
	@Test
	public void when_last_name_is_set_as_pandey_it_should_return_pandey() {
		participant.setLastName("pandey");
		assertEquals("pandey",participant.getLastName());
		
	}
	

	@Test
	public void on_update_gets_managed_entity_and_sets_new_id() {
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		Participant participant= mock(Participant.class);
		Participant found= mock(Participant.class);
		when(participant.getId()).thenReturn(5);
		when(manager.find(Participant.class,5)).thenReturn(found);
		
		dao.update(participant);
	
		InOrder order= inOrder(manager,transaction,emf,found);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(transaction).begin();
		order.verify(manager).find(Participant.class, 5);
		order.verify(found).update(participant);
		order.verify(manager).close();
	}
	
	
	
}
