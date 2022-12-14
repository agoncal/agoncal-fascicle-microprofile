package org.agoncal.fascicle.microprofile.interceptors.firstlook;

import jakarta.inject.Inject;
// tag::adocSnippet[]
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

// end::adocSnippet[]
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@Transactional
public class CustomerService {

  @Inject
  private Logger logger;

  public Customer createCustomer(String firstName, String lastName, String email) {
    Customer customer = new Customer();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setEmail(email);
    return customer;
  }

  @AroundInvoke
  private Object logMethod(InvocationContext ic) throws Exception {
    logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
    try {
      return ic.proceed();
    } finally {
      logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
    }
  }
}
// end::adocSnippet[]
