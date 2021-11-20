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

import ballonplate.model.DatabaseModel;

@Named("DatabaseModelRepository")
@ApplicationScoped
public class DatabaseModelRepository {

	@Inject
	private EntityManager em;
	
	@Inject
	private DatabaseModelListProducer databaseModelListProducer;

	public DatabaseModel findById(Long id) {
		return em.find(DatabaseModel.class, id);
	}
	
	public List<DatabaseModel> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DatabaseModel> criteria = cb.createQuery(DatabaseModel.class);
		Root<DatabaseModel> databaseModel = criteria.from(DatabaseModel.class);
		criteria.select(databaseModel).orderBy(cb.asc(databaseModel.get("id")));
		return em.createQuery(criteria).getResultList();
	}
	
	private long findMaxId() {
		List<DatabaseModel> list = databaseModelListProducer.getAllEntrys();
		return list.size();
	}
	
	public double getPosX() {
		long id = findMaxId();
		return findById(id).getPositionX();
	}

	public double getPosY() {
		long id = findMaxId();
		return findById(id).getPositionY();
	}
}