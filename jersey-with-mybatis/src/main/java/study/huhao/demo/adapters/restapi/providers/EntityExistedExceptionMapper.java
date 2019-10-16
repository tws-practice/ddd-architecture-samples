package study.huhao.demo.adapters.restapi.providers;

import com.google.common.collect.ImmutableMap;
import study.huhao.demo.domain.core.common.excpetions.EntityExistedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.status;


@Provider
public class EntityExistedExceptionMapper implements ExceptionMapper<EntityExistedException> {
    @Override
    public Response toResponse(EntityExistedException ex) {
        ImmutableMap<String, String> entity = ImmutableMap.of("message", ex.getMessage());
        return status(CONFLICT).entity(entity).build();
    }
}
