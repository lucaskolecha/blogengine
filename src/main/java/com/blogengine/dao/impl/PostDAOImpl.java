package com.blogengine.dao.impl;

import com.blogengine.dao.GenericDAO;
import com.blogengine.dao.PostDAO;
import com.blogengine.model.Post;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author renanhr
 */
@ApplicationScoped
public class PostDAOImpl extends GenericDAO<Post, Long> implements PostDAO {

    public PostDAOImpl() {
        super(Post.class);
    }

    @Override
    public Post findByTitle(String title) {
        String hql = "select t from Post t where t.title = :title";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("title", title);
        List<Post> posts = query.getResultList();

        if (posts == null || posts.isEmpty()) {
            return null;
        } else if (posts.size() > 1) {
            throw new NonUniqueResultException();
        } else {
            return posts.get(0);
        }
    }

}
