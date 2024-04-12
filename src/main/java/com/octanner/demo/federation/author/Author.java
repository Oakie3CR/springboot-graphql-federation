package com.octanner.demo.federation.author;

import com.octanner.demo.federation.books.Book;
import java.util.List;
import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Author(@Id int id, String name, List<Book> books) implements AuthorResult {}
