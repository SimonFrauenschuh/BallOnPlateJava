package ballonplate.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ballonplate.data.DatabaseTouchpanelRepository;

/**
 * Servlet implementation class TouchpanelServlet
 */
public class TouchpanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
    private DatabaseTouchpanelRepository repository;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TouchpanelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Read the values from the db and pass them in the format xxxyyy
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(Integer.toString(repository.getPosXReal())).append(Integer.toString(repository.getPosYReal()));
	}

}
