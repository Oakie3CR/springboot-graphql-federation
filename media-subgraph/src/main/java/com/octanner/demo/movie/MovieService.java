package com.octanner.demo.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> listMoviesById(List<Integer> ids) {
        return movieRepository.findAllByIdIn(ids);
    }
}
