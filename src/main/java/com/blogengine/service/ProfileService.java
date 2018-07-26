package com.blogengine.service;

import com.blogengine.model.Profile;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernando
 */
public interface ProfileService extends CRUDRestService<Profile> {
    
    Response getByName(String name);
    
    Response getByEmail(String email);
    
}
