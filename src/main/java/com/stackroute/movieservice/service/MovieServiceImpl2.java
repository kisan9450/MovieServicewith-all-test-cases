package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl2 implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    public Movie savedMovie(Movie movie) throws MovieAlreadyExistsException {
        if (movieRepository.existsById(movie.getImdbId())){
            throw new MovieAlreadyExistsException("movie already exists");
        }
        Movie saveMovie=movieRepository.save(movie);
        if(saveMovie==null)
        {
            throw new MovieAlreadyExistsException("movie already exists");
        }
        return saveMovie;
    }
    public List<Movie> getAllMovies(){
        return (List<Movie>)movieRepository.findAll();
    }
    public boolean deleteMovie(String imdbId)  throws MovieNotFoundException {
        if (movieRepository.findById(imdbId).isPresent()) {
            Movie movie = movieRepository.findById(imdbId).get();
            movieRepository.delete(movie);
            return true;
        }
        else {
            throw new MovieNotFoundException("movie not found");
        }
    }
    public boolean deleteMovieByMovieName(String  movieName) throws MovieNotFoundException{
        Movie movie=movieRepository.findByMovieTitle(movieName);
        if(movie==null){
            throw new MovieNotFoundException("movie not found");
        }
        movieRepository.delete(movie);
        return true;
    }
    public Movie getMovieById(String id) throws MovieNotFoundException{
        Movie movie=movieRepository.findById(id).get();
        if (movie==null){
            throw new MovieNotFoundException("movie not found");
        }
        return movie;

    }
    public Movie getMovieByMovieName(String movieName) throws MovieNotFoundException{
        Movie movie=movieRepository.findByMovieTitle(movieName);
        if (movie==null){
            throw new MovieNotFoundException("movie not found");
        }

        return movie;
    }

    public Movie updateMovie(Movie movie){
        Movie updateMovie=movieRepository.save(movie);
        return updateMovie;
    }
}
