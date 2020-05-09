package org.agoncal.fascicle.microprofile.openapi.filter;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

// tag::adocSnippet[]
@Path("/api/books")
public class BookResource {

  @Operation(summary = "Returns a book for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  @GET
  @Path("/{id}")
  public Response getBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    // ...
    // tag::adocSkip[]
    return Response.status(NOT_FOUND).build();
    // end::adocSkip[]
  }
}
// end::adocSnippet[]
