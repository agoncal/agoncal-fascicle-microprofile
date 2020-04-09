package org.agoncal.fascicle.microprofile.health.firststep;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

// tag::adocSnippet[]
@ApplicationScoped
public class NumberService {

  @Inject
  @ConfigProperty(name = "country.code", defaultValue = "us")
  String countryCode;

  @Inject
  @ConfigProperty(name = "book.prefix")
  int bookPrefix;

  public String generateBookNumber() {
    return bookPrefix + generateNumber() + countryCode;
  }

  public String generateCDNumber() {
    return generateNumber() + countryCode;
  }
  // tag::adocSkip[]

  private String generateNumber() {
    return Math.random() + "-";
  }
  // end::adocSkip[]
}
// end::adocSnippet[]
