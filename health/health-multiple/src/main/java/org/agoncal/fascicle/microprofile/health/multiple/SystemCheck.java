package org.agoncal.fascicle.microprofile.health.multiple;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

// tag::adocSnippet[]
@Liveness
@Readiness
@ApplicationScoped
public class SystemCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
    long memUsed = memBean.getHeapMemoryUsage().getUsed();
    long memMax = memBean.getHeapMemoryUsage().getMax();

    return HealthCheckResponse
      .named(SystemCheck.class.getSimpleName())
      .withData("memory used", memUsed)
      .withData("memory max", memMax)
      .status(memUsed < memMax * 0.9).build();
  }
}
// end::adocSnippet[]
