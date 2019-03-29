package com.fdm.hikingevents;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class GenericDao<T extends Persistable> {
	
	private final EntityManagerFactory emf;
	private Class<T> clazz;
	
	public GenericDao(EntityManagerFactory emf, Class<T> clazz) {
		this.emf= emf;
		this.clazz=clazz;
	}

	
	public void add(T persistable) {
		EntityManager manager= emf.createEntityManager();
		EntityTransaction transaction= manager.getTransaction();
		transaction.begin();
		manager.persist(persistable);
		transaction.commit();
		manager.close();
		
	}


	public T get(int id) {
		EntityManager manager= emf.createEntityManager();
		T found= manager.find(clazz, id);
		manager.close();
		return found;
	}


	public void update(T persistable) {
		EntityManager manager= emf.createEntityManager();
		EntityTransaction transaction= manager.getTransaction();
		transaction.begin();
		T found= manager.find(clazz, persistable.getId());
		found.update(persistable);
		transaction.commit();
		manager.close();
	}
	
	public void remove(T persistable) {
		EntityManager manager= emf.createEntityManager();
		EntityTransaction transaction= manager.getTransaction();
		T found= manager.find(clazz, persistable.getId());
		transaction.begin();
		manager.remove(found);
		transaction.commit();
		manager.close();
	}
	

	public List<T> getAll() {
		EntityManager manager= emf.createEntityManager();
		TypedQuery query= manager.createQuery(
				"SELECT e FROM " +clazz.getName() +" e", Persistable.class);

		List<T> allEvents = query.getResultList();
		return  allEvents;

		
	}
	
	

}

