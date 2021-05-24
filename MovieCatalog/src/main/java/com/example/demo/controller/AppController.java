package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.CatalogItem;
import com.example.demo.model.MovieInfo;
import com.example.demo.model.RatingData;
import com.example.demo.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class AppController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
    @GetMapping("/{userId}")
    public List<CatalogItem> catalogList(@PathVariable String userId) {
    	
    	UserRating ratings= restTemplate.getForObject("http://localhost:8083/ratings/"+userId, UserRating.class);
    	
    	return ratings.getUserRating().stream().map(rating->{
    		//MovieInfo movieInfo= restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), MovieInfo.class);    		
    		MovieInfo[] movieInfo= restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), MovieInfo[].class);
    		
    	/*	MovieInfo[] movieInfo= webClientBuilder.build()
    				.get()
    				.uri("http://localhost:8082/movies/"+rating.getMovieId())
    				.retrieve()
    				.bodyToMono(MovieInfo[].class)
    				.block();
    	*/
    		
    		MovieInfo info= new MovieInfo();
    		for(MovieInfo movies:movieInfo) {
    			info.setMovieId(movies.getMovieId());
    			info.setName(movies.getName());
    		}
    		return new CatalogItem(info.getName(), "DESC", rating.getRating());
    	})
    	.collect(Collectors.toList());
    	
//        return Collections.singletonList(new CatalogItem("Transformers", "Action Movie", 8));
    }
}
