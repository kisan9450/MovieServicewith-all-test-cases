package com.stackroute.movieservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.movieservice.domain.Movie;

import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;

import com.stackroute.movieservice.service.MovieService;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

@WebMvcTest

public class MovieControllerTest {

    @Autowired

    private MockMvc mockMvc;

    private Movie movie;

    @MockBean

    private MovieService movieService;

    @InjectMocks

    private MovieController movieController;

    private List<Movie> list =null;

    @Before

    public void setUp(){

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

        movie = new Movie();

        movie.setImdbId("10");

        movie.setMovieTitle("dunkirk");

        movie.setPosterUrl("asjhgjhsa/asvvhfh/vgxgxacjhgvbcgh");

        movie.setRating(48);

        movie.setYearOfRelease(2016);

        movie.setComments("abcd");

       list = new ArrayList();

        list.add(movie);
    }

    @Test

    public void saveMovie() throws Exception {

        when(movieService.savedMovie(any())).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")

                .contentType(MediaType.APPLICATION_JSON)

                .content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isCreated())

                .andDo(MockMvcResultHandlers.print());

    }

    @Test

    public void saveMovieFailure() throws Exception {

        when(movieService.savedMovie(any())).thenThrow(MovieAlreadyExistsException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isConflict())

                .andDo(MockMvcResultHandlers.print());

    }

    @Test

    public void getAllMovie() throws Exception {

        when(movieService.getAllMovies()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }
    @Test

    public void updateMovie() throws Exception {

        when(movieService.savedMovie(movie)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/update")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }


    @Test

    public void deleteMovie() throws Exception {

        when(movieService.deleteMovie(movie.getImdbId())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/10")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }
    @Test

    public void deleteMoviebyTitle() throws Exception {

        when(movieService.deleteMovie(movie.getMovieTitle())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete/dunkirk")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }

    @Test

    public void getMovieById() throws Exception {

        when(movieService.getMovieById(movie.getImdbId())).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/10")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }
    @Test

    public void getMoviebyTitle() throws Exception {

        when(movieService.getMovieByMovieName(movie.getMovieTitle())).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/dunkirk")

                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }
    @Test

    public void updatedMovie() throws Exception {

        when(movieService.updateMovie(any())).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/update")

                .contentType(MediaType.APPLICATION_JSON)

                .content(asJsonString(movie)))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());

    }

    private static String asJsonString(final Object obj)

    {

        try{

            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){

            throw new RuntimeException(e);

        }

    }








}

