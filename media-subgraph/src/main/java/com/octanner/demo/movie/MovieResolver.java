package com.octanner.demo.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieResolver {
    private final MovieService movieService;

    @EntityMapping
    public List<Movie> movie(@Argument List<Integer> idList) {
        return movieService.listMoviesById(idList);
    }
}