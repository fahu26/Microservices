package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RatingData;
import com.example.demo.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class AppController {

	@GetMapping("/{movieId}")
	public UserRating getList(@PathVariable String movieId){
		List<RatingData> ratings= Arrays.asList(
    			new RatingData("1234", 6),
    			new RatingData("578", 8));
		
		UserRating userRating= new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
}
