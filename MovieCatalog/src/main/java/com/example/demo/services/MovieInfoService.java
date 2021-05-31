package com.example.demo.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CatalogItem;
import com.example.demo.model.MovieInfo;
import com.example.demo.model.RatingData;

@Service
public class MovieInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public CatalogItem getMovieInfo(RatingData rating ) {
		MovieInfo[] movieInfo= restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), MovieInfo[].class);
    		MovieInfo info= new MovieInfo();
    		for(MovieInfo movies:movieInfo) {
    			info.setMovieId(movies.getMovieId());
    			info.setName(movies.getName());
    		}
    		return new CatalogItem(info.getName(), "DESC", rating.getRating());
	}
}
