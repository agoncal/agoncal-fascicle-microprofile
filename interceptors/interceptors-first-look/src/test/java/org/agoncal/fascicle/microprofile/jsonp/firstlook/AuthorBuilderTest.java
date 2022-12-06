package org.agoncal.fascicle.microprofile.jsonp.firstlook;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.agoncal.fascicle.microprofile.jsonp.firstlook.UtilTest.initBufferedWriter;
import static org.agoncal.fascicle.microprofile.jsonp.firstlook.UtilTest.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@QuarkusTest
// tag::adocBegin[]
public class AuthorBuilderTest {

  private static Jsonb jsonb;

  @BeforeAll
  static void init() {
    JsonbConfig config = new JsonbConfig().withFormatting(true);
    jsonb = JsonbBuilder.create(config);
  }
  // end::adocBegin[]

  static BufferedWriter bw;

  @BeforeAll
  static void initFile() throws FileNotFoundException {
    bw = initBufferedWriter("src/main/java/org/agoncal/fascicle/microprofile/jsonp/firststep/Author.json");
  }

  @AfterAll
  static void closeFile() throws IOException {
    bw.close();
  }

  // ======================================
  // =              Unit tests            =
  // ======================================

  @Test
  void shouldBuildAnAuthor() throws IOException {

    JsonObject jsonObject = new AuthorBuilder().buildAuthor();

    output(bw, jsonObject, "adocShouldBuildAnAuthor");

    // tag::adocSnippet[]
    assertEquals("Adams", jsonObject.getString("first_name"));
    assertEquals("Douglas", jsonObject.getString("last_name"));
    assertEquals("adams@douglas.com", jsonObject.getString("email"));
    assertEquals("1952-03-11", jsonObject.getString("dateOfBirth"));
    // end::adocSnippet[]
  }
}
