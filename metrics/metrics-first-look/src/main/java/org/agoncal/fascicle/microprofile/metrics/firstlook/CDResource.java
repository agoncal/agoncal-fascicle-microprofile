package org.agoncal.fascicle.microprofile.metrics.firstlook;

import org.eclipse.microprofile.metrics.annotation.Counted;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

/**
 * curl -X POST "http://localhost:8080/cds" -H "Content-Type: application/json" -d "{\"description\":\"string\"}"
 * curl -H "Accept: application/json " http://localhost:8080/metrics/application
 */
@Consumes(MediaType.APPLICATION_JSON)
// tag::adocSnippet[]
@Path("/cds")
public class CDResource {

  @POST
  @Counted(name = "Created Books", description = "Counts the number of created books")
  public Response create(CD cd, @Context UriInfo uriInfo) {
    persist(cd);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(cd.getId()));
    return Response.created(builder.build()).build();
  }
  // tag::adocSkip[]
  private void persist(CD cd) {
  }
  // end::adocSkip[]
}
// end::adocSnippet[]
