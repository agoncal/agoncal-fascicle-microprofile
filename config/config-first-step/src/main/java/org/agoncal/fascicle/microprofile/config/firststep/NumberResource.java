package org.agoncal.fascicle.microprofile.config.firststep;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// tag::adocSnippet[]
@Path("/numbers")
@Produces(MediaType.TEXT_PLAIN)
public class NumberResource {

  @ConfigProperty(name = "country.code", defaultValue = "us")
  String countryCode;

  @ConfigProperty(name = "book.prefix")
  int bookPrefix;

  @GET()
  @Path("/book")
  public String generateBookNumber() {
    return bookPrefix + generateNumber() + countryCode;
  }

  @GET()
  @Path("/cd")
  public Response generateCDNumber() {
    return Response.ok(generateNumber() + countryCode).build();
  }
  // tag::adocSkip[]
  private String generateNumber() {
    return String.valueOf(Math.random() + "-");
  }
  // tag::adocSkip[]
}
// end::adocSnippet[]
