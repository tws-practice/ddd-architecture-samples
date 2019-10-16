package study.huhao.demo.adapters.restapi.providers;

import com.google.common.collect.ImmutableMap;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.status;


@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException ex) {
        ImmutableMap<String, String> entity = ImmutableMap.of("message", ex.getMessage());
        return status(NOT_FOUND).entity(entity).build();
    }
}
