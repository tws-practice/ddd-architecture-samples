package study.huhao.demo.adapters.restapi.providers;

import com.google.common.collect.ImmutableMap;
import study.huhao.demo.domain.blogcontext.blog.exceptions.NoNeedToPublishException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.status;


@Provider
public class NoNeedToPublishExceptionMapper implements ExceptionMapper<NoNeedToPublishException> {
    @Override
    public Response toResponse(NoNeedToPublishException ex) {
        ImmutableMap<String, String> entity = ImmutableMap.of("message", ex.getMessage());
        return status(CONFLICT).entity(entity).build();
    }
}
