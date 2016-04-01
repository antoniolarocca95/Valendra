import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class commentsTest extends Mockito{
    
    @Test
    public void doGetTest() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HTTPServletResponse.class);
        
        when(request.getParameter("document").thenReturn("docName"));
        assertEquals(request.getParameter("document"), "docName");
    }
    
    public void doPostTest() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HTTPServletResponse.class);
        
        when(request.setContentType("document").thenReturn(1));
        when(response.getWriter().thenReturn(2));
        when(request.getParameter("rating").thenReturn(3));
        when(request.getParameter("comments").thenReturn(4));
    
        
        //Testing assertEquals now
        assertEquals(request.setContentType("document"), 1);
        assertEquals(response.getWriter(), 2);
        assertEquals(request.getParameter("rating"), 3);
        assertEquals(request.getParameter("comments"), 4);
    }
}