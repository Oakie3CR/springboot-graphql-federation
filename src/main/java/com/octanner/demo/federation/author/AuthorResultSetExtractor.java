package com.octanner.demo.federation.author;

import com.octanner.demo.federation.books.Book;
import com.octanner.demo.federation.books.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AuthorResultSetExtractor implements ResultSetExtractor<List<Author>> {

  @Override
  public List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<Author> authors = new ArrayList<>();
    Author author = null;
    while (rs.next()) {
      if (author == null || author.id() != rs.getInt("author_id")) {
        author =
            Author.builder()
                .id(rs.getInt("author_id"))
                .name(rs.getString("author_name"))
                .books(new ArrayList<>())
                .build();
        authors.add(author);
      }
      int bookId = rs.getInt("book_id");
      if (bookId != 0 && !rs.wasNull()) {
        author
            .books()
            .add(
                Book.builder()
                    .id(bookId)
                    .name(rs.getString("book_name"))
                    .genre(Genre.valueOf(rs.getString("genre")))
                    .build());
      }
    }
    return authors;
  }
}
