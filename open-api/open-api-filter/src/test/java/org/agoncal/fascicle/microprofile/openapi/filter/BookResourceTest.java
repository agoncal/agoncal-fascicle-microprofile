package org.agoncal.fascicle.microprofile.openapi.filter;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;

//@formatter:off
@QuarkusTest
public class BookResourceTest {

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

  @Test
  void shouldWriteOpenAPIFile()throws Exception {
    String yamlFile = given()
      .header(ACCEPT, "application/yaml").
        when()
      .get("/openapi").
        then()
      .statusCode(OK.getStatusCode())
      .extract().asString();

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("open-dummy.yaml"))));
    yamlFile = yamlFile.substring(yamlFile.indexOf('\n')+1);
    bw.write(yamlFile);
    bw.close();
  }
}
