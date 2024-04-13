package com.octanner.demo.books.books;

import lombok.Builder;

@Builder
public record UpdateBookInput(int id, String name, Genre genre) {}
