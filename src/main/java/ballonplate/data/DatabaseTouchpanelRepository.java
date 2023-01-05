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

import ballonplate.model.DatabaseTouchpanel;

@Named("DatabaseTouchpanelRepository")
@ApplicationScoped
public class DatabaseTouchpanelRepository {

	@Inject
	private EntityManager em;
	
	@Inject
	private DatabaseTouchpanelListProducer databaseTouchpanelListProducer;

	public DatabaseTouchpanel findById(int id) {
		return em.find(DatabaseTouchpanel.class, id);
	}
	
	public List<DatabaseTouchpanel> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DatabaseTouchpanel> criteria = cb.createQuery(DatabaseTouchpanel.class);
		Root<DatabaseTouchpanel> databaseTouchpanel = criteria.from(DatabaseTouchpanel.class);
		criteria.select(databaseTouchpanel).orderBy(cb.desc(databaseTouchpanel.get("id")));
		return em.createQuery(criteria).getResultList();
	}
	
	// Only way because of an unknown bug some ids don't get used
	public int findMaxId() {
		return databaseTouchpanelListProducer.getAllEntrys().get(0).getId();
	}
	
	public int getPosXEst() {
		int id = findMaxId();
		return findById(id).getXEst();
	}

	public int getPosYEst() {
		int id = findMaxId();
		return findById(id).getYEst();
	}
	
	public int getPosXReal() {
		int id = findMaxId();
		return findById(id).getXReal();
	}

	public int getPosYReal() {
		int id = findMaxId();
		return findById(id).getYReal();
	}
}