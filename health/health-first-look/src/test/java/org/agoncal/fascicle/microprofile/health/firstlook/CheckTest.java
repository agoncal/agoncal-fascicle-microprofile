package org.agoncal.fascicle.microprofile.health.firstlook;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

//@formatter:off
@QuarkusTest
public class CheckTest {

  @Test
  void shouldPingHealth() {
    given().
    when()
      .get("/health").
    then()
      .statusCode(200);
  }

  @Test
  void shouldPingLiveness() {
    given().
    when()
      .get("/health/live").
    then()
      .statusCode(200);
  }

  @Test
  void shouldPingReadiness() {
    given().
    when()
      .get("/health/ready").
    then()
      .statusCode(200);
  }
}
