package org.agoncal.fascicle.microprofile.openapi.filter;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.media.Schema;

// tag::adocSnippet[]
public class RemoveIdFilter implements OASFilter {

  @Override
  public Schema filterSchema(Schema schema) {
    schema.removeProperty("id");
    return schema;
  }
}
// end::adocSnippet[]
