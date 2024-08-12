package com.octanner.demo.book;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, Integer> {
  List<Book> findAllByGenre(Genre genre);

  List<Book> findAllByIdIn(List<Integer> ids);
}
