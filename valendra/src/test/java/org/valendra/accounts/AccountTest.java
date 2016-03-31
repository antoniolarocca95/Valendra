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

import org.valendra.accounts.Account;
import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class AccountTest{

	@Before
	public void setUp(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		Account test = new Account(request, response);
	}

	@Test
	public void doGetTest(){
        when(request.getParameter("document").thenReturn("docName"));
        assertEquals(request.getParameter("document"), "docName"));

	}

	@Test
	public void doPostTest(){
		when(request.getParameter("newfirstname").thenReturn("a"));
		when(request.getParameter("newlastname").thenReturn("b"));
		when(request.getParameter("newemail").thenReturn("c"));
		when(request.getParameter("newcurrentpassword").thenReturn("d"));
		when(request.getParameter("newconfirmpassword").thenReturn("e"));
		
        assertEquals(request.getParameter("newfirstname"), "a"));
        assertEquals(request.getParameter("newlastname"), "b"));
        assertEquals(request.getParameter("newemail"), "c"));
        assertEquals(request.getParameter("newcurrentpassword"), "d"));
        assertEquals(request.getParameter("newconfirmpassword"), "e"));

	}

	@After
	public void tearDown(){
		test = null;

	}

}
