package ballonplate.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ballonplate.data.DatabaseModelRepository;
import ballonplate.model.DatabaseModel;
import ballonplate.service.DatabaseModelRegistration;

/**
 * Servlet implementation class GamemodeServlet
 */
public class GamemodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
    private DatabaseModelRepository repository;
	
	@Inject
    private DatabaseModelRegistration registration;
	
	@Produces
	@Named
	private DatabaseModel gameDatabaseModel;
	
    /**
     * Default constructor. 
     */
    public GamemodeServlet() {
        // TODO Auto-generated constructor stub
    }
    
    // Read the values from the db and pass them in the format xxxyyy
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(Integer.toString(repository.getPosXReal())).append(Integer.toString(repository.getPosYReal()));
	}
	
	// Copyright by OneCricketeer and Adrian Ayala Torres (https://stackoverflow.com/a/14885950/16552518)
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
            bufferedReader = request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
	
	// Extract the data from the POST-Request and store it into the db
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postBody = getBody(request);
		
		gameDatabaseModel = new DatabaseModel();
		gameDatabaseModel.setError(0);
		gameDatabaseModel.setMode(1);
		gameDatabaseModel.setPositionXEst(Integer.parseInt("" + postBody.charAt(11) + postBody.charAt(12)));
		gameDatabaseModel.setPositionYEst(Integer.parseInt("" + postBody.charAt(24) + postBody.charAt(25)));
		
		
		try {
			registration.register(gameDatabaseModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
