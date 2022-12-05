package org.agoncal.fascicle.microprofile.openapi.config;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@Path("/authors")
@Produces(MediaType.TEXT_PLAIN)
public class AuthorResource {

  String[] scifiAuthors = {"Isaac Asimov", "Ray Bradbury", "Douglas Adams"};

  @GET
  @Path("/{index}")
  public String getScifiAuthor(@Parameter(description = "Author index", required = true) @PathParam("index") int index) {
    return scifiAuthors[index];
  }
}
// end::adocSnippet[]
