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

import org.valendra.accounts.AccountsLogin;
import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class AccountsLoginTest{

	@Before
	public void setUp(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		AccountsLogin test = new AccountsLogin(request, response);
	}


	@Test
	public void doPostTest(){
		when(request.getParameter("buddy").thenReturn("a"));
		when(request.getParameter("search").thenReturn("b"));

        assertEquals(request.getParameter("username"), "a"));
        assertEquals(request.getParameter("password"), "b"));
	}


	@After
	public void tearDown(){
		test = null;

	}

}
