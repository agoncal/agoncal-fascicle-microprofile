package org.agoncal.fascicle.microprofile.jwt.firststep;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@Path("/authors")
@Produces(MediaType.TEXT_PLAIN)
@PermitAll
@RequestScoped
public class AuthorResource {

  @Inject
  JsonWebToken token;

  String[] scifiAuthors = {"Isaac Asimov", "Ray Bradbury", "Douglas Adams"};

  @GET
  public String getAllScifiAuthors() {
    return String.join(", ", scifiAuthors);
  }

  @GET
  @Path("/{index}")
  public String getScifiAuthor(@PathParam("index") int index) {
    return scifiAuthors[index];
  }
}
// end::adocSnippet[]
