package com.stackroute.movieservice.repository;

import com.google.common.base.Verify;
import com.stackroute.movieservice.domain.Movie;

import org.junit.Assert;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.google.common.base.Verify.verifyNotNull;

@RunWith(SpringRunner.class)

@DataMongoTest

public class MovieRepositoryTest {

    @Autowired

    MovieRepository movieRepository;

    Movie movie;

    @Before

    public void setUp()

    {

        movie = new Movie();

        movie.setImdbId("10");

        movie.setMovieTitle("ranjhana");

        movie.setPosterUrl("asjhgjhsa.asvvhfh.vgxgxacjhgvbcgh");

        movie.setRating(48);
        movie.setYearOfRelease(2017);
        movie.setComments("very nice");

    }
//    @After
//    public void .tearDown(){
//

//        userRepository.deleteAll();

//    }

    @Test

    public void testSaveMovie(){

        movieRepository.save(movie);

        Movie fetchUser = movieRepository.findById(movie.getImdbId()).get();

        Assert.assertEquals("10",fetchUser.getImdbId());

    }

    @Test

    public void testSaveMovieFailure(){

        Movie testMovie = new Movie("asdfffjhgvb","IT","mnmbdgcnfhsgh",46,2017,"nice");

        movieRepository.save(movie);

        Movie fetchMovie = movieRepository.findById(movie.getImdbId()).get();

        Assert.assertNotSame(testMovie.getImdbId(),fetchMovie.getImdbId());

    }

    @Test

    public void testGetAllMovie(){

        Movie movie1 = new Movie("asdfffjhgvbdsfk","IT22","mnmbdgsdfsggh",43,2018,"very nice");

        Movie movie2 = new Movie("asdfffjcgvxhgvb","IT223","mnmbdgcnfgffdgdhsgh",47,2019,"no comments");

        movieRepository.save(movie1);

        movieRepository.save(movie2);

        List<Movie> list = (List<Movie>)movieRepository.findAll();

        Assert.assertEquals("IT22",list.get(0).getMovieTitle());

    }

    @Test
     public void testdeleteMovie(){
        Movie movieToBeDeleted= new Movie("Dk23","Dunkirk","a.bfds.ASA",9,2017,"Superb");
        movieRepository.save(movieToBeDeleted);
        movieRepository.deleteById("Dk23");
        verifyNotNull(movieToBeDeleted);
    }
    @Test
    public void testgetMovieById(){
        Movie foundedMovie= new Movie("Dk23","Dunkirk","a.bfds.ASA",9,2017,"Superb");
        movieRepository.save(foundedMovie);
        Movie fetchUser1 = movieRepository.findById(foundedMovie.getImdbId()).get();

        Assert.assertEquals("Dunkirk",fetchUser1.getMovieTitle());

    }
    @Test
    public void testgetMovieByMovieName(){

        Movie foundedMovie= new Movie("Dk23","Dunkirk","a.bfds.ASA",9,2017,"Superb");
        movieRepository.save(foundedMovie);
        Movie fetchUser1 = movieRepository.findByMovieTitle(foundedMovie.getMovieTitle());

        Assert.assertEquals("Dunkirk",fetchUser1.getMovieTitle());

    }

    @Test
    public void testupdatemovie(){
        Movie movie1 = new Movie("k1","Batman","mnmbdgsdfsggh",43,2018,"very nice");
        movieRepository.save(movie1);
        Movie movie2 = new Movie("k1","Homecoming","mnmbdgcnfgffdgdhsgh",47,2019,"no comments");
        movieRepository.save(movie2);
        Movie fetchUser1 = movieRepository.findByMovieTitle(movie2.getMovieTitle());
        verifyNotNull(movie1);
        Assert.assertEquals("Homecoming",fetchUser1.getMovieTitle());
    }


}