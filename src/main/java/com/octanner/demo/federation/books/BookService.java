package com.octanner.demo.federation.books;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
  private final BookRepository bookRepository;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  public BookResult getById(int id) {
    var book = bookRepository.findById(id);
    if (book.isPresent()) {
      return book.get();
    } else {
      return new BookNotFound("There is no book with that id");
    }
  }

  public List<Book> getByGenre(String genre) {
    return bookRepository.findAllByGenre(genre);
  }

  public BookResult save(String name, Genre genre, int authorId) {
    try {
      return bookRepository.save(name, genre, authorId);
    } catch (DbActionExecutionException e) {
      return new BookNotFound("There is no book with that id");
    }
  }

  public BookResult update(int id, String name, Genre genre, int authorId) {
    try {
      return bookRepository.update(id, name, genre, authorId);
    } catch (DbActionExecutionException e) {
      return new BookNotFound("There is no book with that id");
    }
  }
}
