package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MovieController {
    @Autowired
   // @Qualifier("MovieServiceImpl")
    MovieService movieService;

    @PostMapping("movie")
    public ResponseEntity<?> savedMovie(@Valid @RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {
            Movie storedMovie=movieService.savedMovie(movie);
            responseEntity=new ResponseEntity<Movie>(storedMovie, HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }
    @GetMapping("movie")
    public ResponseEntity<?> getAllMovies() {
        List<Movie>  getMovies=movieService.getAllMovies();
        ResponseEntity responseEntity=new ResponseEntity<List<Movie>>(getMovies, HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("{id}")
    public String deleteMovie(@Valid @PathVariable("id") String imdbId){
        ResponseEntity responseEntity;
        try {
            movieService.deleteMovie(imdbId);
            return "success";
        }
        catch (MovieNotFoundException ex){
            return  ex.getMessage();
        }
    }

    @DeleteMapping("delete/{movieName}")
    public ResponseEntity  deleteMovieByMovieName(@Valid @PathVariable("movieName") String movieName){
        ResponseEntity responseEntity;
        try {
            movieService.deleteMovieByMovieName(movieName);
            responseEntity=new ResponseEntity<String>("deleted successful",HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>("movie not found",HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("movie/{id}")
    public ResponseEntity<?> getMovieByImdbId(@Valid  @PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            Movie getMovie=movieService.getMovieById(id);
            responseEntity=new ResponseEntity(getMovie, HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    @GetMapping("{movieName}")
    public ResponseEntity<?> getMovieByMovieName(@Valid @PathVariable("movieName") String movieName){
        ResponseEntity responseEntity;
        try {
            Movie getMovieByName=movieService.getMovieByMovieName(movieName);
            responseEntity=new ResponseEntity(getMovieByName, HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @PutMapping("update")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody Movie movie){
        Movie updatedMovie=movieService.updateMovie(movie);
        ResponseEntity responseEntity=new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
        return responseEntity;

    }
}
