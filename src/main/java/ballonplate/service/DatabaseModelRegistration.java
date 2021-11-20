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

import ballonplate.model.DatabaseModel;

import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class DatabaseModelRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<DatabaseModel> memberEventSrc;

	public void register(DatabaseModel databaseModel) throws Exception {
		log.info("Registering " + databaseModel.getId());
		em.persist(databaseModel);
		memberEventSrc.fire(databaseModel);
	}

	public void delete(Long id) throws Exception {
		DatabaseModel databaseModel = em.find(DatabaseModel.class, id);
		if (databaseModel != null) {
			em.remove(databaseModel);
		}
	}
}