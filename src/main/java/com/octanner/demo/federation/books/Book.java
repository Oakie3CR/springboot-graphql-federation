package com.octanner.demo.federation.books;

import com.octanner.demo.federation.author.Author;
import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Book(@Id int id, String name, Genre genre, Author author) implements BookResult {}
