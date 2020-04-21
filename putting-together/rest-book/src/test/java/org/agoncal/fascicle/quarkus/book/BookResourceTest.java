package org.agoncal.fascicle.quarkus.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

//@formatter:off
// tag::adocHeader[]
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {

  @Test
  void shouldPingOpenAPI() {
    given()
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
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

  @Test
  void shouldPingMetrics() {
    given()
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
    when()
      .get("/metrics/application").
    then()
      .statusCode(OK.getStatusCode());
  }

  @Test
  public void shouldNotFindDummy() {
    given()
      .when().get("/api/books/dummy")
      .then()
      .statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  void shouldGetRandomBook() {
    given()
      .when().get("/api/books/random")
      .then()
      .statusCode(OK.getStatusCode())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
  }
}
