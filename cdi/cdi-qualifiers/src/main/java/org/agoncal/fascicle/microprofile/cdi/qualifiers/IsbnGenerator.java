package org.agoncal.fascicle.microprofile.cdi.qualifiers;


import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@ThirteenDigits
@ApplicationScoped
public class IsbnGenerator implements NumberGenerator {

  public String generateNumber() {
    return "13-84356-" + Math.abs(new Random().nextInt());
  }
}
// end::adocSnippet[]
