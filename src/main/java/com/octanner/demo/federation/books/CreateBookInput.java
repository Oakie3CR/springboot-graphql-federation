package com.octanner.demo.federation.books;

import lombok.Builder;

@Builder
public record CreateBookInput(String name, Genre genre, int authorId) {}
