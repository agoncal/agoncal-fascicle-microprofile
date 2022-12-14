package org.agoncal.fascicle.microprofile.interceptors.types.ex05;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.microprofile.interceptors.types.Customer;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@Transactional
@Loggable
public class CustomerService {

  @Inject
  private EntityManager em;

  public void createCustomer(Customer customer) {
    em.persist(customer);
  }

  public Customer findCustomerById(Long id) {
    return em.find(Customer.class, id);
  }
}
// end::adocSnippet[]
