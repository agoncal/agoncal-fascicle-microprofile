package org.agoncal.fascicle.microprofile.cdi.injection;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import java.util.Random;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@Default
@ApplicationScoped
public class IsbnGenerator implements NumberGenerator {

  public String generateNumber() {
    return "13-84356-" + Math.abs(new Random().nextInt());
  }
}
// end::adocSnippet[]
