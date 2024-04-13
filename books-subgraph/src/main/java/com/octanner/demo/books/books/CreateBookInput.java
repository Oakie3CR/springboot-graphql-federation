package com.octanner.demo.books.books;

import lombok.Builder;

@Builder
public record CreateBookInput(String name, Genre genre, int authorId) {}
