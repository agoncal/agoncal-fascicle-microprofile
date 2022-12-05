package org.agoncal.fascicle.microprofile.openapi.advanced;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BookResource {

  @Inject
  BookService service;

  private static final Logger LOGGER = Logger.getLogger(BookResource.class);

  // tag::adocOperation[]
  @GET
  @Path("/random")
  @Operation(
    summary = "Returns a random book",
    description = "Each time this API is invoked, a random book is returned from the database"
  )
  @Tag(name = "BETA", description = "This API is still in BETA. Use carefully")
  public Response getRandomBook() {
  // end::adocOperation[]
    Book book = service.findRandomBook();
    LOGGER.debug("Found random book " + book);
    return Response.ok(book).build();
  }

  // tag::adocResponse[]
  @GET
  @Operation(summary = "Returns all the books from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No books")
  public Response getAllBooks() {
  // end::adocResponse[]
    List<Book> books = service.findAllBooks();
    LOGGER.debug("Total number of books " + books);
    return Response.ok(books).build();
  }

  @Operation(summary = "Returns a book for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  @APIResponse(responseCode = "404", description = "The book is not found for the given identifier")
  // tag::adocParameter[]
  @GET
  @Path("/{id}")
  public Response getBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
  // end::adocParameter[]
    Optional<Book> book = service.findBookById(id);
    if (book.isPresent()) {
      LOGGER.debug("Found book " + book);
      return Response.ok(book).build();
    } else {
      LOGGER.debug("No book found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Creates a book")
  @APIResponse(responseCode = "201", description = "The URI of the created book", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
  // tag::adocRequestBody[]
  @POST
  public Response createBook(@RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class))) Book book, @Context UriInfo uriInfo) {
  // end::adocRequestBody[]
    book = service.persistBook(book);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
    LOGGER.debug("New book created with URI " + builder.build().toString());
    return Response.created(builder.build()).build();
  }

  @Operation(summary = "Updates an exiting  book")
  @APIResponse(responseCode = "200", description = "The updated book", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  @PUT
  public Response updateBook(@RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class))) Book book) {
    book = service.updateBook(book);
    LOGGER.debug("Book updated with new valued " + book);
    return Response.ok(book).build();
  }

  @Operation(summary = "Deletes an exiting book")
  @APIResponse(responseCode = "204", description = "The book has been successfully deleted")
  @DELETE
  @Path("/{id}")
  public Response deleteBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    service.deleteBook(id);
    LOGGER.debug("Book deleted with " + id);
    return Response.noContent().build();
  }

  @GET
  @Produces(TEXT_PLAIN)
  @Path("/ping")
  public String ping() {
    return "ping";
  }
}
