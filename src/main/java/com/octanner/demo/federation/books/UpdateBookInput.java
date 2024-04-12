package com.octanner.demo.federation.books;

import lombok.Builder;

@Builder
public record UpdateBookInput(int id, String name, Genre genre, int authorId) {}
