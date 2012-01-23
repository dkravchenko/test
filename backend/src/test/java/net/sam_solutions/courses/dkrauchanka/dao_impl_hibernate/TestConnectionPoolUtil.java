package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import java.sql.Connection;
import net.sam_solutions.courses.dkrauchanka.utils.ConnectionPoolUtil;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TestConnectionPoolUtil {
    private BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
    private ConnectionPoolUtil util = (ConnectionPoolUtil) factory.getBean("ConnectionPoolUtil");
    
    @Test
    public void testConnection(){
        Connection conn = util.getConnection();
        assertNotNull(conn);
    }
}
