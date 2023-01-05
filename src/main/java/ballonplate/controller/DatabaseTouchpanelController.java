/* Copyright (C) 2021 Simon Frauenschuh - All Rights Reserved
 * You may use and / or modify this code in
 * terms of private use.
 * Any caused damage or misbehaviour of any components are
 * under the responsibility of the user and and the editor
 * cannot be prosecuted for it
 */

package ballonplate.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ballonplate.model.DatabaseResult;
import ballonplate.model.DatabaseTouchpanel;
import ballonplate.service.DatabaseResultRegistration;
import ballonplate.service.DatabaseTouchpanelRegistration;

// The @Model annotation is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
public class DatabaseTouchpanelController {

	@Inject
    private FacesContext facesContext;
	
	@Inject
    private DatabaseTouchpanelRegistration databaseTouchpanelRegistration;
	
	@Inject
    private DatabaseResultRegistration databaseResultRegistration;
	
	@Produces
	@Named
	private DatabaseTouchpanel newDatabaseModel;
	
	@Produces
	@Named
	private DatabaseResult newResultModel;
	
	@PostConstruct
    public void initNewDatabaseModel() {
		newDatabaseModel = new DatabaseTouchpanel();
    }

	// Method to be called, whenever the site is reloaded / called
	// Set the Mode to "0" (auto-balancing)
	public void startup() throws Exception {
		newResultModel = new DatabaseResult();
		newResultModel.setMode(0);
		newResultModel.setResult(100);
		newResultModel.setError(0);
	    
	    try {
	    	databaseResultRegistration.register(newResultModel);
		} catch (Exception e) {}
	}
	
	public void register() throws Exception {
		try {
			databaseTouchpanelRegistration.register(newDatabaseModel);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initNewDatabaseModel();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
	}
	
	private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
}