package com.blogengine.dao;

import com.blogengine.model.Tag;

/**
 *
 * @author fernando
 */
public interface TagDAO extends DAO<Tag, Long> {

    Tag findByName(String name);

}
