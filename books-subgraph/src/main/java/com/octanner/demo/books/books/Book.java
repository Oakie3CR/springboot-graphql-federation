package com.octanner.demo.books.books;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Book(@Id int id, String name, Genre genre) implements BookResult {
  public static final String TYPE_NAME = "Book";
  public static final String PRIMARY_KEY = "id";
}
