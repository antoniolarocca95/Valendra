import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.accounts.Buddy;
import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;


public class AccountsRegistrationTest{

	@Before
	public void setUp(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		AccountRegistration test = new AccountRegistration(request, response);
	}


	@Test
	public void doPostTest(){
		when(request.getParameter("firstname").thenReturn("a"));
		when(request.getParameter("lastname").thenReturn("b"));
		when(request.getParameter("email").thenReturn("c"));
		when(request.getParameter("password").thenReturn("d"));
		when(request.getParameter("cpassword").thenReturn("e"));
		
        assertEquals(request.getParameter("firstname"), "a"));
        assertEquals(request.getParameter("lastname"), "b"));
        assertEquals(request.getParameter("email"), "c"));
        assertEquals(request.getParameter("password"), "d"));
        assertEquals(request.getParameter("cpassword"), "e"));

	}

	@After
	public void tearDown(){
		test = null;

	}

}
