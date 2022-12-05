package org.agoncal.fascicle.microprofile.openapi.filter;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

// tag::adocSnippet[]
@Path("/api/books")
public class BookResource {

  @Operation(summary = "Returns a random book")
  @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  @GET
  @Path("/{id}")
  public Response getRandomBook() {
    // ...
    // tag::adocSkip[]
    return Response.status(NOT_FOUND).build();
    // end::adocSkip[]
  }
}
// end::adocSnippet[]
