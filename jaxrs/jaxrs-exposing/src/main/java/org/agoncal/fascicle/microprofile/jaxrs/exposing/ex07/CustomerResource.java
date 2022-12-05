package org.agoncal.fascicle.microprofile.jaxrs.exposing.ex07;

import org.agoncal.fascicle.microprofile.jaxrs.exposing.Customer;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
// tag::adocSnippet[]
@Path("/customer")
public class CustomerResource {

  @GET
  public String getAsPlainText() {
    return new Customer("John", "Smith").toString();
  }

  @GET
  @Path("max")
  public Integer getMaximumAge() {
    return 42;
  }

  @GET
  @Produces(MediaType.APPLICATION_XML)
  public Customer getAsXML() {
    return new Customer("John", "Smith");
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAsJson() {
    return Response.ok(new Customer("John", "Smith"), MediaType.APPLICATION_JSON).build();
  }
}
// end::adocSnippet[]
