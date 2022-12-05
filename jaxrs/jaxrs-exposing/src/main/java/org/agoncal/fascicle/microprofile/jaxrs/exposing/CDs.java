package org.agoncal.fascicle.microprofile.jaxrs.exposing;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CDs extends ArrayList<CD> {

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public List<CD> getCDs() {
    return this;
  }
}
