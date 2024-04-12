package com.octanner.demo.federation.books;

import com.octanner.demo.federation.author.Author;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BookRepository {
  private final JdbcClient client;

  private RowMapper<Book> bookRowMapper() {
    return (rs, rowNum) -> {
      var author = new Author(rs.getInt("author_id"), rs.getString("author_name"), List.of());
      return new Book(
          rs.getInt("book_id"),
          rs.getString("book_name"),
          Genre.valueOf(rs.getString("genre")),
          author);
    };
  }

  public List<Book> findAll() {
    var selectAll =
        """
        select b.id as book_id, b.name as book_name, b.genre, b.author_id, a.name as author_name
        from book b
        join author a on b.author_id = a.id
        """;
    return client.sql(selectAll).query(bookRowMapper()).list();
  }

  public Optional<Book> findById(int id) {
    var selectById =
        """
            select b.id as book_id, b.name as book_name, b.genre, b.author_id, a.name as author_name
            from book b
            join author a on b.author_id = a.id
            where b.id = :id
            """;
    return client.sql(selectById).param("id", id).query(bookRowMapper()).optional();
  }

  public List<Book> findAllByGenre(String genre) {
    var selectByGenre =
        """
            select b.id as book_id, b.name as book_name, b.genre, b.author_id, a.name as author_name
            from book b
            join author a on b.author_id = a.id
            where b.genre = :genre
            """;
    return client.sql(selectByGenre).param("genre", genre).query(bookRowMapper()).list();
  }

  public Book save(String name, Genre genre, int authorId) {
    var insertBook =
        """
            insert into book (name, genre, author_id)
            values (:name, :genre, :authorId)
            returning id as book_id, name as book_name, genre, author_id,
                (select name as author_name from author where id = author_id);
            """;
    return client
        .sql(insertBook)
        .param("name", name)
        .param("genre", genre.name())
        .param("authorId", authorId)
        .query(bookRowMapper())
        .single();
  }

  public Book update(int id, String name, Genre genre, int authorId) {
    var updateBook =
        """
            update book
            set name = :name, genre = :genre, author_id = :authorId
            where id = :id
            returning id as book_id, name as book_name, genre, author_id,
                (select name as author_name from author where id = author_id);
            """;
    return client
        .sql(updateBook)
        .param("id", id)
        .param("name", name)
        .param("genre", genre.name())
        .param("authorId", authorId)
        .query(bookRowMapper())
        .single();
  }
}
