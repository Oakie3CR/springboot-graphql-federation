package com.octanner.demo.books.books;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
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

  public List<Book> getByGenre(Genre genre) {
    return bookRepository.findAllByGenre(genre);
  }

  public BookResult save(Book book) {
    try {
      return bookRepository.save(book);
    } catch (DbActionExecutionException e) {
      return new BookNotFound("There is no book with that id");
    }
  }

  public Map<Integer, Book> getBooksByIds(Set<Integer> ids) {
    return bookRepository.findAllByIdIn(ids).stream()
        .collect(Collectors.toMap(Book::id, Function.identity()));
  }
}
