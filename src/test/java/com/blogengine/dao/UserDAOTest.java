package com.blogengine.dao;

import com.blogengine.dao.impl.UserDAOImpl;
import com.blogengine.model.User;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author fernando
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(UserDAOImpl.class)
public class UserDAOTest {

    @Inject
    private UserDAO userDAO;

    private static final User ROOT;

    static {
        ROOT = new User("root", "senha10");
    }

    @Test
    public void cdiInjectionTest() {
        Assert.assertNotNull(userDAO);
    }

    @Test
    public void saveTest() {
        User found = userDAO.findByUsername(ROOT.getUsername());
        if (found == null) {
            userDAO.save(ROOT);
        }

        found = userDAO.findByCredentials("root", "senha10");
        Assert.assertNotNull(found);
        Assert.assertNotNull(found.getId());
    }

    @Test
    public void passwordEncryptionTest() {
        User admin = new User("admin", "admin");
        User adminFound = userDAO.findByUsername(admin.getUsername());
        if (adminFound == null) {
            userDAO.save(admin);
        }

        adminFound = userDAO.findByCredentials("admin", "admin");
        Assert.assertNotNull(adminFound);
        Assert.assertNotNull(adminFound.getId());

        Assert.assertEquals(admin.getPassword(), adminFound.getPassword());
    }
}
