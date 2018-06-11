package com.blogengine.dao;

import com.blogengine.model.User;

/**
 *
 * @author fernando
 */
public interface UserDAO extends DAO<User, Long> {

    User findByUsername(String username);

    User findByCredentials(String username, String password);

}
