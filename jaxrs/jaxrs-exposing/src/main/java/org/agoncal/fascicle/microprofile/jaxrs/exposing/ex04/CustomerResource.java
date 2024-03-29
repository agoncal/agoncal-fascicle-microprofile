package org.agoncal.fascicle.microprofile.jaxrs.exposing.ex04;

import org.agoncal.fascicle.microprofile.jaxrs.exposing.Customer;
import org.agoncal.fascicle.microprofile.jaxrs.exposing.Customers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// tag::adocSnippet[]
@Path("/customers")
public class CustomerResource {

  @GET
  @Path("search/{text}")
  public List<Customer> searchCustomers(@PathParam("text") String textToSearch) {
    // URI : /customers/search/smith
    // tag::adocSkip1[]
    System.out.println("searchCustomer : " + textToSearch);
    Customers customers = new Customers();
    customers.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565", new Date(), new Date()));
    customers.add(new Customer("John", "Smith", "jsmith@gmail.com", "1234565", new Date(), new Date()));
    return customers;
    // end::adocSkip1[]
  }

  @GET
  @Path("{login: [a-zA-Z]*}")
  public Customer getCustomerByLogin(@PathParam("login") String login) {
    // URI : /customers/foobarsmith
    // tag::adocSkip2[]
    System.out.println("getCustomerByLogin : " + login);
    return new Customer("John", "Smith", "jsmith@gmail.com", "1234565", new Date(), new Date());
    // end::adocSkip2[]
  }

  @GET
  @Path("{customerId : \\d+}")
  public Customer getCustomerById(@PathParam("customerId") Long id) {
    // URI : /customers/12345
    // tag::adocSkip3[]
    System.out.println("getCustomerById : " + id);
    return new Customer("John", "Smith", "jsmith@gmail.com", "1234565", new Date(), new Date());
    // end::adocSkip3[]
  }
}
// end::adocSnippet[]
