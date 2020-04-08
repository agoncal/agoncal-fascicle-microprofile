package org.agoncal.fascicle.microprofile.openapi.custom;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;

// tag::adocInfo[]
@ApplicationPath("/")
@OpenAPIDefinition(
  info = @Info(title = "Book API",
    description = "This API allows CRUD operations on books",
    version = "1.0",
    contact = @Contact(name = "@agoncal", url = "https://twitter.com/agoncal")),
  externalDocs = @ExternalDocumentation(url = "https://github.com/agoncal/agoncal-fascicle-microprofile", description = "All the MicroProfile fascicle code"),
  tags = {
    @Tag(name = "api", description = "Public API that can be used by anybody"),
    @Tag(name = "books", description = "Anybody interested in books")
  }
// end::adocInfo[]
  ,
// tag::adocServer[]
  servers = @Server(
    description = "CD-Book Store server 1",
    url = "http://{host}.cdbookstore/{port}",
    variables = {
      @ServerVariable(name = "host",
        description = "CD-Book Store main server",
        defaultValue = "localhost"),
      @ServerVariable(name = "port",
        description = "CD-Book Store listening port",
        defaultValue = "80")
    }
  )
// end::adocServer[]
)
public class BookApplication {
}
// end::adocSnippet[]
