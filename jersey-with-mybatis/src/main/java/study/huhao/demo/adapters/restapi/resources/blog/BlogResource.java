package study.huhao.demo.adapters.restapi.resources.blog;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.huhao.demo.application.BlogEditor;
import study.huhao.demo.application.BlogQuery;
import study.huhao.demo.domain.core.common.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.status;

@Path("blog")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class BlogResource {

    private final BlogQuery blogQuery;
    private final BlogEditor blogEditor;
    private final MapperFacade mapper;

    @Autowired
    public BlogResource(BlogQuery blogQuery, BlogEditor blogEditor, MapperFacade mapper) {
        this.blogQuery = blogQuery;
        this.blogEditor = blogEditor;
        this.mapper = mapper;
    }

    @GET
    public Page<BlogDto> allBlog(@QueryParam("limit") int limit, @QueryParam("offset") int offset) {
        return blogQuery.all(limit, offset).map(blog -> mapper.map(blog, BlogDto.class));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBlog(BlogCreateRequest data) {
        BlogDto entity = mapper.map(
                blogEditor.create(data.title, data.body, UUID.fromString(data.authorId)),
                BlogDto.class
        );

        URI uri = UriBuilder.fromResource(BlogResource.class).path("{id}").build(entity.id);
        return created(uri).entity(entity).build();
    }

    @GET
    @Path("{id}")
    public BlogDto getBlog(@PathParam("id") UUID id) {
        return mapper.map(blogQuery.get(id), BlogDto.class);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveBlog(@PathParam("id") UUID id, BlogSaveRequest data) {
        blogEditor.save(id, data.title, data.body);
    }

    @DELETE
    @Path("{id}")
    public void deleteBlog(@PathParam("id") UUID id) {
        blogEditor.delete(id);
    }

    @POST
    @Path("{id}/published")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response publishBlog(@PathParam("id") UUID id) {
        blogEditor.publish(id);
        return status(CREATED).build();
    }
}
