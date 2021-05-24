package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MovieInfo;

@RestController
@RequestMapping("/movies")
public class AppController {

	@GetMapping("/{movieId}")
	public List<MovieInfo> getList(@PathVariable String movieId){
		return Arrays.asList(new MovieInfo(movieId, "IT"));
	}
}
