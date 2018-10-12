package com.stackroute.movieservice;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MovieApplicationStartup implements ApplicationListener<ContextRefreshedEvent>,CommandLineRunner {
    @Autowired
private MovieRepository movieRepository;

    /*This method is called during Spring's startup.
     * @param event Event raised when an ApplicationContext gets initialized or
     * refreshed.*/

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        Movie startupMovie = new Movie();
        startupMovie.setMovieTitle("xyz");
        startupMovie.setImdbId("1");
        startupMovie.setPosterUrl("#");
        startupMovie.setRating(0);
        startupMovie.setYearOfRelease(2017);
        startupMovie.setComments("");
        movieRepository.save(startupMovie);
        System.out.println("The App Startup has Initiated");
    }

    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie("4", "Titanic", "jkgwuefi",4,2017, "Ultimate movie");
        movieRepository.save(movie);
    }

}
