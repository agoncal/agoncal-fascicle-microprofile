package org.agoncal.fascicle.microprofile.health.firststep;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

//@formatter:off
@QuarkusTest
public class BookResourceTest {

  @Test
  void shouldSayPing() {
    given()
      .header(ACCEPT, TEXT_PLAIN)
      .when().get("/books/ping")
      .then()
      .statusCode(OK.getStatusCode())
      .body(is("ping"));
  }

  @Test
  void shouldPingLiveness() {
    given().
    when()
      .get("/health/live").
    then()
      .statusCode(OK.getStatusCode());
  }

  @Test
  void shouldPingReadiness() {
    given().
    when()
      .get("/health/ready").
    then()
      .statusCode(OK.getStatusCode());
  }
}
