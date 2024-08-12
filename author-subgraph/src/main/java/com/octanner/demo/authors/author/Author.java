package com.octanner.demo.authors.author;

import com.octanner.demo.authors.media.Media;
import java.util.List;
import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Author(@Id int id, String name, List<Media> media) implements AuthorResult {}
