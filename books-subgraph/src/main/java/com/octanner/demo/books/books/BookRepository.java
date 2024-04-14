package com.octanner.demo.books.books;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, Integer> {
  List<Book> findAllByGenre(Genre genre);
  List<Book> findAllByIdIn(Set<Integer> ids);
}
