package org.agoncal.fascicle.microprofile.metrics.application;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.validation.Valid;
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
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BookResource {

  @Inject
  BookService service;

  @Counted(name = "countGetRandomBook", description = "Counts how many times the getRandomBook method has been invoked")
  @Timed(name = "timeGetRandomBook", description = "Times how long it takes to invoke the getRandomBook method", unit = MetricUnits.MILLISECONDS)
  @GET
  @Path("/random")
  public Response getRandomBook() {
    Book book = service.findRandomBook();
    return Response.ok(book).build();
  }

  @Counted(name = "countGetAllBooks", description = "Counts how many times the getAllBooks method has been invoked")
  @Timed(name = "timeGetAllBooks", description = "Times how long it takes to invoke the getAllBooks method", unit = MetricUnits.MILLISECONDS)
  @GET
  public Response getAllBooks() {
    List<Book> books = service.findAllBooks();
    return Response.ok(books).build();
  }

  @Counted(name = "countGetBook", description = "Counts how many times the getBook method has been invoked")
  @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the getBook method", unit = MetricUnits.MILLISECONDS)
  @GET
  @Path("/{id}")
  public Response getBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    Optional<Book> book = service.findBookById(id);
    if (book.isPresent()) {
      return Response.ok(book).build();
    } else {
      return Response.status(NOT_FOUND).build();
    }
  }

  @Counted(name = "countCreateBook", description = "Counts how many times the createBook method has been invoked")
  @Timed(name = "timeCreateBook", description = "Times how long it takes to invoke the createBook method", unit = MetricUnits.MILLISECONDS)
  @POST
  public Response createBook(@RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class))) @Valid Book book, @Context UriInfo uriInfo) {
    book = service.persistBook(book);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
    return Response.created(builder.build()).build();
  }

  @Counted(name = "countUpdateBook", description = "Counts how many times the updateBook method has been invoked")
  @Timed(name = "timeUpdateBook", description = "Times how long it takes to invoke the updateBook method", unit = MetricUnits.MILLISECONDS)
  @PUT
  public Response updateBook(@RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class))) @Valid Book book) {
    book = service.updateBook(book);
    return Response.ok(book).build();
  }

  @Counted(name = "countDeleteBook", description = "Counts how many times the deleteBook method has been invoked")
  @Timed(name = "timeDeleteBook", description = "Times how long it takes to invoke the deleteBook method", unit = MetricUnits.MILLISECONDS)
  @DELETE
  @Path("/{id}")
  public Response deleteBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    service.deleteBook(id);
    return Response.noContent().build();
  }
}
