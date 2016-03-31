import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import org.valendra.accounts.Password

public class PasswordTest{

	@Before
	public void setUp(){
		Password test = new Password();
	}


	@Test
	public void hashTest(){
		assertEquals(test.hash("a"), "0cc175b9c0f1b6a831c399e269772661");
	}


	@After
	public void tearDown(){
		test = null;

	}

}
