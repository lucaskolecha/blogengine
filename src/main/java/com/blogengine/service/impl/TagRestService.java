package com.blogengine.service.impl;

import com.blogengine.dao.DAO;
import com.blogengine.dao.TagDAO;
import com.blogengine.model.Tag;
import com.blogengine.service.GenericCRUDRestService;
import com.blogengine.service.TagService;
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
@Path("/tag")
public class TagRestService extends GenericCRUDRestService<Tag> implements TagService {

    @Inject
    private TagDAO tagDAO;
    
    public TagRestService() {
        super(Tag.class);
    }

    @Override
    public GenericEntity listToGenericEntity(List<Tag> list) {
        return new GenericEntity<List<Tag>>(list){};
    }

    @Override
    public DAO getDao() {
        return tagDAO;
    }

    @GET
    @Path("/name/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public Response getByName(@PathParam("param") String name) {
        getLogger().debug("Search Tag object by name: {}", name);
        Tag tag = tagDAO.findByName(name);
        if (tag == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(tag).build();
    }

}
