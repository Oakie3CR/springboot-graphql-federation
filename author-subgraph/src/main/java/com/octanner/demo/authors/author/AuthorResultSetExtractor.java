package com.octanner.demo.authors.author;

import com.octanner.demo.authors.media.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.octanner.demo.authors.media.Movie;
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
                .media(new ArrayList<>())
                .build();
        authors.add(author);
      }
      int mediaId = rs.getInt("media_id");
      String mediaTypeCode = rs.getString("media_type_code");
      if (mediaId != 0 && !rs.wasNull()) {
        if (mediaTypeCode.equals("b")) {
          author.media().add(new Book(mediaId));
        }
        if (mediaTypeCode.equals("m")) {
          author.media().add(new Movie(mediaId));
        }
      }
    }
    return authors;
  }
}
