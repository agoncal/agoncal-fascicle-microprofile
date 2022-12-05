package org.agoncal.fascicle.microprofile.jsonp.firststep;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.LocalDate;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class AuthorBuilder {

  // tag::adocSnippet[]
  public JsonObject buildAuthor() {
    return Json.createObjectBuilder()
      .add("first_name", "Adams")
      .add("last_name", "Douglas")
      .add("email", "adams@douglas.com")
      .add("dateOfBirth", LocalDate.of(1952, 03, 11).toString())
      .build();
  }
  // end::adocSnippet[]
}
