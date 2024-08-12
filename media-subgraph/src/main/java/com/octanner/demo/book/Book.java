package com.octanner.demo.book;

import com.octanner.demo.MediaInterface;
import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Book(@Id int id, String name, Genre genre) implements BookResult, MediaInterface {
  public static final String TYPE_NAME = "Book";
  public static final String PRIMARY_KEY = "id";
}
