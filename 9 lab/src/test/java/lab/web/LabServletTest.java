package lab.web;

import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test; 
import org.mockito.Mock;
import org.mockito.MockitoAnnotations; 

public class LabServletTest extends TestCase {
	@Mock
	HttpServletRequest request, request1;
	@Mock
	HttpServletResponse response, response1;
	@Mock
	HttpSession session;
	
	@Before
	protected void setUp() throws Exception {  
		MockitoAnnotations.openMocks(this);
	} 
	
	@Test
	public void test() throws Exception {
		when(request.getParameter("experience")).thenReturn("8");
		when(request.getSession()).thenReturn(session);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw); 
		
		ExperienceProcessor s = new ExperienceProcessor();
		s.doGet(request, response);
		
		String result = sw.getBuffer().toString().trim();
		assertEquals("Request is valid", result); 
		verify(session).setAttribute("experience", "8"); 
		verify(response).sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/MusiciansTitle.jsp"));
		
		
		when(request1.getParameter("experience")).thenReturn("qwerty");
		
		StringWriter sw1 = new StringWriter();
		PrintWriter pw1 = new PrintWriter(sw1);
		
		when(response1.getWriter()).thenReturn(pw1);
		s.doGet(request1, response1);
		String result2 = sw1.getBuffer().toString().trim();
		assertEquals("Request is invalid", result2); 
		verify(response1).sendRedirect(response1.encodeRedirectURL(request1.getContextPath() + "/ExperienceError.jsp"));
	}
	
}
