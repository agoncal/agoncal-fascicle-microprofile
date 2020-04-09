package org.agoncal.fascicle.microprofile.health.firststep;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookResource {

  private static final Logger LOGGER = Logger.getLogger(BookResource.class);

  @GET
  @Path("/ping")
  @Produces(MediaType.TEXT_PLAIN)
  public String ping() {
    LOGGER.info("Invoking ping");
    return "ping";
  }
}
