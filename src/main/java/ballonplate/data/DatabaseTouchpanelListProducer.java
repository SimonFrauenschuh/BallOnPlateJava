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

import ballonplate.model.DatabaseTouchpanel;

import java.util.List;

@RequestScoped
public class DatabaseTouchpanelListProducer {

    @Inject
    private DatabaseTouchpanelRepository databaseTouchpanelRepository;

    private List<DatabaseTouchpanel> allEntrys;

    // @Named provides access the return value via the EL variable name "allEntrys" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<DatabaseTouchpanel> getAllEntrys() {
        return allEntrys;
    }

    public void onAllEntrysListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final DatabaseTouchpanel databaseModel) {
        retrieveAllEntrysOrderedByName();
    }

    @PostConstruct
    public void retrieveAllEntrysOrderedByName() {
    	allEntrys = databaseTouchpanelRepository.findAllOrderedByName();
    }
}