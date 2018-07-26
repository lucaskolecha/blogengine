package com.blogengine.service.impl;

import com.blogengine.dao.DAO;
import com.blogengine.dao.ProfileDAO;
import com.blogengine.model.Profile;
import com.blogengine.service.GenericCRUDRestService;
import com.blogengine.service.ProfileService;
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
 * @author fernando
 */
@ManagedBean
@Path("/profile")
public class ProfileRestService extends GenericCRUDRestService<Profile> implements ProfileService {

    @Inject
    private ProfileDAO profileDAO;

    public ProfileRestService() {
        super(Profile.class);
    }

    @GET
    @Path("/name/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public Response getByName(@PathParam("param") String name) {
        getLogger().debug("Search User object by name: {}", name);
        Profile found = profileDAO.findByName(name);
        if (found == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(found).build();
    }

    @GET
    @Path("/email/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public Response getByEmail(@PathParam("param") String email) {
        getLogger().debug("Search User object by credentials: {}", email);
        Profile found = profileDAO.findByEmail(email);
        if (found == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(found).build();
    }

    @Override
    public GenericEntity listToGenericEntity(List<Profile> list) {
        return new GenericEntity<List<Profile>>(list){};
    }
    
    @Override
    public DAO getDao() {
        return profileDAO;
    }
}
