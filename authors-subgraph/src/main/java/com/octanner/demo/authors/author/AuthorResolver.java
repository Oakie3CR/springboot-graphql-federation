package com.octanner.demo.authors.author;

import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorResolver {
  private final AuthorService authorService;

  public AuthorResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

  @QueryMapping
  public List<Author> getAuthors() {
    return authorService.getAll();
  }

  @QueryMapping
  public AuthorResult getAuthorById(@Argument int id) {
    return authorService.getById(id);
  }

  @MutationMapping
  public AuthorResult addAuthor(@Argument CreateAuthorInput input) {
    return authorService.save(input.name());
  }

  @MutationMapping
  public AuthorResult updateAuthor(@Argument UpdateAuthorInput input) {
    return authorService.update(input.id(), input.name());
  }
}
