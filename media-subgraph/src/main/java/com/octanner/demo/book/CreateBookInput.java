package com.octanner.demo.book;

import lombok.Builder;

@Builder
public record CreateBookInput(String name, Genre genre) {}
