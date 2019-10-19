package study.huhao.demo.adapters.restapi.resources.blog;

import study.huhao.demo.application.EditBlogUseCase;
import study.huhao.demo.application.QueryBlogUseCase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.status;

@Produces(MediaType.APPLICATION_JSON)
public class BlogSubResource {
    private UUID id;
    private QueryBlogUseCase queryBlogUseCase;
    private EditBlogUseCase editBlogUseCase;

    BlogSubResource(UUID id, QueryBlogUseCase queryBlogUseCase, EditBlogUseCase editBlogUseCase) {
        this.id = id;
        this.queryBlogUseCase = queryBlogUseCase;
        this.editBlogUseCase = editBlogUseCase;
    }

    @GET
    public BlogDto get() {
        return BlogDto.of(queryBlogUseCase.get(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(BlogSaveRequest data) {
        editBlogUseCase.saveDraft(id, data.title, data.body);
    }

    @DELETE
    public void delete() {
        editBlogUseCase.delete(id);
    }

    @POST
    @Path("published")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response publish() {
        editBlogUseCase.publish(id);
        return status(CREATED).build();
    }
}
