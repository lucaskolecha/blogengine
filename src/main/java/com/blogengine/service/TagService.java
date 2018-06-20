package com.blogengine.service;

import com.blogengine.model.Tag;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernando
 */
public interface TagService extends CRUDRestService<Tag> {

    Response getByName(String name);
    
}
