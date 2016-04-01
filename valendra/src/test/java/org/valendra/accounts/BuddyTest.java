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

public class BuddyTest{

	@Before
	public void setUp(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		Buddy test = new Buddy(request, response);
	}

	@Test
	public void doGetTest(){
        when(request.getParameter("user").thenReturn("a"));
        assertEquals(request.getParameter("document"), "a"));

	}

	@Test
	public void doPostTest(){
		when(request.getParameter("buddy").thenReturn("a"));
		when(request.getParameter("search").thenReturn("b"));

        assertEquals(request.getParameter("buddy"), "a"));
        assertEquals(request.getParameter("search"), "b"));
	}


	@After
	public void tearDown(){
		test = null;

	}

}
