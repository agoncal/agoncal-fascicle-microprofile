package org.agoncal.fascicle.microprofile.metrics.firststep;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

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
