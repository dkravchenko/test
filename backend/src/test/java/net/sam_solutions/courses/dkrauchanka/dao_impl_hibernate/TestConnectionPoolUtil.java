package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import java.sql.Connection;
import net.sam_solutions.courses.dkrauchanka.utils.ConnectionPoolUtil;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TestConnectionPoolUtil {
    
    @Test
    public void testConnection(){
        Connection conn = ConnectionPoolUtil.getInstance().getConnection();
        assertNotNull(conn);
    }
}
