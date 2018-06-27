package com.blogengine.service;

import com.blogengine.model.Post;
import javax.ws.rs.core.Response;

/**
 *
 * @author renanhr
 */
public interface PostService extends CRUDRestService<Post> {

    Response getByTitle(String title);
}    