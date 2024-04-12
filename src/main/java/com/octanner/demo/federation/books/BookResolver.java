package com.octanner.demo.federation.books;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookResolver {
  private final BookService bookService;

  @QueryMapping
  public List<Book> getBooks() {
    return bookService.getAll();
  }

  @QueryMapping
  public BookResult getBookById(@Argument int id) {
    return bookService.getById(id);
  }

  @QueryMapping
  public List<Book> getBooksByGenre(@Argument String genre) {
    return bookService.getByGenre(genre);
  }

  @MutationMapping
  public BookResult addBook(@Argument CreateBookInput input) {
    return bookService.save(input.name(), input.genre(), input.authorId());
  }

  @MutationMapping
  public BookResult updateBook(@Argument UpdateBookInput input) {
    return bookService.update(input.id(), input.name(), input.genre(), input.authorId());
  }
}
