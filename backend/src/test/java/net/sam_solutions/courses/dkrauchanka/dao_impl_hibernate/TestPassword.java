package net.sam_solutions.courses.dkrauchanka.dao_impl_hibernate;

import net.sam_solutions.courses.dkrauchanka.utils.Password;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPassword {
   @Test
    public void testPasswordHashing(){
        assertEquals(Password.hashPassword(Password.hashPassword("Scorpion")+Password.SALT),"B79A907CF3AD842F7D758A2DCBA9967B24336FC6");
    } 
}
