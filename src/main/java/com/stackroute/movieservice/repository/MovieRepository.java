package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    public  Movie findByMovieTitle(String movieName);
    public  Movie  findByImdbId(String id);
}
