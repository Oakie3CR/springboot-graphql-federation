package com.octanner.demo.books.books;

import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, Integer> {
  List<Book> findAllByGenre(Genre genre);
}
