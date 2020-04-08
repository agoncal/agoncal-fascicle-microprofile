package org.agoncal.fascicle.microprofile.config.firststep;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.containsString;

//@formatter:off
@QuarkusTest
public class NumberResourceTest {

  @Test
  void shouldGenerateBookNumber() {
    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/numbers/book").
    then()
      .statusCode(OK.getStatusCode())
      .body(containsString("13"))
      .body(containsString("pt"));
  }

  @Test
  void shouldGenerateCDNumber() {
    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/numbers/cd").
    then()
      .statusCode(OK.getStatusCode())
      .body(containsString("pt"));
  }
}
