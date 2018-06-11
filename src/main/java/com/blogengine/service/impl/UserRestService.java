package com.blogengine.service.impl;

import com.blogengine.dao.DAO;
import com.blogengine.dao.UserDAO;
import com.blogengine.model.User;
import com.blogengine.service.GenericCRUDRestService;
import com.blogengine.service.UserService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fernando
 */
@ManagedBean
@Path("/user")
public class UserRestService extends GenericCRUDRestService<User> implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestService.class);

    @Inject
    private UserDAO userDAO;

    public UserRestService() {
        super(User.class);
    }

    @GET
    @Path("/username/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public Response getByUsername(@PathParam("param") String username) {
        getLogger().debug("Search User object by username: {}", username);
        User found = userDAO.findByUsername(username);
        if (found == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(found).build();
    }

    @GET
    @Path("/login/{username}/{password}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public Response getByCredentials(@PathParam("username") String username, @PathParam("password") String password) {
        getLogger().debug("Search User object by credentials: {}/{}", username, password);
        User found = userDAO.findByCredentials(username, password);
        if (found == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(found).build();
    }

    @Override
    public GenericEntity listToGenericEntity(List<User> list) {
        return new GenericEntity<List<User>>(list){};
    }
    
    @Override
    public DAO getDao() {
        return userDAO;
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}
