package com.octanner.demo.authors.author;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorService {
  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  public AuthorResult getById(int id) {
    var author = authorRepository.findById(id);
    if (author.isPresent()) {
      return author.get();
    } else {
      return new AuthorNotFound("There is no author with that id");
    }
  }

  public AuthorResult save(String name) {
    try {
      return authorRepository.save(name);
    } catch (DbActionExecutionException e) {
      return new AuthorNotFound("There is no author with that id");
    }
  }

  public AuthorResult update(int id, String name) {
    try {
      return authorRepository.update(id, name);
    } catch (DbActionExecutionException e) {
      return new AuthorNotFound("There is no author with that id");
    }
  }
}
