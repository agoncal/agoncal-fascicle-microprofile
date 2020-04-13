package org.agoncal.fascicle.microprofile.jaxrs.invoking.ex01;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@QuarkusTest
public class CustomerResourceTest {

  // ======================================
  // =              Unit tests            =
  // ======================================

  @Test
  public void shouldPing() {
// tag::adocclient[]
    Client client = ClientBuilder.newClient();
// end::adocclient[]
// tag::adoctarget[]
    WebTarget target = client.target("http://localhost:8081/customers");
// end::adoctarget[]
// tag::adocinvoke[]
    Invocation invocation = target.request().buildGet();
// end::adocinvoke[]
// tag::adocresponse[]
    Response response = invocation.invoke();
// end::adocresponse[]
    Assertions.assertEquals(200, response.getStatus());
  }

  @Test
  public void shouldPingURI() throws URISyntaxException {
    Client client = ClientBuilder.newClient();
// tag::adocuri[]
    URI uri = new URI("http://localhost:8081/customers");
    WebTarget target = client.target(uri);
// end::adocuri[]
    Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
    Response response = invocation.invoke();
    Assertions.assertEquals(200, response.getStatus());
  }

  @Test
  public void shouldPingCompact() {
// tag::adocompact[]
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8081/customers");
    Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
    Response response = invocation.invoke();
// end::adocompact[]
    Assertions.assertEquals(200, response.getStatus());
  }

  @Test
  public void shouldPingOne() {
// tag::adoconeline[]
    Response response = ClientBuilder.newClient().target("http://localhost:8081/customers").request(MediaType.TEXT_PLAIN).get();
// end::adoconeline[]
    Assertions.assertEquals(200, response.getStatus());
  }
}
