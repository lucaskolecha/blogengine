package com.blogengine.service.impl;

import com.blogengine.dao.DAO;
import com.blogengine.dao.PostDAO;
import com.blogengine.model.Post;
import com.blogengine.service.GenericCRUDRestService;
import com.blogengine.service.PostService;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author renanhr
 */
@ManagedBean
@Path("/post")
public class PostRestService extends GenericCRUDRestService<Post> implements PostService {

    @Inject
    private PostDAO postDAO;
    
    public PostRestService() {
        super(Post.class);
    }

    @Override
    public GenericEntity listToGenericEntity(List<Post> list) {
        return new GenericEntity<List<Post>>(list){};
    }

    @Override
    public DAO getDao() {
        return postDAO;
    }

    @GET
    @Path("/title/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public Response getByTitle(@PathParam("param") String title) {
        getLogger().debug("Search Tag object by title: {}", title);
        Post post = postDAO.findByTitle(title);
        if (post == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(post).build();
    }
}
