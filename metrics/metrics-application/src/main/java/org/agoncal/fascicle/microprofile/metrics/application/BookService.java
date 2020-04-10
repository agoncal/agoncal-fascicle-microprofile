package org.agoncal.fascicle.microprofile.metrics.application;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
public class BookService {

  public Book persistBook(Book book) {
    return null;
  }

  public List<Book> findAllBooks() {
    return null;
  }

  public Optional<Book> findBookById(Long id) {
    return Optional.empty();
  }

  @Transactional(SUPPORTS)
  public Book findRandomBook() {
    return null;
  }

  public Book updateBook(@Valid Book book) {
    return null;
  }

  public void deleteBook(Long id) {
  }
}
