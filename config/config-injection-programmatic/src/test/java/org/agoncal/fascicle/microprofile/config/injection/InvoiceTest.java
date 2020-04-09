package org.agoncal.fascicle.microprofile.config.injection;

import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.fascicle.microprofile.config.injection.Invoice;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@formatter:off
@QuarkusTest
class InvoiceTest {

  @Inject
  Invoice invoice;

  // tag::shouldCalculateInvoiceProgrammaticallyInjection[]
  @Inject
  Config config;

  // end::shouldCalculateInvoiceProgrammaticallyInjection[]
  @Test
  public void shouldCalculateInvoiceProgrammaticallyInjection() {
  // tag::shouldCalculateInvoiceProgrammaticallyInjection[]
  invoice.vatRate = config.getValue("invoice.vatRate", Float.class);
  invoice.allowsDiscount = config.getValue("invoice.allowsDiscount", Boolean.class);
  invoice.terms = config.getValue("invoice.terms", String.class);
  invoice.penalties = config.getValue("invoice.penalties", String.class);
  // end::shouldCalculateInvoiceProgrammaticallyInjection[]

    invoice.subtotal = 500f;
    invoice.vatAmount = invoice.subtotal * (invoice.vatRate / 100);
    invoice.total = invoice.subtotal + invoice.vatAmount;
    assertEquals(10f, invoice.vatRate);
    assertEquals(50f, invoice.vatAmount);
    assertEquals(550f, invoice.total);
    assertFalse(invoice.allowsDiscount);
    assertTrue(invoice.terms.startsWith("Payment"));
    assertTrue(invoice.penalties.startsWith("Penalty"));
  }

  @Test
  public void shouldCalculateInvoice() {
    invoice.subtotal = 500f;
    invoice.vatAmount = invoice.subtotal * (invoice.vatRate / 100);
    invoice.total = invoice.subtotal + invoice.vatAmount;
    assertEquals(10f, invoice.vatRate);
    assertEquals(50f, invoice.vatAmount);
    assertEquals(550f, invoice.total);
    assertFalse(invoice.allowsDiscount);
    assertTrue(invoice.terms.startsWith("Payment"));
    assertTrue(invoice.penalties.startsWith("Penalty"));
  }

  @Test
  public void shouldCalculateInvoiceProgrammatically() {
    // tag::adocShouldCalculateInvoiceProgrammatically[]
    Config config = ConfigProvider.getConfig();
    invoice.vatRate = config.getValue("invoice.vatRate", Float.class);
    invoice.allowsDiscount = config.getValue("invoice.allowsDiscount", Boolean.class);
    invoice.terms = config.getValue("invoice.terms", String.class);
    invoice.penalties = config.getValue("invoice.penalties", String.class);
    // end::adocShouldCalculateInvoiceProgrammatically[]

    invoice.subtotal = 500f;
    invoice.vatAmount = invoice.subtotal * (invoice.vatRate / 100);
    invoice.total = invoice.subtotal + invoice.vatAmount;
    assertEquals(10f, invoice.vatRate);
    assertEquals(50f, invoice.vatAmount);
    assertEquals(550f, invoice.total);
    assertFalse(invoice.allowsDiscount);
    assertTrue(invoice.terms.startsWith("Payment"));
    assertTrue(invoice.penalties.startsWith("Penalty"));
  }
}