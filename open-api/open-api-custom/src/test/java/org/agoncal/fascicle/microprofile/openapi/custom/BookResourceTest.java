package org.agoncal.fascicle.microprofile.openapi.custom;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

//@formatter:off
@QuarkusTest
public class BookResourceTest {

  @Test
  public void shouldSayPing() {
    given()
      .when().get("/api/books/ping")
      .then()
      .statusCode(OK.getStatusCode())
      .body(is("ping"));
  }

  @Test
  void shouldPingOpenAPI() {
    given()
      .header(ACCEPT, APPLICATION_JSON).
    when()
      .get("/openapi").
    then()
      .statusCode(OK.getStatusCode());
  }

  @Test
  void shouldPingSwaggerUI() {
    given().
    when()
      .get("/swagger-ui").
    then()
      .statusCode(OK.getStatusCode());
  }
}
