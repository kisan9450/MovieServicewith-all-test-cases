package com.stackroute.movieservice.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stackroute.movieservice.domain.Movie;

import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;

import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;

import org.junit.Assert;

import org.junit.Before;

import org.junit.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

public class MovieServiceTest {

    Movie movie;

    //Create a mock for UserRepository

    @Mock//test double

            MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl

    @InjectMocks

    MovieServiceImpl movieService;

    List<Movie> list= null;

    @Before

    public void setUp(){

        //Initialising the mock object

        MockitoAnnotations.initMocks(this);

        movie = new Movie();

        movie.setImdbId("10");

        movie.setMovieTitle("dunkirk");

        movie.setPosterUrl("asjhgjhsa.asvvhfh.vgxgxacjhgvbcgh");

        movie.setRating(48);

        list = new ArrayList<>();

        list.add(movie);

    }

    @Test

    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie)any())).thenReturn(movie);

        Movie savedMovie = movieService.savedMovie(movie);

        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once

        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)

    public void saveMovieTestFailure() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie)any())).thenReturn(null);

        Movie savedUser = movieService.savedMovie(movie);

        System.out.println("savedUser" + savedUser);

        Assert.assertEquals(movie,savedUser);

//add verify

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));

       userService.saveUser(movie);*/

    }

    @Test

    public void getAllMovie(){

        movieRepository.save(movie);

        //stubbing the mock to return specific data

        when(movieRepository.findAll()).thenReturn(list);

        List<Movie> userlist = movieService.getAllMovies();

        Assert.assertEquals(list,userlist);

        //add verify

    }
    @Test

    public void TestgetMovieById()  throws MovieNotFoundException {

        movieRepository.save(movie);

        when(movieRepository.findById(any())).thenReturn(Optional.ofNullable(movie));

        Movie fetch = movieService.getMovieById(movie.getImdbId());

        Assert.assertEquals("10",fetch.getImdbId());

    }
    @Test

    public void TestgetMovieByTitle()  throws MovieNotFoundException {

        movieRepository.save(movie);

        when(movieRepository.findByMovieTitle(any())).thenReturn(movie);

        Movie fetch = movieService.getMovieByMovieName(movie.getMovieTitle());

        Assert.assertEquals("dunkirk",fetch.getMovieTitle());

    }

    @Test

    public void TestDeleteMovieById()  throws MovieNotFoundException {

        movieRepository.save(movie);

        when(movieRepository.findById(any())).thenReturn(Optional.ofNullable(movie));

        boolean  deleteStatus=movieService.deleteMovie(movie.getImdbId());

        Assert.assertEquals(true,deleteStatus);

    }

    @Test

    public void TestDeleteMovieByTitle()  throws MovieNotFoundException {

        movieRepository.save(movie);

        when(movieRepository.findByMovieTitle(any())).thenReturn(movie);

        boolean  deleteStatus=movieService.deleteMovieByMovieName(movie.getMovieTitle());

        Assert.assertEquals(true,deleteStatus);

    }

    @Test

    public void TestUpdateMovie(){

        Movie updatedMovie=new Movie("1234sd","dunkirk","fsdhgjgkjyutrsadytvnbcgfd",56,20013,"average");

        movieRepository.save(movie);

        when(movieRepository.existsById(updatedMovie.getImdbId())).thenReturn(true);

        when(movieRepository.save(any())).thenReturn(updatedMovie);

        Movie fetchmovie = movieService.updateMovie(updatedMovie);

        Assert.assertEquals(updatedMovie.getMovieTitle(),fetchmovie.getMovieTitle());

    }





}