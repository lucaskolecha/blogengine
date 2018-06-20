package com.blogengine.dao.impl;

import com.blogengine.dao.GenericDAO;
import com.blogengine.dao.TagDAO;
import com.blogengine.model.Tag;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author fernando
 */
@ApplicationScoped
public class TagDAOImpl extends GenericDAO<Tag, Long> implements TagDAO {

    public TagDAOImpl() {
        super(Tag.class);
    }

    @Override
    public Tag findByName(String name) {
        String hql = "select t from Tag t where t.name = :name";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("name", name);
        List<Tag> tags = query.getResultList();

        if (tags == null || tags.isEmpty()) {
            return null;
        } else if (tags.size() > 1) {
            throw new NonUniqueResultException();
        } else {
            return tags.get(0);
        }
    }

}
