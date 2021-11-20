/* Copyright (C) 2021 Simon Frauenschuh - All Rights Reserved
 * You may use and / or modify this code in
 * terms of private use.
 * Any caused damage or misbehaviour of any components are
 * under the responsibility of the user and and the editor
 * cannot be prosecuted for it
 */

package ballonplate.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ballonplate.model.DatabaseModel;

import java.util.List;

@RequestScoped
public class DatabaseModelListProducer {

    @Inject
    private DatabaseModelRepository databaseModelRepository;

    private List<DatabaseModel> allEntrys;

    // @Named provides access the return value via the EL variable name "allEntrys" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<DatabaseModel> getAllEntrys() {
        return allEntrys;
    }

    public void onAllEntrysListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final DatabaseModel databaseModel) {
        retrieveAllEntrysOrderedByName();
    }

    @PostConstruct
    public void retrieveAllEntrysOrderedByName() {
    	allEntrys = databaseModelRepository.findAllOrderedByName();
    }
}