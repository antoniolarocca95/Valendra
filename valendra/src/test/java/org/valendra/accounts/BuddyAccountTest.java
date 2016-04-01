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

import org.valendra.accounts.BuddyAccount;
import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class BuddyAccountTest{

	@Before
	public void setUp(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		BuddyAccount test = new BuddyAccount(request, response);
	}

	@Test
	public void doGetTest(){
        when(request.getParameter("user").thenReturn("a"));
        assertEquals(request.getParameter("document"), "a"));

	}


	@After
	public void tearDown(){
		test = null;

	}

}
