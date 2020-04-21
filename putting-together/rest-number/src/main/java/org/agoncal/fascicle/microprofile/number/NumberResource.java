package org.agoncal.fascicle.microprofile.number;

import com.github.javafaker.Faker;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@OpenAPIDefinition(
  info = @Info(title = "Number API",
    description = "This API allows to generate all sorts of numbers",
    version = "1.0",
    contact = @Contact(name = "@agoncal", url = "https://twitter.com/agoncal")),
  externalDocs = @ExternalDocumentation(url = "https://github.com/agoncal/agoncal-fascicle-quarkus-pract", description = "All the Practicing Quarkus code"),
  tags = {
    @Tag(name = "api", description = "Public API that can be used by anybody"),
    @Tag(name = "numbers", description = "Anybody interested in numbers")
  }
)
@Path("/api/numbers")
@Tag(name = "Number Endpoint")
public class NumberResource {

  private final static Logger LOGGER = Logger.getLogger(NumberResource.class.getName());

  @ConfigProperty(name = "number.separator", defaultValue = "false")
  boolean separator;

  @Operation(summary = "Generates book numbers", description = "These book numbers have several formats: ISBN, ASIN and EAN")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = IsbnNumbers.class, required = true)))
  @Counted(name = "countGenerateBookNumber", description = "Counts how many times the generateBookNumbers method has been invoked")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response generateBookNumber() {
    Faker faker = new Faker();
    IsbnNumbers isbnNumbers = new IsbnNumbers();
    isbnNumbers.setIsbn10(faker.code().isbn10(separator));
    isbnNumbers.setIsbn13(faker.code().isbn13(separator));
    LOGGER.info("ISBN book numbers generated " + isbnNumbers);
    return Response.ok(isbnNumbers).build();
  }
}
