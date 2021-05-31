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

import com.example.demo.model.CatalogItem;
import com.example.demo.model.UserRating;
import com.example.demo.services.MovieInfoService;
import com.example.demo.services.RatingDataService;

@RestController
@RequestMapping("/catalog")
public class AppController {

	@Autowired
	private MovieInfoService movieInfoService;
	
	@Autowired
	private RatingDataService ratingDataService;
	
    @GetMapping("/{userId}")
    public List<CatalogItem> catalogList(@PathVariable String userId) {
    	
    	UserRating ratings=ratingDataService.getRatingData(userId);

    	return ratings.getUserRating().stream()
    			.map(rating->movieInfoService.getMovieInfo(rating))
    			.collect(Collectors.toList());
    }
    
}
