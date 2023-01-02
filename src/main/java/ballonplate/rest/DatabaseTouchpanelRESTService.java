/* Copyright (C) 2021 Simon Frauenschuh - All Rights Reserved
 * You may use and / or modify this code in
 * terms of private use.
 * Any caused damage or misbehaviour of any components are
 * under the responsibility of the user and and the editor
 * cannot be prosecuted for it
 */

package ballonplate.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ballonplate.data.DatabaseTouchpanelRepository;
import ballonplate.model.DatabaseTouchpanel;
import ballonplate.service.DatabaseTouchpanelRegistration;

// Defines the path, where the results will be shown
@Path("/entrys")
@RequestScoped
public class DatabaseTouchpanelRESTService {

    @Inject
    private DatabaseTouchpanelRepository repository;

    @Inject
    DatabaseTouchpanelRegistration registration;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<DatabaseTouchpanel> listAllEntrys() {
        return repository.findAllOrderedByName();
    }

    @GET
    @Path("/{id:[0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public DatabaseTouchpanel lookupEntryById(@PathParam("id") int id) {
    	DatabaseTouchpanel databaseModel = repository.findById(id);
        if (databaseModel == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return databaseModel;
    }

    
}