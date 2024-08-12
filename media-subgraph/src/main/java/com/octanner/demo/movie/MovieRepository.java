package com.octanner.demo.movie;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MovieRepository extends ListCrudRepository<Movie, Integer> {
    List<Movie> findAllByIdIn(List<Integer> ids);
}
