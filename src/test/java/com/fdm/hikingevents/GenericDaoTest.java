package com.fdm.hikingevents;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

public class GenericDaoTest {
	private final EntityTransaction transaction= mock(EntityTransaction.class);
	private final EntityManager manager= mock(EntityManager.class);
	private final EntityManagerFactory emf= mock(EntityManagerFactory.class);
	private final GenericDao<Persistable> dao= new GenericDao<>(emf,Persistable.class);
	
	@Test
	public void on_add_persissts_persistable_to_database() {
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		
		Persistable persistable= mock(Persistable.class);
		
		dao.add(persistable);
		
		InOrder order= inOrder(manager,transaction,emf,persistable);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(transaction).begin();
		order.verify(manager).persist(persistable);
		order.verify(transaction).commit();
		order.verify(manager).close();
	}
	
	@Test
	public void on_get_returns_persistable_from_database() {
		when(emf.createEntityManager()).thenReturn(manager);
		Persistable persistable= mock(Persistable.class);
		when(manager.find(Persistable.class,5)).thenReturn(persistable);
		
		Persistable daoPersistable= dao.get(5);
	
		assertSame(persistable,daoPersistable);
		InOrder order= inOrder(manager,transaction,emf);
		order.verify(emf).createEntityManager();
		order.verify(manager).find(Persistable.class, 5);
		order.verify(manager).close();
	}
	
	
	@Test
	public void on_update_gets_managed_entity_and_sets_new_id() {
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		Persistable persistable= mock(Persistable.class);
		Persistable found= mock(Persistable.class);
		when(persistable.getId()).thenReturn(5);
		when(manager.find(Persistable.class,5)).thenReturn(found);
		
		dao.update(persistable);
	
		InOrder order= inOrder(manager,transaction,emf,found);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(transaction).begin();
		order.verify(manager).find(Persistable.class, 5);
		order.verify(found).update(persistable);
		order.verify(manager).close();
	}
	
	@Test
	public void when_delete_it_should_remove_from_the_database() {
		when(emf.createEntityManager()).thenReturn(manager);
		when(manager.getTransaction()).thenReturn(transaction);
		Persistable persistable= mock(Persistable.class);
		Persistable found= mock(Persistable.class);
		when(persistable.getId()).thenReturn(5);
		when(manager.find(Persistable.class,5)).thenReturn(found);
		dao.remove(persistable);
		InOrder order= inOrder(emf, manager,transaction);
		order.verify(emf).createEntityManager();
		order.verify(manager).getTransaction();
		order.verify(manager).find(Persistable.class, 5);
		order.verify(transaction).begin();
		order.verify(manager).remove(found);
		order.verify(transaction).commit();
		order.verify(manager).close();
	}
	
		
	}
	

