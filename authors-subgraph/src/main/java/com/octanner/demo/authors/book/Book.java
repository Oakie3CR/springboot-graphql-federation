package com.octanner.demo.authors.book;

import lombok.Builder;

@Builder
public record Book(int id, int author_id) {}
