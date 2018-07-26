package com.blogengine.dao;

import com.blogengine.model.Profile;

/**
 *
 * @author fernando
 */
public interface ProfileDAO extends DAO<Profile, Long> {

    Profile findByName(String name);

    Profile findByEmail(String email);

}
