package com.octanner.demo.authors.media;

import lombok.Builder;

public record Book(int id) implements Media, MediaInterface {}
