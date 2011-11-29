package net.sam_solutions.courses.dkrauchanka.framework;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;
import static org.junit.Assert.assertTrue;


public class TestClass {
    @Test
    public void testSecurityManager(){
        try {
            SecurityManager manager = SecurityManager.getInstance("security.xml");
            assertTrue(manager.isAccessable("staff", "admin"));
        } catch (IOException ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
