package org.agoncal.fascicle.microprofile.book;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// tag::adocSnippet[]
@Path("/api/numbers")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface IsbnNumbersService {

  @GET
  IsbnNumbers generateIsbnNumbers();
}
// end::adocSnippet[]
