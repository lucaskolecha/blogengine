package com.blogengine.dao;

import com.blogengine.dao.impl.PostDAOImpl;
import com.blogengine.dao.impl.TagDAOImpl;
import com.blogengine.dao.impl.UserDAOImpl;
import com.blogengine.model.Post;
import com.blogengine.model.Tag;
import com.blogengine.model.User;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author renanhr
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({PostDAOImpl.class, UserDAOImpl.class, TagDAOImpl.class})
public class PostDAOTest {

    @Inject
    private PostDAO postDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private TagDAO tagDAO;

    private static final Post POST;

    static {
        POST = new Post("title", "content");
    }

    @Test
    public void cdiInjectionTest() {
        Assert.assertNotNull(postDAO);
    }

    @Test
    public void saveTest() {
        User user = userDAO.findByUsername("root");
        Post found = postDAO.findByTitle(POST.getTitle());
        List<Tag> tags = tagDAO.findAll();
        if (found == null) {
            postDAO.save(POST);
        }
        found = postDAO.findByTitle(POST.getTitle());
        
        found.setUser(user);        
        found.getTags().addAll(tags);
        postDAO.update(found);
        
        Assert.assertNotNull(found);
        Assert.assertNotNull(found.getId());
    }
}
