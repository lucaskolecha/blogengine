package com.blogengine.util;

import com.blogengine.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author fernando
 */
public class CriptographyTest {

    private static final String PASSWORD = "abacaxi123";
    
    public CriptographyTest() {
    }

    @Test
    public void criptographyTest() {
        User root = new User("root", "senha10");
        User admin = new User("admin", PASSWORD);
        
        Assert.assertNotEquals(root.getPassword(), admin.getPassword());
        Assert.assertNotEquals(PASSWORD, admin.getPassword());
        Assert.assertEquals(Criptography.encrypt(PASSWORD), admin.getPassword());
    }
}
