package com.blogengine.dao;

import com.blogengine.dao.impl.TagDAOImpl;
import com.blogengine.model.Tag;
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
@AdditionalClasses(TagDAOImpl.class)
public class TagDAOTest {

    @Inject
    private TagDAO tagDAO;

    private static final Tag TAG;

    static {
        TAG = new Tag("Java");
    }

    @Test
    public void cdiInjectionTest() {
        Assert.assertNotNull(tagDAO);
    }

    @Test
    public void saveTest() {
        Tag found = tagDAO.findByName(TAG.getName());
        if (found == null) {
            tagDAO.save(TAG);
        }

        found = tagDAO.findByName(TAG.getName());
        Assert.assertNotNull(found);
        Assert.assertNotNull(found.getId());
    }
}
