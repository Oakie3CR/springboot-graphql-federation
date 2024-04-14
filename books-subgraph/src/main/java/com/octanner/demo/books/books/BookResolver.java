package com.octanner.demo.books.books;

import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class BookResolver {

  private final BookService bookService;

  public BookResolver(BookService bookService, BatchLoaderRegistry registry) {
    this.bookService = bookService;
    registry
        .forTypePair(Integer.class, Book.class)
        .registerMappedBatchLoader((ids, env) -> Mono.justOrEmpty(bookService.getBooksByIds(ids)));
  }

  @QueryMapping
  public List<Book> getBooks() {
    return bookService.getAll();
  }

  @QueryMapping
  public BookResult getBookById(@Argument int id) {
    return bookService.getById(id);
  }

  @QueryMapping
  public List<Book> getBooksByGenre(@Argument Genre genre) {
    return bookService.getByGenre(genre);
  }

  @MutationMapping
  public BookResult addBook(@Argument CreateBookInput input) {
    var book = Book.builder().name(input.name()).genre(input.genre()).build();
    return bookService.save(book);
  }

  @MutationMapping
  public BookResult updateBook(@Argument UpdateBookInput input) {
    var book = Book.builder().id(input.id()).name(input.name()).genre(input.genre()).build();
    return bookService.save(book);
  }
}
