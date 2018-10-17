package com.LibrarySystemV3.Dao;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.LibrarySystemV3.models.User;

@Repository
@Transactional
public class UserV3Dao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(User user) {
		entityManager.persist(user);
	}
	
	public User getByEmail(String email) {
		return (User) entityManager.createQuery("from UserV3 where emai= :Email").setParameter("Email", email).getSingleResult(); 
	}
}
