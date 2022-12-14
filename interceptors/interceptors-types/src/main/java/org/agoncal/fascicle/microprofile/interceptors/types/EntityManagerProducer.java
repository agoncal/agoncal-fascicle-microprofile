package org.agoncal.fascicle.microprofile.interceptors.types;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cdiPU");

  // ======================================
  // =           Public Methods           =
  // ======================================

  @Produces
  private EntityManager createEntityManager() {
    EntityManager em = entityManagerFactory.createEntityManager();
    return em;
  }

  private void closeEntityManager(@Disposes EntityManager entityManager) {
    if (entityManager.isOpen()) {
      entityManager.close();
    }
  }
}
