package com.keeper.dao;

import java.util.List;

import com.keeper.model.Keeper;
import com.keeper.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class KeeperDao {
	
	public void saveNotes(Keeper note) {
		
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		if (note != null) {
			
			et.begin();
			em.persist(note);
			et.commit();
		}
		
		em.close();
	}
	
	public List<Keeper> getAllNotes() {
		
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Keeper> query = em.createQuery("select k from Keeper k",Keeper.class);
		List<Keeper> notes = query.getResultList();
		
		em.close();
		
		return notes;
		
	}
	
	public void removeNote(int noteid) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory() ;
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Keeper note = em.find(Keeper.class, noteid);
		
		if (note != null) {
			
			et.begin();
			em.remove(note);
			et.commit();
			
			
			
			
		} else {
			
			System.out.println("note is null");
		}
		
		em.close();
		
	}

}
