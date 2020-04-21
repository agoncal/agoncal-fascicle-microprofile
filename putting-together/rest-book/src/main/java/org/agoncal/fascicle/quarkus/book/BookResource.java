package org.agoncal.fascicle.quarkus.book;

import com.github.javafaker.Faker;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbersService;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.logging.Logger;

// tag::adocInjection[]
// tag::adocSnippet[]
// tag::adocGetRandomBook[]
@OpenAPIDefinition(
  info = @Info(title = "Book API",
    description = "This API allows CRUD operations on books",
    version = "1.0",
    contact = @Contact(name = "@agoncal", url = "https://twitter.com/agoncal")),
  externalDocs = @ExternalDocumentation(url = "https://github.com/agoncal/agoncal-fascicle-quarkus-pract", description = "All the Practicing Quarkus code"),
  tags = {
    @Tag(name = "api", description = "Public API that can be used by anybody"),
    @Tag(name = "books", description = "Anybody interested in books")
  }
)
@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Book Endpoint")
public class BookResource {

  private final static Logger LOGGER = Logger.getLogger(BookResource.class.getName());

  @Inject
  @RestClient
  IsbnNumbersService isbnNumbersService;

  @Operation(summary = "Gets a random book")
  @APIResponse(responseCode = "201", description = "The URI of the created book", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
  @Fallback(fallbackMethod = "fallbackPersistBook")
  @GET
  public Response getRandomBook() {

    IsbnNumbers isbnNumbers = isbnNumbersService.generateIsbnNumbers();

    Faker faker = new Faker();
    JsonObject book = Json.createObjectBuilder()
      .add("book", Json.createObjectBuilder()
        .add("isbn_13", isbnNumbers.getIsbn13())
        .add("isbn_13", isbnNumbers.getIsbn13())
        .add("title", faker.book().title())
        .add("author", faker.book().author())
        .add("genre", faker.book().genre())
        .add("publisher", faker.book().publisher()))
      .build();

    return Response.ok(book).build();
  }

  private Response fallbackRandomBook() throws FileNotFoundException {
    LOGGER.warning("Falling back on creating a book");
    return Response.ok("Dummy").build();
  }
}
