package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExperienceProcessor
 */
public class ExperienceProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor
     */
    public ExperienceProcessor() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private boolean stringIsPositiveNumber(String str) {
		try {
			return Integer.parseInt(str) >= 0;
		} catch(final NumberFormatException e) {
			return false;
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	// Получение параметра из строки запроса
    	String parameter = request.getParameter("experience"); // может быть null
    	boolean isValid = false;
    	if(parameter == null || parameter.isEmpty()) {
    		isValid = true;
    	}
    	if(!isValid) {
    		isValid = stringIsPositiveNumber(parameter);
    	}
    	if(isValid) {
    		// Сохранение параметра в сеансе
    		request.getSession().setAttribute("experience", parameter);
    		// Сохранение параметра в Cookie
    		Cookie c = new Cookie("experience", URLEncoder.encode(parameter, "UTF-8"));
    		// Установка времени жизни Cookie в секундах
    		c.setMaxAge(100);
    		response.addCookie(c);
    		// Добавляем сообщение об успешном запросе
    		PrintWriter out = response.getWriter();
    		out.write("Request is valid"); 
    		// Перенаправление на страницу
    		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +
    	    	"/MusiciansTitle.jsp"));
    	} else {
    		// Добавляем сообщение об ошибке
    		PrintWriter out = response.getWriter();
    		out.write("Request is invalid");
         	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +
       	    	"/ExperienceError.jsp"));
    	}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	// Получение параметра из строки запроса
    	String parameter = request.getParameter("experience"); // может быть null
    	boolean isValid = false;
    	if(parameter == null) {
    		isValid = true;
    	}
    	if(!isValid) {
    		isValid = stringIsPositiveNumber(parameter);
    	}
    	if(isValid) {
    		// Сохранение параметра в сеансе
    		request.getSession().setAttribute("experience", parameter);
    		// Сохранение параметра в Cookie
    		Cookie c = new Cookie("experience", URLEncoder.encode(parameter, "UTF-8"));
    		// Установка времени жизни Cookie в секундах
    		c.setMaxAge(100);
    		response.addCookie(c);
    		// Перенаправление на страницу
    		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +
    	    	"/MusiciansTitle.jsp"));
    	} else {
    		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +
        	    	"/ExperienceError.jsp"));
    	}
	}

}
