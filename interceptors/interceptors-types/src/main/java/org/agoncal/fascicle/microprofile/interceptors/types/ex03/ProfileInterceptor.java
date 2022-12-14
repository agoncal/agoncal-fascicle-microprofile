package org.agoncal.fascicle.microprofile.interceptors.types.ex03;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
public class ProfileInterceptor {

  @Inject
  private Logger logger;

  @PostConstruct
  public void logMethod(InvocationContext ic) throws Exception {
    logger.fine(ic.getTarget().toString());
    try {
      ic.proceed();
    } finally {
      logger.fine(ic.getTarget().toString());
    }
  }

  @AroundInvoke
  public Object profile(InvocationContext ic) throws Exception {
    long initTime = System.currentTimeMillis();
    try {
      return ic.proceed();
    } finally {
      long diffTime = System.currentTimeMillis() - initTime;
      logger.fine(ic.getMethod() + " took " + diffTime + " millis");
    }
  }
}
// end::adocSnippet[]
