package ballonplate.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ballonplate.data.DatabaseResultRepository;
import ballonplate.model.DatabaseResult;
import ballonplate.model.DatabaseTouchpanel;
import ballonplate.service.DatabaseResultRegistration;
import ballonplate.service.DatabaseTouchpanelRegistration;

/**
 * Servlet implementation class GamemodeServlet
 */
public class GamemodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
    private DatabaseResultRepository databaseResultRepository;
	
	@Inject
    private DatabaseTouchpanelRegistration databaseTouchpanelRegistration;
	
	@Produces
	@Named
	private DatabaseTouchpanel gameDatabaseModel;
	
	@Inject
    private DatabaseResultRegistration registrationResult;
	
	@Produces
	@Named
	private DatabaseResult gameResultModel;
	
    /**
     * Default constructor. 
     */
    public GamemodeServlet() {
        // TODO Auto-generated constructor stub
    }
    
    // Read the values from the db and pass them in the format xxx
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mode = databaseResultRepository.getMode();
		if(mode == 1) {
			response.getWriter().append(Integer.toString(databaseResultRepository.getResult()));
		} else if (mode == 2) {
			int result = databaseResultRepository.getResult();
			// Convert the result to the format: SS,Ms
			// e.g. like 12,3s
			response.getWriter().append(Integer.toString((result / 100) % 10) + Integer.toString((result / 10) % 10) + "," + Integer.toString(result % 10) + "s");
		}
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
		// If new gyroscope-values are sent
		if (postBody.charAt(0) == '"') {
			gameDatabaseModel = new DatabaseTouchpanel();
			int posX, posY;
			if(postBody.charAt(11) == '-') {
				posX = -Integer.parseInt("" + postBody.charAt(12) + postBody.charAt(13));
			} else {
				posX = Integer.parseInt("" + postBody.charAt(12) + postBody.charAt(13));
			}
			if(postBody.charAt(25) == '-') {
				posY = -Integer.parseInt("" + postBody.charAt(26) + postBody.charAt(27));
			} else {
				posY = Integer.parseInt("" + postBody.charAt(26) + postBody.charAt(27));
			}
			gameDatabaseModel.setXEst(posX);
			gameDatabaseModel.setYEst(posY);
			gameDatabaseModel.setXReal(303);
			gameDatabaseModel.setYReal(303);
			
			try {
				databaseTouchpanelRegistration.register(gameDatabaseModel);
			} catch (Exception e) {}
		}
		// If gamemode 1 (standard balancing) is selected
		else if (postBody.charAt(0) == '1') {
			gameResultModel = new DatabaseResult();
		    gameResultModel.setMode(1);
		    gameResultModel.setResult(100);
		    gameResultModel.setError(0);
		    
		    try {
				registrationResult.register(gameResultModel);
			} catch (Exception e) {}
		}// If gamemode 2 (labyrinth) is selected
		else if (postBody.charAt(0) == '2') {
			gameResultModel = new DatabaseResult();
		    gameResultModel.setMode(2);
		    gameResultModel.setResult(100);
		    gameResultModel.setError(0);
		    
		    try {
				registrationResult.register(gameResultModel);
			} catch (Exception e) {}
		}
	}
}
