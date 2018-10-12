package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {

    public Movie savedMovie(Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> getAllMovies() ;
    public boolean deleteMovie(String imdbId) throws MovieNotFoundException;
    public  boolean deleteMovieByMovieName(String  movieName) throws MovieNotFoundException;
    public Movie getMovieById(String id) throws MovieNotFoundException;
    public Movie getMovieByMovieName(String movieName)  throws MovieNotFoundException;
    public Movie  updateMovie(Movie movie);


}
