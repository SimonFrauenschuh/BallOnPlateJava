/* Copyright (C) 2021 Simon Frauenschuh - All Rights Reserved
 * You may use and / or modify this code in
 * terms of private use.
 * Any caused damage or misbehaviour of any components are
 * under the responsibility of the user and and the editor
 * cannot be prosecuted for it
 */

package ballonplate.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ballonplate.model.DatabaseTouchpanel;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class DatabaseTouchpanelRegistration {
	
	@Inject
	private EntityManager em;

	@Inject
	private Event<DatabaseTouchpanel> memberEventSrc;

	public void register(DatabaseTouchpanel databaseModel) throws Exception {
		em.persist(databaseModel);
		memberEventSrc.fire(databaseModel);
	}

	public void delete(Long id) throws Exception {
		DatabaseTouchpanel databaseModel = em.find(DatabaseTouchpanel.class, id);
		if (databaseModel != null) {
			em.remove(databaseModel);
		}
	}
}