package com.octanner.demo.authors.author;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class AuthorRepository {

  private final JdbcClient client;
  private final AuthorResultSetExtractor authorResultSetExtractor = new AuthorResultSetExtractor();

  public List<Author> findAll() {
    var selectAll =
        """
        select a.id as author_id, a.name as author_name, b.book_id
        from author a
        left join book b on a.id = b.author_id;
        """;
    return client.sql(selectAll).query(authorResultSetExtractor);
  }

  public Optional<Author> findById(int id) {
    var selectById =
        """
        select a.id as author_id, a.name as author_name, b.book_id
        from author a
        left join book b on a.id = b.author_id
        where a.id = :id;
        """;
    return client.sql(selectById).param("id", id).query(authorResultSetExtractor).stream()
        .findFirst();
  }

  public Author save(String name) {
    var keyHolder = new GeneratedKeyHolder();
    var insertAuthor =
        """
            insert into author (name) values (:name)
            returning id
            """;
    client.sql(insertAuthor).param("name", name).update(keyHolder);
    var id = keyHolder.getKey().intValue();
    return Author.builder().id(id).name(name).books(List.of()).build();
  }

  public Author update(int id, String name) {
    var updateAuthor =
        """
        update author set name = :name where id = :id
        """;
    client.sql(updateAuthor).param("name", name).param("id", id).update();
    return Author.builder().id(id).name(name).books(List.of()).build();
  }
}
