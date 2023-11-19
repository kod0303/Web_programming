package lab.web;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class Musicians
 */

public class Musicians extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object[][] list_of_musicians;
	ResourceBundle res;
    /**
     * Default constructor. 
     */
    public Musicians() {
    	super();
    	list_of_musicians = new Object[][] {
    		{"Иванов Юрий", "Альт", 3}, 
    		{"Ежова Арина", "Скрипка", 5}, 
    		{"Гришин Артем", "Труба", 9}, 
    			};
    }

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
		// Setting the encoding type for request parameters
		request.setCharacterEncoding("utf-8");
		// Reading the "experience" parameter from a request
		String experience =  request.getParameter("experience");
		// Reading the "language" parameter from a request
		String lang =  request.getParameter("lang");
		// Set the content type for the response (including encoding)
		response.setContentType("text/html;charset=UTF-8");
		
		if (lang == null) lang = "ru";
		if (!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
			"Параметр lang может принимать значения en или ru \"" + lang + "\"");
			return;
		}
		ResourceBundle res = ResourceBundle.getBundle(
				"/Musicians", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());
		
		// Get a stream to display the response
		PrintWriter out = response.getWriter();
		 try {
			// Creating HTML-page
			 out.println("<html>");
			 out.println("<head><title>Состав оркестра</title></head>");
			 out.println("<body>");
			 out.println("<h1>" + res.getString("title") + " " + ((experience == null)? " ":
				 res.getString("condition") + " " + experience) + "</h1>");
			 out.println("<table border='1'>");
			 out.println("<tr><td><b>" + res.getString("name") + "</b></td>"
						+ "<td><b>" + res.getString("instrument") + "</b></td>"
						+ "<td><b>" + res.getString("experience") + "</b></td></tr>");
			 
			 for (Object[] temp : list_of_musicians)
				 if (experience == null || (int)temp[2] <= Integer.parseInt(experience))
					 out.println("<tr><td>" + temp[0] + "</td><td>" + temp[1] + "</td><td>"
				     + Integer.toString((int)temp[2]) + "</td></tr>");
			 out.println("</table>");
			 out.println("</body>");
			 out.println("</html>");
		 }
		 finally {
			 // Closing the output stream
			 out.close();
			 }
	}

	/**
	 * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 processRequest(request, response);
			 }
	
	/**
	* Handles the HTTP
	* <code>POST</code> method.
	*
	* @param request servlet request
	* @param response servlet response
	* @throws ServletException if a servlet-specific error occurs
	* @throws IOException if an I/O error occurs
	*/
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		processRequest(request, response);
	}
}
