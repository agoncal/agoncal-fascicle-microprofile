package org.agoncal.fascicle.microprofile.health.firststep;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

// tag::adocSnippet[]
@Liveness
@ApplicationScoped
public class PingResourceCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.named("Ping Resource").up().build();
  }
}
// end::adocSnippet[]
