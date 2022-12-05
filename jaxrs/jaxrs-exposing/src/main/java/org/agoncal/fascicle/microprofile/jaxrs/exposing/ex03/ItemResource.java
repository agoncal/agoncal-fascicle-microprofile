package org.agoncal.fascicle.microprofile.jaxrs.exposing.ex03;

import org.agoncal.fascicle.microprofile.jaxrs.exposing.Book;
import org.agoncal.fascicle.microprofile.jaxrs.exposing.Books;
import org.agoncal.fascicle.microprofile.jaxrs.exposing.CD;
import org.agoncal.fascicle.microprofile.jaxrs.exposing.CDs;
import org.agoncal.fascicle.microprofile.jaxrs.exposing.Items;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// tag::adocSnippet[]
@Path("/items")
public class ItemResource {

  @GET
  public Items getItems() {
    // URI : /items
    // tag::adocSkip1[]
    Items items = new Items();
    items.add(new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, "Science fiction comedy book", "1-84023-742-2", 354, false));
    return items;
    // end::adocSkip1[]
  }

  @GET
  @Path("/cds")
  public CDs getCDs() {
    // URI : /items/cds
    // tag::adocSkip2[]
    CDs cds = new CDs();
    cds.add(new CD("Help", 12.5F, "Best Beatles album", "EMI", 1, 45.6F, "Pop"));
    return cds;
    // end::adocSkip2[]
  }

  @GET
  @Path("/books")
  public Books getBooks() {
    // URI : /items/books
    // tag::adocSkip3[]
    Books books = new Books();
    books.add(new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, "Science fiction comedy book", "1-84023-742-2", 354, false));
    return books;
    // end::adocSkip3[]
  }

  @POST
  @Path("/books")
  public Response createBook(Book book) {
    // URI : /items/book
    // tag::adocSkip4[]
    return Response.created(null).build();
    // end::adocSkip4[]
  }
}
// end::adocSnippet[]
