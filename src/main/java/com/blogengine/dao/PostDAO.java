
package com.blogengine.dao;

import com.blogengine.model.Post;

/**
 *
 * @author renanhr
 */
public interface PostDAO extends DAO<Post, Long> {

    Post findByTitle(String title);
}    
