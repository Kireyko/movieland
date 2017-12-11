package com.kireyko.movieland.controller;

import com.kireyko.movieland.entity.Country;
import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.MovieService;
import com.kireyko.movieland.util.GlobalExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MovieControllerTest {

    @Mock
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    private MockMvc mockMvc;
    private List<Movie> movies;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new GlobalExceptionHandler()).build();


       /*
        when(movieService.getMoviesByGenreId(anyInt())).thenReturn(movieList);
        when(movieService.getMoviesRandom()).thenReturn(movieList);
        when(movieService.getById(anyInt())).thenReturn(movieOne);
        */
/*
        User user = new User(1, "nickname", "email", "password", Arrays.asList(Role.USER));
        User admin = new User(1, "nickname", "email", "password", Arrays.asList(Role.ADMIN));
        UserToken userTokenOne = new UserToken(UUID.randomUUID(), LocalDateTime.now(), user);
        UserToken userTokenTwo = new UserToken(UUID.randomUUID(), LocalDateTime.now(), admin);
        when(authService.authorize(UUID.fromString("096f33e2-a224-3aed-9f93-a82fc74549fe"))).thenReturn(userTokenOne);
        //when(authService.getCurrentUser()).thenReturn(user);
        when(authService.authorize(UUID.fromString("096f33e2-a335-3aed-9f93-a82fc74549fe"))).thenReturn(userTokenTwo);
        //when(authService.getCurrentUser()).thenReturn(admin);
*/
    }


    @Test
    public void getAll() throws Exception {

        movies = new ArrayList<>();
        Genre genre = new Genre(3, "genreTest");
        Country country = new Country(22, "countryTest");
        Movie movieOne = new Movie();
        movieOne.setId(11);
        movieOne.setNameRussian("тест имя");
        movieOne.setNameNative("something interesting");
        movieOne.setYearOfRelease(1949);
        movieOne.setDescription("testDescription");
        movieOne.setRating((float) 0.1);
        movieOne.setPrice((float) 2.2);
        movieOne.setGenres(Arrays.asList(genre));
        movieOne.setCountries(Arrays.asList(country));
        movieOne.setPicturePath("test http");
        movies.add(movieOne);

        Movie movieTwo = new Movie();
        movieTwo.setId(22);
        movieTwo.setNameRussian("тест имя 2");
        movieTwo.setNameNative("something interesting 2");
        movieTwo.setYearOfRelease(2010);
        movieTwo.setDescription("testDescription 2");
        movieTwo.setRating((float) 1.4);
        movieTwo.setPrice((float) 5.0);
        movieTwo.setGenres(Arrays.asList(genre));
        movieTwo.setCountries(Arrays.asList(country));
        movieTwo.setPicturePath("test http 2");
        movies.add(movieTwo);

        HashMap<String, String> requestParamMap = new HashMap<>();


        when(movieService.getAll(requestParamMap)).thenReturn(movies);

        mockMvc.perform(get("/movie")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$[0].id", is(11))).
                andExpect(jsonPath("$[0].nameNative", is("something interesting"))).
                andExpect(jsonPath("$[0].nameRussian", is("тест имя"))).
                andExpect(jsonPath("$[0].yearOfRelease", is(1949))).
                andExpect(jsonPath("$[0].rating", is(0.1))).
                andExpect(jsonPath("$[0].price", is(2.2))).
                andExpect(jsonPath("$[0].picturePath", is("test http"))).
                andExpect(jsonPath("$[1].id", is(22))).
                andExpect(jsonPath("$[1].nameNative", is("something interesting 2"))).
                andExpect(jsonPath("$[1].nameRussian", is("тест имя 2"))).
                andExpect(jsonPath("$[1].yearOfRelease", is(2010))).
                andExpect(jsonPath("$[1].rating", is(1.4))).
                andExpect(jsonPath("$[1].price", is(5.0))).
                andExpect(jsonPath("$[1].picturePath", is("test http 2")));

 /*
        requestParamMap.put("rating", "asc");

        mockMvc.perform(get("/movie").param(requestParamMap)).andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(get("/movie").param("price", "desc")).andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(get("/movie").param("price", "asc")).andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2)));
              */
// not allowed
     //   mockMvc.perform(get("/movie").param("rating", "acs")).andExpect(status().isBadRequest());

       // mockMvc.perform(get("/movie").param("rating", "asc").param("price", "asc")).andExpect(status().isBadRequest());
    }

    @Test
    public void getMoviesRandom() throws Exception {
    }

    @Test
    public void getMovieById() throws Exception {
    }

    @Test
    public void getMoviesByGenreId() throws Exception {
    }

}