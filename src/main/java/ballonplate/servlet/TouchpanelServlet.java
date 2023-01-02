package ballonplate.servlet;

import java.io.IOException;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ballonplate.data.DatabaseTouchpanelRepository;
import ballonplate.model.DatabaseResult;
import ballonplate.service.DatabaseResultRegistration;

/**
 * Servlet implementation class TouchpanelServlet
 */
public class TouchpanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
    private DatabaseTouchpanelRepository repositoryTouchpanel;
	
	@Inject
    private DatabaseResultRegistration registrationResult;
	
	@Produces
	@Named
	private DatabaseResult normalResultModel;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TouchpanelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Read the values from the db and pass them in the format xxxyyy
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(Integer.toString(repositoryTouchpanel.getPosXReal())).append(Integer.toString(repositoryTouchpanel.getPosYReal()));
	}
	
	// Extract the data from the POST-Request and store it into the db
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		normalResultModel = new DatabaseResult();
		normalResultModel.setMode(0);
		normalResultModel.setResult(0);
		normalResultModel.setError(0);
	    
	    try {
			registrationResult.register(normalResultModel);
		} catch (Exception e) {}
	}
}
