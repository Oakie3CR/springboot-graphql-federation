package com.octanner.demo.movie;

import com.octanner.demo.MediaInterface;
import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Movie(@Id int id, String name, String description) implements MediaInterface {
    public static final String TYPE_NAME = "Movie";
    public static final String PRIMARY_KEY = "id";

}
