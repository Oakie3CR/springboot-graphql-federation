package com.octanner.demo.book;

import lombok.Builder;

@Builder
public record UpdateBookInput(int id, String name, Genre genre) {}
