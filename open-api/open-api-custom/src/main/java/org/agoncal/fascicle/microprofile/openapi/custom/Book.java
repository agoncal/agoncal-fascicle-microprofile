package org.agoncal.fascicle.microprofile.openapi.custom;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.net.URL;

// tag::adocSnippet[]
@Schema(description = "Book representation")
public class Book {

  @Schema(required = true)
  public Long id;
  @Schema(required = true)
  public String title;
  public String isbn13;
  public String isbn10;
  public String author;
  public Integer yearOfPublication;
  public Integer nbOfPages;
  public Integer rank;
  public BigDecimal price;
  public URL smallImageUrl;
  public URL mediumImageUrl;
  public String description;
}
