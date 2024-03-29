/* Copyright (C) 2021 Simon Frauenschuh - All Rights Reserved
 * You may use and / or modify this code in
 * terms of private use.
 * Any caused damage or misbehaviour of any components are
 * under the responsibility of the user and and the editor
 * cannot be prosecuted for it
 */

package ballonplate.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ballonplate.model.DatabaseResult;

@Named("DatabaseResultRepository")
@ApplicationScoped
public class DatabaseResultRepository {

	@Inject
	private EntityManager em;
	
	@Inject
	private DatabaseResultListProducer DatabaseResultListProducer;

	public DatabaseResult findById(int id) {
		return em.find(DatabaseResult.class, id);
	}
	
	public List<DatabaseResult> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DatabaseResult> criteria = cb.createQuery(DatabaseResult.class);
		Root<DatabaseResult> DatabaseResult = criteria.from(DatabaseResult.class);
		criteria.select(DatabaseResult).orderBy(cb.desc(DatabaseResult.get("id")));
		return em.createQuery(criteria).getResultList();
	}
	
	private int findMaxId() {
		return DatabaseResultListProducer.getAllEntrys().get(0).getId();
	}
	
	public int getResult() {
		int id = findMaxId();
		return findById(id).getResult();
	}
	
	public int getMode() {
		int id = findMaxId();
		return findById(id).getMode();
	}
}