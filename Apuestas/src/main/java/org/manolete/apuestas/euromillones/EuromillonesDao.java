package org.manolete.apuestas.euromillones;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "euromillonesDao")
public class EuromillonesDao {
	
	private EntityManager entityManager;	
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Sorteo> findAll() {
		System.out.println("Euromillones.findAll");
		return this.entityManager.createQuery("select s from Sorteo s").getResultList();
	}

}
