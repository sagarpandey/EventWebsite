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

public class ParticipantInfoTest {
	private final EntityTransaction transaction= mock(EntityTransaction.class);
	private final EntityManager manager= mock(EntityManager.class);
	private final EntityManagerFactory emf= mock(EntityManagerFactory.class);
	private final GenericDao<ParticipantInfo> dao= new GenericDao<>(emf,ParticipantInfo.class);
	private ParticipantInfo participateInfo;
	
	@Before
	public void setUp() {
		 participateInfo= new ParticipantInfo();
	}
	
	@Test
	public void when_id_is_set_as_1_shoud_return_1() {
		participateInfo.setpInfoid(1);
		assertEquals(1,participateInfo.getId());
		
	}
	
	@Test
	public void when_address_is_set_as_sydney_shoud_return_sydney() {
		participateInfo.setAddress("sydney");
		assertEquals("sydney",participateInfo.getAddress());
		
	}
	
	@Test
	public void when_desgnation_is_set_as_hiker_shoud_return_hiker() {
		participateInfo.setDesignation("hiker");
		assertEquals("hiker",participateInfo.getDesignation());
		
	}
	
	@Test
	public void on_update_gets_managed_entity_and_sets_new_id() {
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		ParticipantInfo participantInfo= mock(ParticipantInfo.class);
		ParticipantInfo found= mock(ParticipantInfo.class);
		when(participantInfo.getId()).thenReturn(5);
		when(manager.find(ParticipantInfo.class,5)).thenReturn(found);
		
		dao.update(participantInfo);
	
		InOrder order= inOrder(manager,transaction,emf,found);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(transaction).begin();
		order.verify(manager).find(ParticipantInfo.class, 5);
		order.verify(found).update(participantInfo);
		order.verify(manager).close();
	}
	

}
